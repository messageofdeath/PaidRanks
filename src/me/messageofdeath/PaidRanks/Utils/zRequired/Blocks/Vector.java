package me.messageofdeath.PaidRanks.Utils.zRequired.Blocks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class Vector {
	
	private String worldName;
	private Location loc;

	public Vector(String world, int x, int y, int z, float yaw, float pitch) {
		this.loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
		this.worldName = world;
	}

	public Vector(String world, int x, int y, int z) {
		this(world, x, y, z, 0.0F, 0.0F);
	}

	public Vector(String world, double x, double y, double z, float yaw, float pitch) {
		this.loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
		this.worldName = world;
	}

	public Vector(String world, double x, double y, double z) {
		this(world, x, y, z, 0.0F, 0.0F);
	}

	public Vector(Location loc) {
		this.loc = loc;
		this.worldName = loc.getWorld().getName();
	}

	public void updateLocation() {
		this.loc = new Location(Bukkit.getWorld(getWorldName()), this.loc.getX(), this.loc.getY(), this.loc.getZ(), this.loc.getYaw(), this.loc.getPitch());
	}

	public Location getLocation() {
		return this.loc;
	}

	public double getX() {
		return this.loc.getX();
	}

	public double getY() {
		return this.loc.getY();
	}

	public double getZ() {
		return this.loc.getZ();
	}

	public float getYaw() {
		return this.loc.getYaw();
	}

	public float getPitch() {
		return this.loc.getPitch();
	}

	public int getBlockX() {
		return this.loc.getBlockX();
	}

	public int getBlockY() {
		return this.loc.getBlockY();
	}

	public int getBlockZ() {
		return this.loc.getBlockZ();
	}

	public Block getBlock() {
		return this.loc.getBlock();
	}

	public String getWorldName() {
		return this.worldName;
	}

	public World getWorld() {
		return this.loc.getWorld();
	}

	public boolean isWithinRadius(Vector vec, int radius) {
		updateLocation();
		vec.updateLocation();
		double radiusSqt = radius * radius;
		return getLocation().distanceSquared(vec.getLocation()) <= radiusSqt;
	}

	public static boolean compareBlock(Vector a, Vector b) {
		return (a.getBlockX() == b.getBlockX()) && (a.getBlockY() == b.getBlockY()) && (a.getBlockZ() == b.getBlockZ()) && (a.getWorldName().equalsIgnoreCase(b.getWorldName()));
	}

	public static boolean compareExact(Vector a, Vector b) {
		return (a.getX() == b.getX()) && (a.getY() == b.getY()) && (a.getZ() == b.getZ()) && (a.getWorldName().equalsIgnoreCase(b.getWorldName()));
	}

	public static Vector getStringToVector(String a) {
		if (!a.isEmpty()) {
			String[] b = a.split(",");
			if (b.length == 6) {
				return new Vector(b[0], Double.parseDouble(b[1]), Double.parseDouble(b[2]), Double.parseDouble(b[3]), Float.parseFloat(b[4]), Float.parseFloat(b[5]));
			}
			if (b.length == 4) {
				return new Vector(b[0], Double.parseDouble(b[1]), Double.parseDouble(b[2]), Double.parseDouble(b[3]));
			}
		}
		return null;
	}

	public static String getVectorToString(Vector a) {
		return a.getWorldName() + "," + a.getX() + "," + a.getY() + "," + a.getZ() + "," + a.getYaw() + "," + a.getPitch();
	}
}
