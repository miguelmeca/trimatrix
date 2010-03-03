package trimatrix.controls;

import java.io.IOException;

import javax.faces.context.FacesContext;

import org.eclnt.jsfserver.elements.BaseActionComponent;
import org.eclnt.jsfserver.elements.BaseComponent;
import org.eclnt.jsfserver.elements.impl.BUTTONComponentTag;
import org.eclnt.jsfserver.elements.impl.FIELDComponent;
import org.eclnt.jsfserver.elements.impl.FIELDComponentTag;

public class DECSPINNERComponent extends BaseActionComponent {

	BaseComponent field;
	BaseComponent buttonPlus;
	BaseComponent buttonMinus;

	boolean built = false;

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		if(built==false) {
			built = true;
			// pick component's own attributes
			String value = getAttributeString("value");
			// create field component
			FIELDComponentTag fct = new FIELDComponentTag();
			fct.setId(createSubId());
			fct.setText(value);
			fct.setWidth("100");
			field = fct.createBaseComponent();
			getChildren().add(field);
			// create min button
			BUTTONComponentTag bct1 = new BUTTONComponentTag();
			bct1.setId(createSubId());
			bct1.setText("-");
			buttonMinus = bct1.createBaseComponent();
			getChildren().add(buttonMinus);
			// create plus button
			BUTTONComponentTag bct2 = new BUTTONComponentTag();
			bct2.setId(createSubId());
			bct2.setText("+");
			buttonPlus = bct2.createBaseComponent();
			getChildren().add(buttonPlus);
		}
	}

	@Override
	public void encodeChildren(FacesContext context) throws IOException {
		field.encodeBegin(context);
		buttonMinus.encodeBegin(context);
		buttonPlus.encodeBegin(context);
	}

	@Override
	public void encodeEnd(FacesContext context) throws IOException {}

	@Override
	public boolean getRendersChildren() {
		return true;
	}



}
