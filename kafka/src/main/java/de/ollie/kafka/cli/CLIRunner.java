package de.ollie.kafka.cli;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import de.ollie.kafka.cli.model.Argument;
import de.ollie.kafka.cli.model.CommandLineCommand;
import de.ollie.kafka.cli.model.CommandLineOption;
import de.ollie.kafka.cli.model.CommandLineOption.Type;
import de.ollie.kafka.producer.service.EventProducer;

@Component
public class CLIRunner {

	@Autowired
	private CommandLineCommandChecker commandLineCommandChecker;
	@Autowired
	private CommandLineOptionChecker commandLineOptionChecker;
	@Autowired
	private EventProducer eventProducer;

	static final CommandLineOption[] OPTIONS = {
			CommandLineOption.of("message", "A rule to check configuration settings for.", false, Type.STRING) };

	public void run(ApplicationArguments args) {
		List<CommandLineCommand> commands = commandLineCommandChecker.check(args);
		List<Argument> arguments = commandLineOptionChecker.check(args, OPTIONS);
		if (commands.contains(CommandLineCommand.SEND) && hasArgument("message", arguments)) {
			getArgument("message", arguments).forEach(message -> {
				System.out.println("sending: " + message);
				eventProducer.sendMessage(message.toString());
			});
		}
		if (!commands.contains(CommandLineCommand.RECEIVE)) {
			System.exit(0);
		} else {
			System.out.println("consumer startet ...");
		}
	}

	private boolean hasArgument(String name, List<Argument> arguments) {
		return arguments.stream().anyMatch(arg -> arg.getName().equals(name));
	}

	private List<?> getArgument(String name, List<Argument> arguments) {
		return arguments.stream()
				.filter(arg -> arg.getName().equals(name))
				.findFirst()
				.map(Argument::getValues)
				.orElse(new ArrayList<>());
	}

}
