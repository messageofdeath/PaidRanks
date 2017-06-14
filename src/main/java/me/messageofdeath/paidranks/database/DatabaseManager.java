package me.messageofdeath.paidranks.database;

import me.messageofdeath.paidranks.database.databases.RankDatabase;
import me.messageofdeath.paidranks.database.language.LanguageConfiguration;
import me.messageofdeath.paidranks.PaidRanks;

public class DatabaseManager {
	
	private PaidRanks instance;
	private LanguageConfiguration languageConfiguration;
	private RankDatabase rankDatabase;

	public DatabaseManager(PaidRanks instance) {
		this.instance = instance;
	}

	public void onStartUp() {
		initLanguage();
		initRanks();
	}

	public void onShutDown() {
		saveDatabases();
	}
	public LanguageConfiguration getLanguageConfiguration() {
		return this.languageConfiguration;
	}

	public RankDatabase getRankDatabase() {
		return this.rankDatabase;
	}

	public void initLanguage() {
		this.languageConfiguration = new LanguageConfiguration(this.instance);
		this.languageConfiguration.initConfiguration();
		this.languageConfiguration.loadConfiguration();
	}

	public void initRanks() {
		this.rankDatabase = new RankDatabase(this.instance);
		this.rankDatabase.initDatabase();
		this.rankDatabase.loadDatabase();
	}

	public void saveDatabases() {
		this.rankDatabase.saveDatabase();
	}
}
