package trimatrix.db;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.ManyToAny;

import trimatrix.entities.IEntityObject;

/**
 * Results entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "results", catalog = "trimatrix")
public class Results implements java.io.Serializable, IEntityObject{

	// Fields

	private String id;
	private String competitionId;
	private String scoutId;
	private String athleteId;
	private String finalPosition;
	private String time;
	private String comment;
	private Timestamp createdAt;
	private String createdBy;
	private Timestamp modifiedAt;
	private String modifiedBy;
	private Boolean deleted;
	private Boolean test;

	// Collections
	private Competitions competition;
	private Persons athlete;
	private Persons scout;
	private ResultsTria resultsTria;

	// Constructors

	/** default constructor */
	public Results() {
	}

	/** minimal constructor */
	public Results(String id, String competitionId, String scoutId,
			String athleteId) {
		this.id = id;
		this.competitionId = competitionId;
		this.scoutId = scoutId;
		this.athleteId = athleteId;
	}

	/** full constructor */
	public Results(String id, String competitionId, String scoutId,
			String athleteId, String finalPosition, String time,
			String comment, Timestamp createdAt, String createdBy,
			Timestamp modifiedAt, String modifiedBy, Boolean deleted,
			Boolean test) {
		this.id = id;
		this.competitionId = competitionId;
		this.scoutId = scoutId;
		this.athleteId = athleteId;
		this.finalPosition = finalPosition;
		this.time = time;
		this.comment = comment;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.modifiedAt = modifiedAt;
		this.modifiedBy = modifiedBy;
		this.deleted = deleted;
		this.test = test;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "competition_id", nullable = false, length = 36, updatable = false, insertable = false)
	public String getCompetitionId() {
		return this.competitionId;
	}

	public void setCompetitionId(String competitionId) {
		this.competitionId = competitionId;
	}

	@Column(name = "scout_id", nullable = false, length = 36, updatable = false, insertable = false)
	public String getScoutId() {
		return this.scoutId;
	}

	public void setScoutId(String scoutId) {
		this.scoutId = scoutId;
	}

	@Column(name = "athlete_id", nullable = false, length = 36, updatable = false, insertable = false)
	public String getAthleteId() {
		return this.athleteId;
	}

	public void setAthleteId(String athleteId) {
		this.athleteId = athleteId;
	}

	@Column(name = "final_position", length = 5)
	public String getFinalPosition() {
		return this.finalPosition;
	}

	public void setFinalPosition(String finalPosition) {
		this.finalPosition = finalPosition;
	}

	@Column(name = "time", length = 8)
	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Column(name = "comment", columnDefinition="text")
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "created_at", length = 19)
	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "created_by", length = 36)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "modified_at", length = 19)
	@Version
	public Timestamp getModifiedAt() {
		return this.modifiedAt;
	}

	public void setModifiedAt(Timestamp modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	@Column(name = "modified_by", length = 36)
	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name = "deleted")
	public Boolean getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Column(name = "test")
	public Boolean getTest() {
		return this.test;
	}

	public void setTest(Boolean test) {
		this.test = test;
	}

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name="competition_id")
	public Competitions getCompetition() {
		return competition;
	}

	public void setCompetition(Competitions competition) {
		this.competition = competition;
	}

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name="athlete_id")
	public Persons getAthlete() {
		return athlete;
	}

	public void setAthlete(Persons athlete) {
		this.athlete = athlete;
	}

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name="scout_id")
	public Persons getScout() {
		return scout;
	}

	public void setScout(Persons scout) {
		this.scout = scout;
	}

	@OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @LazyToOne(LazyToOneOption.FALSE)
	public ResultsTria getResultsTria() {
		return resultsTria;
	}

	public void setResultsTria(ResultsTria resultsTria) {
		this.resultsTria = resultsTria;
	}

	@Override
	public String toString() {
		// same as DB entity implementation
		return competition.toString();
	}
}