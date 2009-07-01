package trimatrix.db;

/**
 * ListVariants entity. @author MyEclipse Persistence Tools
 */

public class ListVariants implements java.io.Serializable {

	// Fields

	private ListVariantsId id;
	private String columnsSequence;
	private String columnsWidth;

	// Constructors

	/** default constructor */
	public ListVariants() {
	}

	/** minimal constructor */
	public ListVariants(ListVariantsId id) {
		this.id = id;
	}

	/** full constructor */
	public ListVariants(ListVariantsId id, String columnsSequence,
			String columnsWidth) {
		this.id = id;
		this.columnsSequence = columnsSequence;
		this.columnsWidth = columnsWidth;
	}

	// Property accessors

	public ListVariantsId getId() {
		return this.id;
	}

	public void setId(ListVariantsId id) {
		this.id = id;
	}

	public String getColumnsSequence() {
		return this.columnsSequence;
	}

	public void setColumnsSequence(String columnsSequence) {
		this.columnsSequence = columnsSequence;
	}

	public String getColumnsWidth() {
		return this.columnsWidth;
	}

	public void setColumnsWidth(String columnsWidth) {
		this.columnsWidth = columnsWidth;
	}

}