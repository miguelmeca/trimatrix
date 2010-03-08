package trimatrix.controls;

import javax.faces.event.ActionEvent;

import org.apache.commons.lang.math.NumberUtils;

public class DecSpinner {
	private Double value;
	public Double getValue() {return value;}
	public void setValue(Double value) {
		this.value = value;
	}	
	public void setValue(Integer value) {
		if(value==null) {
			this.value = null;
		} else {
			this.value = value.doubleValue();
		}		
	}
	
	public Integer getIntValue() {
		if(value==null) return null;
		return value.intValue();
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
