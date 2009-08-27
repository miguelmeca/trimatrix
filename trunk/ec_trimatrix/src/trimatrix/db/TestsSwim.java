package trimatrix.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.beanutils.BeanUtils;


/**
 * TestsSwim entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tests_swim"
    ,catalog="trimatrix"
)

public class TestsSwim  implements java.io.Serializable {


    // Fields    

     private String id;
     private Timestamp date2;
     private String assistantName;
     private String baths;
     private String pool;
     private Integer distance;
     private Integer splits;


    // Constructors

    /** default constructor */
    public TestsSwim() {
    }

	/** minimal constructor */
    public TestsSwim(String id) {
        this.id = id;
    }
    
    /** full constructor */
    public TestsSwim(String id, Timestamp date2, String assistantName, String baths, String pool, Integer distance, Integer splits) {
        this.id = id;
        this.date2 = date2;
        this.assistantName = assistantName;
        this.baths = baths;
        this.pool = pool;
        this.distance = distance;
        this.splits = splits;
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
    
    @Column(name="date2", length=19)

    public Timestamp getDate2() {
        return this.date2;
    }
    
    public void setDate2(Timestamp date2) {
        this.date2 = date2;
    }
    
    @Column(name="assistant_name", length=50)

    public String getAssistantName() {
        return this.assistantName;
    }
    
    public void setAssistantName(String assistantName) {
        this.assistantName = assistantName;
    }
    
    @Column(name="baths", length=50)

    public String getBaths() {
        return this.baths;
    }
    
    public void setBaths(String baths) {
        this.baths = baths;
    }
    
    @Column(name="pool", length=50)

    public String getPool() {
        return this.pool;
    }
    
    public void setPool(String pool) {
        this.pool = pool;
    }
    
    @Column(name="distance")

    public Integer getDistance() {
        return this.distance;
    }
    
    public void setDistance(Integer distance) {
        this.distance = distance;
    }
    
    @Column(name="splits")

    public Integer getSplits() {
        return this.splits;
    }
    
    public void setSplits(Integer splits) {
        this.splits = splits;
    }    
}