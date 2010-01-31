package trimatrix.logic.helper;

import java.util.Arrays;

import com.twolattes.json.Entity;
import com.twolattes.json.Value;

@Entity
public class Limit {
	@Value
	String category;
	@Value
	String[] limits = new String[2]; // LOW - HIGH
	@Value
	String[] swim = new String[2]; // Name - Time
	@Value
	String[] run = new String[2]; //Name - Time

	public Limit() { }

	public Limit(String category, String[] limits, String[] swim, String[] run) {
		this.category = category;
		if(limits!=null) this.limits = limits;
		if(swim!=null) this.swim = swim;
		if(run!=null) this.run = run;
	}

	public String getCategory() {return category;}
	public void setCategory(String category) {this.category = category;}

	public String[] getLimits() {return limits;}
	public void setLimits(String[] limits) {this.limits = limits;}

	public String[] getSwim() {return swim;}
	public void setSwim(String[] swim) {this.swim = swim;}

	public String[] getRun() {return run;}
	public void setRun(String[] run) {this.run = run;}

	@Override
	public String toString() {return category + " : " + Arrays.toString(limits);}
}
