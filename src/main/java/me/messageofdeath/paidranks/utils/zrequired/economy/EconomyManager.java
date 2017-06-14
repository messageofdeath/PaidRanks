package me.messageofdeath.paidranks.utils.zrequired.economy;

import me.messageofdeath.paidranks.PaidRanks;

public class EconomyManager {
	
	private PaidRanks plugin;
	private Economy economy;
	
	public EconomyManager(PaidRanks plugin) {
		this.plugin = plugin;
	}

	public void startEconomy() {
		if (this.plugin.getServer().getPluginManager().isPluginEnabled("Vault")) {
			this.economy = new Economy_Vault(this.plugin);
			this.plugin.log("Attempting to hook with Vault!", true);
			this.plugin.log(this.economy.setupEconomy() ? "Successfully hooked with Vault!" : "Failed to hook with Vault!", true);
		}else if(this.plugin.getServer().getPluginManager().isPluginEnabled("TheNewEconomy")) {
			this.economy = new Economy_TheNewEconomy(this.plugin);
			this.plugin.log("Attempting to hook with TNE Economy!", true);
			this.plugin.log(this.economy.setupEconomy() ? "Successfully hooked with TNE Economy!" : "Failed to hook with TNE Economy!", true);
		}else if(this.plugin.getServer().getPluginManager().isPluginEnabled("SQLEconomy")) {
			this.economy = new Economy_SQLEconomy(this.plugin);
			this.plugin.log("Attempting to hook with SQLEconomy!", true);
			this.plugin.log(this.economy.setupEconomy() ? "Successfully hooked with SQLEconomy!" : "Failed to hook with SQLEconomy!", true);
		}else if(this.plugin.getServer().getPluginManager().isPluginEnabled("iConomy")) {
			if(this.plugin.getServer().getPluginManager().getPlugin("iConomy").getDescription().getVersion().equalsIgnoreCase("7.0.6")) {
				this.economy = new Economy_iConomy7(this.plugin);
				this.plugin.log("Attempting to hook with iConomy 7!", true);
				this.plugin.log(this.economy.setupEconomy() ? "Successfully hooked with iConomy 7!" : "Failed to hook with iConomy 7!", true);
			}else{
				this.plugin.logError("Economy", "EconomyManager", "startEconomy()", "This version of iConomy is not supported!");
			}
		}else{
			this.plugin.logError("Economy", "EconomyManager", "startEconomy()", "No supported Economy plugin!");
		}
	}
	
	public boolean isEconomyActivated() {
		return this.economy != null;
	}
	
	public Economy getEconomy() {
		return this.economy;
	}
}
