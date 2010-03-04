package trimatrix.controls;

import java.io.IOException;

import javax.faces.context.FacesContext;

import org.eclnt.jsfserver.elements.BaseActionComponent;
import org.eclnt.jsfserver.elements.BaseComponent;
import org.eclnt.jsfserver.elements.impl.BEANPROCESSINGComponentTag;
import org.eclnt.jsfserver.elements.impl.BEANPROPERTYSETTERComponentTag;
import org.eclnt.jsfserver.elements.impl.BUTTONComponentTag;

import trimatrix.utils.Helper;

public class STARComponent extends BaseActionComponent {

	transient BaseComponent button1;
	transient BaseComponent button2;
	
	transient BaseComponent setterImageOn;
	transient BaseComponent setterImageOff;
	transient BaseComponent processor;

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		if(getChildren().size() == 0) {
			// pick component's own attributes			
			String objectbinding = getAttributeString("objectbinding");
			String enabled = getAttributeString("enabled");
			String rendered = getAttributeString("rendered");
			String imageon = getAttributeString("imageon");		
			String imageoff = getAttributeString("imageoff");
			String imagewidth = getAttributeString("imagewidth");
			
			if(!Helper.isEmpty(objectbinding)) objectbinding = objectbinding.replace('}', '.');				
			// create property setter imageon
			BEANPROPERTYSETTERComponentTag bpsct = new BEANPROPERTYSETTERComponentTag();
			bpsct.setId(createSubId());
			bpsct.setProperty(objectbinding + "imageOn}");
			bpsct.setValue(imageon);
			setterImageOn = bpsct.createBaseComponent();
			// create property setter imageoff
			BEANPROPERTYSETTERComponentTag bpsct2 = new BEANPROPERTYSETTERComponentTag();
			bpsct2.setId(createSubId());
			bpsct2.setProperty(objectbinding + "imageOff}");
			bpsct2.setValue(imageoff);
			setterImageOff = bpsct2.createBaseComponent();
			// create bean processor
			BEANPROCESSINGComponentTag bpct = new BEANPROCESSINGComponentTag();
			bpct.setId(createSubId());
			processor = bpct.createBaseComponent();
			processor.getChildren().add(setterImageOn);
			processor.getChildren().add(setterImageOff);
			getChildren().add(processor);
			// create button1
			BUTTONComponentTag bct1 = new BUTTONComponentTag();
			bct1.setId(createSubId());
			bct1.setEnabled(enabled);
			bct1.setRendered(rendered);
			bct1.setContentareafilled("false");
			bct1.setFocusable("false");
			bct1.setImage(objectbinding + "star1}");
			bct1.setWidth(imagewidth);
			bct1.setImagewidth(imagewidth);
			if(!Helper.isEmpty(objectbinding)) bct1.setActionListener(objectbinding + "onStar1}");
			button1 = bct1.createBaseComponent();
			getChildren().add(button1);
			// create button1
			BUTTONComponentTag bct2 = new BUTTONComponentTag();
			bct1.setId(createSubId());
			bct1.setEnabled(enabled);
			bct1.setRendered(rendered);
			bct1.setContentareafilled("false");
			bct1.setFocusable("false");
			bct1.setImage(objectbinding + "star2}");
			bct1.setWidth(imagewidth);
			bct1.setImagewidth(imagewidth);
			if(!Helper.isEmpty(objectbinding)) bct1.setActionListener(objectbinding + "onStar2}");
			button2 = bct2.createBaseComponent();
			getChildren().add(button1);

		}
	}

	@Override
	public void encodeChildren(FacesContext context) throws IOException {
		button1.encodeBegin(context);
		button1.encodeEnd(context);
		button2.encodeBegin(context);
		button2.encodeEnd(context);
		setterImageOn.encodeBegin(context);
		setterImageOn.encodeEnd(context);
		setterImageOff.encodeBegin(context);
		setterImageOff.encodeEnd(context);
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
