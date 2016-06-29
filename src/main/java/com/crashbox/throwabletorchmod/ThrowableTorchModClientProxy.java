package com.crashbox.throwabletorchmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
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
    }

    @Override
    public void init(FMLInitializationEvent e)
    {
        super.init(e);

        //  Add renderers.

        final RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        final RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

        RenderingRegistry.registerEntityRenderingHandler(EntityThrowableSlimeTorch.class,
                new RenderSnowball(renderManager, ThrowableTorchMod.ITEM_THROWABLE_SLIME_TORCH, renderItem));

        RenderingRegistry.registerEntityRenderingHandler(EntityThrowableClayTorch.class,
                new RenderSnowball(renderManager, ThrowableTorchMod.ITEM_THROWABLE_CLAY_TORCH, renderItem));

        RenderingRegistry.registerEntityRenderingHandler(EntityThrowableMagmaTorch.class,
                new RenderSnowball(renderManager, ThrowableTorchMod.ITEM_THROWABLE_MAGMA_TORCH, renderItem));


            // TODO: These are the ones we are supposed to be using, but they don't work like this
//        RenderingRegistry.registerEntityRenderingHandler(EntityThrowableSlimeTorch.class,
//            new IRenderFactory<Entity>() {
//                @Override
//                public Render<? super Entity> createRenderFor(RenderManager manager)
//                {
//                    return new RenderSnowball<Entity>(renderManager, ThrowableTorchMod.ITEM_THROWABLE_SLIME_TORCH, renderItem);
//                }
//            }
//        );
//
//        RenderingRegistry.registerEntityRenderingHandler(EntityThrowableClayTorch.class,
//            new IRenderFactory<Entity>()
//            {
//                @Override
//                public Render<? super Entity> createRenderFor(RenderManager manager)
//                {
//                   return  new RenderSnowball<Entity>(renderManager, ThrowableTorchMod.ITEM_THROWABLE_CLAY_TORCH, renderItem);
//                }
//            }
//        );
//
//        RenderingRegistry.registerEntityRenderingHandler(EntityThrowableMagmaTorch.class,
//            new IRenderFactory<Entity>() {
//                @Override
//                public Render<? super Entity> createRenderFor(RenderManager manager)
//                {
//                    return new RenderSnowball<Entity>(renderManager, ThrowableTorchMod.ITEM_THROWABLE_MAGMA_TORCH, renderItem);
//                }
//            }
//        );
    }

    @Override
    public void postInit(FMLPostInitializationEvent e)
    {
        super.postInit(e);
    }
}
