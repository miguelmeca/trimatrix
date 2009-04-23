import trimatrix.utils.Constants;


public class JustForTests {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Constants.Entity.USER.name());
		try {
			Constants.Entity entity = Constants.Entity.valueOf("UfSER");
			System.out.println(entity.name());
		} catch (IllegalArgumentException ex) {
			System.out.println("Fehler");
		}		
		

	}

}
