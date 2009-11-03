package trimatrix.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * ZonesDefinition entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="zones_definition"
    ,catalog="trimatrix"
)

public class ZonesDefinition  implements java.io.Serializable {


    // Fields    

     private String id;
     private String coachId;
     private Integer sequence;
     private String shortcut;
     private String description;
     private String color;
     private Double lactateLow;
     private Double lactateHigh;
     private Integer hrLow;
     private Integer hrHigh;


    // Constructors

    /** default constructor */
    public ZonesDefinition() {
    }

	/** minimal constructor */
    public ZonesDefinition(String id, String coachId) {
        this.id = id;
        this.coachId = coachId;
    }
    
    /** full constructor */
    public ZonesDefinition(String id, String coachId, Integer sequence, String shortcut, String description, String color, Double lactateLow, Double lactateHigh, Integer hrLow, Integer hrHigh) {
        this.id = id;
        this.coachId = coachId;
        this.sequence = sequence;
        this.shortcut = shortcut;
        this.description = description;
        this.color = color;
        this.lactateLow = lactateLow;
        this.lactateHigh = lactateHigh;
        this.hrLow = hrLow;
        this.hrHigh = hrHigh;
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
    
    @Column(name="coach_id", nullable=false, length=36)
    public String getCoachId() {
        return this.coachId;
    }
    
    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }
    
    @Column(name="sequence")

    public Integer getSequence() {
        return this.sequence;
    }
    
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
    
    @Column(name="shortcut", length=20)

    public String getShortcut() {
        return this.shortcut;
    }
    
    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }
    
    @Column(name="description", length=50)

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Column(name="color", length=10)

    public String getColor() {
        return this.color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    @Column(name="lactate_low", columnDefinition="decimal")

    public Double getLactateLow() {
        return this.lactateLow;
    }
    
    public void setLactateLow(Double lactateLow) {
        this.lactateLow = lactateLow;
    }
    
    @Column(name="lactate_high", columnDefinition="decimal")

    public Double getLactateHigh() {
        return this.lactateHigh;
    }
    
    public void setLactateHigh(Double lactateHigh) {
        this.lactateHigh = lactateHigh;
    }
    
    @Column(name="hr_low")

    public Integer getHrLow() {
        return this.hrLow;
    }
    
    public void setHrLow(Integer hrLow) {
        this.hrLow = hrLow;
    }
    
    @Column(name="hr_high")

    public Integer getHrHigh() {
        return this.hrHigh;
    }
    
    public void setHrHigh(Integer hrHigh) {
        this.hrHigh = hrHigh;
    }
   








}