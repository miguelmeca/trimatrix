package trimatrix.db;



/**
 * TProfiles entity. @author MyEclipse Persistence Tools
 */

public class TProfiles  implements java.io.Serializable {


    // Fields    

     private TProfilesId id;
     private String description;
     private String descriptionLong;


    // Constructors

    /** default constructor */
    public TProfiles() {
    }

	/** minimal constructor */
    public TProfiles(TProfilesId id) {
        this.id = id;
    }
    
    /** full constructor */
    public TProfiles(TProfilesId id, String description, String descriptionLong) {
        this.id = id;
        this.description = description;
        this.descriptionLong = descriptionLong;
    }

   
    // Property accessors

    public TProfilesId getId() {
        return this.id;
    }
    
    public void setId(TProfilesId id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionLong() {
        return this.descriptionLong;
    }
    
    public void setDescriptionLong(String descriptionLong) {
        this.descriptionLong = descriptionLong;
    }
   








}