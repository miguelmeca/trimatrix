package trimatrix.controls;

import javax.faces.event.ActionEvent;

public class Star {
	private String imageOn;
	public String getImageOn() {return imageOn;}
	public void setImageOn(String imageOn) {this.imageOn = imageOn;}
	
	private String imageOff;
	public String getImageOff() {return imageOff;}
	public void setImageOff(String imageOff) {this.imageOff = imageOff;}

	private boolean star1;
	public String getStar1() { return star2 ? imageOn : imageOff; }
	
	private boolean star2;
	public String getStar2() { return star1 ? imageOn : imageOff; }
	
	public void onStar1(ActionEvent event) {
		star1 = !star1;
	}
	
	public void onStar2(ActionEvent event) {
		star2 = !star2;
	}
}
