package trimatrix.db;

import java.util.List;

import org.springframework.context.ApplicationContext;

public class DAOLayer {
	private IUsersDAO usersDAO;
	private IPersonsDAO personsDAO;
	private IDoctorsDAO doctorsDAO;
	private IAttachmentsDAO attachmentsDAO;
	private IPersonsHaveRelationsDAO personsHaveRelationsDAO;
	private IPersonsHaveDoctorsDAO personsHaveDoctorsDAO;
	private IPersonsHaveAttachmentsDAO personsHaveAttachmentsDAO;	
	private ITSalutationDAO tsalutationDAO;
	private IListVariantsDAO listVariantsDAO;	
	private ILabelsDAO labelsDAO;
	private IEntitiesHaveLabelsDAO entitiesHaveLabelsDAO;
	private List<IRelationDAO> relationDAOs;		
	
	public int deleteRelationsByPartner(String partnerId) {
		int count = 0;
		for(IRelationDAO relation : relationDAOs) {
			count += relation.deleteByPartners(partnerId);
		}
		return count;
	}
	public IUsersDAO getUsersDAO() {
		return usersDAO;
	}

	public void setUsersDAO(IUsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}

	public IPersonsDAO getPersonsDAO() {
		return personsDAO;
	}

	public void setPersonsDAO(IPersonsDAO personsDAO) {
		this.personsDAO = personsDAO;
	}	
	
	public IDoctorsDAO getDoctorsDAO() {
		return doctorsDAO;
	}

	public void setDoctorsDAO(IDoctorsDAO doctorsDAO) {
		this.doctorsDAO = doctorsDAO;
	}

	public IAttachmentsDAO getAttachmentsDAO() {
		return attachmentsDAO;
	}

	public void setAttachmentsDAO(IAttachmentsDAO attachmentsDAO) {
		this.attachmentsDAO = attachmentsDAO;
	}	

	public IPersonsHaveRelationsDAO getPersonsHaveRelationsDAO() {
		return personsHaveRelationsDAO;
	}

	public void setPersonsHaveRelationsDAO(
			IPersonsHaveRelationsDAO personsHaveRelationsDAO) {
		this.personsHaveRelationsDAO = personsHaveRelationsDAO;
	}	

	public IPersonsHaveDoctorsDAO getPersonsHaveDoctorsDAO() {
		return personsHaveDoctorsDAO;
	}

	public void setPersonsHaveDoctorsDAO(
			IPersonsHaveDoctorsDAO personsHaveDoctorsDAO) {
		this.personsHaveDoctorsDAO = personsHaveDoctorsDAO;
	}	

	public IPersonsHaveAttachmentsDAO getPersonsHaveAttachmentsDAO() {
		return personsHaveAttachmentsDAO;
	}

	public void setPersonsHaveAttachmentsDAO(
			IPersonsHaveAttachmentsDAO personsHaveAttachmentsDAO) {
		this.personsHaveAttachmentsDAO = personsHaveAttachmentsDAO;
	}

	public ITSalutationDAO getTsalutationDAO() {
		return tsalutationDAO;
	}

	public void setTsalutationDAO(ITSalutationDAO tsalutationDAO) {
		this.tsalutationDAO = tsalutationDAO;
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
	public List<IRelationDAO> getRelationDAOs() {
		return relationDAOs;
	}

	public void setRelationDAOs(List<IRelationDAO> relationDAOs) {
		this.relationDAOs = relationDAOs;
	}

	public static DAOLayer getFromApplicationContext(ApplicationContext ctx) {
		return (DAOLayer) ctx.getBean("daoLayer");
	}
}
