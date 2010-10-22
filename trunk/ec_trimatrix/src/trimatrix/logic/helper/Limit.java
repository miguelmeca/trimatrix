package trimatrix.logic.helper;

import java.util.Arrays;

import com.twolattes.json.Entity;
import com.twolattes.json.Value;

@Entity
public class Limit {
	@Value
	String category;
	@Value
	String[] limits = new String[3]; // swim - run - bike
	@Value(optional = true)
	Integer[] tolerances = new Integer[3]; // swim - run - bike
	@Value(optional = true)
	String[] swim = new String[2]; // Name - Time
	@Value(optional = true)
	String[] run = new String[2]; //Name - Time
	@Value(optional = true)
	String[] bike = new String[2]; //Name - Time
	@Value
	Boolean swimsuit;
	@Value(optional = true)
	String resultsTemplate;
	@Value(optional = true)
    String resultsId;

	public Limit() { }

	public Limit(String category, String[] limits, Integer[] tolerances, String[] swim, String[] run, String[] bike, Boolean swimsuit, String resultsTemplate, String resultsId) {
		this.category = category;
		if(limits!=null) this.limits = limits;
		if(tolerances!=null) this.tolerances = tolerances;
		if(swim!=null) this.swim = swim;
		if(run!=null) this.run = run;
		if(bike!=null) this.bike = bike;
		if(swimsuit!=null) this.swimsuit = swimsuit;
		this.resultsTemplate = resultsTemplate;
		this.resultsId = resultsId;
	}

	public Limit(String category, String[] limits, Integer[] tolerances, String[] swim, String[] run, String[] bike, Boolean swimsuit) {
		this.category = category;
		if(limits!=null) this.limits = limits;
		if(tolerances!=null) this.tolerances = tolerances;
		if(swim!=null) this.swim = swim;
		if(run!=null) this.run = run;
		if(bike!=null) this.bike = bike;
		if(swimsuit!=null) this.swimsuit = swimsuit;
	}

	public String getCategory() {return category;}
	public void setCategory(String category) {this.category = category;}

	public String[] getLimits() {return limits;}
	public void setLimits(String[] limits) {this.limits = limits;}

	public Integer[] getTolerances() {return tolerances;}
	public void setTolerances(Integer[] tolerances) {this.tolerances = tolerances;}

	public String[] getSwim() {return swim;}
	public void setSwim(String[] swim) {this.swim = swim;}

	public String[] getRun() {return run;}
	public void setRun(String[] run) {this.run = run;}

	public String[] getBike() {return bike;}
	public void setBike(String[] bike) {this.bike = bike;}

	public Boolean getSwimsuit() {return swimsuit;}
	public void setSwimsuit(Boolean swimsuit) {this.swimsuit = swimsuit;}

	public String getResultsTemplate() {return resultsTemplate;}
	public void setResultsTemplate(String resultsTemplate) {this.resultsTemplate = resultsTemplate;}

	public String getResultsId() {return resultsId;}
	public void setResultsId(String resultsId) {this.resultsId = resultsId;}

	@Override
	public String toString() {return category + " : " + Arrays.toString(limits);}
}
