package trimatrix.ui.utils;

import static trimatrix.utils.Constants.EMPTY;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.eclnt.jsfserver.defaultscreens.ISetId;
import org.eclnt.jsfserver.defaultscreens.IdTextSelection;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;

import trimatrix.db.ImportTemplates;
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
