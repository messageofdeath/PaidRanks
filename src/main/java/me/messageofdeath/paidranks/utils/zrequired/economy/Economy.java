package me.messageofdeath.paidranks.utils.zrequired.economy;

import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.messageofdeath.paidranks.PaidRanks;

public abstract class Economy {
	
	public PaidRanks plugin;
	
	public Economy(PaidRanks plugin) {
		this.plugin = plugin;
	}

	public abstract void withdrawMoney(UUID uuid, double amount);
	
	public abstract void depositMoney(UUID uuid, double amount);
	
	public abstract boolean hasEnoughMoney(UUID uuid, double amount);
	
	public abstract boolean hasAccount(UUID uuid);
	
	public abstract void createAccount(UUID uuid);
	
	public abstract String getFormat(double amount);
	
	public Player getPlayer(UUID uuid) {
		return this.plugin.getServer().getPlayer(uuid);
	}
	
	public OfflinePlayer getOfflinePlayer(UUID uuid) {
		return this.plugin.getServer().getOfflinePlayer(uuid);
	}
	
	public abstract boolean setupEconomy();
}
