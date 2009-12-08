package trimatrix.utils.json;
import java.util.List;

import trimatrix.utils.Helper;

import com.twolattes.json.Entity;
import com.twolattes.json.Json;
import com.twolattes.json.Marshaller;
import com.twolattes.json.TwoLattes;
import com.twolattes.json.Value;

@Entity
public class IntegerList {
	public static Marshaller<IntegerList> marshaller = TwoLattes.createMarshaller(IntegerList.class);
		
	public static IntegerList getInstance(String json) {
		if(Helper.isEmpty(json)) return new IntegerList();
		Json.Object jsonObject = (Json.Object)Json.fromString(json);
		return marshaller.unmarshall(jsonObject);
	}
	
	@Value
	private List<Integer> list;	
	public List<Integer> getList() {return list;}
	public void setList(List<Integer> list) {this.list = list;}
	
	public IntegerList() { }
	
	public IntegerList(List<Integer> list) {
		this.list = list;
	}	
	
	public Integer get(int index) {
		if(list==null) return null;
		return list.get(index);
	}
	
	@Override
	public String toString() {
		return marshaller.marshall(this).toString();
	}
}
