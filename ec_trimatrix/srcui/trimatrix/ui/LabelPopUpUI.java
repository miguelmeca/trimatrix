package trimatrix.ui;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
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
	private String searchText_old;
	
	public LabelPopUpUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);		
	}
	
	protected String entityID;	
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
    	searchText_old = Constants.EMPTY;
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
    	private Labels label;
    	
    	public GridItem(Labels label) {
    		super();
    		this.label = label;
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
			super.onRowExecute();
			apply(label);
		} 
    }
    
    public void onSearch(ActionEvent event) {
    	// flush by timer or enter?
    	if (event instanceof BaseActionEventFlush) {
    		BaseActionEventFlush flushEvent = (BaseActionEventFlush)event;    		
    		// check search text, just do work when sthg changed
    		if(!searchText_old.equalsIgnoreCase(m_searchText)) {
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
                if(searchText && (m_grid.getItems().size()==0 || !m_grid.getSelectedItem().getDescription().equalsIgnoreCase(m_searchText))) {
                  	m_renderCreate = true;
                   	m_txtCreate = "Create " + m_searchText;
                }
    		}
    		// if enter is pressed
            if(!flushEvent.getFlushWasTriggeredByTimer()) {
    			GridItem item = m_grid.getSelectedItem();
    			if(item==null) return;
    			apply(item.getLabel());
    		}    		
    	}        
    }
    
    private void apply(Labels label) {
    	if(LABELLOGIC.createLabelRelation(entityID, label.getId())) callback.apply();
    }
    
    public void onCreate(ActionEvent event) {
    	Labels label = LABELLOGIC.createLabel(m_searchText, Constants.WHITE);
    	apply(label);
    }
    
    public interface IApplyingCallback {
    	public void apply();
    }
    
    protected IApplyingCallback callback;
    public void prepareCallback(IApplyingCallback callback) {
    	this.callback = callback;
    }
}
