package trimatrix.controls;

import org.eclnt.jsfserver.elements.BaseComponentTag;

public class STARComponentTag extends BaseComponentTag {

	public void setObjectbinding(String value) {
		m_attributes.put("objectbinding", value);
	}

	public void setEnabled(String value) {
		m_attributes.put("enabled", value);
	}

	public void setRendered(String value) {
		m_attributes.put("rendered", value);
	}

	public void setImageon(String value) {
		m_attributes.put("imageon", value);
	}
	
	public void setImageoff(String value) {
		m_attributes.put("imageoff", value);
	}
	
	public void setImagewidth(String value) {
		m_attributes.put("imagewidth", value);
	}

}
