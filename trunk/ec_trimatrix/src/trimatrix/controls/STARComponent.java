package trimatrix.controls;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.eclnt.jsfserver.elements.BaseActionComponent;
import org.eclnt.jsfserver.elements.BaseComponent;
import org.eclnt.jsfserver.elements.impl.BUTTONComponentTag;

import trimatrix.utils.Helper;

public class STARComponent extends BaseActionComponent {
	private static String WIDTH = "20";
	private static int COUNT = 5;

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		if(getChildren().size() == 0) {
			// pick component's own attributes
			String objectbinding = getAttributeString("objectbinding");
			String enabled = getAttributeString("enabled");
			String rendered = getAttributeString("rendered");

			if(!Helper.isEmpty(objectbinding)) objectbinding = objectbinding.replace('}', '.');
			// create star buttons
			for(int i=1;i<=COUNT;i++) {
				BUTTONComponentTag bct = new BUTTONComponentTag();
				bct.setId(createSubId());
				bct.setEnabled(enabled);
				bct.setRendered(rendered);
				bct.setContentareafilled("false");
				bct.setFocusable("false");
				bct.setImage(objectbinding + "star" + String.valueOf(i) + "}");
				bct.setWidth(WIDTH);
				bct.setImagewidth(WIDTH);
				if(!Helper.isEmpty(objectbinding)) bct.setActionListener(objectbinding + "onStar" + String.valueOf(i) + "}");
				BaseComponent button = bct.createBaseComponent();
				getChildren().add(button);
			}
		}
	}

	@Override
	public void encodeChildren(FacesContext context) throws IOException {
		for(UIComponent uc : getChildren()) {
			BaseComponent bc = (BaseComponent)uc;
			bc.encodeBegin(context);
			bc.encodeEnd(context);
		}
	}

	@Override
	public void encodeEnd(FacesContext context) throws IOException {}

	@Override
	public boolean getRendersChildren() {
		return true;
	}
}
