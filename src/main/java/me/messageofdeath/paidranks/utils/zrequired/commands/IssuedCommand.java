package me.messageofdeath.paidranks.utils.zrequired.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.messageofdeath.paidranks.utils.Utilities;

public class IssuedCommand {
	
	private String[] args;
	private String commandLabel;
	private CommandSender sender;

	public IssuedCommand(CommandSender sender, String commandLabel, String[] args) {
		this.args = args;
		this.commandLabel = commandLabel;
		this.sender = sender;
	}

	public boolean isPlayer() {
		return this.sender instanceof Player;
	}

	public boolean isConsole() {
		return this.sender instanceof ConsoleCommandSender;
	}

	public CommandSender getSender() {
		return this.sender;
	}

	public Player getPlayer() {
		return (Player) this.sender;
	}

	public ConsoleCommandSender getConsole() {
		return (ConsoleCommandSender) this.sender;
	}

	public String[] getArgs() {
		return this.args;
	}

	public int getLength() {
		return this.args.length;
	}

	public boolean argExist(int arg) {
		return arg < this.args.length;
	}

	public String getArg(int arg) {
		return this.args[arg];
	}

	@SuppressWarnings("deprecation")
	public boolean isOnline(int arg) {
		return Bukkit.getOfflinePlayer(this.args[arg]).isOnline();
	}

	public Player getPlayer(int arg) {
		return Bukkit.getPlayer(this.args[arg]);
	}

	@SuppressWarnings("deprecation")
	public OfflinePlayer getOfflinePlayer(int arg) {
		return Bukkit.getOfflinePlayer(this.args[arg]);
	}

	public int getInteger(int arg) {
		return Integer.parseInt(this.args[arg]);
	}

	public double getDouble(int arg) {
		return Double.parseDouble(this.args[arg]);
	}

	public boolean getBoolean(int arg) {
		return this.args[arg].equalsIgnoreCase("true");
	}

	public boolean isAlphanumeric(int arg) {
		return Utilities.isAlphanumeric(this.args[arg]);
	}

	public boolean isAlpha(int arg) {
		return Utilities.isAlpha(this.args[arg]);
	}

	public boolean isNumeric(int arg) {
		return Utilities.isNumeric(this.args[arg]);
	}
	
	public boolean isInteger(int arg) {
		return Utilities.isInteger(this.args[arg]);
	}
	
	public boolean isDouble(int arg) {
		return Utilities.isDouble(this.args[arg]);
	}
	
	public boolean isFloat(int arg) {
		return Utilities.isFloat(this.args[arg]);
	}

	public boolean isBoolean(int arg) {
		return (this.args[arg].equalsIgnoreCase("true")) || (this.args[arg].equalsIgnoreCase("false"));
	}

	public boolean isCommand(String name) {
		return this.commandLabel.equalsIgnoreCase(name);
	}

	public String getCommand() {
		return this.commandLabel;
	}
}
