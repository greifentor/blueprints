package app;

public class Application {

	static String JAVA = "java";
	static String KOTLIN = "kotlin";

	public static void main(final String[] args) {
		final String language = args[0];
		if (JAVA.equalsIgnoreCase(language)) {
			new JavaService().sayHello();
		} else if (KOTLIN.equalsIgnoreCase(language)) {
			new KotlinService().sayHello();
		}
	}

}