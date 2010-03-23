package trimatrix.logic.helper;

import com.twolattes.json.Entity;
import com.twolattes.json.Value;

@Entity
public class ScheduleRun {

    @Value(optional=true)
    String comment;
    @Value
    String zone;
    @Value
    String duration;
    @Value(optional=true)
    Double lactateLow;
    @Value(optional=true)
    Double lactateHigh;
    @Value(optional=true)
    Integer hrLow;
    @Value(optional=true)
    Integer hrHigh;
    @Value(optional=true)
    String durationAthlete;
    @Value(optional=true)
    Integer hrAvgAthlete;

    public ScheduleRun() { }

	public ScheduleRun(String duration, String zone, Double lactateLow, Double lactateHigh, Integer hrLow, Integer hrHigh, String comment) {
		this.duration = duration;
		this.zone = zone;
		this.lactateLow = lactateLow;
		this.lactateHigh = lactateHigh;
		this.hrLow = hrLow;
		this.hrHigh = hrHigh;
		this.comment = comment;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public Double getLactateLow() {
		return lactateLow;
	}

	public void setLactateLow(Double lactateLow) {
		this.lactateLow = lactateLow;
	}

	public Double getLactateHigh() {
		return lactateHigh;
	}

	public void setLactateHigh(Double lactateHigh) {
		this.lactateHigh = lactateHigh;
	}

	public Integer getHrLow() {
		return hrLow;
	}

	public void setHrLow(Integer hrLow) {
		this.hrLow = hrLow;
	}

	public Integer getHrHigh() {
		return hrHigh;
	}

	public void setHrHigh(Integer hrHigh) {
		this.hrHigh = hrHigh;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDurationAthlete() {
		return durationAthlete;
	}

	public void setDurationAthlete(String durationAthlete) {
		this.durationAthlete = durationAthlete;
	}

	public Integer getHrAvgAthlete() {
		return hrAvgAthlete;
	}

	public void setHrAvgAthlete(Integer hrAvgAthlete) {
		this.hrAvgAthlete = hrAvgAthlete;
	}
}
