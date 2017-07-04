package com.crashbox.throwabletorchmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Copyright 2015 Andrew O. Mellinger
 */
public class EntityThrowableSlimeTorch extends EntityThrowableTorch
{
    // These are for client side
    public EntityThrowableSlimeTorch(World world)
    {
        super(world, 0, false);
    }

    EntityThrowableSlimeTorch(World worldIn, double x, double y, double z,
            int generation, boolean ignites)
    {
        super(worldIn, x, y, z, generation, ignites);
    }

    // These are for server side
    EntityThrowableSlimeTorch(World world, EntityPlayer playerEntity, int generation)
    {
        super(world, playerEntity, generation, false);
    }
}
