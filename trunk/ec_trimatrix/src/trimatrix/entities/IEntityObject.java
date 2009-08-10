package trimatrix.entities;

import java.sql.Timestamp;

public interface IEntityObject {
	public String getId();
	
	public void setModifiedAt(Timestamp modifiedAt);
	
	public Timestamp getModifiedAt();
	
	public void setModifiedBy(String modifiedBy);
	
	public String getModifiedBy();
	
	public void setCreatedAt(Timestamp createdAt);
	
	public Timestamp getCreatedAt();
	
	public void setCreatedBy(String createdBy);
	
	public String getCreatedBy();

	public Boolean getTest();
	
	public void setDeleted(Boolean deleted);
	
	public Boolean getDeleted();	
}
