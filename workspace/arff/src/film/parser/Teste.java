package film.parser;

public class Teste {
	static void throwMethod() throws IllegalAccessException {
		System.out.println("Inside throwMethod.");
		throw new IllegalAccessException("demo");
	}

	public static void main(String args[]) {
		try {
			throwMethod();
		} catch (IllegalAccessException e) {
			System.out.println("Caught " + e);
		}
	}
}
