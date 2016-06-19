package com.crashbox.throwabletorchmod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Base class for throwable entity.
 */
public class EntityThrowableTorch extends EntityThrowable {

	BlockPos posFinal = null;
	int xf, yf, zf;
	public static final PropertyDirection FACING = PropertyDirection.create("facing");

	protected EntityThrowableTorch(World world, boolean ignites) {
		super(world);
		_ignites = ignites;
	}

	protected EntityThrowableTorch(World world, EntityPlayer playerEntity, boolean ignites) {
		super(world, playerEntity);
		_ignites = ignites;
	}

	protected EntityThrowableTorch(World world, double x, double y, double z, boolean ignites) {
		super(world, x, y, z);
		_ignites = ignites;
	}

	@Override
	protected void onImpact(RayTraceResult mop) {
		if (!worldObj.isRemote) {
			int x, y, z;
			BlockPos pos = mop.getBlockPos();
			Block placeBlock = Blocks.TORCH;
			IBlockState state = placeBlock.getDefaultState();
			IBlockState withFacing = state;
			Action action = Action.PLACE;
			Entity entity = mop.entityHit;

			if (mop.entityHit != null) {
			}
			
			// Place a single torch if we didn't hit an entity
			if (mop.entityHit != null) {
				x = (int) entity.posX;
				y = (int) entity.posY;
				z = (int) entity.posZ;

				if (!entity.isImmuneToFire()) {
					// Check if entity is immune to fire and if not set fire
					entity.attackEntityFrom(DamageSource.causeMobDamage(getThrower()), 1.0F);
					if (_ignites) {
						System.out.println("setting on fire");
						action = Action.NONE;
						entity.setFire(6);
					} else {
						action = Action.DROP;
					}
				}
			} else {
				x = pos.getX();
				y = pos.getY();
				z = pos.getZ();
				xf = pos.getX();
				yf = pos.getY();
				zf = pos.getZ();
				posFinal = new BlockPos(xf, yf, zf);

				Block block = worldObj.getBlockState(new BlockPos(xf, yf, zf)).getBlock();
				// CHeck for Dead Bushes, Vines, Snow Layers, or Tall Grass and if found break
				if (block == Blocks.DEADBUSH || block == Blocks.VINE || block == Blocks.SNOW_LAYER || block == Blocks.TALLGRASS) {
					action = Action.DESTROY_PLACE;
				} else {
					// We want to move to the side based on the hit.
					switch (mop.sideHit) {
					case DOWN:
						y = y - 1;
						break;
					case UP:
						y = y + 1;
						break;
					case NORTH:
						withFacing = Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.NORTH);
						z = z - 1;
						break;
					case SOUTH:
						withFacing = Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.SOUTH);
						z = z + 1;
						break;
					case WEST:
						withFacing = Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.WEST);
						x = x - 1;
						break;
					case EAST:
						withFacing = Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.EAST);
						x = x + 1;
						break;
					}

					// Update the block position
					pos = new BlockPos(x, y, z);
				}
			}

			switch (action) {
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
				System.out.println("Should have dropped");
				break;
			case NONE:
				break;
			}

			setDead();
		}
	}

	private enum Action {
		PLACE, DESTROY_PLACE, DROP, NONE
	}

	private final boolean _ignites;
}