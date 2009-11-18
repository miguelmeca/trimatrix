package trimatrix.db;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * CategoriesId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class CategoriesId  implements java.io.Serializable {


    // Fields    

     private String scoutId;
     private String category;


    // Constructors

    /** default constructor */
    public CategoriesId() {
    }

	/** minimal constructor */
    public CategoriesId(String scoutId) {
        this.scoutId = scoutId;
    }
    
    /** full constructor */
    public CategoriesId(String scoutId, String category) {
        this.scoutId = scoutId;
        this.category = category;
    }

   
    // Property accessors

    @Column(name="scout_id", nullable=false, length=36)

    public String getScoutId() {
        return this.scoutId;
    }
    
    public void setScoutId(String scoutId) {
        this.scoutId = scoutId;
    }

    @Column(name="category", length=10)

    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CategoriesId) ) return false;
		 CategoriesId castOther = ( CategoriesId ) other; 
         
		 return ( (this.getScoutId()==castOther.getScoutId()) || ( this.getScoutId()!=null && castOther.getScoutId()!=null && this.getScoutId().equals(castOther.getScoutId()) ) )
 && ( (this.getCategory()==castOther.getCategory()) || ( this.getCategory()!=null && castOther.getCategory()!=null && this.getCategory().equals(castOther.getCategory()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getScoutId() == null ? 0 : this.getScoutId().hashCode() );
         result = 37 * result + ( getCategory() == null ? 0 : this.getCategory().hashCode() );
         return result;
   }   





}