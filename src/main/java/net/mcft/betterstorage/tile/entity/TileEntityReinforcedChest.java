package net.mcft.betterstorage.tile.entity;

import net.mcft.betterstorage.BetterStorage;
import net.mcft.betterstorage.config.GlobalConfig;
import net.mcft.betterstorage.misc.Constants;
import net.mcft.betterstorage.utils.WorldUtils;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityReinforcedChest extends TileEntityLockable {

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return WorldUtils.getAABB(this, 0, 0, 0, 1, 1, 1);
    }

    @SideOnly(Side.CLIENT)
    public ResourceLocation getResource() {
        return getMaterial().getChestResource(isConnected());
    }

    @Override
    public void setAttachmentPosition() {
        double x = (!isConnected() ? 8
            : (((getOrientation() == ForgeDirection.WEST) || (getOrientation() == ForgeDirection.SOUTH)) ? 0 : 16));
        lockAttachment.setBox(x, 6.5, 0.5, 7, 7, 1);
    }

    // TileEntityContainer stuff

    @Override
    public int getColumns() {
        return BetterStorage.globalConfig.getInteger(GlobalConfig.reinforcedColumns);
    }

    // TileEntityConnactable stuff

    private static ForgeDirection[] neighbors = { ForgeDirection.EAST, ForgeDirection.NORTH, ForgeDirection.WEST,
        ForgeDirection.SOUTH };

    @Override
    protected String getConnectableName() {
        return Constants.containerReinforcedChest;
    }

    @Override
    public ForgeDirection[] getPossibleNeighbors() {
        return neighbors;
    }

    @Override
    public boolean canConnect(TileEntityConnectable connectable) {
        return ((connectable instanceof TileEntityReinforcedChest) && super.canConnect(connectable)
            && (((xCoord != connectable.xCoord)
                && ((getOrientation() == ForgeDirection.SOUTH) || (getOrientation() == ForgeDirection.NORTH)))
                || ((zCoord != connectable.zCoord)
                    && ((getOrientation() == ForgeDirection.EAST) || (getOrientation() == ForgeDirection.WEST)))));
    }

}
