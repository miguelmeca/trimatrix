package trimatrix.db;

import java.sql.Timestamp;

/**
 * AbstractPersons entity provides the base persistence definition of the
 * Persons entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPersons implements java.io.Serializable {

	// Fields

	private String id;
	private String salutationKey;
	private String nameFirst;
	private String nameLast;
	private String sexKey;
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
	private Timestamp birthdate;
	private byte[] picture;
	private Timestamp createdAt;
	private String createdBy;
	private Timestamp modifiedAt;
	private String modifiedBy;
	private Boolean deleted;
	private Boolean test;

	// Constructors

	/** default constructor */
	public AbstractPersons() {
	}

	/** full constructor */
	public AbstractPersons(String salutationKey, String nameFirst,
			String nameLast, String sexKey, String street, String housenumber,
			String postcode, String city, String state, String countryKey,
			String email, String homepage, String telephone, String mobile,
			String fax, Timestamp birthdate, byte[] picture, Timestamp createdAt,
			String createdBy, Timestamp modifiedAt, String modifiedBy,
			Boolean deleted, Boolean test) {
		this.salutationKey = salutationKey;
		this.nameFirst = nameFirst;
		this.nameLast = nameLast;
		this.sexKey = sexKey;
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
		this.birthdate = birthdate;
		this.picture = picture;
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

	public String getSalutationKey() {
		return this.salutationKey;
	}

	public void setSalutationKey(String salutationKey) {
		this.salutationKey = salutationKey;
	}

	public String getNameFirst() {
		return this.nameFirst;
	}

	public void setNameFirst(String nameFirst) {
		this.nameFirst = nameFirst;
	}

	public String getNameLast() {
		return this.nameLast;
	}

	public void setNameLast(String nameLast) {
		this.nameLast = nameLast;
	}

	public String getSexKey() {
		return this.sexKey;
	}

	public void setSexKey(String sexKey) {
		this.sexKey = sexKey;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHousenumber() {
		return this.housenumber;
	}

	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountryKey() {
		return this.countryKey;
	}

	public void setCountryKey(String countryKey) {
		this.countryKey = countryKey;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomepage() {
		return this.homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Timestamp getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Timestamp birthdate) {
		this.birthdate = birthdate;
	}

	public byte[] getPicture() {
		return this.picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
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