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
	
	public boolean deleteEntity(Constants.Entity entity, String id) {
		return serviceLayer.getEntityResolverService().deleteEntity(entity, id);
	}
	
	public IEntityObject createEntity(Constants.Entity entity) {
		return serviceLayer.getEntityResolverService().createEntity(entity);
	}
	
	public IEntityObject getEntity(Constants.Entity entity, String id) {
		return serviceLayer.getEntityResolverService().getEntity(entity, id);
	}
	
	public void saveEntity(Constants.Entity entity, IEntityObject entityObject) {
		serviceLayer.getEntityResolverService().saveEntity(entity, entityObject);
	}
	
	public void reloadEntity(Constants.Entity entity, IEntityObject entityObject) {
		serviceLayer.getEntityResolverService().reloadEntity(entity, entityObject);
	}

	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}
}
