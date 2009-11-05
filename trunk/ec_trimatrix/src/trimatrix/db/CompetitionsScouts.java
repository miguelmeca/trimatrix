package trimatrix.db;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * CompetitionsScouts entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="competitions_scouts"
    ,catalog="trimatrix"
)

public class CompetitionsScouts  implements java.io.Serializable {


    // Fields    

     private CompetitionsScoutsId id;
     private String factors;


    // Constructors

    /** default constructor */
    public CompetitionsScouts() {
    }

	/** minimal constructor */
    public CompetitionsScouts(CompetitionsScoutsId id) {
        this.id = id;
    }
    
    /** full constructor */
    public CompetitionsScouts(CompetitionsScoutsId id, String factors) {
        this.id = id;
        this.factors = factors;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="id", column=@Column(name="id", nullable=false, length=36) ), 
        @AttributeOverride(name="scoutId", column=@Column(name="scout_id", nullable=false, length=36) ) } )

    public CompetitionsScoutsId getId() {
        return this.id;
    }
    
    public void setId(CompetitionsScoutsId id) {
        this.id = id;
    }
    
    @Column(name="factors", columnDefinition="text")

    public String getFactors() {
        return this.factors;
    }
    
    public void setFactors(String factors) {
        this.factors = factors;
    }
   








}