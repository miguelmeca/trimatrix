package trimatrix.ui.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.eclnt.jsfserver.elements.util.ValidValuesBinding;

import trimatrix.services.ServiceLayer;
import trimatrix.utils.Context;

public class BeanHelper implements Serializable {
	private ServiceLayer serviceLayer = ServiceLayer.getFromApplicationContext(Context.getInstance());

	public Map<String, ValidValuesBinding> vvb = new HashMap<String, ValidValuesBinding>() {
		public ValidValuesBinding get(Object key) {
			return serviceLayer.getValueListBindingService().getVVBinding((String)key);
		}
	};

	public Map<String, String> dvb = new HashMap<String, String>() {
		public String get(Object key) {
			return serviceLayer.getDefaultValueBindingService().getDVBinding((String)key);

		}
	};

	public Map<String, ValidValuesBinding> getVvb() {
		return vvb;
	}

	public Map<String, String> getDvb() {
		return dvb;
	}
}
