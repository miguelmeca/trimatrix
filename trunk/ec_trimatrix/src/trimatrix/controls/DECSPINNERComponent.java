package trimatrix.controls;

import java.io.IOException;

import javax.faces.context.FacesContext;

import org.eclnt.jsfserver.elements.BaseActionComponent;
import org.eclnt.jsfserver.elements.BaseComponent;
import org.eclnt.jsfserver.elements.impl.BEANPROCESSINGComponentTag;
import org.eclnt.jsfserver.elements.impl.BEANPROPERTYSETTERComponentTag;
import org.eclnt.jsfserver.elements.impl.BUTTONComponentTag;
import org.eclnt.jsfserver.elements.impl.FORMATTEDFIELDComponentTag;

import trimatrix.utils.Helper;

public class DECSPINNERComponent extends BaseActionComponent {

	transient BaseComponent field;
	transient BaseComponent buttonPlus;
	transient BaseComponent buttonMinus;
	transient BaseComponent setter;
	transient BaseComponent processor;

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		if(getChildren().size() == 0) {
			// pick component's own attributes			
			String objectbinding = getAttributeString("objectbinding");
			String width = getAttributeString("width");
			String enabled = getAttributeString("enabled");
			String rendered = getAttributeString("rendered");
			String align = getAttributeString("align");
			String bgpaint = getAttributeString("bgpaint");
			String userhint = getAttributeString("userhint");
			String tooltip = getAttributeString("tooltip");		
			String step = getAttributeString("step");
			
			if(!Helper.isEmpty(objectbinding)) objectbinding = objectbinding.replace('}', '.');
			// create field component
			FORMATTEDFIELDComponentTag fct = new FORMATTEDFIELDComponentTag();
			fct.setId(createSubId());
			if(!Helper.isEmpty(objectbinding)) fct.setValue(objectbinding + "value}");
			fct.setWidth(width);
			fct.setFormat("double");
			fct.setEnabled(enabled);
			fct.setRendered(rendered);
			fct.setBgpaint(bgpaint);
			fct.setAlign(align);
			fct.setUserhint(userhint);
			fct.setTooltip(tooltip);	
			field = fct.createBaseComponent();
			getChildren().add(field);				
			// create property setter
			BEANPROPERTYSETTERComponentTag bpsct = new BEANPROPERTYSETTERComponentTag();
			bpsct.setId(createSubId());
			bpsct.setProperty(objectbinding + "step}");
			bpsct.setValue(step);
			setter = bpsct.createBaseComponent();
			// create bean processor
			BEANPROCESSINGComponentTag bpct = new BEANPROCESSINGComponentTag();
			bpct.setId(createSubId());
			processor = bpct.createBaseComponent();
			processor.getChildren().add(setter);
			getChildren().add(processor);
			// create max button
			BUTTONComponentTag bct1 = new BUTTONComponentTag();
			bct1.setId(createSubId());
			bct1.setText("+");
			bct1.setEnabled(enabled);
			bct1.setRendered(rendered);
			bct1.setContentareafilled("false");
			bct1.setFont("size:16;weight:bold");
			bct1.setFocusable("false");
			if(!Helper.isEmpty(objectbinding)) bct1.setActionListener(objectbinding + "increment}");
			buttonMinus = bct1.createBaseComponent();
			getChildren().add(buttonMinus);
			// create min button
			BUTTONComponentTag bct2 = new BUTTONComponentTag();
			bct2.setId(createSubId());
			bct2.setText("-");
			bct2.setEnabled(enabled);
			bct2.setRendered(rendered);
			bct2.setContentareafilled("false");
			bct2.setFont("size:16;weight:bold");
			bct2.setFocusable("false");
			if(!Helper.isEmpty(objectbinding)) bct2.setActionListener(objectbinding + "decrement}");
			buttonPlus = bct2.createBaseComponent();
			getChildren().add(buttonPlus);
		}
	}

	@Override
	public void encodeChildren(FacesContext context) throws IOException {
		field.encodeBegin(context);
		field.encodeEnd(context);
		buttonMinus.encodeBegin(context);
		buttonMinus.encodeEnd(context);
		buttonPlus.encodeBegin(context);
		buttonPlus.encodeEnd(context);
		setter.encodeBegin(context);
		setter.encodeEnd(context);
		processor.encodeBegin(context);
		processor.encodeEnd(context);
	}

	@Override
	public void encodeEnd(FacesContext context) throws IOException {}

	@Override
	public boolean getRendersChildren() {
		return true;
	}
}
