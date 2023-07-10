package de.ollie.kafka.util;

public class Check {

	Check() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("It's an utility class! Do not instantiate!");
	}

	public static void ensure(boolean condition, RuntimeException runtimeException) {
		if (!condition) {
			throw runtimeException;
		}
	}

	public static <E extends Exception> void ensure(boolean condition, E exception) throws E { // NOSONAR
		if (!condition) {
			throw exception;
		}
	}

	public static void ensure(boolean condition, String message) {
		if (!condition) {
			throw new IllegalArgumentException(message);
		}
	}

}