package trimatrix.db;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TCountries entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_countries", catalog = "trimatrix")
public class TCountries implements java.io.Serializable {

	// Fields

	private TCountriesId id;
	private String description;
	private String descriptionLong;

	// Constructors

	/** default constructor */
	public TCountries() {
	}

	/** minimal constructor */
	public TCountries(TCountriesId id) {
		this.id = id;
	}

	/** full constructor */
	public TCountries(TCountriesId id, String description,
			String descriptionLong) {
		this.id = id;
		this.description = description;
		this.descriptionLong = descriptionLong;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "key", column = @Column(name = "key", nullable = false, length = 2)),
			@AttributeOverride(name = "languageKey", column = @Column(name = "language_key", nullable = false, length = 2)) })
	public TCountriesId getId() {
		return this.id;
	}

	public void setId(TCountriesId id) {
		this.id = id;
	}

	@Column(name = "description", length = 20)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "description_long", length = 50)
	public String getDescriptionLong() {
		return this.descriptionLong;
	}

	public void setDescriptionLong(String descriptionLong) {
		this.descriptionLong = descriptionLong;
	}

}