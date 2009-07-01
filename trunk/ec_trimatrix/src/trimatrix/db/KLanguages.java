package trimatrix.db;

/**
 * KLanguages entity. @author MyEclipse Persistence Tools
 */

public class KLanguages implements java.io.Serializable {

	// Fields

	private String key;
	private Boolean logon;

	// Constructors

	/** default constructor */
	public KLanguages() {
	}

	/** full constructor */
	public KLanguages(String key, Boolean logon) {
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