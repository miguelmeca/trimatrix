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
}
