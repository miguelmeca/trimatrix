package utils;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.eclnt.jsfserver.util.HttpSessionAccess;
import org.junit.Test;

import trimatrix.utils.Constants;

public class LactateSamplesTest {

	@Test
	public void testLactateSamples() {
		String sample1 = "1,35";
		LactateSamples lactateSamples1 = new LactateSamples(sample1);
		Assert.assertEquals(sample1, lactateSamples1.toString());
		Assert.assertEquals(sample1, lactateSamples1.getSingleValue());
		
		String sample2 = "1,2@2 1.85@3 3@4 10,12@10";
		LactateSamples lactateSamples2 = new LactateSamples(sample2);
		Assert.assertEquals("1,2@2 1,85@3 3@4 10,12@10", lactateSamples2.toString());
		Assert.assertEquals("10,12", lactateSamples2.getSingleValue());
	}
	
	public class LactateSamples {

		NumberFormat nf = NumberFormat.getInstance();
		LactateList lactates;
		Double lactate;
		boolean singleValue;

		public LactateSamples() {
			setLactateSamples("0");
		}
		
		public LactateSamples(String samples) {
			setLactateSamples(samples);
		}

		public void setLactateSamples(String samples) {
			if(samples==null || samples.length()==0) samples = "0";
			// 1. Check if single value
			if (!samples.contains("@")) {
				singleValue = true;
				try {
					lactate = Double.valueOf(samples.replaceAll(",", "."));
				} catch (NumberFormatException nfe) {
					//logger.warn("Lactate " + lactate + " is not of type double!");
					lactate = 0d;
				}
				return;
			}
			// 2. Multi values
			singleValue = false;
			lactates = new LactateList();
			Pattern pattern = Pattern.compile("(\\d*[,.]?\\d*@\\d*)");
			Matcher matcher = pattern.matcher(samples);
			while (matcher.find()) {
				String[] parts = matcher.group().split("@");
				Double lactate = 0d;
				Integer minutes = 0;
				try {
					lactate = Double.valueOf(parts[0].replaceAll(",", "."));
				} catch (NumberFormatException nfe) {
					//logger.warn("Lactate " + lactate + " is not of type double!");
				}
				try {
					minutes = Integer.valueOf(parts[1]);
				} catch (NumberFormatException nfe) {
					//logger.warn("Minutes " + minutes + " is not of type integer!");
				}
				lactates.add(new Lactate(minutes, lactate));
			}
		}

		public boolean isSingleValue() {
			return singleValue;
		}
		
		public String getSingleValue() {
			Double lactate;
			if(isSingleValue()) {
				lactate = this.lactate;	
			} else {
				lactate = lactates.maxLactate;
			}
			return nf.format(lactate);	
		}

		@Override
		public String toString() {
			StringBuilder result = new StringBuilder();		
			if(isSingleValue()) {
				result.append(nf.format(lactate));
			} else {
				for(Lactate lactate : lactates) {
					if(result.length()>0) result.append(Constants.WHITESPACE);
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
				return nf.format(lactate) + "@" + minutes;
			}		
		}
		
		private class LactateList extends ArrayList<Lactate> {
			Double maxLactate;
			Double minLactate;
			@Override
			public boolean add(Lactate e) {			
				if(!super.add(e)) return false;
				if(maxLactate==null || e.getLactate() > maxLactate) maxLactate = e.getLactate();
				if(minLactate==null || e.getLactate() < minLactate) minLactate = e.getLactate();
				return true;
			}
			public Double getMaxLactate() {
				return maxLactate;
			}
			public Double getMinLactate() {
				return minLactate;
			}
		}
	}
}
