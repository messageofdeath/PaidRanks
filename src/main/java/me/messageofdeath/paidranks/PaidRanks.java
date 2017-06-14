package me.messageofdeath.paidranks;

import java.util.ArrayList;

import me.messageofdeath.paidranks.commands.PaidRanksCommand;
import me.messageofdeath.paidranks.commands.RankupCommand;
import me.messageofdeath.paidranks.database.DatabaseManager;
import me.messageofdeath.paidranks.database.language.LanguageSettings;
import me.messageofdeath.paidranks.utils.api.PaidRanksAPI;
import me.messageofdeath.paidranks.utils.laddermanager.LadderManager;
import me.messageofdeath.paidranks.utils.zrequired.commands.CommandManager;
import me.messageofdeath.paidranks.utils.zrequired.economy.EconomyManager;
import me.messageofdeath.paidranks.utils.zrequired.permissions.PermissionsManager;

import org.bstats.Metrics;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class PaidRanks extends JavaPlugin {
	
	private DatabaseManager databaseManager;
	private LadderManager ladderManager;
	private EconomyManager economyManager;
	private PermissionsManager permissionsManager;
	private Metrics metrics;

	public void onEnable() {
		metrics = new Metrics(this);
		PaidRanksAPI.setInstance(this);
		this.ladderManager = new LadderManager(this);
		this.databaseManager = new DatabaseManager(this);
		this.databaseManager.onStartUp();
		this.log("Loading PaidRanks...", true);
		this.log("Searching for Economy plug-ins...", true);
		this.economyManager = new EconomyManager(this);
		this.economyManager.startEconomy();
		PaidRanksAPI.setEconomy(this.economyManager.getEconomy());
		this.log("Searching for Permission plug-ins...", true);
		this.permissionsManager = new PermissionsManager(this);
		this.permissionsManager.startPermissions();
		PaidRanksAPI.setPermissions(this.permissionsManager.getPermissions());
		this.log("Registering Commands...", true);
		CommandManager.register(this, new PaidRanksCommand(this));
		CommandManager.register(this, new RankupCommand(this));
		this.log("Successfully loaded PaidRanks!", true);
	}

	public void onDisable() {
		this.log("Shutting down PaidRanks...", true);
		if (this.databaseManager != null) {
			this.databaseManager.onShutDown();
		}
		this.log("PaidRanks has shutdown!", true);
	}

	public String getPrefix() {
		return getColorized(LanguageSettings.Commands_PluginTag.getSetting());
	}

	public String getColorized(String input) {
		return ChatColor.translateAlternateColorCodes('&', input);
	}

	public void log(String log, boolean prefix) {
		super.getServer().getConsoleSender().sendMessage(getColorized((prefix ? getPrefix() : "") + log));
	}

	public void logError(String topic, String classx, String method, String error) {
		String space = "                                                             ";
		String text = "&cTopic";
		topic = "&c" + topic;
		log("&4---------------------&b{&2PaidRanks &cError&b}&4---------------------", false);
		log("", false);
		log(space.substring((space.length() + text.length()) / 2, space.length()) + text, false);
		log(space.substring((space.length() + topic.length()) / 2, space.length()) + topic, false);
		log("", false);
		for (String s : getLines(error, space)) {
			log("&b" + space.substring((space.length() + s.length()) / 2, space.length()) + s, false);
		}
		log("", false);
		String cl = "&8Class: &c" + classx + "   &8Method: &c" + method;
		for (String s : getLines(cl, space)) {
			log("&c" + space.substring((space.length() + s.length()) / 2, space.length()) + s, false);
		}
		log("", false);
		log("&4---------------------&b{&2PaidRanks &cError&b}&4---------------------", false);
	}

	private ArrayList<String> getLines(String parse, String space) {
		ArrayList<String> lines = new ArrayList<String>();
		String s = "";
		String[] split = parse.split(" ");
		int length = split.length;
		for (int i = 0; i < length; i++) {
			if (s.length() + split[i].length() < space.length()) {
				s = s + split[i] + " ";
			} else {
				lines.add(s);
				s = split[i] + " ";
			}
			if (i + 1 == length) {
				lines.add(s);
			}
		}
		return lines;
	}

	public DatabaseManager getDatabaseManager() {
		return this.databaseManager;
	}

	public LadderManager getLadderManager() {
		return this.ladderManager;
	}
	
	public EconomyManager getEconomyManager() {
		return this.economyManager;
	}
}
