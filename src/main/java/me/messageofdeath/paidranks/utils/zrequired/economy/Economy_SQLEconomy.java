package me.messageofdeath.paidranks.utils.zrequired.economy;

import java.util.UUID;

import io.github.andrewward2001.sqlecon.SQLEconomy;
import io.github.andrewward2001.sqlecon.SQLEconomyAPI;
import me.messageofdeath.paidranks.PaidRanks;

public class Economy_SQLEconomy extends Economy {
	
	private SQLEconomyAPI economy;

	public Economy_SQLEconomy(PaidRanks plugin) {
		super(plugin);
	}

	@Override
	public void withdrawMoney(UUID uuid, double amount) {
		this.economy.withdraw(uuid, amount);
	}

	@Override
	public void depositMoney(UUID uuid, double amount) {
		this.economy.give(uuid, amount);
	}

	@Override
	public boolean hasEnoughMoney(UUID uuid, double amount) {
		return this.economy.hasEnough(uuid, amount);
	}

	@Override
	public boolean hasAccount(UUID uuid) {
		return this.economy.accountExists(uuid);
	}

	@Override
	public void createAccount(UUID uuid) {
		this.economy.createAccount(super.getOfflinePlayer(uuid));
	}

	@Override
	public String getFormat(double amount) {
		return amount + " " + SQLEconomy.moneyUnit;
	}

	@Override
	public boolean setupEconomy() {
		this.economy = SQLEconomy.getAPI();
		return true;
	}
}
