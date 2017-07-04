package com.crashbox.throwabletorchmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Base class for all throwable torches.
 */
public class ItemThrowableTorch extends Item
{
    protected enum Type { SLIME, CLAY, MAGMA }

    protected ItemThrowableTorch(String unlocalizedName, Type type, int tier)
    {
        _type = type;
        _tier = tier;

        setMaxStackSize(64);
        setCreativeTab(CreativeTabs.DECORATIONS);

        setUnlocalizedName(unlocalizedName);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entityPlayer, EnumHand hand)
    {
        ItemStack parItemStack = entityPlayer.getHeldItem(hand);

        if (!entityPlayer.capabilities.isCreativeMode)
        {
            parItemStack.shrink(1);
        }

        if (!world.isRemote)
        {
            switch (_type)
            {
                case SLIME:
                    TTUtils.throwSlimeTorch(world, entityPlayer, 0, _tier);
                    break;
                case CLAY:
                    TTUtils.throwClayTorch(world, entityPlayer, 0);
                    break;
                case MAGMA:
                    TTUtils.throwMagmaTorch(world, entityPlayer, 0);
                    break;
            }
        }

        return super.onItemRightClick(world, entityPlayer, hand);
    }

    private final Type _type;
    private int _tier = 0;
    private static final Logger LOGGER = LogManager.getLogger();
}
