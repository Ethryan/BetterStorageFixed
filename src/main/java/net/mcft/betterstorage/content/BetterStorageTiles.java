package net.mcft.betterstorage.content;

import net.mcft.betterstorage.addon.Addon;
import net.mcft.betterstorage.config.GlobalConfig;
import net.mcft.betterstorage.tile.TileBackpack;
import net.mcft.betterstorage.tile.TileCardboardBox;
import net.mcft.betterstorage.tile.TileCraftingStation;
import net.mcft.betterstorage.tile.TileEnderBackpack;
import net.mcft.betterstorage.tile.TileFlintBlock;
import net.mcft.betterstorage.tile.TileLockableDoor;
import net.mcft.betterstorage.tile.TileLocker;
import net.mcft.betterstorage.tile.TilePresent;
import net.mcft.betterstorage.tile.TileReinforcedChest;
import net.mcft.betterstorage.tile.TileReinforcedLocker;
import net.mcft.betterstorage.tile.crate.TileCrate;
import net.mcft.betterstorage.tile.stand.TileArmorStand;
import net.mcft.betterstorage.utils.MiscUtils;

public final class BetterStorageTiles {

    public static TileCrate crate;
    public static TileReinforcedChest reinforcedChest;
    public static TileLocker locker;
    public static TileArmorStand armorStand;
    public static TileBackpack backpack;
    public static TileEnderBackpack enderBackpack;
    public static TileCardboardBox cardboardBox;
    public static TileReinforcedLocker reinforcedLocker;
    public static TileCraftingStation craftingStation;
    public static TileFlintBlock flintBlock;
    public static TileLockableDoor lockableDoor;
    public static TilePresent present;

    private BetterStorageTiles() {}

    public static void initialize() {

        crate = MiscUtils.conditionalNew(TileCrate.class, GlobalConfig.crateEnabled);
        reinforcedChest = MiscUtils.conditionalNew(TileReinforcedChest.class, GlobalConfig.reinforcedChestEnabled);
        locker = MiscUtils.conditionalNew(TileLocker.class, GlobalConfig.lockerEnabled);
        armorStand = MiscUtils.conditionalNew(TileArmorStand.class, GlobalConfig.armorStandEnabled);
        backpack = MiscUtils.conditionalNew(TileBackpack.class, GlobalConfig.backpackEnabled);
        enderBackpack = MiscUtils.conditionalNew(TileEnderBackpack.class, GlobalConfig.enderBackpackEnabled);
        cardboardBox = MiscUtils.conditionalNew(TileCardboardBox.class, GlobalConfig.cardboardBoxEnabled);
        reinforcedLocker = MiscUtils.conditionalNew(TileReinforcedLocker.class, GlobalConfig.reinforcedLockerEnabled);
        craftingStation = MiscUtils.conditionalNew(TileCraftingStation.class, GlobalConfig.craftingStationEnabled);
        flintBlock = MiscUtils.conditionalNew(TileFlintBlock.class, GlobalConfig.flintBlockEnabled);
        lockableDoor = MiscUtils.conditionalNew(TileLockableDoor.class, GlobalConfig.lockableDoorEnabled);
        present = MiscUtils.conditionalNew(TilePresent.class, GlobalConfig.presentEnabled);

        Addon.initializeTilesAll();

    }

}
