package com.crashbox.throwabletorchmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Copyright 2015 Andrew O. Mellinger
 */
public class EntityThrowableSlimeTorchT4 extends EntityThrowableTorch
{
    // These are for client side
    public EntityThrowableSlimeTorchT4(World world)
    {
        super(world, 3, false);
    }

    EntityThrowableSlimeTorchT4(World worldIn, double x, double y, double z,
            int generation, boolean ignites)
    {
        super(worldIn, x, y, z, generation, ignites);
    }

    // These are for server side
    EntityThrowableSlimeTorchT4(World world, EntityPlayer playerEntity, int generation)
    {
        super(world, playerEntity, generation, false);
    }
}
