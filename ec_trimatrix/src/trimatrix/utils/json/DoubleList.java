package trimatrix.utils.json;
import java.util.List;

import trimatrix.utils.Helper;

import com.twolattes.json.Entity;
import com.twolattes.json.Json;
import com.twolattes.json.Marshaller;
import com.twolattes.json.TwoLattes;
import com.twolattes.json.Value;

@Entity
public class DoubleList {
	public static Marshaller<DoubleList> marshaller = TwoLattes.createMarshaller(DoubleList.class);
		
	public static DoubleList getInstance(String json) {
		if(Helper.isEmpty(json)) return new DoubleList();
		Json.Object jsonObject = (Json.Object)Json.fromString(json);
		return marshaller.unmarshall(jsonObject);
	}
	
	@Value
	private List<Double> list;	
	public List<Double> getList() {return list;}
	public void setList(List<Double> list) {this.list = list;}
	
	public DoubleList() { }
	
	public DoubleList(List<Double> list) {
		this.list = list;
	}	
	
	public Double get(int index) {
		if(list==null) return null;
		return list.get(index);
	}
	
	@Override
	public String toString() {
		return marshaller.marshall(this).toString();
	}
}
