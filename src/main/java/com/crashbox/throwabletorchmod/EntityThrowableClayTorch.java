package com.crashbox.throwabletorchmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Copyright 2015 Andrew O. Mellinger
 */
public class EntityThrowableClayTorch extends EntityThrowableTorch
{
    public EntityThrowableClayTorch(World world)
    {
        super(world, false);
    }

    public EntityThrowableClayTorch(World world, EntityPlayer playerEntity)
    {
        super(world, playerEntity, false);
    }

    public EntityThrowableClayTorch(World world, double x, double y, double z)
    {
        super(world, x, y, z, false);
    }
}
