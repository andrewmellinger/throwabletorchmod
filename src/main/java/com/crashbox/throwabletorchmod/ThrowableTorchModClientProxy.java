package com.crashbox.throwabletorchmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/** Copyright 2015 Andrew O. Mellinger */
public class ThrowableTorchModClientProxy extends ThrowableTorchModCommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent e)
    {
        super.preInit(e);

        RenderingRegistry.registerEntityRenderingHandler(EntityThrowableSlimeTorch.class, new SlimeRender());
        RenderingRegistry.registerEntityRenderingHandler(EntityThrowableClayTorch.class, new ClayRender());
        RenderingRegistry.registerEntityRenderingHandler(EntityThrowableMagmaTorch.class, new MagmaRender());

        RenderingRegistry.registerEntityRenderingHandler(EntityThrowableSlimeTorchT2.class, new SlimeTier2());
        RenderingRegistry.registerEntityRenderingHandler(EntityThrowableSlimeTorchT3.class, new SlimeTier3());
        RenderingRegistry.registerEntityRenderingHandler(EntityThrowableSlimeTorchT4.class, new SlimeTier4());
    }

    @Override
    public void init(FMLInitializationEvent e)
    {
        super.init(e);
    }

    private static class SlimeRender implements IRenderFactory<Entity>
    {
        @Override
        public Render<? super Entity> createRenderFor(RenderManager manager)
        {
            final RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            return new RenderSnowball<Entity>(manager, ThrowableTorchMod.ITEM_THROWABLE_SLIME_TORCH, renderItem);
        }
    }

    private static class ClayRender implements IRenderFactory<Entity>
    {
        @Override
        public Render<? super Entity> createRenderFor(RenderManager manager)
        {
            final RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            return new RenderSnowball<Entity>(manager, ThrowableTorchMod.ITEM_THROWABLE_CLAY_TORCH, renderItem);
        }
    }

    private static class MagmaRender implements IRenderFactory<Entity>
    {
        @Override
        public Render<? super Entity> createRenderFor(RenderManager manager)
        {
            final RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            return new RenderSnowball<Entity>(manager, ThrowableTorchMod.ITEM_THROWABLE_MAGMA_TORCH, renderItem);
        }
    }

    private static class SlimeTier2 implements IRenderFactory<Entity>
    {
        @Override
        public Render<? super Entity> createRenderFor(RenderManager manager)
        {
            final RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            return new RenderSnowball<Entity>(manager, ThrowableTorchMod.ITEM_THROWABLE_MULTI_TORCH_TIER2, renderItem);
        }
    }

    private static class SlimeTier3 implements IRenderFactory<Entity>
    {
        @Override
        public Render<? super Entity> createRenderFor(RenderManager manager)
        {
            final RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            return new RenderSnowball<Entity>(manager, ThrowableTorchMod.ITEM_THROWABLE_MULTI_TORCH_TIER3, renderItem);
        }
    }

    private static class SlimeTier4 implements IRenderFactory<Entity>
    {
        @Override
        public Render<? super Entity> createRenderFor(RenderManager manager)
        {
            final RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            return new RenderSnowball<Entity>(manager, ThrowableTorchMod.ITEM_THROWABLE_MULTI_TORCH_TIER4, renderItem);
        }
    }

    @Override
    public void postInit(FMLPostInitializationEvent e)
    {
        super.postInit(e);
    }
}
