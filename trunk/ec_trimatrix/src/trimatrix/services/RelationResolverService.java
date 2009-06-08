package trimatrix.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import trimatrix.relations.IRelation;
import trimatrix.relations.IRelationData;
import trimatrix.relations.IRelationObject;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;

public class RelationResolverService {
	private IRelation personPersonRelation;
	private IRelation personDoctorRelation;
	private IRelation personAttachmentRelation;
	
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
		}
		Dictionary.logger.warn("SAVE : Relation " + relation.toString() + " not valid!");
	}
	
	public void reload(Constants.Relation relation, IRelationObject relationObject) {
		if (relation.getBase()==Constants.Relation.PERSONPERSON) {
			personPersonRelation.reload(relationObject);
		} else if (relation.getBase()==Constants.Relation.PERSONDOCTOR) {
			personDoctorRelation.reload(relationObject);
		} else if (relation.getBase()==Constants.Relation.PERSONATTACHMENT) {
			personAttachmentRelation.reload(relationObject);
		}
		Dictionary.logger.warn("RELOAD : Relation " + relation.toString() + " not valid!");
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

	public static RelationResolverService getFromApplicationContext(ApplicationContext ctx) {
		return (RelationResolverService) ctx.getBean("relationResolverService");
	}	
}
