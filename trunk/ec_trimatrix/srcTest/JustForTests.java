import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class JustForTests {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String test = "10,23@5 1,5@10 2@15;3.5@20";
		Pattern pattern = Pattern.compile("(\\d*[,.]?\\d*@\\d*)");
		Matcher matcher = pattern.matcher(test);
		Locale locale1 = new Locale("de");
		Locale.setDefault(locale1);
		NumberFormat nf = NumberFormat.getInstance(locale1);
		System.out.println(nf.format(1.3d));
		boolean found = false;
        while (matcher.find()) {
            System.out.format("I found the text \"%s\" starting at index %d and ending at index %d.%n", matcher.group(), matcher.start(), matcher.end());
            String[] objects = matcher.group().split("@");            
            try {
            	Double lactate = Double.valueOf(objects[0].replaceAll(",", "."));
                Integer minutes = Integer.valueOf(objects[1]);                
                System.out.format("Laktat: %f nach %d Minuten!", lactate, minutes);
            } catch (NumberFormatException nfe) {
            	System.out.println("Konvertierungsfehler");
            }
            
            found = true;
        }
        if(!found){
        	System.out.format("No match found.%n");
        }

	}

}
