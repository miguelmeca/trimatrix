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
	private IEntityDAO<Competitions> competitionsDAO;
	private IEntityDAO<Schedules> schedulesDAO;
	private IComplexDAO<CompetitionsScouts, CompetitionsScoutsId> competitionsScoutsDAO;
	private IEntityDAO<Results> resultsDAO;
	private ISimpleDAO<ResultsTria> resultsTriaDAO;
	private IRelationDAO<PersonsHaveRelations> personsHaveRelationsDAO;
	private IRelationDAO<PersonsHaveDoctors> personsHaveDoctorsDAO;
	private IRelationDAO<PersonsHaveAttachments> personsHaveAttachmentsDAO;	
	private IRelationDAO<PersonsHaveCompetitions> personsHaveCompetitionsDAO;	
	private ITextDAO<TSalutation, TSalutationId> tsalutationDAO;
	private ITextDAO<TCategories, TCategoriesId> tcategoriesDAO;
	private ITextDAO<TTesttypes, TTesttypesId> ttesttypesDAO;
	private ITextDAO<TScheduletypes, TScheduletypesId> tscheduletypesDAO;
	private ISimpleDAO<PersonsAthlete> personAthleteDAO;	
	private ISimpleDAO<TestsErgo> testsErgoDAO;
	private ISimpleDAO<TestsTreadmill> testsTreadmillDAO;
	private ISimpleDAO<TestsSwim> testsSwimDAO;
	private ISimpleDAO<TestsProtocol> testsProtocolDAO;	
	private ISimpleDAO<TestsAnalysis> testsAnalysisDAO;	
	private IComplexDAO<TestsSwimProtocol, TestsSwimProtocolId> testsSwimProtocolDAO;	
	private IComplexDAO<ListVariants, ListVariantsId> listVariantsDAO;	
	private ILabelsDAO labelsDAO;
	private ISimpleDAO<Zones> zonesDAO;
	private ISimpleDAO<ZonesDefinition> zonesDefinitionDAO;
	private IViewDAO<Entities, EntitiesId> entitiesDAO;
	private IViewDAO<Categories, CategoriesId> categoriesDAO;
	
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

	public IRelationDAO<PersonsHaveCompetitions> getPersonsHaveCompetitionsDAO() {
		return personsHaveCompetitionsDAO;
	}
	public void setPersonsHaveCompetitionsDAO(
			IRelationDAO<PersonsHaveCompetitions> personsHaveCompetitionsDAO) {
		this.personsHaveCompetitionsDAO = personsHaveCompetitionsDAO;
	}
	public IEntityDAO<Tests> getTestsDAO() {
		return testsDAO;
	}
	public void setTestsDAO(IEntityDAO<Tests> testsDAO) {
		this.testsDAO = testsDAO;
	}	
	public IEntityDAO<Competitions> getCompetitionsDAO() {
		return competitionsDAO;
	}	
	public IComplexDAO<CompetitionsScouts, CompetitionsScoutsId> getCompetitionsScoutsDAO() {
		return competitionsScoutsDAO;
	}
	public void setCompetitionsScoutsDAO(
			IComplexDAO<CompetitionsScouts, CompetitionsScoutsId> competitionsScoutsDAO) {
		this.competitionsScoutsDAO = competitionsScoutsDAO;
	}
	public void setCompetitionsDAO(IEntityDAO<Competitions> competitionsDAO) {
		this.competitionsDAO = competitionsDAO;
	}	
	public IEntityDAO<Results> getResultsDAO() {
		return resultsDAO;
	}
	public void setResultsDAO(IEntityDAO<Results> resultsDAO) {
		this.resultsDAO = resultsDAO;
	}	
	public IEntityDAO<Schedules> getSchedulesDAO() {
		return schedulesDAO;
	}
	public void setSchedulesDAO(IEntityDAO<Schedules> schedulesDAO) {
		this.schedulesDAO = schedulesDAO;
	}
	public ISimpleDAO<ResultsTria> getResultsTriaDAO() {
		return resultsTriaDAO;
	}
	public void setResultsTriaDAO(ISimpleDAO<ResultsTria> resultsTriaDAO) {
		this.resultsTriaDAO = resultsTriaDAO;
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
	
	public ITextDAO<TTesttypes, TTesttypesId> getTtesttypesDAO() {
		return ttesttypesDAO;
	}
	public void setTtesttypesDAO(ITextDAO<TTesttypes, TTesttypesId> ttesttypesDAO) {
		this.ttesttypesDAO = ttesttypesDAO;
	}	
	public ITextDAO<TScheduletypes, TScheduletypesId> getTscheduletypesDAO() {
		return tscheduletypesDAO;
	}
	public void setTscheduletypesDAO(ITextDAO<TScheduletypes, TScheduletypesId> tscheduletypesDAO) {
		this.tscheduletypesDAO = tscheduletypesDAO;
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
	
	public ISimpleDAO<TestsSwim> getTestsSwimDAO() {
		return testsSwimDAO;
	}
	public void setTestsSwimDAO(ISimpleDAO<TestsSwim> testsSwimDAO) {
		this.testsSwimDAO = testsSwimDAO;
	}
	public ISimpleDAO<TestsProtocol> getTestsProtocolDAO() {
		return testsProtocolDAO;
	}
	public void setTestsProtocolDAO(ISimpleDAO<TestsProtocol> testsProtocolDAO) {
		this.testsProtocolDAO = testsProtocolDAO;
	}		
	public ISimpleDAO<TestsAnalysis> getTestsAnalysisDAO() {
		return testsAnalysisDAO;
	}
	public void setTestsAnalysisDAO(ISimpleDAO<TestsAnalysis> testsAnalysisDAO) {
		this.testsAnalysisDAO = testsAnalysisDAO;
	}
	public IComplexDAO<TestsSwimProtocol, TestsSwimProtocolId> getTestsSwimProtocolDAO() {
		return testsSwimProtocolDAO;
	}
	public void setTestsSwimProtocolDAO(
			IComplexDAO<TestsSwimProtocol, TestsSwimProtocolId> testsSwimProtocolDAO) {
		this.testsSwimProtocolDAO = testsSwimProtocolDAO;
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
	
	public ISimpleDAO<Zones> getZonesDAO() {
		return zonesDAO;
	}
	public void setZonesDAO(ISimpleDAO<Zones> zonesDAO) {
		this.zonesDAO = zonesDAO;
	}
	public ISimpleDAO<ZonesDefinition> getZonesDefinitionDAO() {
		return zonesDefinitionDAO;
	}
	public void setZonesDefinitionDAO(ISimpleDAO<ZonesDefinition> zonesDefinitionDAO) {
		this.zonesDefinitionDAO = zonesDefinitionDAO;
	}		
	public IViewDAO<Entities, EntitiesId> getEntitiesDAO() {
		return entitiesDAO;
	}
	public void setEntitiesDAO(IViewDAO<Entities, EntitiesId> entitiesDAO) {
		this.entitiesDAO = entitiesDAO;
	}
	public IViewDAO<Categories, CategoriesId> getCategoriesDAO() {
		return categoriesDAO;
	}
	public void setCategoriesDAO(IViewDAO<Categories, CategoriesId> categoriesDAO) {
		this.categoriesDAO = categoriesDAO;
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
