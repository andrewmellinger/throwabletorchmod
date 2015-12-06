package com.crashbox.throwabletorchmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;


/**
 * Base class for throwable entity.
 */
public class EntityThrowableTorch extends EntityThrowable
{
    public static final PropertyDirection FACING = PropertyDirection.create("facing");

    protected EntityThrowableTorch(World world, boolean ignites)
    {
        super(world);
        _ignites = ignites;
    }

    protected EntityThrowableTorch(World world, EntityPlayer playerEntity, boolean ignites)
    {
        super(world, playerEntity);
        _ignites = ignites;
    }

    protected EntityThrowableTorch(World world, double x, double y, double z, boolean ignites)
    {
        super(world, x, y, z);
        _ignites = ignites;
    }

//    @Override
//    protected float getGravityVelocity()
//    {
//        return 0.0F;
//    }

    private enum Action { PLACE, DESTROY_PLACE, DROP, NONE}

    @Override
    protected void onImpact(MovingObjectPosition mop)
    {
        // isRemote true on client, false on server
        // We ONLY want to do the creation part on the server.  We could do particles locally,
        // but since we are creating blocks we really should let the server creat them.
        if (!worldObj.isRemote)
        {
            int x, y, z;
            BlockPos pos = mop.getBlockPos();

            Block placeBlock = Blocks.torch;
            //Block placeBlock = Blocks.cobblestone;
            IBlockState state = placeBlock.getDefaultState();
            IBlockState withFacing = state;

            Action action = Action.PLACE;

            // Place a single torch if we didn't hit an entity
            if (mop.entityHit != null)
            {
                Entity entity = mop.entityHit;
                x = (int) entity.posX;
                y = (int) entity.posY;
                z = (int) entity.posZ;

                if (!entity.isImmuneToFire())
                {
                    entity.attackEntityFrom(DamageSource.causeMobDamage(getThrower()), 1.0F);
                    if (_ignites)
                    {
                        action = Action.NONE;
                        entity.setFire(6);
                    }
                    else
                    {
                        action = Action.DROP;
                    }
                }
            }
            else
            {
                x = pos.getX();
                y = pos.getY();
                z = pos.getZ();

                Block block = worldObj.getBlockState(pos).getBlock();
                if (block.getMaterial() ==  Material.vine)
                {
                    action = Action.DESTROY_PLACE;
                }
                else
                {
                    // We want to move to the side based on the hit.
                    switch (mop.sideHit)
                    {
                        case DOWN: // 0
                            // Bottom
                            y = y - 1;
                            break;
                        case UP: // 1
                            // Top
                            y = y + 1;
                            break;
                        case NORTH: // 2
                            withFacing = state.withProperty(FACING, EnumFacing.NORTH);
                            z = z - 1;
                            break;
                        case SOUTH: // 3:
                            withFacing = state.withProperty(FACING, EnumFacing.SOUTH);
                            z = z + 1;
                            break;
                        case WEST: // 4:
                            withFacing = state.withProperty(FACING, EnumFacing.WEST);
                            x = x - 1;
                            break;
                        case EAST: // 5:
                            withFacing = state.withProperty(FACING, EnumFacing.EAST);
                            x = x + 1;
                            break;
                    }

                    // Update the block position
                    pos = new BlockPos(x, y, z);
                }
            }

            switch (action)
            {
                case PLACE:
                    if (worldObj.isAirBlock(pos))
                        worldObj.setBlockState(pos, withFacing);
                    else
                        worldObj.spawnEntityInWorld(new EntityItem(worldObj, x, y, z, new ItemStack(placeBlock)));
                    break;
                case DESTROY_PLACE:
                    worldObj.destroyBlock(pos, true);
                    worldObj.setBlockState(pos, placeBlock.getDefaultState());
                    break;
                case DROP:
                    worldObj.spawnEntityInWorld(new EntityItem(worldObj, x, y, z, new ItemStack(placeBlock)));
                    break;
                case NONE:
                    break;
            }

            setDead();
        }
    }

    private final boolean _ignites;
}
