package trimatrix.db;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * CompetitionsScoutsId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class CompetitionsScoutsId  implements java.io.Serializable {


    // Fields    

     private String competitionId;
     private String scoutId;


    // Constructors

    /** default constructor */
    public CompetitionsScoutsId() {
    }

    
    /** full constructor */
    public CompetitionsScoutsId(String competitionId, String scoutId) {
        this.competitionId = competitionId;
        this.scoutId = scoutId;
    }

   
    // Property accessors

    @Column(name="competition_id", nullable=false, length=36)
    public String getCompetitionId() {
        return this.competitionId;
    }
    
    public void setCompetitionId(String competitionId) {
        this.competitionId = competitionId;
    }

    @Column(name="scout_id", nullable=false, length=36)

    public String getScoutId() {
        return this.scoutId;
    }
    
    public void setScoutId(String scoutId) {
        this.scoutId = scoutId;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CompetitionsScoutsId) ) return false;
		 CompetitionsScoutsId castOther = ( CompetitionsScoutsId ) other; 
         
		 return ( (this.getCompetitionId()==castOther.getCompetitionId()) || ( this.getCompetitionId()!=null && castOther.getCompetitionId()!=null && this.getCompetitionId().equals(castOther.getCompetitionId()) ) )
 && ( (this.getScoutId()==castOther.getScoutId()) || ( this.getScoutId()!=null && castOther.getScoutId()!=null && this.getScoutId().equals(castOther.getScoutId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCompetitionId() == null ? 0 : this.getCompetitionId().hashCode() );
         result = 37 * result + ( getScoutId() == null ? 0 : this.getScoutId().hashCode() );
         return result;
   }   





}