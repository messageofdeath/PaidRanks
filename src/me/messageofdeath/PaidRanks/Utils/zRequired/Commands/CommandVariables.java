package me.messageofdeath.PaidRanks.Utils.zRequired.Commands;

public class CommandVariables {
	private MessageCommand cmd;
	private Command annotation;

	public CommandVariables(Command annotation, MessageCommand cmd) {
		this.annotation = annotation;
		this.cmd = cmd;
	}

	public MessageCommand getCommand() {
		return this.cmd;
	}

	public Command getAnnotation() {
		return this.annotation;
	}
}
