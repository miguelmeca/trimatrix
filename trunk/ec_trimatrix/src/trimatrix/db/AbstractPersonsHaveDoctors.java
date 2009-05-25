package trimatrix.db;



/**
 * AbstractPersonsHaveDoctors entity provides the base persistence definition of the PersonsHaveDoctors entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPersonsHaveDoctors  implements java.io.Serializable {


    // Fields    

     private String id;
     private String person;
     private String doctor;
     private String reltypKey;
     private Boolean standard;


    // Constructors

    /** default constructor */
    public AbstractPersonsHaveDoctors() {
    }

	/** minimal constructor */
    public AbstractPersonsHaveDoctors(String id) {
        this.id = id;
    }
    
    /** full constructor */
    public AbstractPersonsHaveDoctors(String id, String person, String doctor, String reltypKey, Boolean standard) {
        this.id = id;
        this.person = person;
        this.doctor = doctor;
        this.reltypKey = reltypKey;
        this.standard = standard;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getPerson() {
        return this.person;
    }
    
    public void setPerson(String person) {
        this.person = person;
    }

    public String getDoctor() {
        return this.doctor;
    }
    
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getReltypKey() {
        return this.reltypKey;
    }
    
    public void setReltypKey(String reltypKey) {
        this.reltypKey = reltypKey;
    }

    public Boolean getStandard() {
        return this.standard;
    }
    
    public void setStandard(Boolean standard) {
        this.standard = standard;
    }
   








}