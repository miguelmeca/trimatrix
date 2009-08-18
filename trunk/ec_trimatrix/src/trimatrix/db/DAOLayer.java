package trimatrix.db;

import java.util.List;

import org.springframework.context.ApplicationContext;

import trimatrix.relations.IRelationObject;

public class DAOLayer {
	private IEntityDAO<Users> usersDAO;
	private IEntityDAO<Persons> personsDAO;
	private IEntityDAO<Doctors> doctorsDAO;
	private IEntityDAO<Attachments> attachmentsDAO;
	private IEntityDAO<Tests> testsDAO;
	private IRelationDAO<PersonsHaveRelations> personsHaveRelationsDAO;
	private IRelationDAO<PersonsHaveDoctors> personsHaveDoctorsDAO;
	private IRelationDAO<PersonsHaveAttachments> personsHaveAttachmentsDAO;	
	private ITextDAO<TSalutation, TSalutationId> tsalutationDAO;
	private ITextDAO<TCategories, TCategoriesId> tcategoriesDAO;
	private ISimpleDAO<PersonsAthlete> personAthleteDAO;	
	private ISimpleDAO<TestsErgo> testsErgoDAO;
	private ISimpleDAO<TestsTreadmill> testsTreadmillDAO;
	private ISimpleDAO<TestsProtocol> testsProtocolDAO;	
	private IComplexDAO<ListVariants, ListVariantsId> listVariantsDAO;	
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

	public IEntityDAO<Tests> getTestsDAO() {
		return testsDAO;
	}
	public void setTestsDAO(IEntityDAO<Tests> testsDAO) {
		this.testsDAO = testsDAO;
	}
	public ITextDAO<TSalutation, TSalutationId> getTsalutationDAO() {
		return tsalutationDAO;
	}

	public void setTsalutationDAO(ITextDAO<TSalutation, TSalutationId> tsalutationDAO) {
		this.tsalutationDAO = tsalutationDAO;
	}	
		
	public ITextDAO<TCategories, TCategoriesId> getTcategoriesDAO() {
		return tcategoriesDAO;
	}
	public void setTcategoriesDAO(ITextDAO<TCategories, TCategoriesId> tcategoriesDAO) {
		this.tcategoriesDAO = tcategoriesDAO;
	}
	
	public ISimpleDAO<PersonsAthlete> getPersonAthleteDAO() {
		return personAthleteDAO;
	}
	public void setPersonAthleteDAO(ISimpleDAO<PersonsAthlete> personAthleteDAO) {
		this.personAthleteDAO = personAthleteDAO;
	}
	
	public ISimpleDAO<TestsErgo> getTestsErgoDAO() {
		return testsErgoDAO;
	}
	public void setTestsErgoDAO(ISimpleDAO<TestsErgo> testsErgoDAO) {
		this.testsErgoDAO = testsErgoDAO;
	}
	public ISimpleDAO<TestsTreadmill> getTestsTreadmillDAO() {
		return testsTreadmillDAO;
	}
	public void setTestsTreadmillDAO(ISimpleDAO<TestsTreadmill> testsTreadmillDAO) {
		this.testsTreadmillDAO = testsTreadmillDAO;
	}
	public ISimpleDAO<TestsProtocol> getTestsProtocolDAO() {
		return testsProtocolDAO;
	}
	public void setTestsProtocolDAO(ISimpleDAO<TestsProtocol> testsProtocolDAO) {
		this.testsProtocolDAO = testsProtocolDAO;
	}
	public IComplexDAO<ListVariants, ListVariantsId> getListVariantsDAO() {
		return listVariantsDAO;
	}
	
	public void setListVariantsDAO(IComplexDAO<ListVariants, ListVariantsId> listVariantsDAO) {
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
