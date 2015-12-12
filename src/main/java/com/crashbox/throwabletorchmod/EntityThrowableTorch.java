package com.crashbox.throwabletorchmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Created by andrew on 2/22/15.
 */
public class EntityThrowableTorch extends EntityThrowable
{
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

    @Override
    protected void onImpact(MovingObjectPosition mop)
    {
        // isRemote true on client, false on server
        // We ONLY want to do the creation part on the server.  We could do particles locally,
        // but since we are creating blocks we really should let the server creat them.
        if (!worldObj.isRemote)
        {
//            int x, y, z;
//            boolean dropItem = false;
//            boolean destroyAndPlace = false;
//
//            // Place a single torch if we didn't hit an entity
//            if (mop.entityHit != null)
//            {
//                Entity entity = mop.entityHit;
//                x = (int) entity.posX;
//                y = (int) entity.posY;
//                z = (int) entity.posZ;
//
//                dropItem = true;
//            }
//            else
//            {
//                x = mop.blockX;
//                y = mop.blockY;
//                z = mop.blockZ;
//
//                Block block = worldObj.getBlock(x,y,z);
//                if (block.getMaterial() == Material.vine)
//                {
//                    destroyAndPlace = true;
//
//                }
//                else
//                {
//
//                    // We want to move to the side based on the hit.
//                    switch (mop.sideHit)
//                    {
//                        case 0:
//                            // Bottom
//                            y = y - 1;
//                            break;
//                        case 1:
//                            // Top
//                            y = y + 1;
//                            break;
//                        case 2:
//                            // North (neg Z)
//                            z = z - 1;
//                            break;
//                        case 3:
//                            // South
//                            z = z + 1;
//                            break;
//                        case 4:
//                            // West
//                            x = x - 1;
//                            break;
//                        case 5:
//                            // East
//                            x = x + 1;
//                            break;
//                    }
//                }
//            }
//
//            if (destroyAndPlace)
//            {
//                worldObj.setBlock(x, y, z, Blocks.torch);
//            }
//            else if (worldObj.isAirBlock(x, y, z) && ! dropItem)
//            {
//                worldObj.setBlock(x, y, z, Blocks.torch);
//            }
//            else
//            {
//                worldObj.spawnEntityInWorld(new EntityItem(worldObj, x, y, z, new ItemStack(Blocks.torch)));
//            }
//
//            setDead();

            int x, y, z;

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
                x = mop.blockX;
                y = mop.blockY;
                z = mop.blockZ;

                Block block = worldObj.getBlock(x,y,z);
                if (block.getMaterial() == Material.vine)
                {
                    action = Action.DESTROY_PLACE;
                }
                else
                {
                    // We want to move to the side based on the hit.
                    switch (mop.sideHit)
                    {
                        case 0: // DOWN: // 0
                            // Bottom
                            y = y - 1;
                            break;
                        case 1: // UP: // 1
                            // Top
                            y = y + 1;
                            break;
                        case 2: // NORTH: // 2
                            z = z - 1;
                            break;
                        case 3: // SOUTH: // 3:
                            z = z + 1;
                            break;
                        case 4: //WEST: // 4:
                            x = x - 1;
                            break;
                        case 5: // EAST: // 5:
                            x = x + 1;
                            break;
                    }
                }
            }

            Block placeBlock = Blocks.torch;
            switch (action)
            {
                case PLACE:
                    if (worldObj.isAirBlock(x, y, z))
                        worldObj.setBlock(x, y, z, placeBlock);
                    else
                        worldObj.spawnEntityInWorld(new EntityItem(worldObj, x, y, z, new ItemStack(placeBlock)));
                    break;
                case DESTROY_PLACE:
                    worldObj.setBlock(x, y, z, placeBlock);
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

    private enum Action { PLACE, DESTROY_PLACE, DROP, NONE}
    private final boolean _ignites;
}
