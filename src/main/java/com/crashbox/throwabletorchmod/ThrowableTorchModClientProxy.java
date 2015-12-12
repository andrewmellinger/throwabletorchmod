package com.crashbox.throwabletorchmod;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.client.renderer.entity.RenderSnowball;

/** Copyright 2015 Andrew O. Mellinger */
public class ThrowableTorchModClientProxy extends ThrowableTorchModCommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent e)
    {
        super.preInit(e);
    }

    @Override
    public void init(FMLInitializationEvent e)
    {
        super.init(e);

        // Add renderers.
        RenderingRegistry.registerEntityRenderingHandler(EntityThrowableSlimeTorch.class,
                new RenderSnowball(ThrowableTorchMod.ITEM_THROWABLE_SLIME_TORCH));

        RenderingRegistry.registerEntityRenderingHandler(EntityThrowableClayTorch.class,
                new RenderSnowball(ThrowableTorchMod.ITEM_THROWABLE_CLAY_TORCH));

        RenderingRegistry.registerEntityRenderingHandler(EntityThrowableMagmaTorch.class,
                new RenderSnowball(ThrowableTorchMod.ITEM_THROWABLE_MAGMA_TORCH));
    }

    @Override
    public void postInit(FMLPostInitializationEvent e)
    {
        super.postInit(e);
    }
}
