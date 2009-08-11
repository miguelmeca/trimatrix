package trimatrix.db;



/**
 * PersonsHaveProfiles entity. @author MyEclipse Persistence Tools
 */

public class PersonsHaveProfiles  implements java.io.Serializable {


    // Fields    

     private PersonsHaveProfilesId id;


    // Constructors

    /** default constructor */
    public PersonsHaveProfiles() {
    }

    
    /** full constructor */
    public PersonsHaveProfiles(PersonsHaveProfilesId id) {
        this.id = id;
    }

   
    // Property accessors

    public PersonsHaveProfilesId getId() {
        return this.id;
    }
    
    public void setId(PersonsHaveProfilesId id) {
        this.id = id;
    }
   








}