package trimatrix.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclnt.editor.annotations.CCGenClass;
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
    private List<EntityListUI> entityLists;

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
		// build dynamic lists per entity type
		entityLists = new ArrayList<EntityListUI>();
		for (Constants.Entity entity : entityMap.keySet()) {
			entityLists.add(new EntityListUI(getOwningDispatcher(), entity, entityMap.get(entity)));
		}
		// build dynamic result
		setResultRowDynamic();
	}
    
    public void setResultRowDynamic() { 		
    	StringBuffer xml = new StringBuffer();
 		xml.append("<t:pane>");
		for(EntityListUI entityList : entityLists) {
			xml.append("<t:row>");
			xml.append("<t:foldablepane height='100%' text='Test' width='100%' >");
			xml.append("<t:rowinclude contentreplacedrilldown='EntityListUI:LabelSearchResultUI.attachmentsListUI' page='entitylist.jsp' />");
			xml.append("</t:foldablepane>");
			xml.append("</t:row>");
			xml.append("<t:rowdistance />");
		}
		xml.append("</t:pane>");
		m_result.setContentXml(xml.toString());
	}
    
    public EntityListUI getAttachmentsListUI() {
    	return entityLists.get(0);
    }
}
