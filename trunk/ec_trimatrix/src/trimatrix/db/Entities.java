package trimatrix.db;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entities entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "entities", catalog = "trimatrix")
public class Entities implements java.io.Serializable {

	// Fields

	private EntitiesId id;

	// Constructors

	/** default constructor */
	public Entities() {
	}

	/** full constructor */
	public Entities(EntitiesId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "id", column = @Column(name = "id", nullable = false, length = 36)),
			@AttributeOverride(name = "entity", column = @Column(name = "entity", nullable = false, length = 10)),
			@AttributeOverride(name = "deleted", column = @Column(name = "deleted", columnDefinition="tinyint")) })
	public EntitiesId getId() {
		return this.id;
	}

	public void setId(EntitiesId id) {
		this.id = id;
	}

}