package com.crashbox.throwabletorchmod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Base class for throwable entity.
 */
public class EntityThrowableTorch extends EntityThrowable
{
    // These are for client side
    EntityThrowableTorch(World world, int generation, boolean ignites)
    {
        super(world);
        _ignites = ignites;
        _generation = generation;
    }

    EntityThrowableTorch(World worldIn, double x, double y, double z,
            int generation, boolean ignites)
    {
        super(worldIn, x, y, z);
        _ignites = ignites;
        _generation = generation;
    }

    EntityThrowableTorch(World world, EntityPlayer playerEntity, int generation,
            boolean ignites)
    {
        super(world, playerEntity);
        _ignites = ignites;
        _generation = generation;
    }

    @Override
    protected void onImpact(RayTraceResult mop)
    {
        if (!world.isRemote)
        {
            int x, y, z;
            BlockPos pos = mop.getBlockPos();
            Block placeBlock = Blocks.TORCH;
            IBlockState withFacing = placeBlock.getDefaultState();
            Action action = Action.PLACE;
            Entity entity = mop.entityHit;

            if (pos == null)
            {
//                LOGGER.info(">>>>>>>>>>>>>>>>>>>> POS IS NULL early, {}", mop);
                setDead();
                return;
            }

            // Place a single torch if we didn't hit an entity
            if (mop.entityHit != null)
            {
                x = (int) entity.posX;
                y = (int) entity.posY;
                z = (int) entity.posZ;

                if (!entity.isImmuneToFire() && getThrower() != null)
                {
                    // Check if entity is immune to fire and if not set fire
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

                Block block = world.getBlockState(pos).getBlock();
                if (block.isReplaceable(world, pos))
                {
                    action = Action.DESTROY_PLACE;
                }
                else
                {
                    // We want to move to the side based on the hit.
                    switch (mop.sideHit)
                    {
                        case DOWN:
                            y = y - 1;
                            break;
                        case UP:
                            y = y + 1;
                            break;
                        case NORTH:
                            withFacing = Blocks.TORCH.getDefaultState()
                                    .withProperty(BlockTorch.FACING, EnumFacing.NORTH);
                            z = z - 1;
                            break;
                        case SOUTH:
                            withFacing = Blocks.TORCH.getDefaultState()
                                    .withProperty(BlockTorch.FACING, EnumFacing.SOUTH);
                            z = z + 1;
                            break;
                        case WEST:
                            withFacing = Blocks.TORCH.getDefaultState()
                                    .withProperty(BlockTorch.FACING, EnumFacing.WEST);
                            x = x - 1;
                            break;
                        case EAST:
                            withFacing = Blocks.TORCH.getDefaultState()
                                    .withProperty(BlockTorch.FACING, EnumFacing.EAST);
                            x = x + 1;
                            break;
                    }

                    // Update the block position
                    pos = new BlockPos(x, y, z);
                }

                // Spread based on new position
                if (_generation != 0)
                {
                    spreadTorch(pos);
                }
            }

            switch (action)
            {
                case PLACE:
                    if (world.isAirBlock(pos))
                        world.setBlockState(pos, withFacing);
                    else
                        world.spawnEntity(new EntityItem(world, x, y, z, new ItemStack(placeBlock)));
                    break;
                case DESTROY_PLACE:
                    world.destroyBlock(pos, true);
                    world.setBlockState(pos, withFacing);
                    break;
                case DROP:
                    world.spawnEntity(new EntityItem(world, x, y, z, new ItemStack(placeBlock)));
                    break;
                case NONE:
                    break;
            }

            setDead();
        }
    }

    private void spreadTorch(BlockPos pos)
    {
        float yaw = -90.0F;
        for (int i = 0; i < 4; ++i)
        {
            TTUtils.throwSlimeTorch(world,
                    pos.getX(),
                    pos.getY() + 2,
                    pos.getZ(),
                    -60,                            // pitch
                    yaw,                            // yaw
                    _generation - 1);               // generation

            yaw += 90.0F;
        }
    }

    private enum Action
    {
        PLACE, DESTROY_PLACE, DROP, NONE
    }

    private final boolean _ignites;
    private final int _generation;

    private static final Logger LOGGER = LogManager.getLogger();
}