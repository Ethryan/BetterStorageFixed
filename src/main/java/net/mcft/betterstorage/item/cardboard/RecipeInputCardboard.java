package net.mcft.betterstorage.item.cardboard;

import java.util.List;

import net.mcft.betterstorage.api.crafting.ContainerInfo;
import net.mcft.betterstorage.api.crafting.IRecipeInput;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RecipeInputCardboard implements IRecipeInput {

    public static final RecipeInputCardboard instance = new RecipeInputCardboard();

    private RecipeInputCardboard() {}

    @Override
    public int getAmount() {
        return 1;
    }

    @Override
    public boolean matches(ItemStack stack) {
        return (stack.getItem() instanceof ICardboardItem);
    }

    @Override
    public void craft(ItemStack input, ContainerInfo containerInfo) {}

    @Override
    @SideOnly(Side.CLIENT)
    public List<ItemStack> getPossibleMatches() {
        return null;
    }

}
