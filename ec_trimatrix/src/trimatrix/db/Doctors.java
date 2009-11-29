package trimatrix.db;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.validator.Email;

import trimatrix.entities.IEntityObject;
import trimatrix.utils.Constants;

/**
 * Doctors entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "doctors", catalog = "trimatrix")
public class Doctors implements java.io.Serializable, IEntityObject {

	// Fields

	private String id;
	private String name;
	private String street;
	private String housenumber;
	private String postcode;
	private String city;
	private String state;
	private String countryKey;
	private String email;
	private String homepage;
	private String telephone;
	private String mobile;
	private String fax;
	private Timestamp createdAt;
	private String createdBy;
	private Timestamp modifiedAt;
	private String modifiedBy;
	private Boolean deleted;
	private Boolean test;

	// Constructors

	/** default constructor */
	public Doctors() {
	}

	/** minimal constructor */
	public Doctors(String id) {
		this.id = id;
	}

	/** full constructor */
	public Doctors(String id, String name, String street, String housenumber,
			String postcode, String city, String state, String countryKey,
			String email, String homepage, String telephone, String mobile,
			String fax, Timestamp createdAt, String createdBy,
			Timestamp modifiedAt, String modifiedBy, Boolean deleted,
			Boolean test) {
		this.id = id;
		this.name = name;
		this.street = street;
		this.housenumber = housenumber;
		this.postcode = postcode;
		this.city = city;
		this.state = state;
		this.countryKey = countryKey;
		this.email = email;
		this.homepage = homepage;
		this.telephone = telephone;
		this.mobile = mobile;
		this.fax = fax;
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

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "street", length = 45)
	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Column(name = "housenumber", length = 45)
	public String getHousenumber() {
		return this.housenumber;
	}

	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}

	@Column(name = "postcode", length = 45)
	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Column(name = "city", length = 45)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "state", length = 45)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "country_key", length = 2)
	public String getCountryKey() {
		return this.countryKey;
	}

	public void setCountryKey(String countryKey) {
		this.countryKey = countryKey;
	}

	@Column(name = "email")
	@Email
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "homepage")
	public String getHomepage() {
		return this.homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	@Column(name = "telephone", length = 25)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "mobile", length = 25)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "fax", length = 25)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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

	@Override
	public String toString() {
		// same as DB entity implementation
		return name.replace(Constants.NULL, Constants.EMPTY).trim();
	}

}