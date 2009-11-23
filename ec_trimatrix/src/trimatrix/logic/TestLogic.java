package trimatrix.logic;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import trimatrix.db.DAOLayer;
import trimatrix.services.ServiceLayer;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;

public class TestLogic {
	public static final Log logger = LogFactory.getLog(TestLogic.class);
	private DAOLayer daoLayer;
	private ServiceLayer serviceLayer;
	
	public void deleteAllSwimProtocolls(String id) {
		int size = serviceLayer.getSqlExecutorService().deleteAllSwimProtocols(id);
		logger.debug(size + " : Swim protocols deleted!");
	}
	
	public LactateSamples createLactateSamples() {
		return new LactateSamples();
	}
	
	public LactateSamples createLactateSamples(String samples) {
		return new LactateSamples(samples);
	}
	
	public Split createSplit() {
		return new Split();
	}
	
	public String buildString(Split[] splits, Integer count) {
        if(splits==null || splits.length==0) return Constants.EMPTY;
        StringBuilder result = new StringBuilder();       
        for(int i=0;i<count;i++) {            
        	if(result.length()>0) result.append(',');
            result.append('{');
            result.append(splits[i].getTime());
            result.append(';');
            result.append(splits[i].getStrokes());
            result.append('}');
        }
        result.insert(0,'[');       
        result.append(']');
        return result.toString();
    }
   
    public Split[] buildArray(String splits, Integer count) {
    	Split[] result = new Split[count];
    	if(splits==null || splits.length()==0) {
      		for(int i=0;i<result.length;i++) {
    			result[i] = new Split();
    		}
        } else {
        	splits = splits.substring(1, splits.length()-1);
            String[] parts = splits.split(",");            
            result = new Split[count];       
            for(int i = 0;i<count;i++) {
            	if(i>=parts.length) {
            		result[i] = new Split();
            	}
            	// remove brackets
                String part = parts[i].substring(1, parts[i].length()-1);
                String[] elements = part.split(";");
                String time = elements[0];
                Integer strokes = 0;
                try {
                	strokes = Integer.valueOf(elements[1]);
                } catch (NumberFormatException nfe) {/* strokes set to zero */ }             
                result[i] = new Split(time, strokes);
            }
        }        
        return result;
    }
	
	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}
	
	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}
	
	public class LactateSamples {		
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
					logger.warn("Lactate " + lactate + " is not of type double!");
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
					logger.warn("Lactate " + lactate + " is not of type double!");
				}
				try {
					minutes = Integer.valueOf(parts[1]);
				} catch (NumberFormatException nfe) {
					logger.warn("Minutes " + minutes + " is not of type integer!");
				}
				lactates.add(new Lactate(minutes, lactate));
			}
		}

		public boolean isSingleValue() {
			return singleValue;
		}
		
		public String getSingleValue() {
			return Helper.getNumberFormat().format(getSingleDoubleValue());	
		}
		
		public Double getSingleDoubleValue() {
			Double lactate;
			if(isSingleValue()) {
				lactate = this.lactate;	
			} else {
				lactate = lactates.maxLactate;
			}
			return lactate;	
		}

		@Override
		public String toString() {
			StringBuilder result = new StringBuilder();		
			if(isSingleValue()) {
				result.append(Helper.getNumberFormat().format(lactate));
			} else {
				for(Lactate lactate : lactates) {
					if(result.length()>0) result.append(Constants.WHITESPACE);
					result.append(lactate.toString());
				}			
			}
			return result.toString();
		}

		public class Lactate {
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
				return Helper.getNumberFormat().format(lactate) + "@" + minutes;
			}		
		}
		
		public class LactateList extends ArrayList<Lactate> {
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
	
	public class Split {
		String time = "00:00";
		int strokes;
		
		public Split() { };
		
		public Split(String time, int strokes) {
			this.time = time;
			this.strokes = strokes;
		}
		
		public String getTime() {return time;}
		public void setTime(String time) {this.time = time;}
		
		public int getStrokes() {return strokes;}
		public void setStrokes(int strokes) {this.strokes = strokes;}			
	}
}