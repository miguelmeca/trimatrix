package trimatrix.ui;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.impl.ROWDYNAMICCONTENTBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Labels;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;

@CCGenClass (expressionBase="#{d.LabelSearchResultUI}")

public class LabelSearchResultUI extends MyWorkpageDispatchedBean implements Serializable
{
    protected ROWDYNAMICCONTENTBinding m_result = new ROWDYNAMICCONTENTBinding();
    public ROWDYNAMICCONTENTBinding getResult() { return m_result; }
    public void setResult(ROWDYNAMICCONTENTBinding value) { m_result = value; }
    
    private Labels label;
    private Map<String, EntityListUI> entityLists;

    public LabelSearchResultUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);	
		// get label id
		String labelId = getWorkpage().getParam(Constants.P_LABEL);	
		// get label
		label = getDaoLayer().getLabelsDAO().findById(labelId);
		// set title
		getWorkpage().setTitle(label.getDescription());
		// get entities by label
		Map<Constants.Entity, List<String>> entityMap = getServiceLayer().getSqlExecutorService().getEntitiesByLabelList(labelId);
		// is label assigned?
		if (entityMap.size()==0) {
			Statusbar.outputWarning("Label not assigned to an entity!");
		}
		// build dynamic lists per entity type
		entityLists = new HashMap<String, EntityListUI>();
		for (Constants.Entity entity : entityMap.keySet()) {
			entityLists.put(entity.name(), new EntityListUI(getOwningDispatcher(), entity, entityMap.get(entity)));
		}
		// build dynamic result
		setResultRowDynamic();
	}
    
    public void setResultRowDynamic() { 		
    	StringBuffer xml = new StringBuffer();
 		xml.append("<t:pane height='100%' width='100%' background='" + label.getColor() + "30'>");
		for(String entityName : entityLists.keySet()) {
			xml.append("<t:row>");
			xml.append("<t:foldablepane height='100%' width='100%' text='" + entityName + "'>");
			xml.append("<t:rowinclude contentreplacedrilldown='EntityListUI:LabelSearchResultUI.entityLists." + entityName + "' page='entitylist.jsp' />");
			xml.append("</t:foldablepane>");
			xml.append("</t:row>");
			xml.append("<t:rowdistance />");
		}
		xml.append("</t:pane>");
		m_result.setContentXml(xml.toString());
	}
    
	public Map<String, EntityListUI> getEntityLists() {
		return entityLists;
	}    
}
