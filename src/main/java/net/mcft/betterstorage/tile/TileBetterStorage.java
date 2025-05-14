package net.mcft.betterstorage.tile;

import net.mcft.betterstorage.BetterStorage;
import net.mcft.betterstorage.utils.MiscUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import cpw.mods.fml.common.registry.GameRegistry;

public class TileBetterStorage extends Block {

    private String name;

    public TileBetterStorage(Material material) {

        super(material);

        setCreativeTab(BetterStorage.creativeTab);

        setBlockName(BetterStorage.MODID + "." + getTileName());
        registerBlock();

    }

    /** Returns the name of this tile, for example "craftingStation". */
    public String getTileName() {
        return ((name != null) ? name : (name = MiscUtils.getName(this)));
    }

    /** Returns the item class used for this block. */
    protected Class<? extends ItemBlock> getItemClass() {
        return ItemBlock.class;
    }

    /** Registers the block in the GameRegistry. */
    protected void registerBlock() {
        Class<? extends Item> itemClass = getItemClass();

        if (itemClass != null) {
            GameRegistry
                .registerBlock(this, (Class<? extends ItemBlock>) itemClass, getTileName(), BetterStorage.MODID);
        } else {
            GameRegistry.registerBlock(this, null, getTileName(), BetterStorage.MODID);
        }
    }

}
