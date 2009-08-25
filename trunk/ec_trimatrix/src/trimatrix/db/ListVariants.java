package trimatrix.db;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * ListVariants entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="list_variants"
    ,catalog="trimatrix"
)

public class ListVariants  implements java.io.Serializable {


    // Fields    

     private ListVariantsId id;
     private String columnsSequence;
     private String columnsWidth;


    // Constructors

    /** default constructor */
    public ListVariants() {
    }

	/** minimal constructor */
    public ListVariants(ListVariantsId id) {
        this.id = id;
    }
    
    /** full constructor */
    public ListVariants(ListVariantsId id, String columnsSequence, String columnsWidth) {
        this.id = id;
        this.columnsSequence = columnsSequence;
        this.columnsWidth = columnsWidth;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="list", column=@Column(name="list", nullable=false, length=25) ), 
        @AttributeOverride(name="entity", column=@Column(name="entity", nullable=false, length=25) ), 
        @AttributeOverride(name="userId", column=@Column(name="user_id", nullable=false, length=36) ) } )

    public ListVariantsId getId() {
        return this.id;
    }
    
    public void setId(ListVariantsId id) {
        this.id = id;
    }
    
    @Column(name="columns_sequence", columnDefinition="text")

    public String getColumnsSequence() {
        return this.columnsSequence;
    }
    
    public void setColumnsSequence(String columnsSequence) {
        this.columnsSequence = columnsSequence;
    }
    
    @Column(name="columns_width", columnDefinition="text")

    public String getColumnsWidth() {
        return this.columnsWidth;
    }
    
    public void setColumnsWidth(String columnsWidth) {
        this.columnsWidth = columnsWidth;
    }
   








}