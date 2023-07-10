package de.ollie.kafka.cli;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import de.ollie.kafka.cli.model.CommandLineCommand;

@Component
public class CommandLineCommandChecker {

	public List<CommandLineCommand> check(ApplicationArguments args) {
		List<CommandLineCommand> commands = new ArrayList<>();
		for (String token : args.getNonOptionArgs()) {
			commands.add(CommandLineCommand.getByToken(token));
		}
		return commands;
	}

}
