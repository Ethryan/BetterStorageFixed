package net.mcft.betterstorage.inventory;

import net.mcft.betterstorage.utils.StackUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class InventoryItem extends InventoryStacks {

    private final String title;

    protected final EntityPlayer player;
    protected final int slot;

    protected final ItemStack stack;

    public InventoryItem(EntityPlayer player, int size, String title, boolean localized) {
        super(StackUtils.getStackContents(player.getCurrentEquippedItem(), size));
        this.title = title;
        this.player = player;
        this.slot = player.inventory.currentItem;
        this.stack = player.getCurrentEquippedItem()
            .copy();
    }

    @Override
    public String getInventoryName() {
        return title;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void closeInventory() {
        updateStack();
    }

    protected void updateStack() {
        StackUtils.setStackContents(stack, allContents[0]);
        player.inventory.setInventorySlotContents(slot, stack);
    }

}
