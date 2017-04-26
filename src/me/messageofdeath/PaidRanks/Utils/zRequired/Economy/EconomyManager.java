package me.messageofdeath.PaidRanks.Utils.zRequired.Economy;

import me.messageofdeath.PaidRanks.PaidRanks;

public class EconomyManager {
	
	private PaidRanks plugin;
	private Economy economy;
	
	public EconomyManager(PaidRanks plugin) {
		this.plugin = plugin;
	}

	public void startEconomy() {
		if(this.plugin.getServer().getPluginManager().isPluginEnabled("TheNewEconomy")) {
			this.economy = new Economy_TheNewEconomy(this.plugin);
			this.plugin.log("Attempting to hook with TNE Economy!", true);
			this.plugin.log(this.economy.setupEconomy() ? "Successfully hooked with TNE Economy!" : "Failed to hook with TNE Economy!", true);
		}else if(this.plugin.getServer().getPluginManager().isPluginEnabled("SQLEconomy")) {
			this.economy = new Economy_SQLEconomy(this.plugin);
			this.plugin.log("Attempting to hook with SQLEconomy!", true);
			this.plugin.log(this.economy.setupEconomy() ? "Successfully hooked with SQLEconomy!" : "Failed to hook with SQLEconomy!", true);
		}else{
			this.plugin.logError("EconomyManager", "Economy", "startEconomy()", "No supported Economy plugin!");
		}
	}
	
	public boolean isEconomyActivated() {
		return this.economy != null;
	}
	
	public Economy getEconomy() {
		return this.economy;
	}
}
