package net.mcft.betterstorage.tile;

import net.mcft.betterstorage.BetterStorage;
import net.mcft.betterstorage.config.GlobalConfig;
import net.mcft.betterstorage.tile.entity.TileEntityCraftingStation;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileCraftingStation extends TileContainerBetterStorage {

    private IIcon topIcon;
    private IIcon bottomIconDisabled;
    private IIcon bottomIconEnabled;

    public TileCraftingStation() {
        super(Material.iron);

        setHardness(1.5f);
        setStepSound(soundTypeStone);
    }

    @Override
    public boolean isNormalCube() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(BetterStorage.MODID + ":" + getTileName());
        topIcon = iconRegister.registerIcon(BetterStorage.MODID + ":" + getTileName() + "_top");
        bottomIconDisabled = iconRegister.registerIcon(BetterStorage.MODID + ":" + getTileName() + "_bottom_0");
        bottomIconEnabled = iconRegister.registerIcon(BetterStorage.MODID + ":" + getTileName() + "_bottom_1");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return ((side == 0)
            ? (BetterStorage.globalConfig.getBoolean(GlobalConfig.enableStationAutoCrafting) ? bottomIconEnabled
                : bottomIconDisabled)
            : ((side == 1) ? topIcon : blockIcon));
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntityCraftingStation();
    }

}
