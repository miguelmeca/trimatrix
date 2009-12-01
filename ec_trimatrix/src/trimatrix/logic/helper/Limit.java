package trimatrix.logic.helper;

import java.util.Arrays;

import com.twolattes.json.Entity;
import com.twolattes.json.Value;

@Entity
public class Limit {
	@Value
	String category;
	@Value
	Double[] limits = new Double[2];	// initialize!!
	
	public Limit() { }
	
	public Limit(String category, Double[] limits) {
		this.category = category;
		if(limits!=null) this.limits = limits;
	}
	
	public String getCategory() {return category;}
	public void setCategory(String category) {this.category = category;}

	public Double[] getLimits() {return limits;}
	public void setLimits(Double[] limits) {this.limits = limits;}
	
	@Override
	public String toString() {return category + " : " + Arrays.toString(limits);}
}
