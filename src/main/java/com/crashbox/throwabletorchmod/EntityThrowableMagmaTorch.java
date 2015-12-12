package com.crashbox.throwabletorchmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Copyright 2015 Andrew O. Mellinger
 */
public class EntityThrowableMagmaTorch extends EntityThrowableTorch
{
    public EntityThrowableMagmaTorch(World world)
    {
        super(world, true);
    }

    public EntityThrowableMagmaTorch(World world, EntityPlayer playerEntity)
    {
        super(world, playerEntity, true);
    }

    public EntityThrowableMagmaTorch(World world, double x, double y, double z)
    {
        super(world, x, y, z, true);
    }
}
