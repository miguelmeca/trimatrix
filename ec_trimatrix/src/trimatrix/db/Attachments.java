package trimatrix.db;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import trimatrix.entities.IEntityObject;
import trimatrix.utils.Constants;


/**
 * Attachments entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="attachments"
    ,catalog="trimatrix"
)

public class Attachments  implements java.io.Serializable, IEntityObject {


    // Fields    

     private String id;
     private String categoryKey;
     private String description;
     private String ownerId;
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
     
     // Collections
     private Persons owner;


    // Constructors

    /** default constructor */
    public Attachments() {
    }

	/** minimal constructor */
    public Attachments(String id) {
        this.id = id;
    }
    
    /** full constructor */
    public Attachments(String id, String categoryKey, String description, String ownerId, String mimeType, String fileName, Integer fileSize, byte[] fileContent, Timestamp createdAt, String createdBy, Timestamp modifiedAt, String modifiedBy, Boolean deleted, Boolean test) {
        this.id = id;
        this.categoryKey = categoryKey;
        this.description = description;
        this.ownerId = ownerId;
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
    @Id 
    
    @Column(name="id", unique=true, nullable=false, length=36)

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name="category_key", length=10)

    public String getCategoryKey() {
        return this.categoryKey;
    }
    
    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey;
    }
    
    @Column(name="description", length=150)

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Column(name="owner_id", length=36, updatable = false, insertable = false)
    public String getOwnerId() {
        return this.ownerId;
    }
    
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
    
    @Column(name="mime_type", length=100)

    public String getMimeType() {
        return this.mimeType;
    }
    
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
    
    @Column(name="file_name")

    public String getFileName() {
        return this.fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    @Column(name="file_size")

    public Integer getFileSize() {
        return this.fileSize;
    }
    
    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }
    
    @Column(name="file_content", columnDefinition="mediumblob")    
    public byte[] getFileContent() {
        return this.fileContent;
    }
    
    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }
    
    @Column(name="created_at", length=19)

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    @Column(name="created_by", length=36)

    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    @Column(name="modified_at", length=19)
    @Version
    public Timestamp getModifiedAt() {
        return this.modifiedAt;
    }
    
    public void setModifiedAt(Timestamp modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
    
    @Column(name="modified_by", length=36)

    public String getModifiedBy() {
        return this.modifiedBy;
    }
    
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    
    @Column(name="deleted")

    public Boolean getDeleted() {
        return this.deleted;
    }
    
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    
    @Column(name="test")

    public Boolean getTest() {
        return this.test;
    }
    
    public void setTest(Boolean test) {
        this.test = test;
    }
    
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name="owner_id")
    public Persons getOwner() {
		return owner;
	}

	public void setOwner(Persons owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		// same as DB entity implementation
		return description.replace(Constants.NULL, Constants.EMPTY).trim();
	}
}