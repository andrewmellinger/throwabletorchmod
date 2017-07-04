package com.crashbox.throwabletorchmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Copyright 2015 Andrew O. Mellinger
 */
public class EntityThrowableClayTorch extends EntityThrowableTorch
{
    // These are for client side
    public EntityThrowableClayTorch(World world)
    {
        super(world, 0, false);
    }

    // These are for spread
    EntityThrowableClayTorch(World worldIn, double x, double y, double z,
            int generation, boolean ignites)
    {
        super(worldIn, x, y, z, generation, ignites);
    }

    // These are for server side
    EntityThrowableClayTorch(World world, EntityPlayer playerEntity)
    {
        super(world, playerEntity, 0, false);
    }
}
