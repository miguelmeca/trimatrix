package trimatrix.logic;

import java.util.List;

import trimatrix.entities.IEntityData;
import trimatrix.entities.IEntityObject;
import trimatrix.services.EntityResolverService;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;

public class EntityListLogic {
	private EntityResolverService entityResolverService;	
	
	/**
	 * Get metadata of entity
	 * @param entity entity
	 * @return metadata of entity
	 */
	public List<SGridMetaData> getGridMetaData(Constants.Entity entity) {
		return entityResolverService.getGridMetaData(entity);
	}
	
	public List<IEntityData> getData(Constants.Entity entity) {
		return entityResolverService.getData(entity);
	}
	
	public boolean deleteEntity(Constants.Entity entity, String id) {
		return entityResolverService.deleteEntity(entity, id);
	}
	
	public IEntityObject createEntity(Constants.Entity entity) {
		return entityResolverService.createEntity(entity);
	}
	
	public IEntityObject getEntity(Constants.Entity entity, String id) {
		return entityResolverService.getEntity(entity, id);
	}
	
	public void saveEntity(Constants.Entity entity, IEntityObject entityObject) {
		entityResolverService.saveEntity(entity, entityObject);
	}
	
	public void reloadEntity(Constants.Entity entity, IEntityObject entityObject) {
		entityResolverService.reloadEntity(entity, entityObject);
	}
	
	public void setEntityResolverService(EntityResolverService entityResolverService) {
		this.entityResolverService = entityResolverService;
	}

}
