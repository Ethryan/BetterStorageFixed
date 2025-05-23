package net.mcft.betterstorage.misc;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class Region {

    public int minX, minY, minZ;
    public int maxX, maxY, maxZ;

    public int width() {
        return maxX - minX + 1;
    }

    public int depth() {
        return maxY - minY + 1;
    }

    public int height() {
        return maxZ - minZ + 1;
    }

    public int volume() {
        return width() * depth() * height();
    }

    public Region(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        set(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public Region(TileEntity entity) {
        this(entity.xCoord, entity.yCoord, entity.zCoord, entity.xCoord, entity.yCoord, entity.zCoord);
    }

    public void set(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
    }

    public void expand(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        this.minX -= minX;
        this.minY -= minY;
        this.minZ -= minZ;
        this.maxX += maxX;
        this.maxY += maxY;
        this.maxZ += maxZ;
    }

    public void expand(int size) {
        expand(size, size, size, size, size, size);
    }

    public boolean contains(int x, int y, int z) {
        return ((x >= minX) && (y >= minY) && (z >= minZ) && (x <= maxX) && (y <= maxY) && (z <= maxZ));
    }

    public boolean contains(TileEntity entity) {
        return contains(entity.xCoord, entity.yCoord, entity.zCoord);
    }

    public void expandToContain(int x, int y, int z) {
        minX = Math.min(minX, x);
        minY = Math.min(minY, y);
        minZ = Math.min(minZ, z);
        maxX = Math.max(maxX, x);
        maxY = Math.max(maxY, y);
        maxZ = Math.max(maxZ, z);
    }

    public void expandToContain(TileEntity entity) {
        expandToContain(entity.xCoord, entity.yCoord, entity.zCoord);
    }

    public NBTTagCompound toCompound() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setInteger("minX", minX);
        compound.setInteger("minY", minY);
        compound.setInteger("minZ", minZ);
        compound.setInteger("maxX", maxX);
        compound.setInteger("maxY", maxY);
        compound.setInteger("maxZ", maxZ);
        return compound;
    }

    public static Region fromCompound(NBTTagCompound compound) {
        int minX = compound.getInteger("minX");
        int minY = compound.getInteger("minY");
        int minZ = compound.getInteger("minZ");
        int maxX = compound.getInteger("maxX");
        int maxY = compound.getInteger("maxY");
        int maxZ = compound.getInteger("maxZ");
        return new Region(minX, minY, minZ, maxX, maxY, maxZ);
    }

    @Override
    public Region clone() {
        return new Region(minX, minY, minZ, maxX, maxY, maxZ);
    }

    @Override
    public String toString() {
        return "[ " + minX + "," + minY + "," + minZ + " : " + maxX + "," + maxY + "," + maxZ + " ]";
    }

}
