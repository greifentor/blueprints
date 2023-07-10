package de.ollie.kafka.cli.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Generated
@Getter
@ToString
public class CommandLineOption {

	public enum Type {
		BOOLEAN,
		LONG,
		STRING;
	}

	public static CommandLineOption of(String name, String description, boolean required, Type type) {
		return new CommandLineOption(description, name, required, type);
	}

	private String description;
	private String name;
	private boolean required;
	private Type type;

}
