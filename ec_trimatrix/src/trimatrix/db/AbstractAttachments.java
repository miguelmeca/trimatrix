package trimatrix.db;

import java.sql.Timestamp;


/**
 * AbstractAttachments entity provides the base persistence definition of the Attachments entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAttachments  implements java.io.Serializable {


    // Fields    

     private String id;
     private String categoryKey;
     private String description;
     private Persons owner;
     private String mimeType;
     private String fileName;
     private Integer fileSize;
     private byte[] fileContent;
     private Timestamp createdAt;
     private String createdBy;
     private Timestamp modifiedAt;
     private String modifiedBy;
     private Boolean deleted;
     private Boolean test;


    // Constructors

    /** default constructor */
    public AbstractAttachments() {
    }

	/** minimal constructor */
    public AbstractAttachments(String id) {
        this.id = id;
    }
    
    /** full constructor */
    public AbstractAttachments(String id, String categoryKey, String description, Persons owner, String mimeType, String fileName, Integer fileSize, byte[] fileContent, Timestamp createdAt, String createdBy, Timestamp modifiedAt, String modifiedBy, Boolean deleted, Boolean test) {
        this.id = id;
        this.categoryKey = categoryKey;
        this.description = description;
        this.owner = owner;
        this.mimeType = mimeType;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileContent = fileContent;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
        this.deleted = deleted;
        this.test = test;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryKey() {
        return this.categoryKey;
    }
    
    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Persons getOwner() {
        return this.owner;
    }
    
    public void setOwner(Persons owner) {
        this.owner = owner;
    }

    public String getMimeType() {
        return this.mimeType;
    }
    
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getFileName() {
        return this.fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileSize() {
        return this.fileSize;
    }
    
    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getFileContent() {
        return this.fileContent;
    }
    
    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getModifiedAt() {
        return this.modifiedAt;
    }
    
    public void setModifiedAt(Timestamp modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }
    
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Boolean getDeleted() {
        return this.deleted;
    }
    
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getTest() {
        return this.test;
    }
    
    public void setTest(Boolean test) {
        this.test = test;
    }
   








}