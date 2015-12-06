package com.crashbox.throwabletorchmod;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Base class for all throwable torches.
 */
public class ItemThrowableTorch extends Item
{
    protected enum Type { SLIME, CLAY, MAGMA }

//    public static ItemThrowableTorch registerItem()
//    {
//        ItemThrowableTorch item = new ItemThrowableTorch();
//        GameRegistry.registerItem(item, ItemThrowableTorch.ID);
//        return item;
//    }

    protected ItemThrowableTorch(String unlocalizedName, Type type)
    {
        _type = type;

        setMaxStackSize(64);
        setCreativeTab(CreativeTabs.tabDecorations);

        setUnlocalizedName(unlocalizedName);
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
            switch (_type)
            {
                case SLIME:
                    world.spawnEntityInWorld(new EntityThrowableSlimeTorch(world, entityPlayer));
                    break;
                case CLAY:
                    world.spawnEntityInWorld(new EntityThrowableClayTorch(world, entityPlayer));
                    break;
                case MAGMA:
                    world.spawnEntityInWorld(new EntityThrowableMagmaTorch(world, entityPlayer));
                    break;
            }
        }

        return par1ItemStack;
    }

    private final Type _type;

}
