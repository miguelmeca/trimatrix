package trimatrix.logic.helper;

import com.twolattes.json.Entity;
import com.twolattes.json.Value;

@Entity
public class DayInfo {
	@Value
	Boolean restingHr = true;
	@Value
	Boolean weather = true;
	@Value
	boolean location = true;
	@Value
	Boolean weight = true;
	@Value
	boolean dinners = true;
	@Value
	Boolean fluids = true;
	@Value
	Boolean sleep = true;
	@Value
	Boolean tiredness = true;
	@Value
	Boolean feeling = true;
	@Value
	Boolean intensity = true;
	@Value
	Boolean valuation = true;
	@Value
	Boolean laboratory = true;
	@Value
	Boolean days = true;
	@Value
	Boolean illness = true;
	@Value
	Boolean massage = true;
	@Value
	Boolean therapy = true;
	@Value
	Boolean comments = true;

	public DayInfo() {}

	public Boolean getRestingHr() {
		return restingHr;
	}

	public void setRestingHr(Boolean restingHr) {
		this.restingHr = restingHr;
	}

	public Boolean getWeather() {
		return weather;
	}

	public void setWeather(Boolean weather) {
		this.weather = weather;
	}

	public Boolean getLocation() {
		return location;
	}

	public void setLocation(Boolean location) {
		this.location = location;
	}

	public Boolean getWeight() {
		return weight;
	}

	public void setWeight(Boolean weight) {
		this.weight = weight;
	}

	public Boolean getDinners() {
		return dinners;
	}

	public void setDinners(Boolean dinners) {
		this.dinners = dinners;
	}

	public Boolean getFluids() {
		return fluids;
	}

	public void setFluids(Boolean fluids) {
		this.fluids = fluids;
	}

	public Boolean getSleep() {
		return sleep;
	}

	public void setSleep(Boolean sleep) {
		this.sleep = sleep;
	}

	public Boolean getTiredness() {
		return tiredness;
	}

	public void setTiredness(Boolean tiredness) {
		this.tiredness = tiredness;
	}

	public Boolean getFeeling() {
		return feeling;
	}

	public void setFeeling(Boolean feeling) {
		this.feeling = feeling;
	}

	public Boolean getIntensity() {
		return intensity;
	}

	public void setIntensity(Boolean intensity) {
		this.intensity = intensity;
	}

	public Boolean getValuation() {
		return valuation;
	}

	public void setValuation(Boolean valuation) {
		this.valuation = valuation;
	}

	public Boolean getLaboratory() {
		return laboratory;
	}

	public void setLaboratory(Boolean laboratory) {
		this.laboratory = laboratory;
	}

	public Boolean getDays() {
		return days;
	}

	public void setDays(Boolean days) {
		this.days = days;
	}

	public Boolean getIllness() {
		return illness;
	}

	public void setIllness(Boolean illness) {
		this.illness = illness;
	}

	public Boolean getMassage() {
		return massage;
	}

	public void setMassage(Boolean massage) {
		this.massage = massage;
	}

	public Boolean getTherapy() {
		return therapy;
	}

	public void setTherapy(Boolean therapy) {
		this.therapy = therapy;
	}

	public Boolean getComments() {
		return comments;
	}

	public void setComments(Boolean comments) {
		this.comments = comments;
	}
}
