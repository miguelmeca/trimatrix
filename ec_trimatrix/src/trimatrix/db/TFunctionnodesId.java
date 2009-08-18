package trimatrix.db;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TFunctionnodesId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TFunctionnodesId implements java.io.Serializable {

	// Fields

	private String key;
	private String languageKey;

	// Constructors

	/** default constructor */
	public TFunctionnodesId() {
	}

	/** full constructor */
	public TFunctionnodesId(String key, String languageKey) {
		this.key = key;
		this.languageKey = languageKey;
	}

	// Property accessors

	@Column(name = "key", nullable = false, length = 36)
	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "language_key", nullable = false, length = 2)
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
		if (!(other instanceof TFunctionnodesId))
			return false;
		TFunctionnodesId castOther = (TFunctionnodesId) other;

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