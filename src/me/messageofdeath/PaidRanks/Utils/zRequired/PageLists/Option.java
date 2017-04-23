package me.messageofdeath.PaidRanks.Utils.zRequired.PageLists;

import org.bukkit.command.CommandSender;

public class Option {
	private String text;
	private String perm;

	public Option(String text, String perm) {
		this.text = text;
		this.perm = perm;
	}

	public String getText() {
		return this.text;
	}

	public boolean hasPermission(CommandSender sender) {
		return (sender.hasPermission(this.perm)) || (this.perm.equalsIgnoreCase("noPerm"))
				|| (this.perm.equalsIgnoreCase("")) || (this.perm.equalsIgnoreCase("noPermission"));
	}
}
