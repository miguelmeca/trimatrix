package trimatrix.ui.tests;

import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;

public class AGridItem extends FIXGRIDItem implements java.io.Serializable {
	protected Integer step;
	protected String step_time = "00:00";
	protected String time_total = "00:00";
	protected Double lactate;
	protected Integer hr;
	protected Integer o2_absorption;
	protected Integer co2_emission;
	protected Double rq;
	
	public AGridItem(Integer step) {
		this.step = step;
	}
	
	public Integer getStep() {return step;}
	public void setStep(Integer step) { this.step = step; }	
	
	public String getStep_time() { return step_time; }
	public void setStep_time(String step_time) { this.step_time = step_time; }

	public String getTime_total() {	return time_total; }
	public void setTime_total(String time_total) { this.time_total = time_total; }

	public Double getLactate() {return lactate;}
	public void setLactate(Double lactate) {this.lactate = lactate;}
	
	public Integer getHr() {return hr;}
	public void setHr(Integer hr) {this.hr = hr;}
	
	public Integer getO2_absorption() {return o2_absorption;}		
	public void setO2_absorption(Integer o2_absorption) {this.o2_absorption = o2_absorption;}
	
	public Integer getCo2_emission() {return co2_emission;}
	public void setCo2_emission(Integer co2_emission) {this.co2_emission = co2_emission;}
	
	public Double getRq() {return rq;}
	public void setRq(Double rq) {this.rq = rq;}
}
