package net.mcft.copy.betterstorage.item.tile;

import net.mcft.copy.betterstorage.content.BetterStorageTiles;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTileBetterStorage extends ItemBlock {

    public ItemTileBetterStorage(Block block) {
        super(block);
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
        float hitX, float hitY, float hitZ, int metadata) {
        if (!super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata)) return false;
        BetterStorageTiles.crate.onBlockPlacedExtended(world, x, y, z, side, hitX, hitY, hitZ, player, stack);
        return true;
    }

}
