import jamlab.Polyfit;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import org.dbunit.dataset.datatype.NumberDataType;


public class JustForTests {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("de"));
		Double d = 1.25;
		System.out.println('\u00a0');
		System.out.println(nf.format(d));
	}

}
