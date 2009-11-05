package trimatrix.db;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * EntitiesHaveLabels entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "entities_have_labels", catalog = "trimatrix")
public class EntitiesHaveLabels implements java.io.Serializable {

	// Fields

	private EntitiesHaveLabelsId id;

	// Constructors

	/** default constructor */
	public EntitiesHaveLabels() {
	}

	/** full constructor */
	public EntitiesHaveLabels(EntitiesHaveLabelsId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "entity", column = @Column(name = "entity", nullable = false, length = 36)),
			@AttributeOverride(name = "label", column = @Column(name = "label", nullable = false, length = 36)),
			@AttributeOverride(name = "personId", column = @Column(name = "person_id", nullable = false, length = 36)) })
	public EntitiesHaveLabelsId getId() {
		return this.id;
	}

	public void setId(EntitiesHaveLabelsId id) {
		this.id = id;
	}

}