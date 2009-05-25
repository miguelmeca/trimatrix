package trimatrix.db;

import java.sql.Timestamp;


/**
 * AbstractDoctors entity provides the base persistence definition of the Doctors entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractDoctors  implements java.io.Serializable {


    // Fields    

     private String id;
     private String name;
     private String street;
     private String housenumber;
     private String postcode;
     private String city;
     private String state;
     private String countryKey;
     private String email;
     private String homepage;
     private String telephone;
     private String mobile;
     private String fax;
     private Timestamp createdAt;
     private String createdBy;
     private Timestamp modifiedAt;
     private String modifiedBy;
     private Boolean deleted;
     private Boolean test;


    // Constructors

    /** default constructor */
    public AbstractDoctors() {
    }

	/** minimal constructor */
    public AbstractDoctors(String id) {
        this.id = id;
    }
    
    /** full constructor */
    public AbstractDoctors(String id, String name, String street, String housenumber, String postcode, String city, String state, String countryKey, String email, String homepage, String telephone, String mobile, String fax, Timestamp createdAt, String createdBy, Timestamp modifiedAt, String modifiedBy, Boolean deleted, Boolean test) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.housenumber = housenumber;
        this.postcode = postcode;
        this.city = city;
        this.state = state;
        this.countryKey = countryKey;
        this.email = email;
        this.homepage = homepage;
        this.telephone = telephone;
        this.mobile = mobile;
        this.fax = fax;
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

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return this.street;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }

    public String getHousenumber() {
        return this.housenumber;
    }
    
    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public String getPostcode() {
        return this.postcode;
    }
    
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }

    public String getCountryKey() {
        return this.countryKey;
    }
    
    public void setCountryKey(String countryKey) {
        this.countryKey = countryKey;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomepage() {
        return this.homepage;
    }
    
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return this.fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
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