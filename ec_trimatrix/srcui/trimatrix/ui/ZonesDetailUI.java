package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;

@CCGenClass (expressionBase="#{d.ZonesDetailUI}")

public class ZonesDetailUI extends MyWorkpageDispatchedBean implements Serializable
{
    public ZonesDetailUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		buildGrid();
	}

	public void onAddZone(ActionEvent event) {
    	m_gridZones.getItems().add(new GridZonesItem());	
    }

	protected FIXGRIDListBinding<GridZonesItem> m_gridZones = new FIXGRIDListBinding<GridZonesItem>();
    public FIXGRIDListBinding<GridZonesItem> getGridZones() { return m_gridZones; }
    public void setGridZones(FIXGRIDListBinding<GridZonesItem> value) { m_gridZones = value; }

    public class GridZonesItem extends FIXGRIDItem implements java.io.Serializable
    {
       
    	public GridZonesItem() { };
    	
    	public GridZonesItem(double high, double low, String m_description,
				String m_token, String m_color) {
			m_lactateHigh = high;
			m_lactateLow = low;
			this.m_description = m_description;
			this.m_token = m_token;
			this.m_color = m_color;
		}

		protected double m_lactateHigh;
        public double getLactateHigh() { return m_lactateHigh; }
        public void setLactateHigh(double value) { m_lactateHigh = value; }

        protected double m_lactateLow;
        public double getLactateLow() { return m_lactateLow; }
        public void setLactateLow(double value) { m_lactateLow = value; }

        protected String m_description;
        public String getDescription() { return m_description; }
        public void setDescription(String value) { m_description = value; }

        protected String m_token;
        public String getToken() { return m_token; }
        public void setToken(String value) { m_token = value; }

        protected String m_color = "#FFFFFF";
        public String getColor() { return m_color; }
        public void setColor(String value) { 
        	m_color = value;
        	//m_color2 = value;
        }
        
        protected String m_color2;
        public String getColor2() { return m_color2; }
        public void setColor2(String value) { m_color2 = value; }
        
        public void onShowColor(ActionEvent event) {
        	Statusbar.outputMessage("Color: " + m_color);
        }
        
        public void onChangeColor(ActionEvent event) {
        	m_color2 = m_color;
        }
    }
    
    private void buildGrid() {
    	
    }

}
