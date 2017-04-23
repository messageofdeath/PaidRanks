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
}
