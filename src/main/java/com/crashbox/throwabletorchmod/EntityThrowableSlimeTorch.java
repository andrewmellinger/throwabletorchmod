package com.crashbox.throwabletorchmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Copyright 2015 Andrew O. Mellinger
 */
public class EntityThrowableSlimeTorch extends EntityThrowableTorch
{
    public EntityThrowableSlimeTorch(World world)
    {
        super(world, false);
    }

    public EntityThrowableSlimeTorch(World world, EntityPlayer playerEntity)
    {
        super(world, playerEntity, false);
    }

    public EntityThrowableSlimeTorch(World world, double x, double y, double z)
    {
        super(world, x, y, z, false);
    }
}