package trimatrix.logic;

import java.util.List;

import trimatrix.entities.IEntityData;
import trimatrix.entities.IEntityObject;
import trimatrix.services.ServiceLayer;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;

public class EntityListLogic {	
	private ServiceLayer serviceLayer;
		
	/**
	 * Get metadata of entity
	 * @param entity entity
	 * @return metadata of entity
	 */
	public List<SGridMetaData> getGridMetaData(Constants.Entity entity) {
		return serviceLayer.getEntityResolverService().getGridMetaData(entity);
	}
	
	public List<IEntityData> getData(Constants.Entity entity) {
		return serviceLayer.getEntityResolverService().getData(entity);
	}	
	
	public List<IEntityData> getData(Constants.Entity entity, String personId) {
		if(personId==null || personId.length() == 0) {
			return serviceLayer.getEntityResolverService().getData(entity);
		} 
		return serviceLayer.getEntityResolverService().getData(entity, personId);
	}
	
	public boolean delete(Constants.Entity entity, String id) {
		return serviceLayer.getEntityResolverService().delete(entity, id);
	}
	
	public IEntityObject create(Constants.Entity entity) {
		return serviceLayer.getEntityResolverService().create(entity);
	}
	
	public IEntityObject get(Constants.Entity entity, String id) {
		return serviceLayer.getEntityResolverService().get(entity, id);
	}
	
	public void save(Constants.Entity entity, IEntityObject entityObject) {
		serviceLayer.getEntityResolverService().save(entity, entityObject);
	}
	
	public void reload(Constants.Entity entity, IEntityObject entityObject) {
		serviceLayer.getEntityResolverService().reload(entity, entityObject);
	}

	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}
}
