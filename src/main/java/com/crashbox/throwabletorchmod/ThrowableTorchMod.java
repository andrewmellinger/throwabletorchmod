 package com.crashbox.throwabletorchmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;


// See:
// http://www.minecraftforum.net/forums/mapping-and-modding/mapping-and-modding-tutorials/1571522-custom-projectile-and-rendering

@Mod(modid= ThrowableTorchMod.MODID, name= ThrowableTorchMod.NAME, version= ThrowableTorchMod.VERSION)
public class ThrowableTorchMod
{
    // This guy talks about what each event handler does
    // http://greyminecraftcoder.blogspot.com/2013/11/how-forge-starts-up-your-code.html
    public static final String MODID = "throwabletorchmod";
    public static final String NAME = "ThrowableTorchMod";
    public static final String VERSION = "1.5";

    // These are the blocks and items we load that other parts need to use.
    public static ItemThrowableTorch ITEM_THROWABLE_SLIME_TORCH;
    public static ItemThrowableTorch ITEM_THROWABLE_CLAY_TORCH;
    public static ItemThrowableTorch ITEM_THROWABLE_MAGMA_TORCH;

    public static ItemThrowableTorch ITEM_THROWABLE_MULTI_TORCH_TIER2;
    public static ItemThrowableTorch ITEM_THROWABLE_MULTI_TORCH_TIER3;
    public static ItemThrowableTorch ITEM_THROWABLE_MULTI_TORCH_TIER4;

    @Instance(value = ThrowableTorchMod.MODID)
    public static ThrowableTorchMod instance;

    @SidedProxy(clientSide = "com.crashbox.throwabletorchmod.ThrowableTorchModClientProxy",
                serverSide = "com.crashbox.throwabletorchmod.ThrowableTorchModCommonProxy")
    public static ThrowableTorchModCommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // Load config, create blocks, items, etc and register them

        ITEM_THROWABLE_SLIME_TORCH = new ItemThrowableSlimeTorch();
        ITEM_THROWABLE_SLIME_TORCH.setRegistryName(ItemThrowableSlimeTorch.ID);
        GameRegistry.register(ITEM_THROWABLE_SLIME_TORCH);

        ITEM_THROWABLE_CLAY_TORCH = new ItemThrowableClayTorch();
        ITEM_THROWABLE_CLAY_TORCH.setRegistryName(ItemThrowableClayTorch.ID);
        GameRegistry.register(ITEM_THROWABLE_CLAY_TORCH);

        ITEM_THROWABLE_MAGMA_TORCH = new ItemThrowableMagmaTorch();
        ITEM_THROWABLE_MAGMA_TORCH.setRegistryName(ItemThrowableMagmaTorch.ID);
        GameRegistry.register(ITEM_THROWABLE_MAGMA_TORCH);

        ITEM_THROWABLE_MULTI_TORCH_TIER2 = new ItemThrowableSlimeTorchT2();
        ITEM_THROWABLE_MULTI_TORCH_TIER2.setRegistryName(ItemThrowableSlimeTorchT2.ID);
        GameRegistry.register(ITEM_THROWABLE_MULTI_TORCH_TIER2);

        ITEM_THROWABLE_MULTI_TORCH_TIER3 = new ItemThrowableSlimeTorchT3();
        ITEM_THROWABLE_MULTI_TORCH_TIER3.setRegistryName(ItemThrowableSlimeTorchT3.ID);
        GameRegistry.register(ITEM_THROWABLE_MULTI_TORCH_TIER3);

        ITEM_THROWABLE_MULTI_TORCH_TIER4 = new ItemThrowableSlimeTorchT4();
        ITEM_THROWABLE_MULTI_TORCH_TIER4.setRegistryName(ItemThrowableSlimeTorchT4.ID);
        GameRegistry.register(ITEM_THROWABLE_MULTI_TORCH_TIER4);

        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Add entities
        // TODO:  Since I create the things server side, should this be in common???
        int entityID = 0;

        // TODO:  Localization
        ResourceLocation reLoc = new ResourceLocation(ThrowableTorchMod.MODID, ItemThrowableSlimeTorch.ID);
        EntityRegistry.registerModEntity(reLoc, EntityThrowableSlimeTorch.class,
                "Throwable Slime Torch",
                ++entityID, ThrowableTorchMod.instance, 80, 10, true);

        GameRegistry.addRecipe(new ItemStack(ITEM_THROWABLE_SLIME_TORCH),
                "T",
                "S",
                'T', Blocks.TORCH,
                'S', Items.SLIME_BALL
        );

        reLoc = new ResourceLocation(ThrowableTorchMod.MODID, ItemThrowableClayTorch.ID);
        EntityRegistry.registerModEntity(reLoc, EntityThrowableClayTorch.class,
                "Throwable Clay Torch",
                ++entityID, ThrowableTorchMod.instance, 80, 10, true);

        GameRegistry.addRecipe(new ItemStack(ITEM_THROWABLE_CLAY_TORCH),
                "T",
                "C",
                'T', Blocks.TORCH,
                'C', Items.CLAY_BALL
        );

        reLoc = new ResourceLocation(ThrowableTorchMod.MODID, ItemThrowableMagmaTorch.ID);
        EntityRegistry.registerModEntity(reLoc, EntityThrowableMagmaTorch.class,
                "Throwable Magma Torch",
                ++entityID, ThrowableTorchMod.instance, 80, 10, true);

        GameRegistry.addRecipe(new ItemStack(ITEM_THROWABLE_MAGMA_TORCH),
                "T",
                "M",
                'T', Blocks.TORCH,
                'M', Items.MAGMA_CREAM
        );

        //======================================================================

        reLoc = new ResourceLocation(ThrowableTorchMod.MODID, ItemThrowableSlimeTorchT2.ID);
        EntityRegistry.registerModEntity(reLoc, EntityThrowableSlimeTorchT2.class,
                "Throwable Slime Torch Tier1",
                ++entityID, ThrowableTorchMod.instance, 80, 10, true);

        GameRegistry.addRecipe(new ItemStack(ITEM_THROWABLE_MULTI_TORCH_TIER2),
                "-S-",
                "SSS",
                "-S-",
                'S', ITEM_THROWABLE_SLIME_TORCH
        );

        reLoc = new ResourceLocation(ThrowableTorchMod.MODID, ItemThrowableSlimeTorchT3.ID);
        EntityRegistry.registerModEntity(reLoc, EntityThrowableSlimeTorchT3.class,
                "Throwable Slime Torch Tier2",
                ++entityID, ThrowableTorchMod.instance, 80, 10, true);

        GameRegistry.addRecipe(new ItemStack(ITEM_THROWABLE_MULTI_TORCH_TIER3),
                "-S-",
                "STS",
                "-S-",
                'T', ITEM_THROWABLE_SLIME_TORCH,
                'S', ITEM_THROWABLE_MULTI_TORCH_TIER2
        );

        reLoc = new ResourceLocation(ThrowableTorchMod.MODID, ItemThrowableSlimeTorchT4.ID);
        EntityRegistry.registerModEntity(reLoc, EntityThrowableSlimeTorchT4.class,
                "Throwable Slime Torch Tier3",
                ++entityID, ThrowableTorchMod.instance, 80, 10, true);

        GameRegistry.addRecipe(new ItemStack(ITEM_THROWABLE_MULTI_TORCH_TIER4),
                "-S-",
                "STS",
                "-S-",
                'T', ITEM_THROWABLE_SLIME_TORCH,
                'S', ITEM_THROWABLE_MULTI_TORCH_TIER3
        );

        //======================================================================

        // TODO: Should I put this in client? Seems fine here and is easier to manage.
        if(event.getSide() == Side.CLIENT)
        {
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

            renderItem.getItemModelMesher().register(ITEM_THROWABLE_SLIME_TORCH, 0,
                    new ModelResourceLocation(ThrowableTorchMod.MODID + ":" +
                            ItemThrowableSlimeTorch.ID, "inventory"));

            renderItem.getItemModelMesher().register(ITEM_THROWABLE_CLAY_TORCH, 0,
                    new ModelResourceLocation(ThrowableTorchMod.MODID + ":" +
                            ItemThrowableClayTorch.ID, "inventory"));

            renderItem.getItemModelMesher().register(ITEM_THROWABLE_MAGMA_TORCH, 0,
                    new ModelResourceLocation(ThrowableTorchMod.MODID + ":" +
                            ItemThrowableMagmaTorch.ID, "inventory"));

            //============

            renderItem.getItemModelMesher().register(ITEM_THROWABLE_MULTI_TORCH_TIER2, 0,
                    new ModelResourceLocation(ThrowableTorchMod.MODID + ":" +
                            ItemThrowableSlimeTorchT2.ID, "inventory"));

            renderItem.getItemModelMesher().register(ITEM_THROWABLE_MULTI_TORCH_TIER3, 0,
                    new ModelResourceLocation(ThrowableTorchMod.MODID + ":" +
                            ItemThrowableSlimeTorchT3.ID, "inventory"));

            renderItem.getItemModelMesher().register(ITEM_THROWABLE_MULTI_TORCH_TIER4, 0,
                    new ModelResourceLocation(ThrowableTorchMod.MODID + ":" +
                            ItemThrowableSlimeTorchT4.ID, "inventory"));
        }

        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        // Handle interaction with other mods, complete your setup based on this.
        proxy.postInit(event);
    }
}
