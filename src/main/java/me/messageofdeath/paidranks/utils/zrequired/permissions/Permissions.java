package me.messageofdeath.paidranks.utils.zrequired.permissions;

import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.messageofdeath.paidranks.PaidRanks;

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
	
	public int amountOfGroups(UUID uuid, String world) {
		return getGroups(uuid, world).length;
	}
	
	public int amountOfGroups(Player player, String world) {
		return amountOfGroups(player.getUniqueId(), world);
	}
	
	public int amountOfGroups(OfflinePlayer player, String world) {
		return amountOfGroups(player.getUniqueId(), world);
	}

	public Player getPlayer(UUID uuid) {
		return this.plugin.getServer().getPlayer(uuid);
	}

	public OfflinePlayer getOfflinePlayer(UUID uuid) {
		return this.plugin.getServer().getOfflinePlayer(uuid);
	}
	
	public abstract boolean setupPermissions();
}
