package trimatrix.logic;

import java.util.List;

import trimatrix.db.DAOLayer;
import trimatrix.services.ServiceLayer;
import trimatrix.structures.SPersonPersonRelation;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;

public class RelationListLogic {
	private ServiceLayer serviceLayer;
	private DAOLayer daoLayer;
	
	public List<SPersonPersonRelation> getPersonPersonRelations(Constants.Relation relation) {
		return serviceLayer.getSqlExecutorService().getPersonPersonRelation(relation);
	}
	
	public boolean deletePersonPersonRelation(Constants.Relation relation, String id) {
		try {
			daoLayer.getPersonsHaveRelationsDAO().delete(daoLayer.getPersonsHaveRelationsDAO().findById(id));
		} catch (Exception ex) {
			Dictionary.logger.error(ex.toString());
			return false;
		}		
		return true;
	}
	
	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}

	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}	
}
