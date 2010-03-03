package trimatrix.controls;

import org.eclnt.jsfserver.elements.BaseComponentTag;

public class DECSPINNERComponentTag extends BaseComponentTag {

	public void setValue(String value) {
		m_attributes.put("value", value);
	}
}
