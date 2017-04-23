package me.messageofdeath.PaidRanks.Utils.zRequired.Economy;

import java.util.UUID;

import me.messageofdeath.PaidRanks.PaidRanks;

public abstract class Economy {
	
	public PaidRanks plugin;
	
	public Economy(PaidRanks plugin) {
		this.plugin = plugin;
	}

	public abstract void withdrawMoney(UUID player, double amount);
	
	public abstract void depositMoney(UUID player, double amount);
	
	public abstract boolean hasEnoughMoney(UUID player, double amount);
	
	public abstract boolean hasAccount(UUID player);
	
	public abstract void createAccount(UUID player);
	
	public abstract String getFormat(double amount);
	
	public abstract boolean setupEconomy();
}
