package trimatrix.logic;

import java.util.List;
import java.util.Vector;

import trimatrix.db.DAOLayer;
import trimatrix.db.Labels;
import trimatrix.services.ServiceLayer;

public class LabelLogic {
	private DAOLayer daoLayer;
	private ServiceLayer serviceLayer;
	
	public List<Labels> getAllLabels() {
		List<Labels> labels = daoLayer.getLabelsDAO().findByPersonId(serviceLayer.getDictionaryService().getMyPerson().getId());
		if(labels==null) return new Vector<Labels>();
		return labels;
	}
	
	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}
	
	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}	
}
