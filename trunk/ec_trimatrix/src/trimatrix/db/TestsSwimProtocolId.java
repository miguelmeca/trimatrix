package trimatrix.db;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * TestsSwimProtocolId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class TestsSwimProtocolId  implements java.io.Serializable {


    // Fields    

     private Integer step;
     private Integer attempt;
     private String id;


    // Constructors

    /** default constructor */
    public TestsSwimProtocolId() {
    }

    
    /** full constructor */
    public TestsSwimProtocolId(String id, Integer step, Integer attempt) {
        this.step = step;
        this.attempt = attempt;
        this.id = id;
    }

   
    // Property accessors

    @Column(name="step", nullable=false)

    public Integer getStep() {
        return this.step;
    }
    
    public void setStep(Integer step) {
        this.step = step;
    }

    @Column(name="attempt", nullable=false)

    public Integer getAttempt() {
        return this.attempt;
    }
    
    public void setAttempt(Integer attempt) {
        this.attempt = attempt;
    }

    @Column(name="id", nullable=false, length=36)

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TestsSwimProtocolId) ) return false;
		 TestsSwimProtocolId castOther = ( TestsSwimProtocolId ) other; 
         
		 return ( (this.getStep()==castOther.getStep()) || ( this.getStep()!=null && castOther.getStep()!=null && this.getStep().equals(castOther.getStep()) ) )
 && ( (this.getAttempt()==castOther.getAttempt()) || ( this.getAttempt()!=null && castOther.getAttempt()!=null && this.getAttempt().equals(castOther.getAttempt()) ) )
 && ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getStep() == null ? 0 : this.getStep().hashCode() );
         result = 37 * result + ( getAttempt() == null ? 0 : this.getAttempt().hashCode() );
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         return result;
   }   





}