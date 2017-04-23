package me.messageofdeath.PaidRanks.Utils.zRequired.Economy;

import java.util.UUID;

import com.github.tnerevival.TNE;
import com.github.tnerevival.core.api.TNEAPI;

import me.messageofdeath.PaidRanks.PaidRanks;

public class Economy_TheNewEconomy extends Economy {
	
	private TNEAPI economy;
	
	public Economy_TheNewEconomy(PaidRanks plugin) {
		super(plugin);
		this.setupEconomy();
	}

	@Override
	public void withdrawMoney(UUID player, double amount) {
		this.economy.fundsRemove(this.plugin.getServer().getOfflinePlayer(player).getName(), amount);
	}

	@Override
	public void depositMoney(UUID player, double amount) {
		this.economy.fundsAdd(this.plugin.getServer().getOfflinePlayer(player).getName(), amount);
	}

	@Override
	public boolean hasEnoughMoney(UUID player, double amount) {
		return this.economy.fundsHas(this.plugin.getServer().getOfflinePlayer(player).getName(), amount);
	}

	@Override
	public boolean hasAccount(UUID player) {
		return this.economy.accountExists(this.plugin.getServer().getOfflinePlayer(player).getName());
	}

	@Override
	public void createAccount(UUID player) {
		this.economy.createAccount(this.plugin.getServer().getOfflinePlayer(player).getName());
	}

	@Override
	public String getFormat(double amount) {
		return this.economy.format(amount);
	}

	@Override
	public boolean setupEconomy() {
		TNE tne = (TNE)super.plugin.getServer().getPluginManager().getPlugin("TheNewEconomy");
		this.economy = tne.api();
		return this.economy != null;
	}
}
