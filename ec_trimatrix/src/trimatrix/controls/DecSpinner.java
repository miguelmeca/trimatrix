package trimatrix.controls;

import javax.faces.event.ActionEvent;

import org.apache.commons.lang.math.NumberUtils;

public class DecSpinner {
	private double value;
	public double getValue() {return value;}
	public void setValue(double value) {
		this.value = value;
	}
	
	private Double step;		
	public String getStep() {return step.toString();}
	public void setStep(String step) {
		if(NumberUtils.isNumber(step)) {
			this.step = Double.valueOf(step);
		} else {
			this.step = new Double(0);
		}			
	}
	
	public void increment(ActionEvent event) {
		if(step==null) return;
		value += step;
	}
	
	public void decrement(ActionEvent event) {
		if(step==null) return;
		value -= step;
	}
}
