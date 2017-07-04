package com.crashbox.throwabletorchmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Copyright 2015 Andrew O. Mellinger
 */
public class EntityThrowableSlimeTorchT3 extends EntityThrowableTorch
{
    // These are for client side
    public EntityThrowableSlimeTorchT3(World world)
    {
        super(world, 2, false);
    }

    EntityThrowableSlimeTorchT3(World worldIn, double x, double y, double z,
            int generation, boolean ignites)
    {
        super(worldIn, x, y, z, generation, ignites);
    }

    // These are for server side
    EntityThrowableSlimeTorchT3(World world, EntityPlayer playerEntity, int generation)
    {
        super(world, playerEntity, generation, false);
    }
}
