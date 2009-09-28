package trimatrix.ui.tests;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import trimatrix.utils.Constants;

public class LactateSamples {

	List<Lactate> lactates;
	Double lactate;
	boolean singleValue;

	public LactateSamples(String samples) {
		setLactateSamples(samples);
	}

	public void setLactateSamples(String samples) {
		// 1. Check if single value
		if (!samples.contains("@")) {
			singleValue = true;
			try {
				lactate = Double.valueOf(samples);
			} catch (NumberFormatException nfe) {
				// TODO add log entry
				lactate = 0d;
			}
			return;
		}
		// 2. Multi values
		singleValue = false;
		lactates = new ArrayList<Lactate>();
		Pattern pattern = Pattern.compile("(\\d*[,.]?\\d*@\\d*)");
		Matcher matcher = pattern.matcher(samples);
		while (matcher.find()) {
			String[] parts = matcher.group().split("@");
			Double lactate = 0d;
			Integer minutes = 0;
			try {
				lactate = Double.valueOf(parts[0].replaceAll(",", "."));
			} catch (NumberFormatException nfe) {
				// TODO add log entry
			}
			try {
				minutes = Integer.valueOf(parts[1]);
			} catch (NumberFormatException nfe) {
				// TODO add log entry
			}
			lactates.add(new Lactate(minutes, lactate));
		}
	}

	public boolean isSingleValue() {
		return singleValue;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();		
		if(isSingleValue()) {
			// TODO set locale
			result.append(NumberFormat.getInstance().format(lactate));
		} else {
			for(Lactate lactate : lactates) {
				if(result.capacity()>0) result.append(Constants.WHITESPACE);
				result.append(lactate.toString());
			}			
		}
		return result.toString();
	}

	private class Lactate {
		Integer minutes = 0;
		Double lactate = 0d;

		public Lactate(Integer minutes, Double lactate) {
			this.minutes = minutes;
			this.lactate = lactate;
		}

		public Integer getMinutes() {
			return minutes;
		}

		public void setMinutes(Integer minutes) {
			this.minutes = minutes;
		}

		public Double getLactate() {
			return lactate;
		}

		public void setLactate(Double lactate) {
			this.lactate = lactate;
		}

		@Override
		public String toString() {
			// TODO set locale
			return NumberFormat.getInstance().format(lactate) + "@" + minutes;
		}		
	}
}
