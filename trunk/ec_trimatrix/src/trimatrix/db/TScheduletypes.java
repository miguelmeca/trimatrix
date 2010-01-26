package trimatrix.db;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TScheduletypes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_scheduletypes", catalog = "trimatrix")
public class TScheduletypes implements java.io.Serializable {

	// Fields

	private TScheduletypesId id;
	private String description;
	private String descriptionLong;

	// Constructors

	/** default constructor */
	public TScheduletypes() {
	}

	/** minimal constructor */
	public TScheduletypes(TScheduletypesId id) {
		this.id = id;
	}

	/** full constructor */
	public TScheduletypes(TScheduletypesId id, String description, String descriptionLong) {
		this.id = id;
		this.description = description;
		this.descriptionLong = descriptionLong;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( { @AttributeOverride(name = "key", column = @Column(name = "key", nullable = false, length = 10)),
			@AttributeOverride(name = "languageKey", column = @Column(name = "language_key", nullable = false, length = 2)) })
	public TScheduletypesId getId() {
		return this.id;
	}

	public void setId(TScheduletypesId id) {
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