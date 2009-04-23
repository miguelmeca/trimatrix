package trimatrix.services;

import java.util.List;

import org.eclnt.jsfserver.elements.util.ValidValuesBinding;

import trimatrix.structures.SValueList;
import trimatrix.utils.Constants;

public class ValueListBindingService {
	private SQLExecutorService sqlExecutorService;
	
	public ValidValuesBinding getVVBinding(Constants.ValueList valueList, String language) {
		ValidValuesBinding vvb = new ValidValuesBinding();
		List<SValueList> list = sqlExecutorService.getValueList(valueList, language);
		for (SValueList entry : list) {
			vvb.addValidValue(entry.key, entry.description);
		}
		return vvb;
	}
	
	public void setSqlExecutorService(SQLExecutorService sqlExecutorService) {
		this.sqlExecutorService = sqlExecutorService;
	}
}
