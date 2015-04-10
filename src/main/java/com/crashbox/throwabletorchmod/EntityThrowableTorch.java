package com.crashbox.throwabletorchmod;

import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;


/**
 * Created by andrew on 2/22/15.
 */
public class EntityThrowableTorch extends EntityThrowable
{
    public static final PropertyDirection FACING = PropertyDirection.create("facing");

    public EntityThrowableTorch(World world)
    {
        super(world);
    }

    public EntityThrowableTorch(World world, EntityPlayer playerEntity)
    {
        super(world, playerEntity);
    }

    public EntityThrowableTorch(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    @Override
    protected void onImpact(MovingObjectPosition mop)
    {
        // isRemote true on client, false on server
        // We ONLY want to do the creation part on the server.  We could do particles locally,
        // but since we are creating blocks we really should let the server creat them.
        if (!worldObj.isRemote)
        {
            int x, y, z;
            boolean dropItem = false;
            BlockPos pos = mop.getBlockPos();
            BlockPos origPos = mop.getBlockPos();
            IBlockState state = Blocks.torch.getDefaultState();
            IBlockState withFacing = state;

            // Place a single torch if we didn't hit an entity
            if (mop.entityHit != null)
            {
                Entity entity = mop.entityHit;
                x = (int) entity.posX;
                y = (int) entity.posY;
                z = (int) entity.posZ;

                dropItem = true;
            }
            else
            {
                x = pos.getX();
                y = pos.getY();
                z = pos.getZ();

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

            if (!dropItem && worldObj.isAirBlock(pos))
            {
                worldObj.setBlockState(pos, withFacing);
            }
            else
            {
                worldObj.spawnEntityInWorld(new EntityItem(worldObj, x, y, z, new ItemStack(Blocks.torch)));
            }

            setDead();
        }
    }

}
