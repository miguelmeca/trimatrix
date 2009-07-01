package trimatrix.db;

/**
 * KAuthorizations entity. @author MyEclipse Persistence Tools
 */

public class KAuthorizations implements java.io.Serializable {

	// Fields

	private String key;
	private String entityKey;
	private String entityField;
	private String valueLow;
	private Boolean exclusive;
	private String valueHigh;

	// Constructors

	/** default constructor */
	public KAuthorizations() {
	}

	/** minimal constructor */
	public KAuthorizations(String key) {
		this.key = key;
	}

	/** full constructor */
	public KAuthorizations(String key, String entityKey, String entityField,
			String valueLow, Boolean exclusive, String valueHigh) {
		this.key = key;
		this.entityKey = entityKey;
		this.entityField = entityField;
		this.valueLow = valueLow;
		this.exclusive = exclusive;
		this.valueHigh = valueHigh;
	}

	// Property accessors

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getEntityKey() {
		return this.entityKey;
	}

	public void setEntityKey(String entityKey) {
		this.entityKey = entityKey;
	}

	public String getEntityField() {
		return this.entityField;
	}

	public void setEntityField(String entityField) {
		this.entityField = entityField;
	}

	public String getValueLow() {
		return this.valueLow;
	}

	public void setValueLow(String valueLow) {
		this.valueLow = valueLow;
	}

	public Boolean getExclusive() {
		return this.exclusive;
	}

	public void setExclusive(Boolean exclusive) {
		this.exclusive = exclusive;
	}

	public String getValueHigh() {
		return this.valueHigh;
	}

	public void setValueHigh(String valueHigh) {
		this.valueHigh = valueHigh;
	}

}