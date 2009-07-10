package trimatrix.ui;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.events.BaseActionEventFlush;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Labels;
import trimatrix.logic.LabelLogic;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;

@CCGenClass (expressionBase="#{d.LabelPopUpUI}")

public class LabelPopUpUI extends MyWorkpageDispatchedBean implements Serializable
{    
	protected final LabelLogic LABELLOGIC = getLogic().getLabelLogic();
	protected List<Labels> allLabels;
	
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

    public void initialize() {  
    	// reset search text
    	m_searchText = Constants.EMPTY;
    	// don't show create button
    	m_renderCreate = false;
    	// get all labels
    	allLabels = LABELLOGIC.getAllLabels();    
    	// build grid for first display
    	m_grid.getItems().clear();    			
    	for(Labels label:allLabels) {
    		GridItem item = new GridItem(label);
            m_grid.getItems().add(item);
    	}
    }
    
    public class GridItem extends FIXGRIDItem implements java.io.Serializable
    {
    	private boolean select;
    	private Labels label;
    	
    	public GridItem(Labels label) {
    		super();
    		this.select = false;
    		this.label = label;
    	}
    	
		public GridItem(boolean select, Labels label) {
			super();
			this.select = select;
			this.label = label;
		}
		public boolean isSelect() {
			return select;
		}
		public void setSelect(boolean select) {
			this.select = select;
		}
		
		public String getDescription() {
			return label.getDescription();
		}
		
		public String getColor() {
			return label.getColor();
		}
		
		public Labels getLabel() {
			return label;
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
    			// check search text
    			boolean searchText = (m_searchText!=null && m_searchText.trim().length() > 0);
    			m_grid.getItems().clear();    			
    	    	for(Labels label:allLabels) {
    	    		if(searchText) {
    	    			if(!label.getDescription().toUpperCase().contains(m_searchText.toUpperCase())) continue;
    	    		}
    	    		GridItem item = new GridItem(label);
    	            m_grid.getItems().add(item);
    	    	}

                if(m_grid.getItems().size() > 0) m_grid.selectItem(0);
                if(searchText && 
                   (m_grid.getItems().size()==0 || !m_grid.getSelectedItem().getDescription().equalsIgnoreCase(m_searchText))) {
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
    	callback.apply(m_grid.getSelectedItem().getLabel());
    }
    
    public void onCreate(ActionEvent event) {
    	Statusbar.outputMessage("Create value(s)!");
    	// TODO Move to logic layer
    	Labels label = new Labels();
    	label.setColor("#FF0000"); // create with default Color
        label.setDescription(m_searchText);
        label.setPersonId("0b0b7658-2ddb-11de-86ae-00301bb60f17");
        label.setId("123456");
    	callback.apply(label);
    }
    
    public interface IApplyingCallback {
    	public void apply(Labels label);
    }
    
    protected IApplyingCallback callback;
    public void prepareCallback(IApplyingCallback callback) {
    	this.callback = callback;
    }
}
