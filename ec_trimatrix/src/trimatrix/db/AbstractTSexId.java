package trimatrix.db;

/**
 * AbstractTSexId entity provides the base persistence definition of the TSexId
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTSexId implements java.io.Serializable {

	// Fields

	private String key;
	private String languageKey;

	// Constructors

	/** default constructor */
	public AbstractTSexId() {
	}

	/** full constructor */
	public AbstractTSexId(String key, String languageKey) {
		this.key = key;
		this.languageKey = languageKey;
	}

	// Property accessors

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLanguageKey() {
		return this.languageKey;
	}

	public void setLanguageKey(String languageKey) {
		this.languageKey = languageKey;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractTSexId))
			return false;
		AbstractTSexId castOther = (AbstractTSexId) other;

		return ((this.getKey() == castOther.getKey()) || (this.getKey() != null
				&& castOther.getKey() != null && this.getKey().equals(
				castOther.getKey())))
				&& ((this.getLanguageKey() == castOther.getLanguageKey()) || (this
						.getLanguageKey() != null
						&& castOther.getLanguageKey() != null && this
						.getLanguageKey().equals(castOther.getLanguageKey())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getKey() == null ? 0 : this.getKey().hashCode());
		result = 37
				* result
				+ (getLanguageKey() == null ? 0 : this.getLanguageKey()
						.hashCode());
		return result;
	}

}