package net.mcft.betterstorage.network.packet;

import java.io.IOException;

import net.mcft.betterstorage.BetterStorage;
import net.mcft.betterstorage.config.GlobalConfig;
import net.mcft.betterstorage.network.AbstractPacket;
import net.mcft.betterstorage.tile.entity.TileEntityLockable;
import net.mcft.betterstorage.utils.WorldUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;

/** Lets other clients know about a lock being hit. */
public class PacketLockHit extends AbstractPacket<PacketLockHit> {

    public int x, y, z;
    public boolean damage;

    public PacketLockHit() {}

    public PacketLockHit(int x, int y, int z, boolean damage) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.damage = damage;
    }

    @Override
    public void encode(PacketBuffer buffer) throws IOException {
        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);
        buffer.writeBoolean(damage);
    }

    @Override
    public void decode(PacketBuffer buffer) throws IOException {
        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();
        damage = buffer.readBoolean();
    }

    @Override
    public void handle(EntityPlayer player) {
        TileEntityLockable lockable = WorldUtils.get(player.worldObj, x, y, z, TileEntityLockable.class);
        damage &= BetterStorage.globalConfig.getBoolean(GlobalConfig.lockBreakable);
        if (lockable != null) lockable.lockAttachment.hit(damage);
    }

}
