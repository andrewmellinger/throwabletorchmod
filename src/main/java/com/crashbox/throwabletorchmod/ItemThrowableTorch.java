package com.crashbox.throwabletorchmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/**
 * Base class for all throwable torches.
 */
public class ItemThrowableTorch extends Item
{
    protected enum Type { SLIME, CLAY, MAGMA }

    protected ItemThrowableTorch(String unlocalizedName, Type type)
    {
        _type = type;

        setMaxStackSize(64);
        setCreativeTab(CreativeTabs.DECORATIONS);

        setUnlocalizedName(unlocalizedName);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer entityPlayer, EnumHand hand)
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
                    EntityThrowableSlimeTorch entitythrowst = new EntityThrowableSlimeTorch(world, entityPlayer);
                    entitythrowst.setHeadingFromThrower(entityPlayer, entityPlayer.rotationPitch, entityPlayer.rotationYaw, 0.0F, 1.5F, 1.0F);
                    world.spawnEntityInWorld(entitythrowst);
                    break;
                case CLAY:
                    EntityThrowableClayTorch entitythrowct = new EntityThrowableClayTorch(world, entityPlayer);
                    entitythrowct.setHeadingFromThrower(entityPlayer, entityPlayer.rotationPitch, entityPlayer.rotationYaw, 0.0F, 1.5F, 1.0F);
                    world.spawnEntityInWorld(entitythrowct);
                    break;
                case MAGMA:
                    EntityThrowableMagmaTorch entitythrowmt = new EntityThrowableMagmaTorch(world, entityPlayer);
                    entitythrowmt.setHeadingFromThrower(entityPlayer, entityPlayer.rotationPitch, entityPlayer.rotationYaw, 0.0F, 1.5F, 1.0F);
                    world.spawnEntityInWorld(entitythrowmt);
                    break;
            }
        }

        return super.onItemRightClick(par1ItemStack, world, entityPlayer, hand);
    }

    private final Type _type;

}
