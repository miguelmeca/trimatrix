package trimatrix.db;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Categories entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="categories"
    ,catalog="trimatrix"
)

public class Categories  implements java.io.Serializable {


    // Fields    

     private CategoriesId id;


    // Constructors

    /** default constructor */
    public Categories() {
    }

    
    /** full constructor */
    public Categories(CategoriesId id) {
        this.id = id;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="scoutId", column=@Column(name="scout_id", nullable=false, length=36) ), 
        @AttributeOverride(name="category", column=@Column(name="category", length=10) ) } )

    public CategoriesId getId() {
        return this.id;
    }
    
    public void setId(CategoriesId id) {
        this.id = id;
    }
   








}