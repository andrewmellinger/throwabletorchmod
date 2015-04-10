package com.crashbox.throwabletorchmod;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by andrew on 2/22/15.
 */
public class ItemThrowableTorch extends Item
{
    public static String ID = "throwableTorch";

    public static ItemThrowableTorch registerItem()
    {
        ItemThrowableTorch item = new ItemThrowableTorch();
        GameRegistry.registerItem(item, ItemThrowableTorch.ID);
        return item;
    }

    public ItemThrowableTorch()
    {
        setMaxStackSize(64);
        setCreativeTab(CreativeTabs.tabDecorations);

        setUnlocalizedName(ID);
        //setTextureName("throwableTorchMod:throwableTorch");
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer entityPlayer)
    {
        if (!entityPlayer.capabilities.isCreativeMode)
        {
            par1ItemStack.stackSize--;
        }

        //par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        if (!world.isRemote)
        {
            world.spawnEntityInWorld(new EntityThrowableTorch(world, entityPlayer));
        }

        return par1ItemStack;
    }

}
