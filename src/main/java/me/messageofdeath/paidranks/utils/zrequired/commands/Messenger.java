package me.messageofdeath.paidranks.utils.zrequired.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Messenger {
	
	public String name;

	public Messenger(String prefix) {
		this.name = prefix;
	}

	public void sendMessage(CommandSender sender, String message, boolean prefixed) {
		if (sender == null) {
			throw new NullPointerException("I tried to send a message to a null CommandSender. Oops.");
		}
		sender.sendMessage(prefixed ? this.name + message : message);
	}

	public void sendMessage(CommandSender sender, String message) {
		sendMessage(sender, message, true);
	}

	public void sendMessageToConsole(String message, boolean prefixed) {
		sendMessage(Bukkit.getConsoleSender(), message, prefixed);
	}

	public void sendMessageToConsole(String message) {
		sendMessage(Bukkit.getConsoleSender(), message, true);
	}

	public void sendErrorToConsole(String message, boolean prefixed) {
		sendError(Bukkit.getConsoleSender(), message, prefixed);
	}

	public void sendErrorToConsole(String message) {
		sendError(Bukkit.getConsoleSender(), message, true);
	}

	public void sendError(CommandSender sender, String message, boolean prefixed) {
		sendMessage(sender, ChatColor.RED + "ERROR: " + ChatColor.RESET + message, prefixed);
	}

	public void sendError(CommandSender sender, String message) {
		sendError(sender, message, true);
	}

	public void sendNoPermissionError(CommandSender sender) {
		sendError(sender, "You don't have enough permission to do this!");
	}

	public void broadcastMessage(String message, boolean prefixed) {
		@SuppressWarnings("unchecked")
		ArrayList<Player> players = (ArrayList<Player>)Bukkit.getServer().getOnlinePlayers();
		for(int i = 0; i < players.size(); i++) {
			this.sendMessage(players.get(i), message, prefixed);
		}
	}

	public void broadcastMessage(String message) {
		broadcastMessage(message, true);
	}
}
