package trimatrix.ui;

import static trimatrix.utils.Constants.EMPTY;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.bufferedcontent.BufferedContentMgr;
import org.eclnt.jsfserver.defaultscreens.ISetId;
import org.eclnt.jsfserver.defaultscreens.IdTextSelection;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoCancelListener;
import org.eclnt.jsfserver.elements.events.BaseActionEventUpload;
import org.eclnt.jsfserver.elements.impl.ARRAYGRIDItem;
import org.eclnt.jsfserver.elements.impl.ARRAYGRIDListBinding;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.eclnt.workplace.WorkpageDefaultLifecycleListener;

import trimatrix.db.Attachments;
import trimatrix.db.Competitions;
import trimatrix.db.ImportTemplates;
import trimatrix.logic.ImportLogic;
import trimatrix.logic.helper.Limit;
import trimatrix.ui.utils.IPopUpCallback2;
import trimatrix.ui.utils.MyBufferedContentForAttachment;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;
import trimatrix.utils.Constants.Entity;
import eu.medsea.mimeutil.MimeType;
import eu.medsea.mimeutil.MimeUtil;

@CCGenClass (expressionBase="#{d.ResultsListPopUp}")

public class ResultsListPopUp extends MyWorkpageDispatchedBean implements Serializable {
	private static final Entity ENTITY = Entity.RESULT;

	private Competitions competition;
	private Limit limit;

	private IPopUpCallback2 callback;
	public void prepareCallback(IPopUpCallback2 callback, Competitions competition, Limit limit) {
    	this.callback = callback;
    	this.competition = competition;
    	this.limit = limit;
    	this.resultList = limit.getResultsId()==null?null:getDaoLayer().getAttachmentsDAO().findById(limit.getResultsId());
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
    	if(attachments==null) {
    		// buffered content
    		bc = new MyBufferedContentForAttachment(attachments);
    		BufferedContentMgr.add(bc);
    		downloadURL = bc.getURL();
    	}
		buildTemplatesData();
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

			// create new entity
			resultList = (Attachments)getEntityLayer().getAttachmentEntity().create();
			resultList.setIntern(true); // mark attachment for internal use
			resultList.setCategoryKey("results");	// set category fix to result
			resultList.setDescription(limit.getCategory()); // set limits category to description

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
			// Limit
			limit.setResultsId(resultList.getId());
			// initialize
			init(resultList);
		}
    }

    public void onDelete(ActionEvent event) {
    	if(resultList!=null) {
    		YESNOPopup popup = YESNOPopup.createInstance(
    				Helper.getMessages("delete_data"),
                    Helper.getMessages("confirm_delete"),
    				new IYesNoCancelListener(){

    					public void reactOnCancel() {}

    					public void reactOnNo() {}

    					public void reactOnYes() {
    			    		// set to null for callback
    			    		limit.setResultsTemplate(null);
    			    		limit.setResultsId(null);
    					}
    				}
    		);
    		popup.getModalPopup().setLeftTopReferenceCentered();
    	}
    }

    public void onOk(ActionEvent event)  {
    	// mandatory fields
    	if(Helper.isEmpty(limit.getResultsTemplate()) || resultList == null) {
    		Statusbar.outputAlert(Helper.getMessages("mandatory"), Helper.getLiteral("warn")).setLeftTopReferenceCentered();
    		return;
    	}
    	// get info for limit
		try {
			ImportLogic.ResultListData data = getLogic().getImportLogic().readResultExcel(resultList.getFileContent(), templates.get(limit.getResultsTemplate()));
			if(!Helper.isEmpty(data.bestSwimmer)) {
				limit.getSwim()[0] = data.bestSwimmer;
				limit.getSwim()[1] = data.bestSwimSplit;
			}
			if(!Helper.isEmpty(data.bestBiker)) {
				limit.getBike()[0] = data.bestBiker;
				limit.getBike()[1] = data.bestBikeSplit;
			}
			if(!Helper.isEmpty(data.bestRunner)) {
				limit.getRun()[0] = data.bestRunner;
				limit.getRun()[1] = data.bestRunSplit;
			}
			callback.ok(resultList, limit);
		} catch (Exception ex) {
			Statusbar.outputAlert(ex.toString(), Helper.getLiteral("error")).setLeftTopReferenceCentered();
		}

    }

    public void onCancel(ActionEvent event)  {
    	callback.cancel();
    }

	private Map<String, ImportTemplates> templates;

	public void buildTemplatesData() {
		templates = getLogic().getImportLogic().getTemplatesData(ENTITY);
    }

	public void onTemplateF4(ActionEvent event) {
    	IdTextSelection idts = IdTextSelection.createInstance();
    	idts.addLine(null, EMPTY);	// null value
     	for(ImportTemplates template : templates.values()) {
    		idts.addLine(template.getId().getDescription(), EMPTY);
    	}
     	idts.setCallBack(new ISetId() {
            public void setId(String id) {
                limit.setResultsTemplate(id);
            }
        });
        idts.setWithHeader(false);
        idts.setSuppressHeadline(true);
        idts.setRenderTextColumn(false);
        idts.setPopupWidth(200);
        idts.setPopupHeight(100);
    }

	public String getTemplate() {
		return limit.getResultsTemplate();
	}

}
