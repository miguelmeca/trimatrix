package trimatrix.ui.tests;

import trimatrix.utils.Constants;

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
	
	public static String buildString(Split[] splits, Integer count) {
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
   
    public static Split[] buildArray(String splits, Integer count) {
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
}
