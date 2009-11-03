package trimatrix.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import trimatrix.relations.IRelationObject;

/**
 * PersonsHaveCompetitions entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "persons_have_competitions", catalog = "trimatrix", uniqueConstraints = @UniqueConstraint(columnNames = {
		"person", "competition", "reltyp_key" }))
public class PersonsHaveCompetitions implements java.io.Serializable, IRelationObject {

	// Fields

	private String id;
	private String person;
	private String competition;
	private String reltypKey;
	private Boolean standard;

	// Constructors

	/** default constructor */
	public PersonsHaveCompetitions() {
	}

	/** minimal constructor */
	public PersonsHaveCompetitions(String id) {
		this.id = id;
	}

	/** full constructor */
	public PersonsHaveCompetitions(String id, String person,
			String competition, String reltypKey, Boolean standard) {
		this.id = id;
		this.person = person;
		this.competition = competition;
		this.reltypKey = reltypKey;
		this.standard = standard;
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

	@Column(name = "person", length = 36)
	public String getPerson() {
		return this.person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	@Column(name = "competition", length = 36)
	public String getCompetition() {
		return this.competition;
	}

	public void setCompetition(String competition) {
		this.competition = competition;
	}

	@Column(name = "reltyp_key", length = 10)
	public String getReltypKey() {
		return this.reltypKey;
	}

	public void setReltypKey(String reltypKey) {
		this.reltypKey = reltypKey;
	}

	@Column(name = "standard")
	public Boolean getStandard() {
		return this.standard;
	}

	public void setStandard(Boolean standard) {
		this.standard = standard;
	}

	@Override
	public String toString() {
		// same as DB relation implementation
		return (getPerson() + " " + getReltypKey() + " " + getCompetition());
	}
	
	@Transient
	public String getPartner1() {
		return person;
	}

	@Transient
	public String getPartner2() {
		return competition;
	}
	
	public void setPartner1(String id) {
		person = id;
	}
	
	public void setPartner2(String id) {
		competition = id;
	}

}