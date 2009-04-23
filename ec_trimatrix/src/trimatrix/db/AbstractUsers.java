package trimatrix.db;

import java.sql.Timestamp;

/**
 * AbstractUsers entity provides the base persistence definition of the Users
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUsers implements java.io.Serializable {
	// Fields
	private String id;
	private String userName;
	private String userHash;
	private String languageKey;
	private String currencyKey;
	private Boolean locked;
	private Boolean initial;
	private Boolean active;
	private String email;
	private Persons person;
	private Timestamp createdAt;
	private String createdBy;
	private Timestamp modifiedAt;
	private String modifiedBy;
	private Boolean deleted;
	private Boolean test;

	// Constructors
	/** default constructor */
	public AbstractUsers() {
	}

	/** minimal constructor */
	public AbstractUsers(String id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractUsers(String id, String userName, String userHash,
			String languageKey, String currencyKey, Boolean locked,
			Boolean initial, Boolean active, String email, Persons person,
			Timestamp createdAt, String createdBy, Timestamp modifiedAt,
			String modifiedBy, Boolean deleted, Boolean test) {
		this.id = id;
		this.userName = userName;
		this.userHash = userHash;
		this.languageKey = languageKey;
		this.currencyKey = currencyKey;
		this.locked = locked;
		this.initial = initial;
		this.active = active;
		this.email = email;
		this.person = person;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.modifiedAt = modifiedAt;
		this.modifiedBy = modifiedBy;
		this.deleted = deleted;
		this.test = test;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserHash() {
		return this.userHash;
	}

	public void setUserHash(String userHash) {
		this.userHash = userHash;
	}

	public String getLanguageKey() {
		return this.languageKey;
	}

	public void setLanguageKey(String languageKey) {
		this.languageKey = languageKey;
	}

	public String getCurrencyKey() {
		return this.currencyKey;
	}

	public void setCurrencyKey(String currencyKey) {
		this.currencyKey = currencyKey;
	}

	public Boolean getLocked() {
		return this.locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Boolean getInitial() {
		return this.initial;
	}

	public void setInitial(Boolean initial) {
		this.initial = initial;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Persons getPerson() {
		return person;
	}

	public void setPerson(Persons person) {
		this.person = person;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getModifiedAt() {
		return this.modifiedAt;
	}

	public void setModifiedAt(Timestamp modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Boolean getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Boolean getTest() {
		return this.test;
	}

	public void setTest(Boolean test) {
		this.test = test;
	}	
}