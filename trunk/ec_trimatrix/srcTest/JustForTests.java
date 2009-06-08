import java.io.File;

import trimatrix.utils.Constants;


public class JustForTests {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String icon = "./webcontent/images/mimeicons/xls.gif";
		File iconFile = new File(icon);	
		System.out.println(iconFile.exists());
	}

}
