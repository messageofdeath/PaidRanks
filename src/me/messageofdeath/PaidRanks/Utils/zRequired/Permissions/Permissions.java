package me.messageofdeath.PaidRanks.Utils.zRequired.Permissions;

import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.messageofdeath.PaidRanks.PaidRanks;

public abstract class Permissions {
	
	public PaidRanks plugin;
	
	public Permissions(PaidRanks plugin) {
		this.plugin = plugin;
	}

	public abstract void addGroup(UUID uuid, String world, String group);
	
	public abstract void removeGroup(UUID uuid, String world, String group);
	
	public void setGroup(UUID uuid, String world, String oldGroup, String newGroup) {
		removeGroup(uuid, world, oldGroup);
		addGroup(uuid, world, newGroup);
	}
	
	public abstract boolean hasGroup(UUID uuid, String world, String group);
	
	public abstract String[] getGroups(UUID uuid, String world);
	
	public abstract int amountOfGroups(UUID uuid);
	
	public int amountOfGroups(Player player) {
		return amountOfGroups(player.getUniqueId());
	}
	
	public int amountOfGroups(OfflinePlayer player) {
		return amountOfGroups(player.getUniqueId());
	}
	
	public abstract boolean setupPermissions();
}
