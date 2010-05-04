package trimatrix.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserPreferences entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_preferences", catalog = "trimatrix")
public class UserPreferences implements java.io.Serializable {

	// Fields

	private String id;
	private Integer sbvisibleamount;
	private String competitionCategories;
	private String dayinfos;

	// Constructors

	/** default constructor */
	public UserPreferences() {
	}

	/** minimal constructor */
	public UserPreferences(String id) {
		this.id = id;
	}

	/** full constructor */
	public UserPreferences(String id, Integer sbvisibleamount, String competitionCategories, String dayinfos) {
		this.id = id;
		this.sbvisibleamount = sbvisibleamount;
		this.competitionCategories = competitionCategories;
		this.dayinfos = dayinfos;
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

	@Column(name = "sbvisibleamount")
	public Integer getSbvisibleamount() {
		return this.sbvisibleamount;
	}

	public void setSbvisibleamount(Integer sbvisibleamount) {
		this.sbvisibleamount = sbvisibleamount;
	}

	@Column(name = "competition_categories", columnDefinition="text")
	public String getCompetitionCategories() {
		return this.competitionCategories;
	}

	public void setCompetitionCategories(String competitionCategories) {
		this.competitionCategories = competitionCategories;
	}

	@Column(name = "dayinfos", columnDefinition="text")
	public String getDayinfos() {
		return dayinfos;
	}

	public void setDayinfos(String dayinfos) {
		this.dayinfos = dayinfos;
	}
}