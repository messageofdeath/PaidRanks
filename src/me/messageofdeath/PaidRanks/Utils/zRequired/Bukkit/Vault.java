package me.messageofdeath.PaidRanks.Utils.zRequired.Bukkit;

import java.util.UUID;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import me.messageofdeath.PaidRanks.PaidRanks;

@Deprecated
public class Vault {
	private Economy economy;
	private Permission permission;
	private PaidRanks plugin;

	public Vault(PaidRanks plugin) {
		this.plugin = plugin;
		setupEconomy();
		setupPermission();
	}

	public void withdrawMoney(UUID uuid, double amount) {
		EconomyResponse s = this.economy.withdrawPlayer(getOfflinePlayer(uuid), amount);
		if(!s.transactionSuccess()) {
			plugin.logError("Economy", "Vault", "withdrawMoney(UUID, double)", "Failed to withdraw "+amount+" from " + getOfflinePlayer(uuid).getName());
		}
	}

	public void depositMoney(UUID uuid, double amount) {
		EconomyResponse s = this.economy.depositPlayer(getOfflinePlayer(uuid), amount);
		if(!s.transactionSuccess()) {
			plugin.logError("Economy", "Vault", "depositMoney(UUID, double)", "Failed to deposit "+amount+" to " + getOfflinePlayer(uuid).getName());
		}
	}

	public boolean hasEnoughMoney(UUID uuid, double amount) {
		return this.economy.has(getOfflinePlayer(uuid), amount);
	}

	public boolean hasAccount(UUID uuid) {
		return this.economy.hasAccount(getOfflinePlayer(uuid));
	}

	public void createAccount(UUID uuid) {
		this.economy.createPlayerAccount(getOfflinePlayer(uuid));
	}

	public String getFormat(double amount) {
		return this.economy.format(amount);
	}

	public void addGroup(UUID uuid, String world, String group) {
		this.permission.playerAddGroup(world, getOfflinePlayer(uuid), group);
	}

	public void removeGroup(UUID uuid, String world, String group) {
		this.permission.playerRemoveGroup(world, getOfflinePlayer(uuid), group);
	}

	public void setGroup(UUID uuid, String world, String oldGroup, String newGroup) {
		removeGroup(uuid, world, oldGroup);
		addGroup(uuid, world, newGroup);
	}

	public boolean hasGroup(UUID uuid, String world, String group) {
		return this.permission.playerInGroup(world, getOfflinePlayer(uuid), group);
	}

	public String[] getGroups(UUID uuid, String world) {
		return this.permission.getPlayerGroups(world, getOfflinePlayer(uuid));
	}

	public int amountOfGroups(Player player) {
		return this.permission.getPlayerGroups(player).length;
	}

	public OfflinePlayer getOfflinePlayer(UUID uuid) {
		return Bukkit.getOfflinePlayer(uuid);
	}

	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = this.plugin.getServer().getServicesManager()
				.getRegistration(Economy.class);
		this.economy = (economyProvider != null ? (Economy) economyProvider.getProvider() : null);
		return this.economy != null;
	}

	private boolean setupPermission() {
		RegisteredServiceProvider<Permission> permissionProvider = this.plugin.getServer().getServicesManager()
				.getRegistration(Permission.class);
		this.permission = (permissionProvider != null ? (Permission) permissionProvider.getProvider() : null);
		return this.permission != null;
	}
}
