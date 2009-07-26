package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;

@CCGenClass (expressionBase="#{d.LabelChangePopUp}")

public class LabelChangePopUp implements Serializable
{
    public void onCancel(ActionEvent event) {}

    public void onChange(ActionEvent event) {}

    protected String m_description;
    public String getDescription() { return m_description; }
    public void setDescription(String value) { m_description = value; }

    protected String m_color;
    public String getColor() { return m_color; }
    public void setColor(String value) { m_color = value; }
    
    public void setLabel(String label) {
    	setColor("000000");
    	setDescription(label);
    }

}
