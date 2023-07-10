package de.ollie.kafka.cli.model;

import java.util.List;

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
public class Argument {

	public static Argument of(String name, List<?> values) {
		return new Argument(name, values);
	}

	private String name;
	private List<?> values;

}
