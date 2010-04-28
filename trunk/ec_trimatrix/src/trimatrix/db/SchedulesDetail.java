package trimatrix.db;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SchedulesDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "schedules_detail", catalog = "trimatrix")
public class SchedulesDetail implements java.io.Serializable {

	// Fields

	private SchedulesDetailId id;
	private String zoneId;
	private String durationTarget;
	private String durationActual;
	private Integer hrLow;
	private Integer hrHigh;
	private Integer hrAvg;
	private Double lactateLow;
	private Double lactateHigh;
	private Integer cadence;
	private Integer power;
	private String unit;
	private String description;
	private Integer distance;
	private String timeLow;
	private String timeHigh;
	private String timeAvg;
	private String comment;

	// Constructors

	/** default constructor */
	public SchedulesDetail() {
	}

	/** minimal constructor */
	public SchedulesDetail(SchedulesDetailId id) {
		this.id = id;
	}

	/** full constructor */
	public SchedulesDetail(SchedulesDetailId id, String zoneId, String durationTarget, String durationActual, Integer hrLow, Integer hrHigh, Integer hrAvg, Double lactateLow, Double lactateHigh,
			Integer cadence, Integer power, String unit, String description, Integer distance, String timeLow, String timeHigh, String timeAvg, String comment) {
		this.id = id;
		this.zoneId = zoneId;
		this.durationTarget = durationTarget;
		this.durationActual = durationActual;
		this.hrLow = hrLow;
		this.hrHigh = hrHigh;
		this.hrAvg = hrAvg;
		this.lactateLow = lactateLow;
		this.lactateHigh = lactateHigh;
		this.cadence = cadence;
		this.power = power;
		this.unit = unit;
		this.description = description;
		this.distance = distance;
		this.timeLow = timeLow;
		this.timeHigh = timeHigh;
		this.timeAvg = timeAvg;
		this.comment = comment;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( { @AttributeOverride(name = "id", column = @Column(name = "id", nullable = false, length = 36)),
			@AttributeOverride(name = "sequence", column = @Column(name = "sequence", nullable = false)) })
	public SchedulesDetailId getId() {
		return this.id;
	}

	public void setId(SchedulesDetailId id) {
		this.id = id;
	}

	@Column(name = "zone_id", length = 36)
	public String getZoneId() {
		return this.zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	@Column(name = "duration_target", length = 8)
	public String getDurationTarget() {
		return this.durationTarget;
	}

	public void setDurationTarget(String durationTarget) {
		this.durationTarget = durationTarget;
	}

	@Column(name = "duration_actual", length = 8)
	public String getDurationActual() {
		return this.durationActual;
	}

	public void setDurationActual(String durationActual) {
		this.durationActual = durationActual;
	}

	@Column(name = "hr_low")
	public Integer getHrLow() {
		return this.hrLow;
	}

	public void setHrLow(Integer hrLow) {
		this.hrLow = hrLow;
	}

	@Column(name = "hr_high")
	public Integer getHrHigh() {
		return this.hrHigh;
	}

	public void setHrHigh(Integer hrHigh) {
		this.hrHigh = hrHigh;
	}

	@Column(name = "hr_avg")
	public Integer getHrAvg() {
		return this.hrAvg;
	}

	public void setHrAvg(Integer hrAvg) {
		this.hrAvg = hrAvg;
	}

	@Column(name = "lactate_low", columnDefinition="decimal")
	public Double getLactateLow() {
		return this.lactateLow;
	}

	public void setLactateLow(Double lactateLow) {
		this.lactateLow = lactateLow;
	}

	@Column(name = "lactate_high", columnDefinition="decimal")
	public Double getLactateHigh() {
		return this.lactateHigh;
	}

	public void setLactateHigh(Double lactateHigh) {
		this.lactateHigh = lactateHigh;
	}

	@Column(name = "cadence")
	public Integer getCadence() {
		return cadence;
	}

	public void setCadence(Integer cadence) {
		this.cadence = cadence;
	}

	@Column(name = "power")
	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	@Column(name = "unit", length = 20)
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "description", length = 100)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "distance")
	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	@Column(name = "time_low", length = 5)
	public String getTimeLow() {
		return timeLow;
	}

	public void setTimeLow(String timeLow) {
		this.timeLow = timeLow;
	}

	@Column(name = "time_high", length = 5)
	public String getTimeHigh() {
		return timeHigh;
	}

	public void setTimeHigh(String timeHigh) {
		this.timeHigh = timeHigh;
	}

	@Column(name = "time_avg", length = 5)
	public String getTimeAvg() {
		return timeAvg;
	}

	public void setTimeAvg(String timeAvg) {
		this.timeAvg = timeAvg;
	}

	@Column(name = "comment", length = 100)
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}