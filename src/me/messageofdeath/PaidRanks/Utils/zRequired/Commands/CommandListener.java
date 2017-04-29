package me.messageofdeath.PaidRanks.Utils.zRequired.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandListener implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) {
		CommandVariables variables = CommandManager.getVariables(cmd.getName());
		if ((variables.getAnnotation().permission().equals(""))
				|| (sender.hasPermission(variables.getAnnotation().permission()))
				|| (variables.getAnnotation().permission().equalsIgnoreCase("noPerm"))
				|| (variables.getAnnotation().permission() == null)) {
			variables.getCommand().issue(new IssuedCommand(sender, cmd.getName(), args));
		} else {
			sender.sendMessage(ChatColor.BLACK + "[" + ChatColor.DARK_RED + "Error" + ChatColor.BLACK + "] " + ChatColor.RED + "You do not have permission for this!");
		}
		return false;
	}
}
