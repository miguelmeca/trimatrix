package trimatrix.db;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * TestsSwimProtocol entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tests_swim_protocol"
    ,catalog="trimatrix"
)

public class TestsSwimProtocol  implements java.io.Serializable {


    // Fields    

     private TestsSwimProtocolId id;
     private Integer intensity;
     private String time;
     private String targetTime;
     private Boolean failed;
     private String lactate;
     private String hr;
     private String strokes;
     private String splitTimes;
     private String comment;

    // Constructors

    /** default constructor */
    public TestsSwimProtocol() {
    }

	/** minimal constructor */
    public TestsSwimProtocol(TestsSwimProtocolId id) {
        this.id = id;
    }
    
    /** full constructor */
    public TestsSwimProtocol(TestsSwimProtocolId id, Integer intensity, String time, String targetTime, Boolean failed, String lactate, String hr, String strokes, String splitTimes, String comment) {
        this.id = id;
        this.intensity = intensity;
        this.time = time;
        this.targetTime = targetTime;
        this.failed = failed;
        this.lactate = lactate;
        this.hr = hr;
        this.strokes = strokes;
        this.splitTimes = splitTimes;
        this.comment = comment;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="step", column=@Column(name="step", nullable=false) ), 
        @AttributeOverride(name="attempt", column=@Column(name="attempt", nullable=false) ), 
        @AttributeOverride(name="id", column=@Column(name="id", nullable=false, length=36) ) } )

    public TestsSwimProtocolId getId() {
        return this.id;
    }
    
    public void setId(TestsSwimProtocolId id) {
        this.id = id;
    }
    
    @Column(name="intensity")

    public Integer getIntensity() {
        return this.intensity;
    }
    
    public void setIntensity(Integer intensity) {
        this.intensity = intensity;
    }
    
    @Column(name="time", length=5)

    public String getTime() {
        return this.time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    @Column(name="target_time", length=5)

    public String getTargetTime() {
        return this.targetTime;
    }
    
    public void setTargetTime(String targetTime) {
        this.targetTime = targetTime;
    }
    
    @Column(name="failed")

    public Boolean getFailed() {
        return this.failed;
    }
    
    public void setFailed(Boolean failed) {
        this.failed = failed;
    }
    
    @Column(name="lactate", columnDefinition="text")

    public String getLactate() {
        return this.lactate;
    }
    
    public void setLactate(String lactate) {
        this.lactate = lactate;
    }
    
    @Column(name="hr", columnDefinition="text")

    public String getHr() {
        return this.hr;
    }
    
    public void setHr(String hr) {
        this.hr = hr;
    }
    
    @Column(name="strokes", columnDefinition="text")

    public String getStrokes() {
        return this.strokes;
    }
    
    public void setStrokes(String strokes) {
        this.strokes = strokes;
    }
    
    @Column(name="split_times", columnDefinition="text")

    public String getSplitTimes() {
        return this.splitTimes;
    }
    
    public void setSplitTimes(String splitTimes) {
        this.splitTimes = splitTimes;
    }
    
    @Column(name="comment", columnDefinition="text")

    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
}