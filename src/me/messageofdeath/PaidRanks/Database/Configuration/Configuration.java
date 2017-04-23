package me.messageofdeath.PaidRanks.Database.Configuration;

import me.messageofdeath.PaidRanks.PaidRanks;
import me.messageofdeath.PaidRanks.Utils.zRequired.Database.YamlDatabase;

public class Configuration {
	private PaidRanks instance;
	private YamlDatabase config;
	private boolean isGlobal;

	public Configuration(PaidRanks instance) {
		this.instance = instance;
		this.config = new YamlDatabase(this.instance, "config");
	}

	public void initConfiguration() {
		this.config.onStartUp();
	}

	public void loadConfiguration() {
		this.isGlobal = this.config.getBoolean("RanksGlobal", true);
	}

	public boolean isGlobal() {
		return this.isGlobal;
	}
}
