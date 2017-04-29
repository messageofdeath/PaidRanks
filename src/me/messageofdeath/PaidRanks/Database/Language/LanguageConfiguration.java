package me.messageofdeath.PaidRanks.Database.Language;

import me.messageofdeath.PaidRanks.PaidRanks;
import me.messageofdeath.PaidRanks.Utils.zRequired.Database.YamlDatabase;

public class LanguageConfiguration {
	
	private PaidRanks instance;
	private YamlDatabase config;

	public LanguageConfiguration(PaidRanks instance) {
		this.instance = instance;
	}

	public void initConfiguration() {
		this.config = new YamlDatabase(this.instance, "language");
		this.config.onStartUp();
		this.config.saveOnSet = false;
		boolean changes = false;
		LanguageSettings[] arrayOfLanguageSettings;
		int j = (arrayOfLanguageSettings = LanguageSettings.values()).length;
		for (int i = 0; i < j; i++) {
			LanguageSettings setting = arrayOfLanguageSettings[i];
			if (!this.config.contains(setting.getName().replace("_", "."))) {
				changes = true;
				this.config.set(setting.getName().replace("_", "."), setting.getDefaultSetting());
			}
		}
		if(changes) {
			this.config.save();
		}
		this.config.saveOnSet = true;
	}

	public YamlDatabase getConfig() {
		return this.config;
	}

	public void loadConfiguration() {
		LanguageSettings[] arrayOfLanguageSettings;
		int j = (arrayOfLanguageSettings = LanguageSettings.values()).length;
		for (int i = 0; i < j; i++) {
			LanguageSettings setting = arrayOfLanguageSettings[i];
			setting.setSetting(this.config.getString(setting.getName().replace("_", "."), setting.getDefaultSetting()));
		}
	}
}
