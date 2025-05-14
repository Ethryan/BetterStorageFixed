package net.mcft.betterstorage.tile.entity;

import net.mcft.betterstorage.BetterStorage;
import net.mcft.betterstorage.config.GlobalConfig;
import net.mcft.betterstorage.misc.Constants;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityReinforcedLocker extends TileEntityLocker {

    @Override
    @SideOnly(Side.CLIENT)
    public ResourceLocation getResource() {
        return getMaterial().getLockerResource(isConnected());
    }

    @Override
    public boolean canHaveLock() {
        return true;
    }

    @Override
    public boolean canHaveMaterial() {
        return true;
    }

    @Override
    public void setAttachmentPosition() {
        double x = (mirror ? 13.5 : 2.5);
        double y = (isConnected() ? 0 : 8);
        lockAttachment.setBox(x, y, 0.5, 5, 5, 1);
        lockAttachment.setScale(0.375F, 1.5F);
    }

    @Override
    public int getColumns() {
        return BetterStorage.globalConfig.getInteger(GlobalConfig.reinforcedColumns);
    }

    @Override
    protected String getConnectableName() {
        return Constants.containerReinforcedLocker;
    }
}
