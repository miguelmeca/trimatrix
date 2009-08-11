package trimatrix.db;

import java.util.List;

import org.springframework.context.ApplicationContext;

import trimatrix.relations.IRelationObject;

public class DAOLayer {
	private IEntityDAO<Users> usersDAO;
	private IEntityDAO<Persons> personsDAO;
	private IEntityDAO<Doctors> doctorsDAO;
	private IEntityDAO<Attachments> attachmentsDAO;
	private IRelationDAO<PersonsHaveRelations> personsHaveRelationsDAO;
	private IRelationDAO<PersonsHaveDoctors> personsHaveDoctorsDAO;
	private IRelationDAO<PersonsHaveAttachments> personsHaveAttachmentsDAO;	
	private ITSalutationDAO tsalutationDAO;
	private ITCategoriesDAO tcategoriesDAO;
	private IListVariantsDAO listVariantsDAO;	
	private ILabelsDAO labelsDAO;
	private IEntitiesHaveLabelsDAO entitiesHaveLabelsDAO;
	private List<IRelationDAO<IRelationObject>> relationDAOs;		
	
	public int deleteRelationsByPartner(String partnerId) {
		int count = 0;
		for(IRelationDAO<IRelationObject> relation : relationDAOs) {
			count += relation.deleteByPartners(partnerId);
		}
		return count;
	}
	public IEntityDAO<Users> getUsersDAO() {
		return usersDAO;
	}

	public void setUsersDAO(IEntityDAO<Users> usersDAO) {
		this.usersDAO = usersDAO;
	}

	public IEntityDAO<Persons> getPersonsDAO() {
		return personsDAO;
	}

	public void setPersonsDAO(IEntityDAO<Persons> personsDAO) {
		this.personsDAO = personsDAO;
	}	
	
	public IEntityDAO<Doctors> getDoctorsDAO() {
		return doctorsDAO;
	}

	public void setDoctorsDAO(IEntityDAO<Doctors> doctorsDAO) {
		this.doctorsDAO = doctorsDAO;
	}

	public IEntityDAO<Attachments> getAttachmentsDAO() {
		return attachmentsDAO;
	}

	public void setAttachmentsDAO(IEntityDAO<Attachments> attachmentsDAO) {
		this.attachmentsDAO = attachmentsDAO;
	}	

	public IRelationDAO<PersonsHaveRelations> getPersonsHaveRelationsDAO() {
		return personsHaveRelationsDAO;
	}

	public void setPersonsHaveRelationsDAO(
			IRelationDAO<PersonsHaveRelations> personsHaveRelationsDAO) {
		this.personsHaveRelationsDAO = personsHaveRelationsDAO;
	}	

	public IRelationDAO<PersonsHaveDoctors> getPersonsHaveDoctorsDAO() {
		return personsHaveDoctorsDAO;
	}

	public void setPersonsHaveDoctorsDAO(
			IRelationDAO<PersonsHaveDoctors> personsHaveDoctorsDAO) {
		this.personsHaveDoctorsDAO = personsHaveDoctorsDAO;
	}	

	public IRelationDAO<PersonsHaveAttachments> getPersonsHaveAttachmentsDAO() {
		return personsHaveAttachmentsDAO;
	}

	public void setPersonsHaveAttachmentsDAO(
			IRelationDAO<PersonsHaveAttachments> personsHaveAttachmentsDAO) {
		this.personsHaveAttachmentsDAO = personsHaveAttachmentsDAO;
	}

	public ITSalutationDAO getTsalutationDAO() {
		return tsalutationDAO;
	}

	public void setTsalutationDAO(ITSalutationDAO tsalutationDAO) {
		this.tsalutationDAO = tsalutationDAO;
	}	
		
	public ITCategoriesDAO getTcategoriesDAO() {
		return tcategoriesDAO;
	}
	public void setTcategoriesDAO(ITCategoriesDAO tcategoriesDAO) {
		this.tcategoriesDAO = tcategoriesDAO;
	}
	
	public IListVariantsDAO getListVariantsDAO() {
		return listVariantsDAO;
	}

	public void setListVariantsDAO(IListVariantsDAO listVariantsDAO) {
		this.listVariantsDAO = listVariantsDAO;
	}	

	public ILabelsDAO getLabelsDAO() {
		return labelsDAO;
	}
	
	public void setLabelsDAO(ILabelsDAO labelsDAO) {
		this.labelsDAO = labelsDAO;
	}
	
	public IEntitiesHaveLabelsDAO getEntitiesHaveLabelsDAO() {
		return entitiesHaveLabelsDAO;
	}
	public void setEntitiesHaveLabelsDAO(
			IEntitiesHaveLabelsDAO entitiesHaveLabelsDAO) {
		this.entitiesHaveLabelsDAO = entitiesHaveLabelsDAO;
	}
	public List<IRelationDAO<IRelationObject>> getRelationDAOs() {
		return relationDAOs;
	}

	public void setRelationDAOs(List<IRelationDAO<IRelationObject>> relationDAOs) {
		this.relationDAOs = relationDAOs;
	}

	public static DAOLayer getFromApplicationContext(ApplicationContext ctx) {
		return (DAOLayer) ctx.getBean("daoLayer");
	}
}
