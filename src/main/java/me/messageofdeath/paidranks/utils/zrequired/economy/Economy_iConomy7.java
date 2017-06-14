package me.messageofdeath.paidranks.utils.zrequired.economy;

import java.util.UUID;

import com.iCo6.iConomy;
import com.iCo6.system.Accounts;

import me.messageofdeath.paidranks.PaidRanks;

public class Economy_iConomy7 extends Economy {
	
	private Accounts economy;

	public Economy_iConomy7(PaidRanks plugin) {
		super(plugin);
	}

	@Override
	public void withdrawMoney(UUID uuid, double amount) {
		this.economy.get(super.getOfflinePlayer(uuid).getName()).getHoldings().subtract(amount);
	}

	@Override
	public void depositMoney(UUID uuid, double amount) {
		this.economy.get(super.getOfflinePlayer(uuid).getName()).getHoldings().add(amount);
	}

	@Override
	public boolean hasEnoughMoney(UUID uuid, double amount) {
		return this.economy.get(super.getOfflinePlayer(uuid).getName()).getHoldings().hasEnough(amount);
	}

	@Override
	public boolean hasAccount(UUID uuid) {
		return this.economy.exists(super.getOfflinePlayer(uuid).getName());
	}

	@Override
	public void createAccount(UUID uuid) {
		this.economy.create(super.getOfflinePlayer(uuid).getName());
	}

	@Override
	public String getFormat(double amount) {
		return iConomy.format(amount);
	}

	@Override
	public boolean setupEconomy() {
		this.economy = new Accounts();
		return true;
	}
}
