package me.messageofdeath.PaidRanks.Database;

import me.messageofdeath.PaidRanks.Database.Configuration.Configuration;
import me.messageofdeath.PaidRanks.Database.Databases.RankDatabase;
import me.messageofdeath.PaidRanks.Database.Language.LanguageConfiguration;
import me.messageofdeath.PaidRanks.PaidRanks;

public class DatabaseManager {
	private PaidRanks instance;
	private Configuration configuration;
	private LanguageConfiguration languageConfiguration;
	private RankDatabase rankDatabase;

	public DatabaseManager(PaidRanks instance) {
		this.instance = instance;
	}

	public void onStartUp() {
		initConfiguration();
		initLanguage();
		initRanks();
	}

	public void onShutDown() {
		saveDatabases();
	}

	public Configuration getConfiguration() {
		return this.configuration;
	}

	public LanguageConfiguration getLanguageConfiguration() {
		return this.languageConfiguration;
	}

	public RankDatabase getRankDatabase() {
		return this.rankDatabase;
	}

	public void initConfiguration() {
		this.configuration = new Configuration(this.instance);
		this.configuration.initConfiguration();
		this.configuration.loadConfiguration();
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
