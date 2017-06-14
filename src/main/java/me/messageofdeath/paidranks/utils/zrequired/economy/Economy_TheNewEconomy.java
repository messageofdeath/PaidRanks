package me.messageofdeath.paidranks.utils.zrequired.economy;

import java.util.UUID;

import com.github.tnerevival.TNE;
import com.github.tnerevival.core.api.TNEAPI;

import me.messageofdeath.paidranks.PaidRanks;

public class Economy_TheNewEconomy extends Economy {
	
	private TNEAPI economy;

	public Economy_TheNewEconomy(PaidRanks plugin) {
		super(plugin);
	}

	@Override
	public void withdrawMoney(UUID uuid, double amount) {
		this.economy.fundsRemove(this.plugin.getServer().getOfflinePlayer(uuid).getName(), amount);
	}

	@Override
	public void depositMoney(UUID uuid, double amount) {
		this.economy.fundsAdd(this.plugin.getServer().getOfflinePlayer(uuid).getName(), amount);
	}

	@Override
	public boolean hasEnoughMoney(UUID uuid, double amount) {
		return this.economy.fundsHas(this.plugin.getServer().getOfflinePlayer(uuid).getName(), amount);
	}

	@Override
	public boolean hasAccount(UUID uuid) {
		return this.economy.accountExists(this.plugin.getServer().getOfflinePlayer(uuid).getName());
	}

	@Override
	public void createAccount(UUID uuid) {
		this.economy.createAccount(this.plugin.getServer().getOfflinePlayer(uuid).getName());
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
