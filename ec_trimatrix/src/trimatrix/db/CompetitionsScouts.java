package trimatrix.db;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    // Constructors

    /** default constructor */
    public CompetitionsScouts() {
    }

	/** minimal constructor */
    public CompetitionsScouts(CompetitionsScoutsId id) {
        this.id = id;
    }

    /** full constructor */
    public CompetitionsScouts(CompetitionsScoutsId id, String limits) {
        this.id = id;
        this.limits = limits;
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

}