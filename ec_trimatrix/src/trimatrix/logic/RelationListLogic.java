package trimatrix.logic;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import trimatrix.db.DAOLayer;
import trimatrix.db.PersonsHaveDoctors;
import trimatrix.db.PersonsHaveRelations;
import trimatrix.relations.IRelationData;
import trimatrix.relations.IRelationObject;
import trimatrix.services.ServiceLayer;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;

public class RelationListLogic {
	public static final Log logger = LogFactory.getLog(RelationListLogic.class);
	
	private ServiceLayer serviceLayer;
	private DAOLayer daoLayer;
	
	public List<SGridMetaData> getGridMetaData(Constants.Relation relation) {
		return serviceLayer.getResolverService().getGridMetaData(relation);
	}
	
	public List<IRelationData> getData(Constants.Relation relation) {
		return serviceLayer.getResolverService().getData(relation);
	}	
	
	public boolean delete(Constants.Relation relation, String id) {
		return serviceLayer.getResolverService().delete(relation, id);
	}
	
	public IRelationObject create(Constants.Relation relation) {
		return serviceLayer.getResolverService().create(relation);
	}
	
	public IRelationObject get(Constants.Relation relation, String id) {
		return serviceLayer.getResolverService().get(relation, id);
	}
	
	public void save(Constants.Relation relation, IRelationObject relationObject) {
		serviceLayer.getResolverService().save(relation, relationObject);
	}
	
	public void reload(Constants.Relation relation, IRelationObject relationObject) {
		serviceLayer.getResolverService().reload(relation, relationObject);
	}
	
	public boolean setStandard(Constants.Relation relation, String id) {
		// get object
		IRelationObject relationObject = get(relation, id);		
		// check if flag is already set
		if(relationObject.getStandard()) {
			// remove flag
			relationObject.setStandard(false);
			save(relation, relationObject);
		} else {
			// set flag and remove flags from other instances
			IRelationObject example;
			switch (relation) {
			case DOCTOR:
				List<PersonsHaveDoctors> phds;
				example = new PersonsHaveDoctors();
				example.setPartner1(relationObject.getPartner1());
				example.setReltypKey(relationObject.getReltypKey());
				example.setStandard(true);
				phds = daoLayer.getPersonsHaveDoctorsDAO().findByExample(example);
				for(PersonsHaveDoctors phd : phds) {
					phd.setStandard(false);
					daoLayer.getPersonsHaveDoctorsDAO().merge(phd);
				}				
				break;
			case COACH:
				List<PersonsHaveRelations> phrs;
				example = new PersonsHaveRelations();
				example.setPartner1(relationObject.getPartner1());
				example.setReltypKey(relationObject.getReltypKey());
				example.setStandard(true);
				phrs = daoLayer.getPersonsHaveRelationsDAO().findByExample(example);
				for(PersonsHaveRelations phr : phrs) {
					phr.setStandard(false);
					daoLayer.getPersonsHaveRelationsDAO().merge(phr);
				}				
				break;
			default:
				logger.warn("SETSTANDARD method not implemented for relation " + relation.name());
				return false;
			}	
			// remove flag
			relationObject.setStandard(true);
			save(relation, relationObject);			
		}
		return true;
	}

	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}

	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}
}
