package trimatrix.controls;

import java.util.BitSet;

import javax.faces.event.ActionEvent;

public class Star {

	public Star(int value) {
		bitSet = new BitSet();
		setValue(value);
	}

	private BitSet bitSet;

	public int getValue() {
		return bitSet.length();
	}

	public void setValue(int value) {
		if(getValue()==value) {
			bitSet.clear();
		} else {
			bitSet.clear();
			bitSet.set(0, value);
		}
	}

	private String imageOn = "/images/icons/star_full.png";
	private String imageOff = "/images/icons/star_none.png";

	public String getStar1() { return bitSet.get(0) ? imageOn : imageOff; }
	public String getStar2() { return bitSet.get(1) ? imageOn : imageOff; }
	public String getStar3() { return bitSet.get(2) ? imageOn : imageOff; }
	public String getStar4() { return bitSet.get(3) ? imageOn : imageOff; }
	public String getStar5() { return bitSet.get(4) ? imageOn : imageOff; }

	public void onStar1(ActionEvent event) {setValue(1);}
	public void onStar2(ActionEvent event) {setValue(2);}
	public void onStar3(ActionEvent event) {setValue(3);}
	public void onStar4(ActionEvent event) {setValue(4);}
	public void onStar5(ActionEvent event) {setValue(5);}
}
