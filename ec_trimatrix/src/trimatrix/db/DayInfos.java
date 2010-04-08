package trimatrix.db;

import java.sql.Time;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * DayInfos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "day_infos", catalog = "trimatrix")
public class DayInfos implements java.io.Serializable {

	// Fields

	private DayInfosId id;
	private Integer restingHr;
	private Double weightMorning;
	private Double weightEvening;
	private Integer sleepingQuality;
	private Double temperature;
	private String location;
	private Integer altitude;
	private String dinnersLunch;
	private String dinnersEvening;
	private Double fluidsIntake;
	private Double sleepingHours;
	private Integer tiredness;
	private Integer feeling;
	private Integer trainingIntensity;
	private Integer trainingValuation;
	private Boolean restday;
	private Boolean travelday;
	private Boolean camp;
	private Boolean illness;
	private String illnessText;
	private Boolean massage;
	private String massageText;
	private Boolean therapy;
	private String therapyText;
	private String comment;
	private String commentCoach;
	private Integer ckMorning;
	private Time ckMorningTime;
	private Integer ckLunch;
	private Time ckLunchTime;
	private Integer ckEvening;
	private Time ckEveningTime;
	private Integer ureaMorning;
	private Time ureaMorningTime;
	private Integer ureaLunch;
	private Time ureaLunchTime;
	private Integer ureaEvening;
	private Time ureaEveningTime;
	private Integer glucoseMorning;
	private Time glucoseMorningTime;
	private Integer glucoseLunch;
	private Time glucoseLunchTime;
	private Integer glucoseEvening;
	private Time glucoseEveningTime;
	private Integer hb;
	private Time hbTime;
	private Integer hkt;
	private Time hktTime;
	private String weather;

	// Constructors

	/** default constructor */
	public DayInfos() {
	}

	/** minimal constructor */
	public DayInfos(DayInfosId id) {
		this.id = id;
	}

	/** full constructor */
	public DayInfos(DayInfosId id, Integer restingHr, Double weightMorning,
			Double weightEvening, Integer sleepingQuality, Double temperature,
			String location, Integer altitude, String dinnersLunch,
			String dinnersEvening, Double fluidsIntake, Double sleepingHours,
			Integer tiredness, Integer feeling, Integer trainingIntensity,
			Integer trainingValuation, Boolean restday, Boolean travelday,
			Boolean camp, Boolean illness, String illnessText, Boolean massage,
			String massageText, Boolean therapy, String therapyText,
			String comment, String commentCoach, Integer ckMorning,
			Time ckMorningTime, Integer ckLunch, Time ckLunchTime,
			Integer ckEvening, Time ckEveningTime, Integer ureaMorning,
			Time ureaMorningTime, Integer ureaLunch, Time ureaLunchTime,
			Integer ureaEvening, Time ureaEveningTime, Integer glucoseMorning,
			Time glucoseMorningTime, Integer glucoseLunch,
			Time glucoseLunchTime, Integer glucoseEvening,
			Time glucoseEveningTime, Integer hb, Time hbTime, Integer hkt,
			Time hktTime, String weather) {
		this.id = id;
		this.restingHr = restingHr;
		this.weightMorning = weightMorning;
		this.weightEvening = weightEvening;
		this.sleepingQuality = sleepingQuality;
		this.temperature = temperature;
		this.location = location;
		this.altitude = altitude;
		this.dinnersLunch = dinnersLunch;
		this.dinnersEvening = dinnersEvening;
		this.fluidsIntake = fluidsIntake;
		this.sleepingHours = sleepingHours;
		this.tiredness = tiredness;
		this.feeling = feeling;
		this.trainingIntensity = trainingIntensity;
		this.trainingValuation = trainingValuation;
		this.restday = restday;
		this.travelday = travelday;
		this.camp = camp;
		this.illness = illness;
		this.illnessText = illnessText;
		this.massage = massage;
		this.massageText = massageText;
		this.therapy = therapy;
		this.therapyText = therapyText;
		this.comment = comment;
		this.commentCoach = commentCoach;
		this.ckMorning = ckMorning;
		this.ckMorningTime = ckMorningTime;
		this.ckLunch = ckLunch;
		this.ckLunchTime = ckLunchTime;
		this.ckEvening = ckEvening;
		this.ckEveningTime = ckEveningTime;
		this.ureaMorning = ureaMorning;
		this.ureaMorningTime = ureaMorningTime;
		this.ureaLunch = ureaLunch;
		this.ureaLunchTime = ureaLunchTime;
		this.ureaEvening = ureaEvening;
		this.ureaEveningTime = ureaEveningTime;
		this.glucoseMorning = glucoseMorning;
		this.glucoseMorningTime = glucoseMorningTime;
		this.glucoseLunch = glucoseLunch;
		this.glucoseLunchTime = glucoseLunchTime;
		this.glucoseEvening = glucoseEvening;
		this.glucoseEveningTime = glucoseEveningTime;
		this.hb = hb;
		this.hbTime = hbTime;
		this.hkt = hkt;
		this.hktTime = hktTime;
		this.weather = weather;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "date", column = @Column(name = "date", nullable = false, length = 10)),
			@AttributeOverride(name = "personId", column = @Column(name = "person_id", nullable = false, length = 36)) })
	public DayInfosId getId() {
		return this.id;
	}

	public void setId(DayInfosId id) {
		this.id = id;
	}

	@Column(name = "resting_hr")
	public Integer getRestingHr() {
		return this.restingHr;
	}

	public void setRestingHr(Integer restingHr) {
		this.restingHr = restingHr;
	}

	@Column(name = "weight_morning", columnDefinition="decimal")
	public Double getWeightMorning() {
		return this.weightMorning;
	}

	public void setWeightMorning(Double weightMorning) {
		this.weightMorning = weightMorning;
	}

	@Column(name = "weight_evening", columnDefinition="decimal")
	public Double getWeightEvening() {
		return this.weightEvening;
	}

	public void setWeightEvening(Double weightEvening) {
		this.weightEvening = weightEvening;
	}

	@Column(name = "sleeping_quality")
	public Integer getSleepingQuality() {
		return this.sleepingQuality;
	}

	public void setSleepingQuality(Integer sleepingQuality) {
		this.sleepingQuality = sleepingQuality;
	}

	@Column(name = "temperature", columnDefinition="decimal")
	public Double getTemperature() {
		return this.temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	@Column(name = "location", length = 45)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "altitude")
	public Integer getAltitude() {
		return this.altitude;
	}

	public void setAltitude(Integer altitude) {
		this.altitude = altitude;
	}

	@Column(name = "dinners_lunch", columnDefinition="text")
	public String getDinnersLunch() {
		return this.dinnersLunch;
	}

	public void setDinnersLunch(String dinnersLunch) {
		this.dinnersLunch = dinnersLunch;
	}

	@Column(name = "dinners_evening", columnDefinition="text")
	public String getDinnersEvening() {
		return this.dinnersEvening;
	}

	public void setDinnersEvening(String dinnersEvening) {
		this.dinnersEvening = dinnersEvening;
	}

	@Column(name = "fluids_intake", columnDefinition="decimal")
	public Double getFluidsIntake() {
		return this.fluidsIntake;
	}

	public void setFluidsIntake(Double fluidsIntake) {
		this.fluidsIntake = fluidsIntake;
	}

	@Column(name = "sleeping_hours", columnDefinition="decimal")
	public Double getSleepingHours() {
		return this.sleepingHours;
	}

	public void setSleepingHours(Double sleepingHours) {
		this.sleepingHours = sleepingHours;
	}

	@Column(name = "tiredness")
	public Integer getTiredness() {
		return this.tiredness;
	}

	public void setTiredness(Integer tiredness) {
		this.tiredness = tiredness;
	}

	@Column(name = "feeling")
	public Integer getFeeling() {
		return this.feeling;
	}

	public void setFeeling(Integer feeling) {
		this.feeling = feeling;
	}

	@Column(name = "training_intensity")
	public Integer getTrainingIntensity() {
		return this.trainingIntensity;
	}

	public void setTrainingIntensity(Integer trainingIntensity) {
		this.trainingIntensity = trainingIntensity;
	}

	@Column(name = "training_valuation")
	public Integer getTrainingValuation() {
		return this.trainingValuation;
	}

	public void setTrainingValuation(Integer trainingValuation) {
		this.trainingValuation = trainingValuation;
	}

	@Column(name = "restday")
	public Boolean getRestday() {
		return this.restday;
	}

	public void setRestday(Boolean restday) {
		this.restday = restday;
	}

	@Column(name = "travelday")
	public Boolean getTravelday() {
		return this.travelday;
	}

	public void setTravelday(Boolean travelday) {
		this.travelday = travelday;
	}

	@Column(name = "camp")
	public Boolean getCamp() {
		return this.camp;
	}

	public void setCamp(Boolean camp) {
		this.camp = camp;
	}

	@Column(name = "illness")
	public Boolean getIllness() {
		return this.illness;
	}

	public void setIllness(Boolean illness) {
		this.illness = illness;
	}

	@Column(name = "illness_text", length = 100)
	public String getIllnessText() {
		return this.illnessText;
	}

	public void setIllnessText(String illnessText) {
		this.illnessText = illnessText;
	}

	@Column(name = "massage")
	public Boolean getMassage() {
		return this.massage;
	}

	public void setMassage(Boolean massage) {
		this.massage = massage;
	}

	@Column(name = "massage_text", length = 100)
	public String getMassageText() {
		return this.massageText;
	}

	public void setMassageText(String massageText) {
		this.massageText = massageText;
	}

	@Column(name = "therapy")
	public Boolean getTherapy() {
		return this.therapy;
	}

	public void setTherapy(Boolean therapy) {
		this.therapy = therapy;
	}

	@Column(name = "therapy_text", length = 100)
	public String getTherapyText() {
		return this.therapyText;
	}

	public void setTherapyText(String therapyText) {
		this.therapyText = therapyText;
	}

	@Column(name = "comment", columnDefinition="text")
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "comment_coach", columnDefinition="text")
	public String getCommentCoach() {
		return this.commentCoach;
	}

	public void setCommentCoach(String commentCoach) {
		this.commentCoach = commentCoach;
	}

	@Column(name = "ck_morning")
	public Integer getCkMorning() {
		return this.ckMorning;
	}

	public void setCkMorning(Integer ckMorning) {
		this.ckMorning = ckMorning;
	}

	@Column(name = "ck_morning_time", length = 8)
	public Time getCkMorningTime() {
		return this.ckMorningTime;
	}

	public void setCkMorningTime(Time ckMorningTime) {
		this.ckMorningTime = ckMorningTime;
	}

	@Column(name = "ck_lunch")
	public Integer getCkLunch() {
		return this.ckLunch;
	}

	public void setCkLunch(Integer ckLunch) {
		this.ckLunch = ckLunch;
	}

	@Column(name = "ck_lunch_time", length = 8)
	public Time getCkLunchTime() {
		return this.ckLunchTime;
	}

	public void setCkLunchTime(Time ckLunchTime) {
		this.ckLunchTime = ckLunchTime;
	}

	@Column(name = "ck_evening")
	public Integer getCkEvening() {
		return this.ckEvening;
	}

	public void setCkEvening(Integer ckEvening) {
		this.ckEvening = ckEvening;
	}

	@Column(name = "ck_evening_time", length = 8)
	public Time getCkEveningTime() {
		return this.ckEveningTime;
	}

	public void setCkEveningTime(Time ckEveningTime) {
		this.ckEveningTime = ckEveningTime;
	}

	@Column(name = "urea_morning")
	public Integer getUreaMorning() {
		return this.ureaMorning;
	}

	public void setUreaMorning(Integer ureaMorning) {
		this.ureaMorning = ureaMorning;
	}

	@Column(name = "urea_morning_time", length = 8)
	public Time getUreaMorningTime() {
		return this.ureaMorningTime;
	}

	public void setUreaMorningTime(Time ureaMorningTime) {
		this.ureaMorningTime = ureaMorningTime;
	}

	@Column(name = "urea_lunch")
	public Integer getUreaLunch() {
		return this.ureaLunch;
	}

	public void setUreaLunch(Integer ureaLunch) {
		this.ureaLunch = ureaLunch;
	}

	@Column(name = "urea_lunch_time", length = 8)
	public Time getUreaLunchTime() {
		return this.ureaLunchTime;
	}

	public void setUreaLunchTime(Time ureaLunchTime) {
		this.ureaLunchTime = ureaLunchTime;
	}

	@Column(name = "urea_evening")
	public Integer getUreaEvening() {
		return this.ureaEvening;
	}

	public void setUreaEvening(Integer ureaEvening) {
		this.ureaEvening = ureaEvening;
	}

	@Column(name = "urea_evening_time", length = 8)
	public Time getUreaEveningTime() {
		return this.ureaEveningTime;
	}

	public void setUreaEveningTime(Time ureaEveningTime) {
		this.ureaEveningTime = ureaEveningTime;
	}

	@Column(name = "glucose_morning")
	public Integer getGlucoseMorning() {
		return this.glucoseMorning;
	}

	public void setGlucoseMorning(Integer glucoseMorning) {
		this.glucoseMorning = glucoseMorning;
	}

	@Column(name = "glucose_morning_time", length = 8)
	public Time getGlucoseMorningTime() {
		return this.glucoseMorningTime;
	}

	public void setGlucoseMorningTime(Time glucoseMorningTime) {
		this.glucoseMorningTime = glucoseMorningTime;
	}

	@Column(name = "glucose_lunch")
	public Integer getGlucoseLunch() {
		return this.glucoseLunch;
	}

	public void setGlucoseLunch(Integer glucoseLunch) {
		this.glucoseLunch = glucoseLunch;
	}

	@Column(name = "glucose_lunch_time", length = 8)
	public Time getGlucoseLunchTime() {
		return this.glucoseLunchTime;
	}

	public void setGlucoseLunchTime(Time glucoseLunchTime) {
		this.glucoseLunchTime = glucoseLunchTime;
	}

	@Column(name = "glucose_evening")
	public Integer getGlucoseEvening() {
		return this.glucoseEvening;
	}

	public void setGlucoseEvening(Integer glucoseEvening) {
		this.glucoseEvening = glucoseEvening;
	}

	@Column(name = "glucose_evening_time", length = 8)
	public Time getGlucoseEveningTime() {
		return this.glucoseEveningTime;
	}

	public void setGlucoseEveningTime(Time glucoseEveningTime) {
		this.glucoseEveningTime = glucoseEveningTime;
	}

	@Column(name = "hb")
	public Integer getHb() {
		return this.hb;
	}

	public void setHb(Integer hb) {
		this.hb = hb;
	}

	@Column(name = "hb_time", length = 8)
	public Time getHbTime() {
		return this.hbTime;
	}

	public void setHbTime(Time hbTime) {
		this.hbTime = hbTime;
	}

	@Column(name = "hkt")
	public Integer getHkt() {
		return this.hkt;
	}

	public void setHkt(Integer hkt) {
		this.hkt = hkt;
	}

	@Column(name = "hkt_time", length = 8)
	public Time getHktTime() {
		return this.hktTime;
	}

	public void setHktTime(Time hktTime) {
		this.hktTime = hktTime;
	}

	@Column(name = "weather")
	public String getWeather() {
		return this.weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public static int EMPTYHASH = -240540129;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 * explicitly exclude ID!!!
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result	+ ((altitude == null) ? 0 : altitude.hashCode());
		result = prime * result + ((camp == null) ? 0 : camp.hashCode());
		result = prime * result	+ ((ckEvening == null) ? 0 : ckEvening.hashCode());
		result = prime * result	+ ((ckEveningTime == null) ? 0 : ckEveningTime.hashCode());
		result = prime * result + ((ckLunch == null) ? 0 : ckLunch.hashCode());
		result = prime * result	+ ((ckLunchTime == null) ? 0 : ckLunchTime.hashCode());
		result = prime * result	+ ((ckMorning == null) ? 0 : ckMorning.hashCode());
		result = prime * result	+ ((ckMorningTime == null) ? 0 : ckMorningTime.hashCode());
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result	+ ((commentCoach == null) ? 0 : commentCoach.hashCode());
		result = prime * result	+ ((dinnersEvening == null) ? 0 : dinnersEvening.hashCode());
		result = prime * result	+ ((dinnersLunch == null) ? 0 : dinnersLunch.hashCode());
		result = prime * result + ((feeling == null) ? 0 : feeling.hashCode());
		result = prime * result	+ ((fluidsIntake == null) ? 0 : fluidsIntake.hashCode());
		result = prime * result	+ ((glucoseEvening == null) ? 0 : glucoseEvening.hashCode());
		result = prime * result	+ ((glucoseEveningTime == null) ? 0 : glucoseEveningTime.hashCode());
		result = prime * result	+ ((glucoseLunch == null) ? 0 : glucoseLunch.hashCode());
		result = prime * result	+ ((glucoseLunchTime == null) ? 0 : glucoseLunchTime.hashCode());
		result = prime * result	+ ((glucoseMorning == null) ? 0 : glucoseMorning.hashCode());
		result = prime * result	+ ((glucoseMorningTime == null) ? 0 : glucoseMorningTime.hashCode());
		result = prime * result + ((hb == null) ? 0 : hb.hashCode());
		result = prime * result + ((hbTime == null) ? 0 : hbTime.hashCode());
		result = prime * result + ((hkt == null) ? 0 : hkt.hashCode());
		result = prime * result + ((hktTime == null) ? 0 : hktTime.hashCode());
		result = prime * result + ((illness == null) ? 0 : illness.hashCode());
		result = prime * result	+ ((illnessText == null) ? 0 : illnessText.hashCode());
		result = prime * result	+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((massage == null) ? 0 : massage.hashCode());
		result = prime * result	+ ((massageText == null) ? 0 : massageText.hashCode());
		result = prime * result + ((restday == null) ? 0 : restday.hashCode());
		result = prime * result	+ ((restingHr == null) ? 0 : restingHr.hashCode());
		result = prime * result	+ ((sleepingHours == null) ? 0 : sleepingHours.hashCode());
		result = prime * result	+ ((sleepingQuality == null) ? 0 : sleepingQuality.hashCode());
		result = prime * result	+ ((temperature == null) ? 0 : temperature.hashCode());
		result = prime * result + ((therapy == null) ? 0 : therapy.hashCode());
		result = prime * result	+ ((therapyText == null) ? 0 : therapyText.hashCode());
		result = prime * result	+ ((tiredness == null) ? 0 : tiredness.hashCode());
		result = prime * result	+ ((trainingIntensity == null) ? 0 : trainingIntensity.hashCode());
		result = prime * result + ((trainingValuation == null) ? 0 : trainingValuation.hashCode());
		result = prime * result	+ ((travelday == null) ? 0 : travelday.hashCode());
		result = prime * result	+ ((ureaEvening == null) ? 0 : ureaEvening.hashCode());
		result = prime * result	+ ((ureaEveningTime == null) ? 0 : ureaEveningTime.hashCode());
		result = prime * result	+ ((ureaLunch == null) ? 0 : ureaLunch.hashCode());
		result = prime * result	+ ((ureaLunchTime == null) ? 0 : ureaLunchTime.hashCode());
		result = prime * result	+ ((ureaMorning == null) ? 0 : ureaMorning.hashCode());
		result = prime * result	+ ((ureaMorningTime == null) ? 0 : ureaMorningTime.hashCode());
		result = prime * result	+ ((weightEvening == null) ? 0 : weightEvening.hashCode());
		result = prime * result	+ ((weightMorning == null) ? 0 : weightMorning.hashCode());
		result = prime * result	+ ((weather == null) ? 0 : weather.hashCode());
		System.out.println(result);
		return result;
	}
}