package de.ollie.kafka.cli;

import static de.ollie.kafka.util.Check.ensure;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import de.ollie.kafka.cli.model.Argument;
import de.ollie.kafka.cli.model.CommandLineOption;
import de.ollie.kafka.cli.model.CommandLineOption.Type;

@Component
public class CommandLineOptionChecker {

	public List<Argument> check(ApplicationArguments args, CommandLineOption... options) {
		ensure(args != null, "application arguments cannot be null!");
		List<Argument> arguments = new ArrayList<>();
		for (CommandLineOption option : options) {
			if (option.isRequired()) {
				ensure(args.containsOption(option.getName()), "required option missed: " + option.getName());
			}
			if (args.containsOption(option.getName())) {
				List<String> values = args.getOptionValues(option.getName());
				arguments.add(Argument.of(option.getName(), convertValues(option.getName(), option.getType(), values)));
			}
		}
		return arguments;
	}

	private List<?> convertValues(String name, Type type, List<String> values) {
		List<Object> l = new ArrayList<>(values.size());
		for (String s : values) {
			try {
				if (type == Type.BOOLEAN) {
					l.add(Boolean.valueOf(s));
				} else if (type == Type.LONG) {
					l.add(Long.valueOf(s));
				} else {
					l.add(s);
				}
			} catch (Exception e) {
				throw new IllegalArgumentException(
						"argument value of '" + name + "' does not match with type: " + type);
			}
		}
		return l;
	}

}
