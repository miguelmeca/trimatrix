package trimatrix.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import trimatrix.relations.IRelationObject;


/**
 * PersonsHaveRelations entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="persons_have_relations"
    ,catalog="trimatrix"
, uniqueConstraints = @UniqueConstraint(columnNames={"partner1", "partner2", "reltyp_key"})
)

public class PersonsHaveRelations  implements java.io.Serializable, IRelationObject {

    // Fields    

     private String id;
     private String partner1;
     private String partner2;
     private String reltypKey;
     private Boolean standard;


    // Constructors

    /** default constructor */
    public PersonsHaveRelations() {
    }

	/** minimal constructor */
    public PersonsHaveRelations(String id) {
        this.id = id;
    }
    
    /** full constructor */
    public PersonsHaveRelations(String id, String partner1, String partner2, String reltypKey, Boolean standard) {
        this.id = id;
        this.partner1 = partner1;
        this.partner2 = partner2;
        this.reltypKey = reltypKey;
        this.standard = standard;
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
    
    @Column(name="partner1", length=36)

    public String getPartner1() {
        return this.partner1;
    }
    
    public void setPartner1(String partner1) {
        this.partner1 = partner1;
    }
    
    @Column(name="partner2", length=36)

    public String getPartner2() {
        return this.partner2;
    }
    
    public void setPartner2(String partner2) {
        this.partner2 = partner2;
    }
    
    @Column(name="reltyp_key", length=10)

    public String getReltypKey() {
        return this.reltypKey;
    }
    
    public void setReltypKey(String reltypKey) {
        this.reltypKey = reltypKey;
    }
    
    @Column(name="standard")
    @OrderBy("desc")
    public Boolean getStandard() {
        return this.standard;
    }
    
    public void setStandard(Boolean standard) {
        this.standard = standard;
    }
   
    @Override
	public String toString() {
		// same as DB relation implementation
		return (getPartner1() + " " + getReltypKey() + " " + getPartner2());
	}
}