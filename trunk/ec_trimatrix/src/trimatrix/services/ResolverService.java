package trimatrix.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import trimatrix.entities.IEntity;
import trimatrix.entities.IEntityData;
import trimatrix.entities.IEntityObject;
import trimatrix.relations.IRelation;
import trimatrix.relations.IRelationData;
import trimatrix.relations.IRelationObject;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;

public final class ResolverService {
	public static final Log logger = LogFactory.getLog(ResolverService.class);
	
	private IEntity userEntity;
	private IEntity personEntity;
	private IEntity doctorEntity;
	private IEntity attachmentEntity;
	private IEntity testEntity;
	private IRelation personPersonRelation;
	private IRelation personDoctorRelation;
	private IRelation personAttachmentRelation;

	// Entities	
	public List<SGridMetaData> getGridMetaData(Constants.Entity entity) {
		switch (entity.getBase()) {
		case USER:
			return userEntity.getGridMetaData();
		case PERSON:
			return personEntity.getGridMetaData();
		case DOCTOR:
			return doctorEntity.getGridMetaData();
		case ATTACHMENT:
			return attachmentEntity.getGridMetaData();
		case TEST:
			return testEntity.getGridMetaData();
		}
		logger.warn("GETMETADATA : Entity " + entity.toString() + " not valid!");
		return new ArrayList<SGridMetaData>();
	}
	
	public List<IEntityData> getData(Constants.Entity entity) {
		switch (entity.getBase()) {
		case USER:
			return userEntity.getData(entity);
		case PERSON:
			return personEntity.getData(entity);
		case DOCTOR:
			return doctorEntity.getData(entity);
		case ATTACHMENT:
			return attachmentEntity.getData(entity);
		case TEST:
			return testEntity.getData(entity);
		}
		logger.warn("GETDATA : Entity " + entity.toString() + " not valid!");
		return new ArrayList<IEntityData>();
	}
	
	public List<IEntityData> getData(Constants.Entity entity, String personId) {
		switch (entity.getBase()) {
		case USER:
			return userEntity.getData(entity, personId);
		case PERSON:
			return personEntity.getData(entity, personId);
		case DOCTOR:
			return doctorEntity.getData(entity, personId);
		case ATTACHMENT:
			return attachmentEntity.getData(entity, personId);
		case TEST:
			return testEntity.getData(entity, personId);
		}
		logger.warn("GETDATA : Entity " + entity.toString() + " not valid!");
		return new ArrayList<IEntityData>();
	}
	
	public List<IEntityData> getData(Constants.Entity entity, List<String> ids) {
		switch (entity.getBase()) {
		case USER:
			return userEntity.getData(ids);
		case PERSON:
			return personEntity.getData(ids);
		case DOCTOR:
			return doctorEntity.getData(ids);
		case ATTACHMENT:
			return attachmentEntity.getData(ids);
		case TEST:
			return testEntity.getData(ids);
		}
		logger.warn("GETDATA : Entity " + entity.toString() + " not valid!");
		return new ArrayList<IEntityData>();
	}
	
	public boolean delete(Constants.Entity entity, String id) {
		switch (entity.getBase()) {
		case USER:
			return userEntity.delete(id);
		case PERSON:
			return personEntity.delete(id);
		case DOCTOR:
			return doctorEntity.delete(id);
		case ATTACHMENT:
			return attachmentEntity.delete(id);
		case TEST:
			return testEntity.delete(id);
		}
		logger.warn("DELETE : Entity " + entity.toString() + " not valid!");
		return false;
	}
	
	public boolean delete(Constants.Entity entity, String id, String personId) {
		switch (entity.getBase()) {
		case PERSON:
			return personPersonRelation.delete(personId, id);
		case DOCTOR:
			return personDoctorRelation.delete(personId, id);
		case ATTACHMENT:
			return personAttachmentRelation.delete(personId, id);
		}
		logger.warn("DELETE : Entity " + entity.toString() + " not valid!");
		return false;
	}
	
	public IEntityObject create(Constants.Entity entity) {
		switch (entity.getBase()) {
		case USER:
			return userEntity.create();
		case PERSON:
			return personEntity.create();
		case DOCTOR:
			return doctorEntity.create();
		case ATTACHMENT:
			return attachmentEntity.create();
		case TEST:
			return testEntity.create();
		}
		logger.warn("CREATE : Entity " + entity.toString() + " not valid!");
		return null;
	}
	
	public IEntityObject get(Constants.Entity entity, String id) {
		switch (entity.getBase()) {
		case USER:
			return userEntity.get(id);
		case PERSON:
			return personEntity.get(id);
		case DOCTOR:
			return doctorEntity.get(id);
		case ATTACHMENT:
			return attachmentEntity.get(id);
		case TEST:
			return testEntity.get(id);
		}
		logger.warn("GET : Entity " + entity.toString() + " not valid!");
		return null;
	}
	
	public void save(Constants.Entity entity, IEntityObject entityObject) {
		switch (entity.getBase()) {
		case USER:
			userEntity.save(entityObject); break;
		case PERSON:
			personEntity.save(entityObject); break;
		case DOCTOR:
			doctorEntity.save(entityObject); break;
		case ATTACHMENT:
			attachmentEntity.save(entityObject); break;
		case TEST:
			testEntity.save(entityObject); break;
		}
		logger.warn("SAVE : Entity " + entity.toString() + " not valid!");
	}
	
	public void reload(Constants.Entity entity, IEntityObject entityObject) {
		switch (entity.getBase()) {
		case USER:
			userEntity.reload(entityObject); break;
		case PERSON:
			personEntity.reload(entityObject); break;
		case DOCTOR:
			doctorEntity.reload(entityObject); break;
		case ATTACHMENT:
			attachmentEntity.reload(entityObject); break;
		case TEST:
			testEntity.save(entityObject); break;
		}
		logger.warn("RELOAD : Entity " + entity.toString() + " not valid!");				
	}
	
	// Relations
	public List<SGridMetaData> getGridMetaData(Constants.Relation relation) {
		if (relation.getBase()==Constants.Relation.PERSONPERSON) {
			return personPersonRelation.getGridMetaData();
		} else if (relation.getBase()==Constants.Relation.PERSONDOCTOR) {
			return personDoctorRelation.getGridMetaData();
		} else if (relation.getBase()==Constants.Relation.PERSONATTACHMENT) {
			return personAttachmentRelation.getGridMetaData();
		}
		logger.warn("GETMETADATA : Relation " + relation.toString() + " not valid!");
		return new ArrayList<SGridMetaData>();
	}
	
	public List<IRelationData> getData(Constants.Relation relation) {
		if (relation.getBase()==Constants.Relation.PERSONPERSON) {
			return personPersonRelation.getData(relation);
		} else if (relation.getBase()==Constants.Relation.PERSONDOCTOR) {
			return personDoctorRelation.getData(relation);
		} else if (relation.getBase()==Constants.Relation.PERSONATTACHMENT) {
			return personAttachmentRelation.getData(relation);
		}
		logger.warn("GETDATA : Relation " + relation.toString() + " not valid!");
		return new ArrayList<IRelationData>();
	}
	
	public boolean delete(Constants.Relation relation, String id) {
		if (relation.getBase()==Constants.Relation.PERSONPERSON) {
			return personPersonRelation.delete(id);
		} else if (relation.getBase()==Constants.Relation.PERSONDOCTOR) {
			return personDoctorRelation.delete(id);
		} else if (relation.getBase()==Constants.Relation.PERSONATTACHMENT) {
			return personAttachmentRelation.delete(id);
		}
		logger.warn("DELETE : Relation " + relation.toString() + " not valid!");
		return false;
	}
	
	public IRelationObject create(Constants.Relation relation) {
		if (relation.getBase()==Constants.Relation.PERSONPERSON) {
			return personPersonRelation.create();
		} else if (relation.getBase()==Constants.Relation.PERSONDOCTOR) {
			return personDoctorRelation.create();
		} else if (relation.getBase()==Constants.Relation.PERSONATTACHMENT) {
			return personAttachmentRelation.create();
		}
		logger.warn("DELETE : Relation " + relation.toString() + " not valid!");
		return null;
	}
	
	public IRelationObject get(Constants.Relation relation, String id) {
		if (relation.getBase()==Constants.Relation.PERSONPERSON) {
			return personPersonRelation.get(id);
		} else if (relation.getBase()==Constants.Relation.PERSONDOCTOR) {
			return personDoctorRelation.get(id);
		} else if (relation.getBase()==Constants.Relation.PERSONATTACHMENT) {
			return personAttachmentRelation.get(id);
		}
		logger.warn("GET : Relation " + relation.toString() + " not valid!");
		return null;
	}
	
	public void save(Constants.Relation relation, IRelationObject relationObject) {
		if (relation.getBase()==Constants.Relation.PERSONPERSON) {
			personPersonRelation.save(relationObject);
		} else if (relation.getBase()==Constants.Relation.PERSONDOCTOR) {
			personDoctorRelation.save(relationObject);
		} else if (relation.getBase()==Constants.Relation.PERSONATTACHMENT) {
			personAttachmentRelation.save(relationObject);
		} else {
			logger.warn("SAVE : Relation " + relation.toString() + " not valid!");
		}		
	}
	
	public void reload(Constants.Relation relation, IRelationObject relationObject) {
		if (relation.getBase()==Constants.Relation.PERSONPERSON) {
			personPersonRelation.reload(relationObject);
		} else if (relation.getBase()==Constants.Relation.PERSONDOCTOR) {
			personDoctorRelation.reload(relationObject);
		} else if (relation.getBase()==Constants.Relation.PERSONATTACHMENT) {
			personAttachmentRelation.reload(relationObject);
		} else {
			logger.warn("RELOAD : Relation " + relation.toString() + " not valid!");
		}		
	}
	
	// Setter 	
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
	
	public void setTestEntity(IEntity testEntity) {
		this.testEntity = testEntity;
	}
	
	public void setPersonPersonRelation(IRelation personPersonRelation) {
		this.personPersonRelation = personPersonRelation;
	}

	public void setPersonDoctorRelation(IRelation personDoctorRelation) {
		this.personDoctorRelation = personDoctorRelation;
	}

	public void setPersonAttachmentRelation(IRelation personAttachmentRelation) {
		this.personAttachmentRelation = personAttachmentRelation;
	}

	public static ResolverService getFromApplicationContext(ApplicationContext ctx) {
		return (ResolverService) ctx.getBean("resolverService");
	}	
}