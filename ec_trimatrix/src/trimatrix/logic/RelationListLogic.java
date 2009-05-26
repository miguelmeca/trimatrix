package trimatrix.logic;

import java.util.List;

import trimatrix.relations.IRelationData;
import trimatrix.relations.IRelationObject;
import trimatrix.services.ServiceLayer;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;

public class RelationListLogic {
	private ServiceLayer serviceLayer;
	
	public List<SGridMetaData> getGridMetaData(Constants.Relation relation) {
		return serviceLayer.getRelationResolverService().getGridMetaData(relation);
	}
	
	public List<IRelationData> getData(Constants.Relation relation) {
		return serviceLayer.getRelationResolverService().getData(relation);
	}	
	
	public boolean delete(Constants.Relation relation, String id) {
		return serviceLayer.getRelationResolverService().delete(relation, id);
	}
	
	public IRelationObject create(Constants.Relation relation) {
		return serviceLayer.getRelationResolverService().create(relation);
	}
	
	public IRelationObject get(Constants.Relation relation, String id) {
		return serviceLayer.getRelationResolverService().get(relation, id);
	}
	
	public void save(Constants.Relation relation, IRelationObject relationObject) {
		serviceLayer.getRelationResolverService().save(relation, relationObject);
	}
	
	public void reload(Constants.Relation relation, IRelationObject relationObject) {
		serviceLayer.getRelationResolverService().reload(relation, relationObject);
	}

	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}
}
