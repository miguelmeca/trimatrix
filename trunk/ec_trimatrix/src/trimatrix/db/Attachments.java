package trimatrix.db;

import java.sql.Timestamp;

import trimatrix.entities.IEntityObject;
import trimatrix.utils.Constants;


/**
 * Attachments entity. @author MyEclipse Persistence Tools
 */
public class Attachments extends AbstractAttachments implements java.io.Serializable, IEntityObject {

    // Constructors

    /** default constructor */
    public Attachments() {
    }

	/** minimal constructor */
    public Attachments(String id) {
        super(id);        
    }
    
    /** full constructor */
    public Attachments(String id, String categoryKey, String description, Persons owner, String mimeType, String fileName, Integer fileSize, byte[] fileContent, Timestamp createdAt, String createdBy, Timestamp modifiedAt, String modifiedBy, Boolean deleted, Boolean test) {
        super(id, categoryKey, description, owner, mimeType, fileName, fileSize, fileContent, createdAt, createdBy, modifiedAt, modifiedBy, deleted, test);        
    }
    
    @Override
	public String toString() {
		// same as DB entity implementation
		return getDescription().replace(Constants.NULL, Constants.EMPTY).trim();
	}
   
}
