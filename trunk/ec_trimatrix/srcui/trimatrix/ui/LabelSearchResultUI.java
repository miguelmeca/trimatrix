package trimatrix.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;

@CCGenClass (expressionBase="#{d.LabelSearchResultUI}")

public class LabelSearchResultUI extends MyWorkpageDispatchedBean implements Serializable
{
    protected boolean m_renderAttachments = true;
    public boolean getRenderAttachments() { return m_renderAttachments; }
    public void setRenderAttachments(boolean value) { m_renderAttachments = value; }

    public LabelSearchResultUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);	
		String id = "b8a954e4-4bca-11de-ab35-74df036e1e4f";
		List<String> ids = new ArrayList<String>();
		ids.add(id);
		m_AttachmentsListUI.buildData(ids);

	}
    
	protected EntityListUI m_AttachmentsListUI = new EntityListUI(getOwningDispatcher(), Constants.Entity.ATTACHMENT);	
    public EntityListUI getAttachmentsListUI() { return m_AttachmentsListUI; }
    public void setAttachmentsListUI(EntityListUI value) { m_AttachmentsListUI = value; }

}
