package net.mcft.betterstorage.addon.minetweaker;

import net.mcft.betterstorage.api.crafting.ICraftingSource;
import net.mcft.betterstorage.api.crafting.StationCrafting;

public class MTStationCrafting extends StationCrafting {

    public MTStationCrafting(StationCrafting base) {
        super(base.getOutput(), base.getCraftRequirements(), base.getRequiredExperience(), base.getCraftingTime());
    }

    @Override
    public void craft(ICraftingSource source) {
        RecipeInputIngredient.player = source.getPlayer();
        super.craft(source);
    }

}
