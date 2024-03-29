package trimatrix.logic;

import java.util.Iterator;
import java.util.List;

import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding.ValidValue;
import org.eclnt.workplace.IWorkpage;
import org.eclnt.workplace.WorkplaceFunctionTree.FunctionNode;
import org.springframework.dao.DataIntegrityViolationException;

import trimatrix.db.Competitions;
import trimatrix.db.CompetitionsScouts;
import trimatrix.db.CompetitionsScoutsId;
import trimatrix.db.DAOLayer;
import trimatrix.db.Persons;
import trimatrix.db.PersonsHaveAttachments;
import trimatrix.db.PersonsHaveDoctors;
import trimatrix.db.Results;
import trimatrix.entities.EntityLayer;
import trimatrix.entities.IEntityData;
import trimatrix.relations.PersonAttachmentRelation;
import trimatrix.relations.PersonDoctorRelation;
import trimatrix.relations.RelationLayer;
import trimatrix.services.ServiceLayer;
import trimatrix.structures.SFunctionTree;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;
import trimatrix.utils.Constants.ValueList;

public class FunctionTreeLogic {
	private EntityLayer entityLayer;
	private RelationLayer relationLayer;
	private DAOLayer daoLayer;
	private ServiceLayer serviceLayer;

	public List<IEntityData> getMyAthletes() {
		return entityLayer.getPersonEntity().getData(Constants.Entity.MYATHLETES, Constants.NO_FILTER);
	}

	public List<IEntityData> getMyScoutedAthletes() {
		return entityLayer.getPersonEntity().getData(Constants.Entity.MYSCOUTEDATHLETES, Constants.NO_FILTER);
	}

	public Iterator<ValidValue> getCompetitionTypes() {
		ValidValuesBinding vvbCompTypes = serviceLayer.getValueListBindingService().getVVBinding(ValueList.COMPTYPE);
		return vvbCompTypes.getValidValues();
	}

	public Iterator<ValidValue> getCompetitionSubtypes(String type) {
		ValidValuesBinding vvbCompSubtypes = serviceLayer.getValueListBindingService().getVVBinding(ValueList.COMPSUBTYPE, type);
		return vvbCompSubtypes.getValidValues();
	}

	public void setAuthority(SFunctionTree functionTree, FunctionNode node) {
		node.setParam(Constants.CREATE, Constants.FALSE);
		if(functionTree.create) {
			node.setParam(Constants.CREATE, Constants.TRUE);
		}
		node.setParam(Constants.CHANGE, Constants.FALSE);
		if(functionTree.edit) {
			node.setParam(Constants.CHANGE, Constants.TRUE);
		}
		node.setParam(Constants.DELETE, Constants.FALSE);
		if(functionTree.delete) {
			node.setParam(Constants.DELETE, Constants.TRUE);
		}
	}

	public void setAuthority(FunctionNode node, boolean create, boolean  edit, boolean delete) {
		node.setParam(Constants.CREATE, Constants.FALSE);
		if(create) {
			node.setParam(Constants.CREATE, Constants.TRUE);
		}
		node.setParam(Constants.CHANGE, Constants.FALSE);
		if(edit) {
			node.setParam(Constants.CHANGE, Constants.TRUE);
		}
		node.setParam(Constants.DELETE, Constants.FALSE);
		if(delete) {
			node.setParam(Constants.DELETE, Constants.TRUE);
		}
	}

	public void setAuthority(IWorkpage wp, boolean create, boolean  edit, boolean delete) {
		wp.setParam(Constants.CREATE, Constants.FALSE);
		if(create) {
			wp.setParam(Constants.CREATE, Constants.TRUE);
		}
		wp.setParam(Constants.CHANGE, Constants.FALSE);
		if(edit) {
			wp.setParam(Constants.CHANGE, Constants.TRUE);
		}
		wp.setParam(Constants.DELETE, Constants.FALSE);
		if(delete) {
			wp.setParam(Constants.DELETE, Constants.TRUE);
		}
	}

	public boolean createCompetitionScout(String competitionId) {
		CompetitionsScoutsId csId = new CompetitionsScoutsId();
		csId.setCompetitionId(competitionId);
		csId.setScoutId(serviceLayer.getDictionaryService().getMyPerson().getId());
		CompetitionsScouts cs = new CompetitionsScouts(csId);
		try {
			daoLayer.getCompetitionsScoutsDAO().merge(cs);
		} catch (DataIntegrityViolationException dive) {
			Statusbar.outputWarning(Helper.getMessages("comp_workspace"));
			return false;
		} catch (Exception ex) {
			Statusbar.outputError(Helper.getMessages("comp_workspace_failure"), ex.toString());
			return false;
		}
		return true;
	}

	public boolean createPersonAttachmentRelation(String personId, String attachmentId) {
		PersonAttachmentRelation relation = relationLayer.getPersonAttachmentRelation();
		PersonsHaveAttachments pha = relation.create();
		pha.setPerson(personId);
		pha.setAttachment(attachmentId);
		pha.setReltypKey(Constants.Relation.ATTACHMENT.type());
		try {
			relation.save(pha);
		} catch (DataIntegrityViolationException dive) {
			Statusbar.outputError(Helper.getMessages("relation_save_failure"), dive.getRootCause().toString());
			return false;
		} catch (Exception ex){
			Statusbar.outputError(Helper.getMessages("relation_save_failure"), ex.toString());
			return false;
		}
		return true;
	}

	public boolean createPersonDoctorRelation(String personId, String doctorId) {
		PersonDoctorRelation relation = relationLayer.getPersonDoctorRelation();
		PersonsHaveDoctors phd = relation.create();
		phd.setPerson(personId);
		phd.setDoctor(doctorId);
		phd.setReltypKey(Constants.Relation.DOCTOR.type());
		try {
			relation.save(phd);
		} catch (DataIntegrityViolationException dive) {
			Statusbar.outputError(Helper.getMessages("relation_save_failure"), dive.getRootCause().toString());
			return false;
		} catch (Exception ex){
			Statusbar.outputError(Helper.getMessages("relation_save_failure"), ex.toString());
			return false;
		}
		return true;
	}

	public Results checkResultRelation(String athleteId, String competitionId) throws Exception {
		// Check if combination already exists
		Results example = new Results();
		example.setCompetitionId(competitionId);
		example.setScoutId(serviceLayer.getDictionaryService().getMyPerson().getId());
		example.setAthleteId(athleteId);
		example.setDeleted(false);
		List<Results> results = daoLayer.getResultsDAO().findByExample(example);
		if(Helper.isEmpty(results)) return null;
		return results.get(0);
	}

	public Results createResultRelation(String athleteId, String competitionId) throws Exception {
		Results result = entityLayer.getResultEntity().create();
		result.setCompetition((Competitions)entityLayer.getCompetitionEntity().get(competitionId));
		result.setScout(serviceLayer.getDictionaryService().getMyPerson());
		result.setAthlete((Persons)entityLayer.getPersonEntity().get(athleteId));
		return (Results)entityLayer.getResultEntity().save(result);
	}

	public String getMyPersonId() {
		return serviceLayer.getDictionaryService().getMyPerson().getId();
	}

	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}

	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}

	public void setEntityLayer(EntityLayer entityLayer) {
		this.entityLayer = entityLayer;
	}

	public void setRelationLayer(RelationLayer relationLayer) {
		this.relationLayer = relationLayer;
	}

}
