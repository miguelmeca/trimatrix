package trimatrix.db;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ImportTemplates entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "import_templates", catalog = "trimatrix")
public class ImportTemplates implements java.io.Serializable {

	// Fields

	private ImportTemplatesId id;
	private Integer startingRow;
	private String mapping;

	// Constructors

	/** default constructor */
	public ImportTemplates() {
	}

	/** minimal constructor */
	public ImportTemplates(ImportTemplatesId id) {
		this.id = id;
	}

	/** full constructor */
	public ImportTemplates(ImportTemplatesId id, Integer startingRow,
			String mapping) {
		this.id = id;
		this.startingRow = startingRow;
		this.mapping = mapping;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "entity", column = @Column(name = "entity", nullable = false, length = 25)),
			@AttributeOverride(name = "personId", column = @Column(name = "person_id", nullable = false, length = 36)),
			@AttributeOverride(name = "description", column = @Column(name = "description", nullable = false, length = 50)) })
	public ImportTemplatesId getId() {
		return this.id;
	}

	public void setId(ImportTemplatesId id) {
		this.id = id;
	}

	@Column(name = "starting_row")
	public Integer getStartingRow() {
		return this.startingRow;
	}

	public void setStartingRow(Integer startingRow) {
		this.startingRow = startingRow;
	}

	@Column(name = "mapping", columnDefinition="text")
	public String getMapping() {
		return this.mapping;
	}

	public void setMapping(String mapping) {
		this.mapping = mapping;
	}

}