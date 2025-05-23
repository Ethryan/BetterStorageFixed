package net.mcft.betterstorage.tile;

import java.util.Random;

import net.mcft.betterstorage.BetterStorage;
import net.mcft.betterstorage.item.tile.ItemCardboardBox;
import net.mcft.betterstorage.tile.entity.TileEntityCardboardBox;
import net.mcft.betterstorage.utils.WorldUtils;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileCardboardBox extends TileContainerBetterStorage {

    private IIcon sideIcon;

    public TileCardboardBox() {
        super(Material.wood);

        setHardness(0.8f);
        setStepSound(soundTypeWood);
        setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
    }

    @Override
    public Class<? extends ItemBlock> getItemClass() {
        return ItemCardboardBox.class;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(BetterStorage.MODID + ":" + getTileName());
        sideIcon = iconRegister.registerIcon(BetterStorage.MODID + ":" + getTileName() + "_side");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return ((side < 2) ? blockIcon : sideIcon);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
        TileEntityCardboardBox box = WorldUtils.get(world, x, y, z, TileEntityCardboardBox.class);
        return (((box != null) && (box.color >= 0)) ? box.color : 0x705030);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int quantityDropped(Random rand) {
        return 0;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntityCardboardBox();
    }

}
