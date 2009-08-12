package trimatrix.db;



/**
 * TProfilesId entity. @author MyEclipse Persistence Tools
 */

public class TProfilesId  implements java.io.Serializable {


    // Fields    

     private String tableKey;
     private String languageKey;


    // Constructors

    /** default constructor */
    public TProfilesId() {
    }

    
    /** full constructor */
    public TProfilesId(String key, String languageKey) {
        this.tableKey = key;
        this.languageKey = languageKey;
    }

   
    // Property accessors

    public String getTableKey() {
        return this.tableKey;
    }
    
    public void setTableKey(String tableKey) {
        this.tableKey = tableKey;
    }

    public String getLanguageKey() {
        return this.languageKey;
    }
    
    public void setLanguageKey(String languageKey) {
        this.languageKey = languageKey;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TProfilesId) ) return false;
		 TProfilesId castOther = ( TProfilesId ) other; 
         
		 return ( (this.getTableKey()==castOther.getTableKey()) || ( this.getTableKey()!=null && castOther.getTableKey()!=null && this.getTableKey().equals(castOther.getTableKey()) ) )
 && ( (this.getLanguageKey()==castOther.getLanguageKey()) || ( this.getLanguageKey()!=null && castOther.getLanguageKey()!=null && this.getLanguageKey().equals(castOther.getLanguageKey()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getTableKey() == null ? 0 : this.getTableKey().hashCode() );
         result = 37 * result + ( getLanguageKey() == null ? 0 : this.getLanguageKey().hashCode() );
         return result;
   }   





}