package com.crashbox.throwabletorchmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

/**
 * Copyright 2017 Andrew O. Mellinger
 */
class TTUtils
{
    static void throwSlimeTorch(World world, EntityPlayer entityPlayer,
            float yawOffset, int generation)
    {
        EntityThrowableTorch torch = new EntityThrowableSlimeTorch(world, entityPlayer,
                generation);
        throwTorch(torch, world, entityPlayer, yawOffset);
    }

    static void throwClayTorch(World world, EntityPlayer entityPlayer, float yawOffset)
    {
        EntityThrowableTorch torch = new EntityThrowableClayTorch(world, entityPlayer);
        throwTorch(torch, world, entityPlayer, yawOffset, 2.5F);
    }

    static void throwMagmaTorch(World world, EntityPlayer entityPlayer, float yawOffset)
    {
        EntityThrowableTorch torch = new EntityThrowableMagmaTorch(world, entityPlayer);
        throwTorch(torch, world, entityPlayer, yawOffset);
    }

    //========

    static void throwTorch(EntityThrowableTorch torch, World world,
            EntityPlayer entityPlayer, float yawOffset)
    {
        float rotationYaw = adjustYaw(entityPlayer.rotationYaw, yawOffset);

        torch.setHeadingFromThrower(entityPlayer,
                entityPlayer.rotationPitch,
                rotationYaw,
                0.0F,
                1.5F,
                1.0F);
        world.spawnEntity(torch);
    }

    static void throwTorch(EntityThrowableTorch torch, World world,
            EntityPlayer entityPlayer, float yawOffset, float velocity)
    {
        float rotationYaw = adjustYaw(entityPlayer.rotationYaw, yawOffset);

        torch.setHeadingFromThrower(entityPlayer,
                entityPlayer.rotationPitch,
                rotationYaw,
                0.0F,
                velocity,
                1.0F);
        world.spawnEntity(torch);
    }

    //=========================================================================

    static void throwSlimeTorch(World world,
            double x, double y, double z,
            float rotationPitch, float rotationYaw,
            int generation)
    {
        EntityThrowableTorch torch = new EntityThrowableSlimeTorch(world,
                x, y, z, generation, false);
        throwTorch(torch, world, rotationPitch, rotationYaw, generation);
    }

    static void throwTorch(EntityThrowableTorch torch, World world,
            float rotationPitch, float rotationYaw, int generation)
    {
        // 10 degrees up or down
        rotationPitch += RAND.nextGaussian() * 8.0F;
        rotationYaw += RAND.nextGaussian() * 10.0F;

        float velocity = 0.2F + (0.20F * (generation + 1));
        //velocity += RAND.nextGaussian() * 0.10D;

        float f =  -MathHelper.sin(rotationYaw   * 0.017453292F) *
                    MathHelper.cos(rotationPitch * 0.017453292F);
        float f1 = -MathHelper.sin(rotationPitch * 0.017453292F);
        float f2 =  MathHelper.cos(rotationYaw   * 0.017453292F) *
                    MathHelper.cos(rotationPitch * 0.017453292F);

        // High generations move further
        torch.setThrowableHeading(f, f1, f2, velocity, 1.0F);

        world.spawnEntity(torch);
    }

    //=========================================================================

    private static float adjustYaw(float yaw, float offset)
    {
        yaw += offset;
        if (yaw > 180)
            yaw -= 360;
        else if (yaw < -180)
            yaw += 360;

        return yaw;
    }

    private static final Random RAND = new Random();
    private static final Logger LOGGER = LogManager.getLogger();
}
