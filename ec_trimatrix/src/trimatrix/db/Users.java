package trimatrix.db;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.Email;

import trimatrix.entities.IEntityObject;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "users", catalog = "trimatrix", uniqueConstraints = @UniqueConstraint(columnNames = "user_name"))
public class Users implements java.io.Serializable, IEntityObject {

	// Fields

	private String id;
	private String userName;
	private String userHash;
	private String languageKey;
	private String currencyKey;
	private Boolean locked;
	private Boolean initial;
	private Boolean active;
	private String personId;
	private String email;
	private Timestamp lastLogin;
	private String lastLoginIp;
	private Timestamp createdAt;
	private String createdBy;
	private Timestamp modifiedAt;
	private String modifiedBy;
	private Boolean deleted;
	private Boolean test;
	
	// Collections
	
	private Set<KRoles> roles = new HashSet<KRoles>();
	private Persons person;

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** minimal constructor */
	public Users(String id) {
		this.id = id;
	}

	/** full constructor */
	public Users(String id, String userName, String userHash,
			String languageKey, String currencyKey, Boolean locked,
			Boolean initial, Boolean active, String personId, String email,
			Timestamp lastLogin, String lastLoginIp,
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
		this.personId = personId;
		this.email = email;
		this.lastLogin = lastLogin;
		this.lastLoginIp = lastLoginIp;
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

	@Column(name = "user_name", unique = true, length = 16)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "user_hash", length = 24)
	public String getUserHash() {
		return this.userHash;
	}

	public void setUserHash(String userHash) {
		this.userHash = userHash;
	}

	@Column(name = "language_key", length = 2)
	public String getLanguageKey() {
		return this.languageKey;
	}

	public void setLanguageKey(String languageKey) {
		this.languageKey = languageKey;
	}

	@Column(name = "currency_key", length = 3)
	public String getCurrencyKey() {
		return this.currencyKey;
	}

	public void setCurrencyKey(String currencyKey) {
		this.currencyKey = currencyKey;
	}

	@Column(name = "locked")
	public Boolean getLocked() {
		return this.locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	@Column(name = "initial")
	public Boolean getInitial() {
		return this.initial;
	}

	public void setInitial(Boolean initial) {
		this.initial = initial;
	}

	@Column(name = "active")
	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Column(name = "person_id", length = 36, updatable = false, insertable = false)
	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	@Column(name = "email")
	@Email
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "last_login", length = 19)
	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Column(name = "last_login_ip")
	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
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
	@JoinColumn(name="person_id")
	public Persons getPerson() {
		return person;
	}

	public void setPerson(Persons person) {
		this.person = person;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="users_have_roles", 
			   joinColumns=@JoinColumn(name="user_id"),
			   inverseJoinColumns=@JoinColumn(name="role_key"))
	public Set<KRoles> getRoles() {
		return roles;
	}

	public void setRoles(Set<KRoles> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return userName;
	}
}