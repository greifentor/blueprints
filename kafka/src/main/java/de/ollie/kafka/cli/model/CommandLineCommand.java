package de.ollie.kafka.cli.model;

public enum CommandLineCommand {

	HELP("help", "show all commands and options."),
	SEND("send", "sends a message to kafka."),
	RECEIVE("receive","receives messages from kafka.");

	private String token;
	private String description;

	private CommandLineCommand(String token, String description) {
		this.description = description;
		this.token = token;
	}

	public static CommandLineCommand getByToken(String token) {
		for (CommandLineCommand command : values()) {
			if (command.getToken().equals(token)) {
				return command;
			}
		}
		throw new IllegalArgumentException("illegal token for command: " + token);
	}

	public String getDescription() {
		return description;
	}

	public String getToken() {
		return token;
	}

}
