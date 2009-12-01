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
     private String limits;
     private String times;

    // Constructors

    /** default constructor */
    public CompetitionsScouts() {
    }

	/** minimal constructor */
    public CompetitionsScouts(CompetitionsScoutsId id) {
        this.id = id;
    }
    
    /** full constructor */
    public CompetitionsScouts(CompetitionsScoutsId id, String limits, String times) {
        this.id = id;
        this.limits = limits;
        this.times = times;
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

	@Column(name="limits", columnDefinition="text")

    public String getLimits() {
        return this.limits;
    }
    
    public void setLimits(String limits) {
        this.limits = limits;
    }

    @Column(name="times", columnDefinition="text")
    
	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}    
}