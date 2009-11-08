package trimatrix.logic;

import java.util.List;

import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.workplace.WorkplaceFunctionTree.FunctionNode;
import org.springframework.dao.DataIntegrityViolationException;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import trimatrix.db.CompetitionsScouts;
import trimatrix.db.CompetitionsScoutsId;
import trimatrix.db.DAOLayer;
import trimatrix.db.PersonsHaveAttachments;
import trimatrix.db.PersonsHaveDoctors;
import trimatrix.entities.EntityLayer;
import trimatrix.entities.IEntityData;
import trimatrix.relations.PersonAttachmentRelation;
import trimatrix.relations.PersonDoctorRelation;
import trimatrix.relations.RelationLayer;
import trimatrix.services.ServiceLayer;
import trimatrix.structures.SFunctionTree;
import trimatrix.utils.Constants;

public class FunctionTreeLogic {
	private EntityLayer entityLayer;
	private RelationLayer relationLayer;
	private DAOLayer daoLayer;
	private ServiceLayer serviceLayer;

	public List<IEntityData> getMyAthletes() {
		return entityLayer.getPersonEntity().getData(Constants.Entity.MYATHLETES);
	}
	
	public List<IEntityData> getMyScoutedAthletes() {
		return entityLayer.getPersonEntity().getData(Constants.Entity.MYSCOUTEDATHLETES);
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
	
	public boolean createCompetitionScout(String competitionId) {
		CompetitionsScoutsId csId = new CompetitionsScoutsId();
		csId.setCompetitionId(competitionId);
		csId.setScoutId(serviceLayer.getDictionaryService().getMyPerson().getId());
		CompetitionsScouts cs = new CompetitionsScouts(csId);
		try {
			daoLayer.getCompetitionsScoutsDAO().merge(cs);
		} catch (DataIntegrityViolationException dive) {
			Statusbar.outputWarning("Competition is already in your workspace!");
			return false;
		} catch (Exception ex) {
			Statusbar.outputError(ex.toString());	
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
			Statusbar.outputError("Relation could not be saved (Data Integrity)", dive.getRootCause().toString());
			return false;
		} catch (Exception ex){			
			Statusbar.outputError("Relation could not be saved", ex.toString());			
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
			Statusbar.outputError("Relation could not be saved (Data Integrity)", dive.getRootCause().toString());
			return false;
		} catch (Exception ex){			
			Statusbar.outputError("Relation could not be saved", ex.toString());			
			return false;
		}
		return true;
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
