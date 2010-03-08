package trimatrix.ui;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.controls.DecSpinner;
import trimatrix.controls.Star;
import trimatrix.db.DayInfos;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;

@CCGenClass(expressionBase = "#{d.DayInfoPopUp}")
public class DayInfoPopUp extends MyWorkpageDispatchedBean implements Serializable {
	private static final Map<String, String> WEATHER = new HashMap<String, String>() {
		//Unnamed Block.
		{
			put("sun", "/images/icons/weather_sun.png");
			put("fewclouds", "/images/icons/weather_fewclouds.png");
			put("overcast", "/images/icons/weather_overcast.png");
			put("scattered", "/images/icons/weather_scattered.png");
			put("showers", "/images/icons/weather_showers.png");
			put("snow", "/images/icons/weather_snow.png");
		}
	};

	public String getWeatherImage() {
		return WEATHER.get(this.dayInfos.getWeather());
	}

	private static final int WIDTH = 150; // distance for UI
	public int getWidth() { return WIDTH; }

	private String rbgDayTime = "0";
	public String getRbgDayTime() {return rbgDayTime;}
	public void setRbgDayTime(String rbgDayTime) {this.rbgDayTime = rbgDayTime;}

	public DayInfoPopUp(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
	}

	protected DayInfos dayInfos;
	public DayInfos getDayInfos() { return dayInfos; }

	public String getSelectedDate() {
		return new SimpleDateFormat("EEEE, d. MMMM yyyy").format(date);
	}

	public void onPreviousDay(ActionEvent event) {
		date = getLogic().getScheduleLogic().addDaysToDate(date, -1);
		refresh();
	}

	public void onNextDay(ActionEvent event) {
		date = getLogic().getScheduleLogic().addDaysToDate(date, 1);
		refresh();
	}

	public interface IPopupCallback {
		public void cancel();
	}

	protected IPopupCallback callback;
	protected String athleteId;
	protected Date date;

	public void prepareCallback(IPopupCallback callback, String athleteId, Date date) {
		this.callback = callback;
		this.athleteId = athleteId;
		this.date = date;
		refresh();
	}

	public void onCancel(ActionEvent event) {
		callback.cancel();
	}

	private void refresh() {
		// get info from db, return a new empty if nothing found
		this.dayInfos = getLogic().getScheduleLogic().getDayInfos(athleteId, date);
		// DecSpinners
		weightMorningDS.setValue(this.dayInfos.getWeightMorning());
		weightEveningDS.setValue(this.dayInfos.getWeightEvening());
		temperatureDS.setValue(this.dayInfos.getTemperature());
		fluidsIntakeDS.setValue(this.dayInfos.getFluidsIntake());
		ckMorningDS.setValue(this.dayInfos.getCkMorning());
		ckLunchDS.setValue(this.dayInfos.getCkLunch());
		ckEveningDS.setValue(this.dayInfos.getCkEvening());
		ureaMorningDS.setValue(this.dayInfos.getUreaMorning());
		ureaLunchDS.setValue(this.dayInfos.getUreaLunch());
		ureaEveningDS.setValue(this.dayInfos.getUreaEvening());
		glucoseMorningDS.setValue(this.dayInfos.getGlucoseMorning());
		glucoseLunchDS.setValue(this.dayInfos.getGlucoseLunch());
		glucoseEveningDS.setValue(this.dayInfos.getGlucoseEvening());
		hbDS.setValue(this.dayInfos.getHb());
		hktDS.setValue(this.dayInfos.getHkt());

		// Stars
		sleepingQualityS.setValue(this.dayInfos.getSleepingQuality());
		tirednessS.setValue(this.dayInfos.getTiredness());
		feelingS.setValue(this.dayInfos.getFeeling());
		trainingIntensityS.setValue(this.dayInfos.getTrainingIntensity());
		trainingValuationS.setValue(this.dayInfos.getTrainingValuation());
	}

	public void onSave(ActionEvent event) {
		// get values from DecSpinners
		dayInfos.setWeightMorning(weightMorningDS.getValue());
		dayInfos.setWeightEvening(weightEveningDS.getValue());
		dayInfos.setTemperature(temperatureDS.getValue());
		dayInfos.setFluidsIntake(fluidsIntakeDS.getValue());
		dayInfos.setCkMorning(ckMorningDS.getIntValue());
		dayInfos.setCkLunch(ckLunchDS.getIntValue());
		dayInfos.setCkEvening(ckEveningDS.getIntValue());
		dayInfos.setUreaMorning(ureaMorningDS.getIntValue());
		dayInfos.setUreaLunch(ureaLunchDS.getIntValue());
		dayInfos.setUreaEvening(ureaEveningDS.getIntValue());
		dayInfos.setGlucoseMorning(glucoseMorningDS.getIntValue());
		dayInfos.setGlucoseLunch(glucoseLunchDS.getIntValue());
		dayInfos.setGlucoseEvening(glucoseEveningDS.getIntValue());
		dayInfos.setHb(hbDS.getIntValue());
		dayInfos.setHkt(hktDS.getIntValue());

		// get values from Stars
		dayInfos.setSleepingQuality(sleepingQualityS.getValue());
		dayInfos.setTiredness(tirednessS.getValue());
		dayInfos.setFeeling(feelingS.getValue());
		dayInfos.setTrainingIntensity(trainingIntensityS.getValue());
		dayInfos.setTrainingValuation(trainingValuationS.getValue());

		getLogic().getScheduleLogic().saveDayInfos(dayInfos);
		refresh();
	}

	public void onPrefill(ActionEvent event) {
		// infos from yesterday
		Date yesterday = getLogic().getScheduleLogic().addDaysToDate(date, -1);
		DayInfos yesterdayInfos = getLogic().getScheduleLogic().getDayInfos(athleteId, yesterday);
		if(yesterdayInfos==null) return;
		// copy only defined values
		this.dayInfos.setRestingHr(yesterdayInfos.getRestingHr());
	}

	// DecSpinner instances
	private DecSpinner weightMorningDS = new DecSpinner();
	public DecSpinner getWeightMorningDS() {return weightMorningDS;}
	public void setWeightMorningDS(DecSpinner weightMorningDS) {this.weightMorningDS = weightMorningDS;}

	private DecSpinner weightEveningDS = new DecSpinner();
	public DecSpinner getWeightEveningDS() {return weightEveningDS;}
	public void setWeightEveningDS(DecSpinner weightEveningDS) {this.weightEveningDS = weightEveningDS;}

	private DecSpinner temperatureDS = new DecSpinner();
	public DecSpinner getTemperatureDS() {return temperatureDS;}
	public void setTemperatureDS(DecSpinner temperatureDS) {this.temperatureDS = temperatureDS;}

	private DecSpinner fluidsIntakeDS = new DecSpinner();
	public DecSpinner getFluidsIntakeDS() {return fluidsIntakeDS;}
	public void setFluidsIntakeDS(DecSpinner fluidsIntakeDS) {this.fluidsIntakeDS = fluidsIntakeDS;}

	private DecSpinner ckMorningDS = new DecSpinner();
	public DecSpinner getCkMorningDS() {return ckMorningDS;}
	public void setCkMorningDS(DecSpinner ckMorningDS) {this.ckMorningDS = ckMorningDS;}

	private DecSpinner ckLunchDS = new DecSpinner();
	public DecSpinner getCkLunchDS() {return ckLunchDS;}
	public void setCkLunchDS(DecSpinner ckLunchDS) {this.ckLunchDS = ckLunchDS;}

	private DecSpinner ckEveningDS = new DecSpinner();
	public DecSpinner getCkEveningDS() {return ckEveningDS;}
	public void setCkEveningDS(DecSpinner ckEveningDS) {this.ckEveningDS = ckEveningDS;}

	private DecSpinner ureaMorningDS = new DecSpinner();
	public DecSpinner getUreaMorningDS() {return ureaMorningDS;}
	public void setUreaMorningDS(DecSpinner ureaMorningDS) {this.ureaMorningDS = ureaMorningDS;}

	private DecSpinner ureaLunchDS = new DecSpinner();
	public DecSpinner getUreaLunchDS() {return ureaLunchDS;}
	public void setUreaLunchDS(DecSpinner ureaLunchDS) {this.ureaLunchDS = ureaLunchDS;}

	private DecSpinner ureaEveningDS = new DecSpinner();
	public DecSpinner getUreaEveningDS() {return ureaEveningDS;}
	public void setUreaEveningDS(DecSpinner ureaEveningDS) {this.ureaEveningDS = ureaEveningDS;}

	private DecSpinner glucoseMorningDS = new DecSpinner();
	public DecSpinner getGlucoseMorningDS() {return glucoseMorningDS;}
	public void setGlucoseMorningDS(DecSpinner glucoseMorningDS) {this.glucoseMorningDS = glucoseMorningDS;}

	private DecSpinner glucoseLunchDS = new DecSpinner();
	public DecSpinner getGlucoseLunchDS() {return glucoseLunchDS;}
	public void setGlucoseLunchDS(DecSpinner glucoseLunchDS) {this.glucoseLunchDS = glucoseLunchDS;}

	private DecSpinner glucoseEveningDS = new DecSpinner();
	public DecSpinner getGlucoseEveningDS() {return glucoseEveningDS;}
	public void setGlucoseEveningDS(DecSpinner glucoseEveningDS) {this.glucoseEveningDS = glucoseEveningDS;}

	private DecSpinner hbDS = new DecSpinner();
	public DecSpinner getHbDS() {return hbDS;}
	public void setHbDS(DecSpinner hbDS) {this.hbDS = hbDS;}

	private DecSpinner hktDS = new DecSpinner();
	public DecSpinner getHktDS() {return hktDS;}
	public void setHktDS(DecSpinner hktDS) {this.hktDS = hktDS;}

	// Star instances
	private Star sleepingQualityS = new Star();
	public Star getSleepingQualityS() {return sleepingQualityS;}
	public void setSleepingQualityS(Star sleepingQualityS) {this.sleepingQualityS = sleepingQualityS;}

	private Star tirednessS = new Star();
	public Star getTirednessS() {return tirednessS;}
	public void setTirednessS(Star tirednessS) {this.tirednessS = tirednessS;}

	private Star feelingS = new Star();
	public Star getFeelingS() {return feelingS;}
	public void setFeelingS(Star feelingS) {this.feelingS = feelingS;}

	private Star trainingIntensityS = new Star();
	public Star getTrainingIntensityS() {return trainingIntensityS;}
	public void setTrainingIntensityS(Star trainingIntensityS) {this.trainingIntensityS = trainingIntensityS;}

	private Star trainingValuationS = new Star();
	public Star getTrainingValuationS() {return trainingValuationS;}
	public void setTrainingValuationS(Star trainingValuationS) {this.trainingValuationS = trainingValuationS;}

}
