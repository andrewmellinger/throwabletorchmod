package com.crashbox.throwabletorchmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
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
    public static final String VERSION = "1.2";

    // These are the blocks and items we load that other parts need to use.
    public static ItemThrowableTorch ITEM_THROWABLE_TORCH;

    @Instance(value = ThrowableTorchMod.MODID)
    public static ThrowableTorchMod instance;

    @SidedProxy(clientSide = "com.crashbox.throwabletorchmod.ThrowableTorchModClientProxy",
                serverSide = "com.crashbox.throwabletorchmod.ThrowableTorchModCommonProxy")
    public static ThrowableTorchModCommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // Load config, create blocks, items, etc and register them
        ITEM_THROWABLE_TORCH = ItemThrowableTorch.registerItem();

        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Add entities
        // TODO:  Since I create the things server side, should this be in common???
        int entityID = 0;
        EntityRegistry.registerModEntity(EntityThrowableTorch.class, "Throwable Torch",
                ++entityID, ThrowableTorchMod.instance, 80, 10, true);

        GameRegistry.addRecipe(new ItemStack(ITEM_THROWABLE_TORCH),
                "T",
                "S",
                'T', Blocks.torch,
                'S', Items.slime_ball
        );

        GameRegistry.addRecipe(new ItemStack(ITEM_THROWABLE_TORCH),
                "T",
                "C",
                'T', Blocks.torch,
                'C', Items.clay_ball
        );

        if(event.getSide() == Side.CLIENT)
        {
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

            renderItem.getItemModelMesher().register(ITEM_THROWABLE_TORCH, 0,
                    new ModelResourceLocation(ThrowableTorchMod.MODID + ":" +
                            ItemThrowableTorch.ID, "inventory"));
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
