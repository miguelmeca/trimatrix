package trimatrix.db;



/**
 * PersonsHaveProfilesId entity. @author MyEclipse Persistence Tools
 */

public class PersonsHaveProfilesId  implements java.io.Serializable {


    // Fields    

     private String personId;
     private String profileKey;


    // Constructors

    /** default constructor */
    public PersonsHaveProfilesId() {
    }

    
    /** full constructor */
    public PersonsHaveProfilesId(String personId, String profileKey) {
        this.personId = personId;
        this.profileKey = profileKey;
    }

   
    // Property accessors

    public String getPersonId() {
        return this.personId;
    }
    
    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getProfileKey() {
        return this.profileKey;
    }
    
    public void setProfileKey(String profileKey) {
        this.profileKey = profileKey;
    }
   



   @Override
public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PersonsHaveProfilesId) ) return false;
		 PersonsHaveProfilesId castOther = ( PersonsHaveProfilesId ) other; 
         
		 return ( (this.getPersonId()==castOther.getPersonId()) || ( this.getPersonId()!=null && castOther.getPersonId()!=null && this.getPersonId().equals(castOther.getPersonId()) ) )
 && ( (this.getProfileKey()==castOther.getProfileKey()) || ( this.getProfileKey()!=null && castOther.getProfileKey()!=null && this.getProfileKey().equals(castOther.getProfileKey()) ) );
   }
   
   @Override
public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getPersonId() == null ? 0 : this.getPersonId().hashCode() );
         result = 37 * result + ( getProfileKey() == null ? 0 : this.getProfileKey().hashCode() );
         return result;
   }   





}