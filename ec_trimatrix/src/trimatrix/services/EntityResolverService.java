package trimatrix.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import trimatrix.entities.IEntity;
import trimatrix.entities.IEntityData;
import trimatrix.entities.IEntityObject;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;

public final class EntityResolverService {
	private IEntity userEntity;
	private IEntity personEntity;
	private IEntity doctorEntity;
	private IEntity attachmentEntity;

	/**
	 * Get metadata for entity
	 * @param entity entity
	 * @return metadata
	 */
	public List<SGridMetaData> getGridMetaData(Constants.Entity entity) {
		if (entity.getBase()==Constants.Entity.USER) {
			return userEntity.getGridMetaData();
		} else if (entity.getBase()==Constants.Entity.PERSON) {
			return personEntity.getGridMetaData();
		} else if (entity.getBase()==Constants.Entity.DOCTOR) {
			return doctorEntity.getGridMetaData();
		} else if (entity.getBase()==Constants.Entity.ATTACHMENT) {
			return attachmentEntity.getGridMetaData();
		}
		Dictionary.logger.warn("GETMETADATA : Entity " + entity.toString() + " not valid!");
		return new ArrayList<SGridMetaData>();
	}
	
	public List<IEntityData> getData(Constants.Entity entity) {
		if (entity.getBase()==Constants.Entity.USER) {
			return userEntity.getData(entity);
		} else if (entity.getBase()==Constants.Entity.PERSON) {
			return personEntity.getData(entity);
		} else if (entity.getBase()==Constants.Entity.DOCTOR) {
			return doctorEntity.getData(entity);
		} else if (entity.getBase()==Constants.Entity.ATTACHMENT) {
			return attachmentEntity.getData(entity);
		}
		Dictionary.logger.warn("GETDATA : Entity " + entity.toString() + " not valid!");
		return new ArrayList<IEntityData>();
	}
	
	public boolean delete(Constants.Entity entity, String id) {
		if (entity.getBase()==Constants.Entity.USER) {
			return userEntity.delete(id);
		} else if (entity.getBase()==Constants.Entity.PERSON) {
			return personEntity.delete(id);
		} else if (entity.getBase()==Constants.Entity.DOCTOR) {
			return doctorEntity.delete(id);
		} else if (entity.getBase()==Constants.Entity.ATTACHMENT) {
			return attachmentEntity.delete(id);
		}
		Dictionary.logger.warn("DELETE : Entity " + entity.toString() + " not valid!");
		return false;
	}
	
	public IEntityObject create(Constants.Entity entity) {
		if (entity.getBase()==Constants.Entity.USER) {
			return userEntity.create();
		} else if (entity.getBase()==Constants.Entity.PERSON) {
			return personEntity.create();
		} else if (entity.getBase()==Constants.Entity.DOCTOR) {
			return doctorEntity.create();
		} else if (entity.getBase()==Constants.Entity.ATTACHMENT) {
			return attachmentEntity.create();
		}
		Dictionary.logger.warn("CREATE : Entity " + entity.toString() + " not valid!");
		return null;
	}
	
	public IEntityObject get(Constants.Entity entity, String id) {
		if (entity.getBase()==Constants.Entity.USER) {
			return userEntity.get(id);
		} else if (entity.getBase()==Constants.Entity.PERSON) {
			return personEntity.get(id);
		}  else if (entity.getBase()==Constants.Entity.DOCTOR) {
			return doctorEntity.get(id);
		} else if (entity.getBase()==Constants.Entity.ATTACHMENT) {
			return attachmentEntity.get(id);
		}
		Dictionary.logger.warn("GET : Entity " + entity.toString() + " not valid!");
		return null;
	}
	
	public void save(Constants.Entity entity, IEntityObject entityObject) {
		if (entity.getBase()==Constants.Entity.USER) {
			userEntity.save(entityObject);
		} else if (entity.getBase()==Constants.Entity.PERSON) {
			personEntity.save(entityObject);
		} else if (entity.getBase()==Constants.Entity.DOCTOR) {
			doctorEntity.save(entityObject);
		} else if (entity.getBase()==Constants.Entity.ATTACHMENT) {
			attachmentEntity.save(entityObject);
		}
		Dictionary.logger.warn("SAVE : Entity " + entity.toString() + " not valid!");
	}
	
	public void reload(Constants.Entity entity, IEntityObject entityObject) {
		if (entity.getBase()==Constants.Entity.USER) {
			userEntity.reload(entityObject);
		} else if (entity.getBase()==Constants.Entity.PERSON) {
			personEntity.reload(entityObject);
		} else if (entity.getBase()==Constants.Entity.DOCTOR) {
			doctorEntity.reload(entityObject);
		} else if (entity.getBase()==Constants.Entity.ATTACHMENT) {
			attachmentEntity.reload(entityObject);
		}
		Dictionary.logger.warn("RELOAD : Entity " + entity.toString() + " not valid!");
	}
	
	public void setUserEntity(IEntity userEntity) {
		this.userEntity = userEntity;
	}

	public void setPersonEntity(IEntity personEntity) {
		this.personEntity = personEntity;
	}		
	
	public void setDoctorEntity(IEntity doctorEntity) {
		this.doctorEntity = doctorEntity;
	}

	public void setAttachmentEntity(IEntity attachmentEntity) {
		this.attachmentEntity = attachmentEntity;
	}

	public static EntityResolverService getFromApplicationContext(ApplicationContext ctx) {
		return (EntityResolverService) ctx.getBean("entityResolverService");
	}	
}
