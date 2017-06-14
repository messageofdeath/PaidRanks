package me.messageofdeath.paidranks.utils.zrequired.commands;

import java.lang.reflect.Method;
import java.util.HashMap;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandManager {
	
	private static HashMap<String, CommandVariables> commands = null;

	public static void register(JavaPlugin plugin, MessageCommand command) {
		if (commands == null) {
			commands = new HashMap<String, CommandVariables>();
		}
		Method[] arrayOfMethod;
		int j = (arrayOfMethod = command.getClass().getMethods()).length;
		for (int i = 0; i < j; i++) {
			Method method = arrayOfMethod[i];
			if (method.isAnnotationPresent(Command.class)) {
				Command cmd = (Command) method.getAnnotation(Command.class);
				commands.put(cmd.name(), new CommandVariables(cmd, command));
				plugin.getCommand(cmd.name()).setExecutor(new CommandListener());
			}
		}
	}

	public static void unregister(String commandLabel) {
		commands.remove(commandLabel);
	}

	public static CommandVariables getVariables(String commandLabel) {
		return (CommandVariables) commands.get(commandLabel);
	}
}
