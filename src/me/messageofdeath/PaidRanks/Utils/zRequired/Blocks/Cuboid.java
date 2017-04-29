package me.messageofdeath.PaidRanks.Utils.zRequired.Blocks;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Cuboid {
	
	private Location loc1;
	private Location loc2;
	private String worldName;

	public Cuboid(Vector a, Vector b) {
		int x1 = Math.min(a.getLocation().getBlockX(), b.getLocation().getBlockX());
		int y1 = Math.min(a.getLocation().getBlockY(), b.getLocation().getBlockY());
		int z1 = Math.min(a.getLocation().getBlockZ(), b.getLocation().getBlockZ());
		int x2 = Math.max(a.getLocation().getBlockX(), b.getLocation().getBlockX());
		int y2 = Math.max(a.getLocation().getBlockY(), b.getLocation().getBlockY());
		int z2 = Math.max(a.getLocation().getBlockZ(), b.getLocation().getBlockZ());
		this.worldName = a.getWorldName();
		this.loc1 = new Location(Bukkit.getWorld(this.worldName), x1, y1, z1);
		this.loc2 = new Location(Bukkit.getWorld(this.worldName), x2, y2, z2);
	}

	public Location getMinimumPoint() {
		return this.loc1;
	}

	public Location getMaximumPoint() {
		return this.loc2;
	}

	public void updateLocations() {
		this.loc1 = new Location(Bukkit.getWorld(this.worldName), this.loc1.getBlockX(), this.loc1.getBlockY(), this.loc1.getBlockZ());
		this.loc2 = new Location(Bukkit.getWorld(this.worldName), this.loc2.getBlockX(), this.loc2.getBlockY(), this.loc2.getBlockZ());
	}

	public boolean contains(Location loc) {
		return (loc.getBlockX() >= this.loc1.getBlockX()) && (loc.getBlockX() <= this.loc2.getBlockX())
				&& (loc.getBlockY() >= this.loc1.getBlockY()) && (loc.getBlockY() <= this.loc2.getBlockY())
				&& (loc.getBlockZ() >= this.loc1.getBlockZ()) && (loc.getBlockZ() <= this.loc2.getBlockZ());
	}

	public static String getCuboidToString(Cuboid cuboid) {
		Location min = cuboid.getMinimumPoint();
		Location max = cuboid.getMaximumPoint();
		return Vector.getVectorToString(new Vector(cuboid.worldName, min.getBlockX(), min.getBlockY(), min.getBlockZ()))+ ";" + 
				Vector.getVectorToString(new Vector(cuboid.worldName, max.getBlockX(), max.getBlockY(), max.getBlockZ()));
	}

	public static Cuboid getStringToCuboid(String cube) {
		String[] split = cube.split(";");
		if (split.length == 2) {
			return new Cuboid(Vector.getStringToVector(split[0]), Vector.getStringToVector(split[1]));
		}
		return null;
	}
}
