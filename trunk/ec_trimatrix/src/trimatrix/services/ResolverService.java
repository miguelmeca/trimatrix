package trimatrix.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import trimatrix.entities.IEntity;
import trimatrix.entities.IEntityData;
import trimatrix.entities.IEntityObject;
import trimatrix.relations.IRelation;
import trimatrix.relations.IRelationData;
import trimatrix.relations.IRelationObject;
import trimatrix.reports.Report;
import trimatrix.structures.SGridMetaData;
import trimatrix.structures.SSearchMetaData;
import trimatrix.utils.Constants;
import trimatrix.utils.SearchRange;
import trimatrix.utils.Constants.Entity;
import trimatrix.utils.Constants.Relation;

@SuppressWarnings("unused")
public final class ResolverService {
	public static final Log logger = LogFactory.getLog(ResolverService.class);

	private IEntity userEntity;
	private IEntity personEntity;
	private IEntity doctorEntity;
	private IEntity attachmentEntity;
	private IEntity testEntity;
	private IEntity competitionEntity;
	private IEntity resultEntity;
	private IEntity scheduleEntity;
	private IRelation personPersonRelation;
	private IRelation personDoctorRelation;
	private IRelation personAttachmentRelation;
	private IRelation personCompetitionRelation;

	// Entities
	public List<SGridMetaData> getGridMetaData(Constants.Entity entity, String filter) {
		filter = filter==null ? Constants.NO_FILTER : filter;
		try {
			return (List<SGridMetaData>)getProxy(entity).getGridMetaData(filter);
		} catch (Exception ex) {
			logger.error("GETMETADATA : Entity " + entity.toString() + " not valid! : " + ex.toString());
			return Constants.EMPTYMETADATA;
		}
	}

	public Map<String, SSearchMetaData> getSearchMetaData(Constants.Entity entity) {
		try {
			return (Map<String, SSearchMetaData>)getProxy(entity).getSearchMetaData();
		} catch (Exception ex) {
			logger.error("GETSEARCHMETADATA : Entity " + entity.toString() + " not valid! : " + ex.toString());
			return Constants.EMPTYSEARCHMETADATA;
		}
	}

	public List<IEntityData> getData(Constants.Entity entity, String filter) {
		filter = filter==null ? Constants.NO_FILTER : filter;
		try {
			return (List<IEntityData>)getProxy(entity).getData(entity, filter);
		} catch (Exception ex) {
			logger.error("GETDATA : Entity " + entity.toString() + " not valid! : " + ex.toString());
			return Constants.EMPTYENTITYDATA;
		}
	}

	public List<IEntityData> getData(Constants.Entity entity, SearchRange srange) {
		try {
			return (List<IEntityData>)getProxy(entity).getData(srange);
		} catch (Exception ex) {
			logger.error("GETDATA : Entity " + entity.toString() + " not valid! : " + ex.toString());
			return Constants.EMPTYENTITYDATA;
		}
	}

	public List<IEntityData> getData(Constants.Entity entity, String personId, String filter) 	{
		filter = filter==null ? Constants.NO_FILTER : filter;
		try {
			return (List<IEntityData>)getProxy(entity).getData(entity, personId, filter);
		} catch (Exception ex) {
			logger.error("GETDATA : Entity " + entity.toString() + " not valid! : " + ex.toString());
			return Constants.EMPTYENTITYDATA;
		}
	}

	public List<IEntityData> getData(Constants.Entity entity, List<String> ids) {
		try {
			return (List<IEntityData>)getProxy(entity).getData(ids);
		} catch (Exception ex) {
			logger.error("GETDATA : Entity " + entity.toString() + " not valid! : " + ex.toString());
			return Constants.EMPTYENTITYDATA;
		}
	}

	public Report getPrintReport(Constants.Entity entity, String filter, List<IEntityData> data) {
		try {
			return (Report)getProxy(entity).getPrintReport(entity, filter, data);
		} catch (Exception ex) {
			logger.error("GETPRINTREPORT : Entity " + entity.toString() + " not valid! : " + ex.toString());
			return null;
		}
	}

	public boolean delete(Constants.Entity entity, String id) {
		try {
			return (boolean)getProxy(entity).delete(id);
		} catch (Exception ex) {
			logger.error("DELETE : Entity " + entity.toString() + " not valid! : " + ex.toString());
			return false;
		}
	}

	public boolean delete(Constants.Entity entity, String id, String personId) {
		switch (entity.getBase()) {
		case PERSON:
			return personPersonRelation.delete(personId, id);
		case DOCTOR:
			return personDoctorRelation.delete(personId, id);
		case ATTACHMENT:
			return personAttachmentRelation.delete(personId, id);
		case COMPETITION:
			return personCompetitionRelation.delete(personId, id);
		case RESULT:
			return resultEntity.delete(id);
		}
		logger.error("DELETE : Entity " + entity.toString() + " not valid!");
		return false;
	}

	public IEntityObject create(Constants.Entity entity) {
		try {
			return (IEntityObject)getProxy(entity).create();
		} catch (Exception ex) {
			logger.error("CREATE : Entity " + entity.toString() + " not valid! : " + ex.toString());
			return null;
		}
	}

	public IEntityObject get(Constants.Entity entity, String id) {
		try {
			return (IEntityObject)getProxy(entity).get(id);
		} catch (Exception ex) {
			logger.error("GET : Entity " + entity.toString() + " not valid! : " + ex.toString());
			return null;
		}
	}

	public IEntityObject save(Constants.Entity entity, IEntityObject entityObject) {
		try {
			return (IEntityObject)getProxy(entity).save(entityObject);
		} catch (Exception ex) {
			logger.error("SAVE : Entity " + entity.toString() + " not valid! : " + ex.toString());
			return null;
		}
	}

	public void reload(Constants.Entity entity, IEntityObject entityObject) {
		try {
			getProxy(entity).reload(entityObject);
		} catch (Exception ex) {
			logger.error("RELOAD : Entity " + entity.toString() + " not valid! : " + ex.toString());
		}

	}

	public IEntityObject copy(Constants.Entity entity, IEntityObject entityObject) {
		try {
			return (IEntityObject)getProxy(entity).copy(entityObject);
		} catch (Exception ex) {
			logger.error("COPY : Entity " + entity.toString() + " not valid! : " + ex.toString());
			return null;
		}
	}

	public boolean isCopyable(Constants.Entity entity, IEntityObject entityObject) {
		try {
			return (boolean)getProxy(entity).isCopyable(entityObject);
		} catch (Exception ex) {
			logger.error("ISCOPYABLE : Entity " + entity.toString() + " not valid! : " + ex.toString());
			return false;
		}
	}

	// Relations
	public List<SGridMetaData> getGridMetaData(Constants.Relation relation) {
		try {
			return (List<SGridMetaData>)getProxy(relation).getGridMetaData();
		} catch (Exception ex) {
			logger.error("GETMETADATA : Relation " + relation.toString() + " not valid! : " + ex.toString());
			return Constants.EMPTYMETADATA;
		}
	}

	public List<IRelationData> getData(Constants.Relation relation) {
		try {
			return (List<IRelationData>)getProxy(relation).getData(relation);
		} catch (Exception ex) {
			logger.error("GETDATA : Relation " + relation.toString() + " not valid! : " + ex.toString());
			return Constants.EMPTYRELATIONDATA;
		}
	}

	public boolean delete(Constants.Relation relation, String id) {
		try {
			return (boolean)getProxy(relation).delete(id);
		} catch (Exception ex) {
			logger.error("DELETE : Relation " + relation.toString() + " not valid! : " + ex.toString());
			return false;
		}
	}

	public IRelationObject create(Constants.Relation relation) {
		try {
			return (IRelationObject)getProxy(relation).create();
		} catch (Exception ex) {
			logger.error("CREATE : Relation " + relation.toString() + " not valid! : " + ex.toString());
			return null;
		}
	}

	public IRelationObject get(Constants.Relation relation, String id) {
		try {
			return (IRelationObject)getProxy(relation).get(id);
		} catch (Exception ex) {
			logger.error("GET : Relation " + relation.toString() + " not valid! : " + ex.toString());
			return null;
		}
	}

	public void save(Constants.Relation relation, IRelationObject relationObject) {
		try {
			getProxy(relation).save(relationObject);
		} catch (Exception ex) {
			logger.error("SAVE : Relation " + relation.toString() + " not valid! : " + ex.toString());
		}
	}

	public void reload(Constants.Relation relation, IRelationObject relationObject) {
		try {
			getProxy(relation).reload(relationObject);
		} catch (Exception ex) {
			logger.error("RELOAD : Relation " + relation.toString() + " not valid! : " + ex.toString());
		}
	}

	// use reflection for resolving entity to a proxy
	private IEntity getProxy(Entity entity) throws Exception{
		Field field;
		Class<? extends ResolverService> clazz = this.getClass();
		field = clazz.getDeclaredField(entity.getBase().getEntityInstance());
		return (IEntity)field.get(this);
	}

	// use reflection for resolving relation to a proxy
	private IRelation getProxy(Relation relation) throws Exception{
		Field field;
		Class<? extends ResolverService> clazz = this.getClass();
		field = clazz.getDeclaredField(relation.getBase().getRelationInstance());
		return (IRelation)field.get(this);
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

	public void setCompetitionEntity(IEntity competitionEntity) {
		this.competitionEntity = competitionEntity;
	}

	public void setResultEntity(IEntity resultEntity) {
		this.resultEntity = resultEntity;
	}

	public void setScheduleEntity(IEntity scheduleEntity) {
		this.scheduleEntity = scheduleEntity;
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

	public void setPersonCompetitionRelation(IRelation personCompetitionRelation) {
		this.personCompetitionRelation = personCompetitionRelation;
	}

	public static ResolverService getFromApplicationContext(ApplicationContext ctx) {
		return (ResolverService) ctx.getBean("resolverService");
	}
}
