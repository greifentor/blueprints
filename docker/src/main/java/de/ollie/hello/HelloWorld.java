package de.ollie.hello;

public class HelloWorld {

	public static void main(String[] args) {
		String name = args.length > 0 ? args[0] : "World";
		System.out.println("\nHello, " + name + "!\n");
	}

}
