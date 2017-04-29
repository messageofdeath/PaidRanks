package me.messageofdeath.PaidRanks.Utils.zRequired.Commands;

import me.messageofdeath.PaidRanks.Database.Language.LanguageSettings;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public abstract class MessageCommand {
	
	public Messenger messenger = null;

	@Command(name = "")
	public abstract void issue(IssuedCommand paramIssuedCommand);

	public void error(IssuedCommand cmd, String msg) {
		error(cmd.getSender(), msg);
	}

	public void error(CommandSender cmd, String msg) {
		msg(cmd, LanguageSettings.Commands_ErrorTag.getSetting() + ChatColor.translateAlternateColorCodes('&', msg));
	}

	public void msg(IssuedCommand cmd, String msg) {
		msg(cmd.getSender(), msg);
	}

	public void msg(CommandSender cmd, String msg) {
		this.messenger.sendMessage(cmd, ChatColor.translateAlternateColorCodes('&', msg), false);
	}

	public void msgPrefix(IssuedCommand cmd, String msg) {
		msgPrefix(cmd.getSender(), msg);
	}

	public void msgPrefix(CommandSender cmd, String msg) {
		this.messenger.sendMessage(cmd, ChatColor.translateAlternateColorCodes('&', msg), true);
	}

	public void wrongArgs(IssuedCommand cmd) {
		wrongArgs(cmd.getSender());
	}

	public void wrongArgs(CommandSender cmd) {
		msgPrefix(cmd, LanguageSettings.Commands_WrongArgs.getSetting());
	}

	public void noPerm(IssuedCommand cmd) {
		noPerm(cmd.getSender());
	}

	public void noPerm(CommandSender cmd) {
		error(cmd, LanguageSettings.Commands_NoPermission.getSetting());
	}
	
	public void msgCenter(IssuedCommand cmd, String msg) {
		String space = "                                                                              ";
		String text1 = msg.replace("&0", "").replace("&1", "").replace("&2", "").replace("&3", "").replace("&4", "").replace("&5", "").replace("&6", "").replace("&7", "")
				.replace("&8", "").replace("&8", "").replace("&9", "").replace("&a", "").replace("&b", "").replace("&c", "").replace("&d", "").replace("&e", "")
				.replace("&f", "").replace("&k", "").replace("&l", "").replace("&m", "").replace("&n", "").replace("&o", "").replace("&r", "");
		String output = space.substring((space.length() + text1.length()) / 2, space.length()) + msg;
		msg(cmd, output);
	}
}
