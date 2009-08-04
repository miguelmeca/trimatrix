package trimatrix.logic;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoListener;
import org.hibernate.exception.ConstraintViolationException;

import trimatrix.db.DAOLayer;
import trimatrix.db.EntitiesHaveLabels;
import trimatrix.db.EntitiesHaveLabelsId;
import trimatrix.db.Labels;
import trimatrix.services.ServiceLayer;
import trimatrix.utils.Dictionary;

public class LabelLogic {
	private DAOLayer daoLayer;
	private ServiceLayer serviceLayer;
	
	public List<Labels> getAllLabels() {
		List<Labels> labels = daoLayer.getLabelsDAO().findByPersonId(serviceLayer.getDictionaryService().getMyPerson().getId());
		if(labels==null) return new Vector<Labels>();
		return labels;
	}
	
	public boolean createLabelRelation(String entity_id, String label_id) {
		try {
			EntitiesHaveLabelsId id = new EntitiesHaveLabelsId();
			id.setEntity(entity_id);
			id.setLabel(label_id);
			id.setPersonId(serviceLayer.getDictionaryService().getMyPerson().getId());
			daoLayer.getEntitiesHaveLabelsDAO().save(new EntitiesHaveLabels(id));
			return true;
		} catch(ConstraintViolationException cve) {
			Statusbar.outputMessage("Label already assigned!");
			return false;
		} catch(Exception ex) {
			Dictionary.logger.error("Error saving relation entity " + entity_id + " and label " + label_id );
			return false;
		}				
	}
	
	public boolean deleteLabelRelation(String entity_id, String label_id) {
		try {
			EntitiesHaveLabelsId id = new EntitiesHaveLabelsId();
			id.setEntity(entity_id);
			id.setLabel(label_id);
			id.setPersonId(serviceLayer.getDictionaryService().getMyPerson().getId());
			EntitiesHaveLabels relation = daoLayer.getEntitiesHaveLabelsDAO().findById(id);
			daoLayer.getEntitiesHaveLabelsDAO().delete(relation);
			return true;
		} catch(Exception ex) {
			Dictionary.logger.error("Error saving relation entity " + entity_id + " and label " + label_id );
			return false;
		}				
	}
	
	public void deleteLabel(String label_id) {
		// TODO Delete the label
		// get label
		final Labels label = daoLayer.getLabelsDAO().findById(label_id);
		// check if relation to entity exist
		final List<EntitiesHaveLabels> relations = daoLayer.getEntitiesHaveLabelsDAO().findByLabel(label_id);
		// popup
		YESNOPopup.createInstance ( "Delete label", "For label " + label.getDescription() + " there exist " + relations.size() + " relations to entities, sure to delete label?",
				new IYesNoListener() {
					public void reactOnNo()	{
						return;
					}
					public void reactOnYes() {
						// delete all relations
						if(relations.size() > 0) {				
							for(EntitiesHaveLabels relation:relations) {
								daoLayer.getEntitiesHaveLabelsDAO().delete(relation);
							}
						}
						// delete label		
						if(label!=null) {
							daoLayer.getLabelsDAO().delete(label);
						}
						Statusbar.outputMessage("Delete Label " + label.getDescription());
					}
			});		
	}
	
	public boolean changeLabel(String label_id, String description, String color) {
		Labels label = daoLayer.getLabelsDAO().findById(label_id);
		if(label==null) return false;
		label.setColor(color);
		label.setDescription(description);
		try {
			daoLayer.getLabelsDAO().merge(label);
			return true;
		} catch (Exception ex) {
			Dictionary.logger.error(ex.toString());
			return false;
		}		
	}
	
	public Labels getLabel(String id) {
		return daoLayer.getLabelsDAO().findById(id);
	}
	
	public List<Labels> getLabelsByEntity(String entity_id) {
		List<Labels> labels = new Vector<Labels>();
		List<EntitiesHaveLabels> relations = daoLayer.getEntitiesHaveLabelsDAO().findByEntity(entity_id);
		for(EntitiesHaveLabels relation:relations) {
			Labels label = daoLayer.getLabelsDAO().findById(relation.getId().getLabel());
			if(label!=null) {
				labels.add(label);
			}
		}
		return labels;		
	}
	
	public List<Labels> getLabelsByPerson(String person_id) {
		return daoLayer.getLabelsDAO().findByPersonId(person_id);
	}
	
	public Labels createLabel(String description, String color) {
		String id = UUID.randomUUID().toString();
		Labels label = new Labels();
		label.setId(id);
		label.setDescription(description);
		label.setColor(color);
		label.setPersonId(serviceLayer.getDictionaryService().getMyPerson().getId());
		daoLayer.getLabelsDAO().save(label);
		return daoLayer.getLabelsDAO().findById(id);
	}
	
	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}
	
	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}	
}
