package net.mcft.betterstorage.item.locking;

import net.mcft.betterstorage.api.IContainerItem;
import net.mcft.betterstorage.api.lock.IKey;
import net.mcft.betterstorage.container.ContainerKeyring;
import net.mcft.betterstorage.item.ItemBetterStorage;
import net.mcft.betterstorage.misc.Constants;
import net.mcft.betterstorage.utils.PlayerUtils;
import net.mcft.betterstorage.utils.StackUtils;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemKeyring extends ItemBetterStorage implements IKey, IContainerItem {

    private IIcon[] icons = new IIcon[4];

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        for (int i = 0; i < icons.length; i++) icons[i] = iconRegister.registerIcon(Constants.modId + ":keyring_" + i);
        itemIcon = icons[0];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        return icons[Math.min(damage, icons.length - 1)];
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (world.isRemote || !player.isSneaking()) return stack;
        String title = StackUtils.get(stack, "", "display", "Name");
        int protectedSlot = player.inventory.currentItem;
        Container container = new ContainerKeyring(player, title, protectedSlot);
        PlayerUtils.openGui(player, Constants.containerKeyring, protectedSlot, 1, title, container);
        return stack;
    }

    // IKey implementation

    @Override
    public boolean isNormalKey() {
        return false;
    }

    @Override
    public boolean unlock(ItemStack keyring, ItemStack lock, boolean useAbility) {

        // Goes through all the keys in the keyring,
        // returns if any of the keys fit in the lock.

        ItemStack[] items = StackUtils.getStackContents(keyring, 9);
        for (ItemStack key : items) {
            if (key == null) continue;
            IKey keyType = (IKey) key.getItem();
            if (keyType.unlock(key, lock, false)) return true;
        }

        return false;

    }

    @Override
    public boolean canApplyEnchantment(ItemStack key, Enchantment enchantment) {
        return false;
    }

    // IContainerItem implementation

    @Override
    public ItemStack[] getContainerItemContents(ItemStack container) {
        return StackUtils.getStackContents(container, 9);
    }

    @Override
    public boolean canBeStoredInContainerItem(ItemStack item) {
        return true;
    }

}
