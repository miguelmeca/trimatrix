package trimatrix.ui;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.events.BaseActionEventUpload;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Attachments;
import trimatrix.db.Persons;
import trimatrix.entities.AttachmentEntity;
import trimatrix.entities.PersonEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;

@SuppressWarnings("serial")
@CCGenClass (expressionBase="#{d.AttachmentDetailUI}")

public class AttachmentDetailUI extends AEntityDetailUI implements Serializable, IEntityDetailUI
{
    protected ValidValuesBinding categoriesVvb = getServiceLayer().getValueListBindingService().getVVBinding(Constants.ValueList.CATEGORY);
    public ValidValuesBinding getCategoriesVvb() { return categoriesVvb; }
    
	private Attachments entity;	
    
	public AttachmentDetailUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher, new String[] {AttachmentEntity.DESCRIPTION, AttachmentEntity.FILENAME});
		// get wrapping entity detail UI bean
		entityDetailUI = getEntityDetailUI();		
		entityDetailUI.setEntityDetailUI(this);		
        // init data
        init(entityDetailUI.getEntityObject());
    }

    public void init(Object entityObject) {
    	// set entity object
    	this.entity = (Attachments)entityObject;        	 	
    	// set enabled state and set fields
    	init();
    }
    
    public void init() {
    	// set fields
    	fillMaps();   
    	// set state
    	setState();
    }    

	public void validate() throws MandatoryCheckException, EmailNotValidException {		
		// mandatory check
		checkMandatory();
		// fill values to entities properties
		fillEntityProperties();
	}
	
	private void fillEntityProperties() {
		entity.setCategoryKey((String)values.get(AttachmentEntity.CATEGORY));		
		entity.setDescription((String)values.get(AttachmentEntity.DESCRIPTION));
		entity.setOwnerId((String)values.get(AttachmentEntity.OWNER));
	}
	
	private void fillMaps() {
		// add values of fields
		values.clear();
		values.put(AttachmentEntity.CATEGORY, entity.getCategoryKey());
		values.put(AttachmentEntity.DESCRIPTION, entity.getDescription());
		values.put(AttachmentEntity.OWNER, entity.getOwnerId());
		values.put(AttachmentEntity.MIMETYPE, entity.getMimeType());
		values.put(AttachmentEntity.FILENAME, entity.getFileName());
		values.put(AttachmentEntity.FILESIZE, entity.getFileSize());
		
		// add bgpaint of fields
		bgpaint.clear();
		// mandatory fields
		for(String field : MANDATORY_FIELDS){
			bgpaint.put(field,Constants.BGP_MANDATORY);
		}		
	}
	
	public String getFileContent() {
		try {
			return Dictionary.getHexString(entity.getFileContent());
		} catch (Exception ex) {
			return Constants.EMPTY;
		}		
	}
	
	public void onUploadFile(ActionEvent event) {
		 if (event instanceof BaseActionEventUpload) {
			 BaseActionEventUpload bae = (BaseActionEventUpload)event;
			 entity.setFileName(bae.getClientFileName());
			 entity.setFileSize(bae.getHexBytes().length);
			 // TODO mime type 			 
			 entity.setFileContent(bae.getHexBytes());
		 }		 
	 }	
}