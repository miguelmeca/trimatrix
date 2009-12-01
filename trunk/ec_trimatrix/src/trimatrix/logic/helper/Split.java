package trimatrix.logic.helper;

import com.twolattes.json.Entity;
import com.twolattes.json.Value;

@Entity
public class Split {
	@Value
	String time = "00:00";
	@Value
	int strokes;
	
	public Split() { };
	
	public Split(String time, int strokes) {
		this.time = time;
		this.strokes = strokes;
	}
	
	public String getTime() {return time;}
	public void setTime(String time) {this.time = time;}
	
	public int getStrokes() {return strokes;}
	public void setStrokes(int strokes) {this.strokes = strokes;}	
}
