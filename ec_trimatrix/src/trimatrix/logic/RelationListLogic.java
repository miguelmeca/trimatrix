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
		return serviceLayer.getResolverService().getGridMetaData(relation);
	}
	
	public List<IRelationData> getData(Constants.Relation relation) {
		return serviceLayer.getResolverService().getData(relation);
	}	
	
	public List<IRelationData> getData(Constants.Relation relation, String personId) {
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

	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}
}
