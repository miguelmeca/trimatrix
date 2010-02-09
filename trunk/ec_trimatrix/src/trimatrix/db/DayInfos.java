package trimatrix.db;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * DayInfos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="day_infos"
    ,catalog="trimatrix"
)

public class DayInfos  implements java.io.Serializable {


    // Fields    

     private DayInfosId id;
     private Integer restingHr;


    // Constructors

    /** default constructor */
    public DayInfos() {
    }

	/** minimal constructor */
    public DayInfos(DayInfosId id) {
        this.id = id;
    }
    
    /** full constructor */
    public DayInfos(DayInfosId id, Integer restingHr) {
        this.id = id;
        this.restingHr = restingHr;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="date", column=@Column(name="date", nullable=false, length=10) ), 
        @AttributeOverride(name="personId", column=@Column(name="person_id", nullable=false, length=36) ) } )

    public DayInfosId getId() {
        return this.id;
    }
    
    public void setId(DayInfosId id) {
        this.id = id;
    }
    
    @Column(name="resting_hr")

    public Integer getRestingHr() {
        return this.restingHr;
    }
    
    public void setRestingHr(Integer restingHr) {
        this.restingHr = restingHr;
    }
   








}