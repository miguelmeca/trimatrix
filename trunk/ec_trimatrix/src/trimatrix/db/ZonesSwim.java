package trimatrix.db;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ZonesSwim entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "zones_swim", catalog = "trimatrix")
public class ZonesSwim implements java.io.Serializable {

	// Fields

	private ZonesSwimId id;
	private String timeLow;
	private String timeHigh;

	// Constructors

	/** default constructor */
	public ZonesSwim() {
	}

	/** minimal constructor */
	public ZonesSwim(ZonesSwimId id) {
		this.id = id;
	}

	/** full constructor */
	public ZonesSwim(ZonesSwimId id, String timeLow, String timeHigh) {
		this.id = id;
		this.timeLow = timeLow;
		this.timeHigh = timeHigh;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( { @AttributeOverride(name = "distance", column = @Column(name = "distance", nullable = false)),
			@AttributeOverride(name = "athleteId", column = @Column(name = "athlete_id", nullable = false, length = 36)),
			@AttributeOverride(name = "zonesDefinitionId", column = @Column(name = "zones_definition_id", nullable = false, length = 36)) })
	public ZonesSwimId getId() {
		return this.id;
	}

	public void setId(ZonesSwimId id) {
		this.id = id;
	}

	@Column(name = "time_low", length = 9)
	public String getTimeLow() {
		return this.timeLow;
	}

	public void setTimeLow(String timeLow) {
		this.timeLow = timeLow;
	}

	@Column(name = "time_high", length = 9)
	public String getTimeHigh() {
		return this.timeHigh;
	}

	public void setTimeHigh(String timeHigh) {
		this.timeHigh = timeHigh;
	}

}