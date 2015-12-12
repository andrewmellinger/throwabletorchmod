package com.crashbox.throwabletorchmod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;


// See:
// http://www.minecraftforum.net/forums/mapping-and-modding/mapping-and-modding-tutorials/1571522-custom-projectile-and-rendering

@Mod(modid= ThrowableTorchMod.MODID, name= ThrowableTorchMod.NAME, version= ThrowableTorchMod.VERSION)
public class ThrowableTorchMod
{
    // This guy talks about what each event handler does
    // http://greyminecraftcoder.blogspot.com/2013/11/how-forge-starts-up-your-code.html
    public static final String MODID = "throwabletorchmod";
    public static final String NAME = "ThrowableTorchMod";
    public static final String VERSION = "1.3";

    // These are the blocks and items we load that other parts need to use.
    public static ItemThrowableTorch ITEM_THROWABLE_SLIME_TORCH;
    public static ItemThrowableTorch ITEM_THROWABLE_CLAY_TORCH;
    public static ItemThrowableTorch ITEM_THROWABLE_MAGMA_TORCH;

    @Instance(value = ThrowableTorchMod.MODID)
    public static ThrowableTorchMod instance;

    @SidedProxy(clientSide = "com.crashbox.throwabletorchmod.ThrowableTorchModClientProxy",
                serverSide = "com.crashbox.throwabletorchmod.ThrowableTorchModCommonProxy")
    public static ThrowableTorchModCommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ITEM_THROWABLE_SLIME_TORCH = new ItemThrowableSlimeTorch();
        GameRegistry.registerItem(ITEM_THROWABLE_SLIME_TORCH, ItemThrowableSlimeTorch.ID);

        ITEM_THROWABLE_CLAY_TORCH = new ItemThrowableClayTorch();
        GameRegistry.registerItem(ITEM_THROWABLE_CLAY_TORCH, ItemThrowableClayTorch.ID);

        ITEM_THROWABLE_MAGMA_TORCH = new ItemThrowableMagmaTorch();
        GameRegistry.registerItem(ITEM_THROWABLE_MAGMA_TORCH, ItemThrowableMagmaTorch.ID);

        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Add entities
        // TODO:  Since I create the things server side, should this be in common???
        int entityID = 0;

        EntityRegistry.registerModEntity(EntityThrowableSlimeTorch.class, "Throwable Slime Torch",
                ++entityID, ThrowableTorchMod.instance, 80, 10, true);

        GameRegistry.addRecipe(new ItemStack(ITEM_THROWABLE_SLIME_TORCH),
                "T",
                "S",
                'T', Blocks.torch,
                'S', Items.slime_ball
        );

        EntityRegistry.registerModEntity(EntityThrowableClayTorch.class, "Throwable Clay Torch",
                ++entityID, ThrowableTorchMod.instance, 80, 10, true);

        GameRegistry.addRecipe(new ItemStack(ITEM_THROWABLE_CLAY_TORCH),
                "T",
                "C",
                'T', Blocks.torch,
                'C', Items.clay_ball
        );

        EntityRegistry.registerModEntity(EntityThrowableMagmaTorch.class, "Throwable Magma Torch",
                ++entityID, ThrowableTorchMod.instance, 80, 10, true);

        GameRegistry.addRecipe(new ItemStack(ITEM_THROWABLE_MAGMA_TORCH),
                "T",
                "M",
                'T', Blocks.torch,
                'M', Items.magma_cream
        );

        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        // Handle interaction with other mods, complete your setup based on this.
        proxy.postInit(event);
    }


}
