
public class StringFormatDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String athlete = "Markus Reich";
		String athlete2 = "Daniela Bucher";
		System.out.println(String.format("Das ist ein Test für %s welcher hoffentlich funktioniert!", athlete));
		System.out.println(String.format("Das ist ein Test für %s, %s welcher hoffentlich funktioniert!", athlete, athlete2));
	}

}
