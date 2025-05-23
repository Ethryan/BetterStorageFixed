package net.mcft.betterstorage.addon.thaumcraft;

import net.mcft.betterstorage.tile.TileReinforcedChest;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileThaumiumChest extends TileReinforcedChest {

    public TileThaumiumChest() {
        super(Material.iron);

        setHardness(12.0f);
        setResistance(35.0f);
        setStepSound(soundTypeMetal);

        setHarvestLevel("pickaxe", 2);
    }

    @Override
    public boolean hasMaterial() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderType() {
        return ThaumcraftAddon.thaumiumChestRenderId;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntityThaumiumChest();
    }

}
