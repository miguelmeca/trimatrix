package trimatrix.logic;

import trimatrix.utils.Constants;

public class CompetitionLogic {
	public String buildString(Double[] limits) {
        if(limits==null || limits.length==0) return Constants.EMPTY;
        StringBuilder result = new StringBuilder();       
        for(int i=0;i<limits.length;i++) {            
        	if(result.length()>0) result.append(',');
            result.append(limits[i].toString());
        }
        result.insert(0,'[');       
        result.append(']');
        return result.toString();
    }
	
	public Double[] getLimits(String limits) {
		if(limits==null || limits.length()==0) return new Double[0];
		// remove brackets
		limits = limits.substring(1, limits.length()-1);
		String[] arrLimits = limits.split(",");
		Double[] result = new Double[arrLimits.length];
		for(int i=0;i<arrLimits.length;i++) {   
			try {
				result[i] = Double.valueOf(arrLimits[0]);
		    } catch (NumberFormatException nfe) {
		    	result[i] = 0d;
		    }       
		}
		return result;
	}
}
