package trimatrix.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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

     private String id;
     private Date date;
     private String personId;
     private Integer restingHr;


    // Constructors

    /** default constructor */
    public DayInfos() {
    }

	/** minimal constructor */
    public DayInfos(String id) {
        this.id = id;
    }
    
    /** full constructor */
    public DayInfos(String id, Date date, String personId, Integer restingHr) {
        this.id = id;
        this.date = date;
        this.personId = personId;
        this.restingHr = restingHr;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="id", unique=true, nullable=false, length=36)

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name="date", length=19)

    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    @Column(name="person_id", length=36)

    public String getPersonId() {
        return this.personId;
    }
    
    public void setPersonId(String personId) {
        this.personId = personId;
    }
    
    @Column(name="resting_hr")

    public Integer getRestingHr() {
        return this.restingHr;
    }
    
    public void setRestingHr(Integer restingHr) {
        this.restingHr = restingHr;
    }
}