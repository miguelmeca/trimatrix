package trimatrix.db;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TSex entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_sex", catalog = "trimatrix")
public class TSex implements java.io.Serializable {

	// Fields

	private TSexId id;
	private String description;
	private String descriptionLong;

	// Constructors

	/** default constructor */
	public TSex() {
	}

	/** minimal constructor */
	public TSex(TSexId id) {
		this.id = id;
	}

	/** full constructor */
	public TSex(TSexId id, String description, String descriptionLong) {
		this.id = id;
		this.description = description;
		this.descriptionLong = descriptionLong;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "key", column = @Column(name = "key", nullable = false, length = 1)),
			@AttributeOverride(name = "languageKey", column = @Column(name = "language_key", nullable = false, length = 2)) })
	public TSexId getId() {
		return this.id;
	}

	public void setId(TSexId id) {
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