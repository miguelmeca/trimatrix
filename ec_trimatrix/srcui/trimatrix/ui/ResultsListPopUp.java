package trimatrix.ui;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Collection;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.bufferedcontent.BufferedContentMgr;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoCancelListener;
import org.eclnt.jsfserver.elements.events.BaseActionEventUpload;
import org.eclnt.jsfserver.elements.impl.ARRAYGRIDItem;
import org.eclnt.jsfserver.elements.impl.ARRAYGRIDListBinding;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.eclnt.workplace.WorkpageDefaultLifecycleListener;

import trimatrix.db.Attachments;
import trimatrix.db.Competitions;
import trimatrix.db.DAOLayer;
import trimatrix.ui.utils.IPopUpCallback;
import trimatrix.ui.utils.MyBufferedContentForAttachment;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;
import eu.medsea.mimeutil.MimeType;
import eu.medsea.mimeutil.MimeUtil;

@CCGenClass (expressionBase="#{d.ResultsListPopUp}")

public class ResultsListPopUp extends MyWorkpageDispatchedBean implements Serializable {
	private Competitions competition;

	private IPopUpCallback callback;
	public void prepareCallback(IPopUpCallback callback, Competitions competition) {
    	this.callback = callback;
    	this.competition = competition;
    	resultList = competition.getResults();
    	init(resultList);
    }

	private MyBufferedContentForAttachment bc;
    public ResultsListPopUp(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		// set close operation - to remove from memory
		getWorkpage().addLifecycleListener(
				new WorkpageDefaultLifecycleListener() {
					public void reactOnDestroyed() {
						super.reactOnDestroyed();
						BufferedContentMgr.remove(bc);
					}
				});
	}

    public void init(Attachments attachments) {
    	if(attachments==null) return;
		// buffered content
		bc = new MyBufferedContentForAttachment(attachments);
		BufferedContentMgr.add(bc);
		downloadURL = bc.getURL();
	}

	protected Attachments resultList;
	public Attachments getResultList() {return resultList;}

	protected String downloadURL;
	public String getDownloadURL() {return downloadURL;}

	protected ARRAYGRIDListBinding<GridPreviewItem> m_gridPreview = new ARRAYGRIDListBinding<GridPreviewItem>();
    public ARRAYGRIDListBinding<GridPreviewItem> getGridPreview() { return m_gridPreview; }
    public void setGridPreview(ARRAYGRIDListBinding<GridPreviewItem> value) { m_gridPreview = value; }

    public class GridPreviewItem extends ARRAYGRIDItem implements java.io.Serializable {
    }


    public void onCreateTemplate(ActionEvent event) {}

    public void onPreview(ActionEvent event) {}

    public void onUpload(ActionEvent event) {
    	if (event instanceof BaseActionEventUpload) {
			BaseActionEventUpload bae = (BaseActionEventUpload) event;
			// check size
			Integer size = bae.getHexBytes().length;
//			if (size > Constants.MB_1) {
//				Statusbar.outputAlert("Filesize " + size
//						+ " is more than max. size " + Constants.MB_1
//						+ " bytes!");
//				return;
//			}
			// create new entity
			resultList = (Attachments)getEntityLayer().getAttachmentEntity().create();
			resultList.setIntern(true); // mark attachment for internal use
			resultList.setCategoryKey("results");	// set category fix to result

			// filename without directory structure
			String filename = bae.getClientFileName();
			resultList.setFileName(filename.substring(filename
					.lastIndexOf(Constants.FILESEPARATOR) + 1));
			resultList.setFileSize(size);
			// Mime type detection
			ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bae
					.getHexBytes());
			Collection<?> mimeTypes = MimeUtil.getMimeTypes(byteInputStream);
			if (mimeTypes.size() > 0) {
				resultList.setMimeType(((MimeType) mimeTypes.toArray()[0])
						.toString());
			} else {
				resultList.setMimeType(Constants.UNKNOWN_MIME_TYPE);
			}
			// Content
			resultList.setFileContent(bae.getHexBytes());
			// initialize
			init(resultList);
		}
    }

    public void onDelete(ActionEvent event) {
    	if(resultList!=null) {
    		YESNOPopup popup = YESNOPopup.createInstance(
    				"Confirm deletion",
    				"Do you really want to delete the result list?",
    				new IYesNoCancelListener(){

    					public void reactOnCancel() {}

    					public void reactOnNo() {}

    					public void reactOnYes() {
    			    		// set to null for callback
    			    		resultList = null;
    					}
    				}
    		);
    		popup.getModalPopup().setLeftTopReferenceCentered();
    	}
    }

    public void onOk(ActionEvent event)  {
    	callback.ok(resultList);
    }

}
