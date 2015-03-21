package com.crashbox.throwabletorchmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Created by andrew on 2/22/15.
 */
public class EntityThrowableTorch extends EntityThrowable
{
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
                x = mop.blockX;
                y = mop.blockY;
                z = mop.blockZ;

                // We want to move to the side based on the hit.
                switch (mop.sideHit)
                {
                    case 0:
                        // Bottom
                        y = y - 1;
                        break;
                    case 1:
                        // Top
                        y = y + 1;
                        break;
                    case 2:
                        // North (neg Z)
                        z = z - 1;
                        break;
                    case 3:
                        // South
                        z = z + 1;
                        break;
                    case 4:
                        // West
                        x = x - 1;
                        break;
                    case 5:
                        // East
                        x = x + 1;
                        break;
                }

            }

            if (worldObj.isAirBlock(x, y, z) && ! dropItem)
            {
                worldObj.setBlock(x, y, z, Blocks.torch);
            }
            else
            {
                worldObj.spawnEntityInWorld(new EntityItem(worldObj, x, y, z, new ItemStack(Blocks.torch)));
            }

            setDead();
        }
    }
}
