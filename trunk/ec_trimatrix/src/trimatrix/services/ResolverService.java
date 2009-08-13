package trimatrix.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import trimatrix.entities.IEntity;
import trimatrix.entities.IEntityData;
import trimatrix.entities.IEntityObject;
import trimatrix.relations.IRelation;
import trimatrix.relations.IRelationData;
import trimatrix.relations.IRelationObject;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;

public final class ResolverService {
	private IEntity userEntity;
	private IEntity personEntity;
	private IEntity doctorEntity;
	private IEntity attachmentEntity;
	private IRelation personPersonRelation;
	private IRelation personDoctorRelation;
	private IRelation personAttachmentRelation;

	// Entities	
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
	
	public List<IEntityData> getData(Constants.Entity entity, String personId) {
		if (entity.getBase()==Constants.Entity.USER) {
			return userEntity.getData(entity, personId);
		} else if (entity.getBase()==Constants.Entity.PERSON) {
			return personEntity.getData(entity, personId);
		} else if (entity.getBase()==Constants.Entity.DOCTOR) {
			return doctorEntity.getData(entity, personId);
		} else if (entity.getBase()==Constants.Entity.ATTACHMENT) {
			return attachmentEntity.getData(entity, personId);
		}
		Dictionary.logger.warn("GETDATA : Entity " + entity.toString() + " not valid!");
		return new ArrayList<IEntityData>();
	}
	
	public List<IEntityData> getData(Constants.Entity entity, List<String> ids) {
		if (entity.getBase()==Constants.Entity.USER) {
			return userEntity.getData(ids);
		} else if (entity.getBase()==Constants.Entity.PERSON) {
			return personEntity.getData(ids);
		} else if (entity.getBase()==Constants.Entity.DOCTOR) {
			return doctorEntity.getData(ids);
		} else if (entity.getBase()==Constants.Entity.ATTACHMENT) {
			return attachmentEntity.getData(ids);
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
	
	public boolean delete(Constants.Entity entity, String id, String personId) {
		if (entity.getBase()==Constants.Entity.PERSON) {
			return personPersonRelation.delete(personId, id);
		} else if (entity.getBase()==Constants.Entity.DOCTOR) {
			return personDoctorRelation.delete(personId, id);
		} else if (entity.getBase()==Constants.Entity.ATTACHMENT) {
			return personAttachmentRelation.delete(personId, id);
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
		} else {
			Dictionary.logger.warn("SAVE : Entity " + entity.toString() + " not valid!");
		}		
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
		} else {
			Dictionary.logger.warn("RELOAD : Entity " + entity.toString() + " not valid!");
		}		
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
		Dictionary.logger.warn("GETMETADATA : Relation " + relation.toString() + " not valid!");
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
		Dictionary.logger.warn("GETDATA : Relation " + relation.toString() + " not valid!");
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
		Dictionary.logger.warn("DELETE : Relation " + relation.toString() + " not valid!");
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
		Dictionary.logger.warn("DELETE : Relation " + relation.toString() + " not valid!");
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
		Dictionary.logger.warn("GET : Relation " + relation.toString() + " not valid!");
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
			Dictionary.logger.warn("SAVE : Relation " + relation.toString() + " not valid!");
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
			Dictionary.logger.warn("RELOAD : Relation " + relation.toString() + " not valid!");
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
