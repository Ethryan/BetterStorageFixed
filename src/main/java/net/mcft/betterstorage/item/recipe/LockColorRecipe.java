package net.mcft.betterstorage.item.recipe;

import java.util.List;

import net.mcft.betterstorage.content.BetterStorageItems;
import net.mcft.betterstorage.item.ItemBetterStorage;
import net.mcft.betterstorage.utils.DyeUtils;
import net.mcft.betterstorage.utils.InventoryUtils;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LockColorRecipe extends ComboRecipe {

    private LockColorRecipe(ItemStack[] recipe, ItemStack result) {
        super(1, 1, recipe, result);

    }

    @Override
    public boolean matches(InventoryCrafting crafting, World world) {
        if (!super.matches(crafting, world)) return false;
        // Just a lock in the crafting matrix is not a valid recipe.
        int items = 0;
        for (int i = 0; i < crafting.getSizeInventory(); i++) if (crafting.getStackInSlot(i) != null) items++;
        return (items > 1);
    }

    @Override
    public boolean checkShapelessItems(InventoryCrafting crafting, List<ItemStack> shapelessItems) {
        // Not a valid recipe if there's more than one key.
        List<ItemStack> locks = InventoryUtils.findItems(crafting, BetterStorageItems.lock);
        if (locks.size() > 1) return false;
        // Not a valid recipe if any shapeless item
        // other than a lock or dye is used.
        for (ItemStack stack : shapelessItems) {
            if ((stack.getItem() != BetterStorageItems.lock) && (!DyeUtils.isDye(stack))) return false;
        }
        return true;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting crafting) {
        ItemStack lock = InventoryUtils.findItem(crafting, BetterStorageItems.lock);
        List<ItemStack> dyes = InventoryUtils.findDyes(crafting);
        ItemStack result = lock.copy();
        // Apply color.
        if (dyes.size() > 0) {
            int color = DyeUtils.getColorFromDyes(dyes);
            if (dyes.size() >= 8) ItemBetterStorage.setFullColor(result, color);
            else ItemBetterStorage.setColor(result, color);
        }
        return result;
    }

    public static LockColorRecipe createLockColorRecipe() {
        ItemStack lock = new ItemStack(BetterStorageItems.lock);
        return new LockColorRecipe(new ItemStack[] { lock }, lock);
    }

}
