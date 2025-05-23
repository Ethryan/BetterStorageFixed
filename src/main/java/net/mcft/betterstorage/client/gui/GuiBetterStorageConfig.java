package net.mcft.betterstorage.client.gui;

import java.util.ArrayList;

import net.mcft.betterstorage.BetterStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import cpw.mods.fml.client.config.DummyConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;

public class GuiBetterStorageConfig extends GuiConfig {

    private static ArrayList<IConfigElement> configElements;

    public GuiBetterStorageConfig(GuiScreen parentScreen) {
        super(parentScreen, configElements, BetterStorage.MODID, true, false, BetterStorage.MODNAME);
    }

    public static void initialize(Minecraft minecraftInstance) {
        configElements = new ArrayList<IConfigElement>();

        ArrayList<IConfigElement> itemCategory = new ArrayList<IConfigElement>();
        ArrayList<IConfigElement> blockCategory = new ArrayList<IConfigElement>();
        ArrayList<IConfigElement> enchantmentCategory = new ArrayList<IConfigElement>();

        itemCategory.addAll(
            BetterStorage.globalConfig.getSettings("item")
                .values());
        blockCategory.addAll(
            BetterStorage.globalConfig.getSettings("tile")
                .values());
        enchantmentCategory.addAll(
            BetterStorage.globalConfig.getSettings("enchantment")
                .values());

        configElements.add(
            new DummyConfigElement.DummyCategoryElement("item", "config.betterstorage.category.item", itemCategory));
        configElements.add(
            new DummyConfigElement.DummyCategoryElement("tile", "config.betterstorage.category.tile", blockCategory));
        configElements.add(
            new DummyConfigElement.DummyCategoryElement(
                "enchantment",
                "config.betterstorage.category.enchantment",
                enchantmentCategory));
        configElements.addAll(
            BetterStorage.globalConfig.getSettings("general")
                .values());
    }

}
