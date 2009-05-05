package trimatrix.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import trimatrix.entities.IEntity;
import trimatrix.entities.IEntityData;
import trimatrix.entities.IEntityObject;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;

public final class EntityResolverService {
	private IEntity userEntity;
	private IEntity personEntity;

	/**
	 * Get metadata for entity
	 * @param entity entity
	 * @return metadata
	 */
	public List<SGridMetaData> getGridMetaData(Constants.Entity entity) {
		if (entity==Constants.Entity.USER || entity.getBase()==Constants.Entity.USER) {
			return userEntity.getGridMetaData();
		} else if (entity==Constants.Entity.PERSON || entity.getBase()==Constants.Entity.PERSON) {
			return personEntity.getGridMetaData();
		} 
		return new ArrayList<SGridMetaData>();
	}
	
	public List<IEntityData> getData(Constants.Entity entity) {
		if (entity==Constants.Entity.USER || entity.getBase()==Constants.Entity.USER) {
			return userEntity.getData(entity);
		} else if (entity==Constants.Entity.PERSON || entity.getBase()==Constants.Entity.PERSON) {
			return personEntity.getData(entity);
		} 
		return new ArrayList<IEntityData>();
	}
	
	public boolean deleteEntity(Constants.Entity entity, String id) {
		if (entity==Constants.Entity.USER || entity.getBase()==Constants.Entity.USER) {
			return userEntity.delete(id);
		} else if (entity==Constants.Entity.PERSON || entity.getBase()==Constants.Entity.PERSON) {
			return personEntity.delete(id);
		} 
		return false;
	}
	
	public IEntityObject createEntity(Constants.Entity entity) {
		if (entity==Constants.Entity.USER || entity.getBase()==Constants.Entity.USER) {
			return userEntity.create();
		} else if (entity==Constants.Entity.PERSON || entity.getBase()==Constants.Entity.PERSON) {
			return personEntity.create();
		} 
		return null;
	}
	
	public IEntityObject getEntity(Constants.Entity entity, String id) {
		if (entity==Constants.Entity.USER || entity.getBase()==Constants.Entity.USER) {
			return userEntity.get(id);
		} else if (entity==Constants.Entity.PERSON || entity.getBase()==Constants.Entity.PERSON) {
			return personEntity.get(id);
		} 
		return null;
	}
	
	public void saveEntity(Constants.Entity entity, IEntityObject entityObject) {
		if (entity==Constants.Entity.USER || entity.getBase()==Constants.Entity.USER) {
			userEntity.save(entityObject);
		} else if (entity==Constants.Entity.PERSON || entity.getBase()==Constants.Entity.PERSON) {
			personEntity.save(entityObject);
		} 
	}
	
	public void reloadEntity(Constants.Entity entity, IEntityObject entityObject) {
		if (entity==Constants.Entity.USER || entity.getBase()==Constants.Entity.USER) {
			userEntity.reload(entityObject);
		} else if (entity==Constants.Entity.PERSON || entity.getBase()==Constants.Entity.PERSON) {
			personEntity.reload(entityObject);
		} 
	}
	
	public void setUserEntity(IEntity userEntity) {
		this.userEntity = userEntity;
	}

	public void setPersonEntity(IEntity personEntity) {
		this.personEntity = personEntity;
	}	
	
	public static EntityResolverService getFromApplicationContext(ApplicationContext ctx) {
		return (EntityResolverService) ctx.getBean("entityResolverService");
	}
	
}
