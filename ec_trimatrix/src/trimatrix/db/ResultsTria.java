package trimatrix.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ResultsTria entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "results_tria", catalog = "trimatrix")
public class ResultsTria implements java.io.Serializable {

	// Fields

	private String id;
	private String category;
	private String swimSplit;
	private String runSplit;
	private String bikeSplit;
	private String swimPosition;
	private String runPosition;
	private String bikePosition;

	// Constructors

	/** default constructor */
	public ResultsTria() {
	}

	/** minimal constructor */
	public ResultsTria(String id) {
		this.id = id;
	}

	/** full constructor */
	public ResultsTria(String id, String category, String swimSplit,
			String runSplit, String bikeSplit, String swimPosition,
			String runPosition, String bikePosition) {
		this.id = id;
		this.category = category;
		this.swimSplit = swimSplit;
		this.runSplit = runSplit;
		this.bikeSplit = bikeSplit;
		this.swimPosition = swimPosition;
		this.runPosition = runPosition;
		this.bikePosition = bikePosition;
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

	@Column(name = "category", length = 10)
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "swim_split", length = 8)
	public String getSwimSplit() {
		return this.swimSplit;
	}

	public void setSwimSplit(String swimSplit) {
		this.swimSplit = swimSplit;
	}

	@Column(name = "run_split", length = 8)
	public String getRunSplit() {
		return this.runSplit;
	}

	public void setRunSplit(String runSplit) {
		this.runSplit = runSplit;
	}

	@Column(name = "swim_position", length = 5)
	public String getSwimPosition() {
		return this.swimPosition;
	}

	public void setSwimPosition(String swimPosition) {
		this.swimPosition = swimPosition;
	}

	@Column(name = "run_position", length = 5)
	public String getRunPosition() {
		return this.runPosition;
	}

	public void setRunPosition(String runPosition) {
		this.runPosition = runPosition;
	}

	@Column(name = "bike_split", length = 8)
	public String getBikeSplit() {
		return bikeSplit;
	}

	public void setBikeSplit(String bikeSplit) {
		this.bikeSplit = bikeSplit;
	}

	@Column(name = "bike_position", length = 5)
	public String getBikePosition() {
		return bikePosition;
	}

	public void setBikePosition(String bikePosition) {
		this.bikePosition = bikePosition;
	}


}