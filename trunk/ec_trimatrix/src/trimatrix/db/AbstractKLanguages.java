package trimatrix.db;

/**
 * AbstractKLanguages entity provides the base persistence definition of the
 * KLanguages entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractKLanguages implements java.io.Serializable {

	// Fields

	private String key;
	private Boolean logon;

	// Constructors

	/** default constructor */
	public AbstractKLanguages() {
	}

	/** full constructor */
	public AbstractKLanguages(String key, Boolean logon) {
		this.key = key;
		this.logon = logon;
	}

	// Property accessors

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Boolean getLogon() {
		return this.logon;
	}

	public void setLogon(Boolean logon) {
		this.logon = logon;
	}

}