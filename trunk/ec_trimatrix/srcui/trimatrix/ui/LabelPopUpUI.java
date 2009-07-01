package trimatrix.ui;

import java.io.Serializable;
import java.util.Random;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.ModelessPopup;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.events.BaseActionEventFlush;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.ui.TestUI.Label;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;

@CCGenClass (expressionBase="#{d.LabelPopUpUI}")

public class LabelPopUpUI extends MyWorkpageDispatchedBean implements Serializable
{    
    public LabelPopUpUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
	}

	private Constants.Entity entity;
    public Constants.Entity getEntity() { return entity; }
	public void setEntity(Constants.Entity entity) { this.entity = entity; }
	
	private String entityID;	
	public String getEntityID() { return entityID; }
	public void setEntityID(String entityID) { this.entityID = entityID; }

	protected String m_searchText;
    public String getSearchText() { return m_searchText; }
    public void setSearchText(String value) { m_searchText = value; }  

    protected FIXGRIDListBinding<GridItem> m_grid = new FIXGRIDListBinding<GridItem>();
    public FIXGRIDListBinding<GridItem> getGrid() { return m_grid; }
    public void setGrid(FIXGRIDListBinding<GridItem> value) { m_grid = value; }
    
    protected boolean m_renderCreate = false;
    public boolean getRenderCreate() { return m_renderCreate; }
    public void setRenderCreate(boolean value) { m_renderCreate = value; } 
    
    protected String m_txtCreate;
    public String getTxtCreate() { return m_txtCreate; }
    public void setTxtCreate(String value) { m_txtCreate = value; }  

    public class GridItem extends FIXGRIDItem implements java.io.Serializable
    {
    	private boolean select;
    	private String text;
    	
    	public GridItem(String text) {
    		super();
    		this.select = false;
			this.text = text;
    	}
    	
		public GridItem(boolean select, String text) {
			super();
			this.select = select;
			this.text = text;
		}
		public boolean isSelect() {
			ModelessPopup popup;
			return select;
		}
		public void setSelect(boolean select) {
			this.select = select;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}

		@Override
		public void onRowExecute() {
			// TODO Auto-generated method stub
			super.onRowExecute();
		} 
    }
    
    public void onSearch(ActionEvent event) {
    	// flush by timer or enter?
    	if (event instanceof BaseActionEventFlush) {
    		BaseActionEventFlush flushEvent = (BaseActionEventFlush)event;
    		if(flushEvent.getFlushWasTriggeredByTimer()) {
    			m_grid.getItems().clear();
                int maxi = (new Random()).nextInt(20) + 2;
                for (int i=0; i<maxi; i++)
                {
                    GridItem gi = new GridItem(m_searchText + " " + i);
                    m_grid.getItems().add(gi);
                }
                m_grid.selectItem(0);
                if(m_searchText!=null && m_searchText.length() > 0 && 
                   (m_grid.getItems().size()==0 || !m_grid.getSelectedItem().text.equalsIgnoreCase(m_searchText))) {
                	m_renderCreate = true;
                	m_txtCreate = "Create " + m_searchText;
                }
    		} else {
    			onApply(null);
    		}    		
    	}        
    }
    
    public void onApply(ActionEvent event) {
    	Statusbar.outputMessage("Apply value(s)!");
    	Label label = new Label("#FF0000", m_grid.getSelectedItem().text);
    	callback.apply(label);
    }
    
    public void onCreate(ActionEvent event) {
    	Statusbar.outputMessage("Create value(s)!");
    	Label label = new Label("#FF0000", m_searchText);
    	callback.apply(label);
    }
    
    public interface IApplyingCallback {
    	public void apply(Label label);
    }
    
    protected IApplyingCallback callback;
    public void prepareCallback(IApplyingCallback callback) {
    	this.callback = callback;
    }
}
