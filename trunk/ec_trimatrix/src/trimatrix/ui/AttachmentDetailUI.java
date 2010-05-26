package trimatrix.ui;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Collection;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.bufferedcontent.BufferedContentMgr;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.events.BaseActionEventUpload;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.util.valuemgmt.ValueManager;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.eclnt.workplace.WorkpageDefaultLifecycleListener;

import trimatrix.db.Attachments;
import trimatrix.entities.AttachmentEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.ui.utils.MyBufferedContentForAttachment;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;
import eu.medsea.mimeutil.MimeType;
import eu.medsea.mimeutil.MimeUtil;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.AttachmentDetailUI}")
public class AttachmentDetailUI extends AEntityDetailUI implements Serializable {
	protected ValidValuesBinding categoriesVvb = getServiceLayer()
			.getValueListBindingService().getVVBinding(
					Constants.ValueList.CATEGORY);

	public ValidValuesBinding getCategoriesVvb() {
		return categoriesVvb;
	}

	protected String downloadURL;

	public String getDownloadURL() {
		return downloadURL;
	}

	private Attachments entity;

	private MyBufferedContentForAttachment bc;

	public AttachmentDetailUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher, new String[] { AttachmentEntity.DESCRIPTION,
				AttachmentEntity.CATEGORY, AttachmentEntity.FILENAME }, true);
		// get wrapping entity detail UI bean
		entityDetailUI = getEntityDetailUI();
		entityDetailUI.setEntityDetailUI(this);
		// set close operation - to remove from memory
		getWorkpage().addLifecycleListener(
				new WorkpageDefaultLifecycleListener() {
					public void reactOnDestroyed() {
						super.reactOnDestroyed();
						BufferedContentMgr.remove(bc);
					}
				});
		// init data
		init(entityDetailUI.getEntityObject());
		// labeling
		setLabelRowDynamic();
	}

	public void init(Object entityObject) {
		// set entity object
		entity = (Attachments) entityObject;
		// buffered content
		bc = new MyBufferedContentForAttachment(entity);
		BufferedContentMgr.add(bc);
		downloadURL = bc.getURL();
		// set enabled state and set fields
		init();
	}

	public void init() {
		// set fields
		fillMaps();
		// set state
		setState();
	}

	public void validate() throws MandatoryCheckException,
			EmailNotValidException {
		// mandatory check
		checkMandatory();
		// fill values to entities properties
		fillEntityProperties();
	}

	private void fillEntityProperties() {
		entity.setCategoryKey((String) values.get(AttachmentEntity.CATEGORY));
		entity
				.setDescription((String) values
						.get(AttachmentEntity.DESCRIPTION));
	}

	private void fillMaps() {
		// add values of fields
		values.clear();
		values.put(AttachmentEntity.CATEGORY, entity.getCategoryKey());
		values.put(AttachmentEntity.DESCRIPTION, entity.getDescription());
		values.put(AttachmentEntity.OWNER, entity.getOwner().toString());
		values.put(AttachmentEntity.MIMETYPE, entity.getMimeType());
		values.put(AttachmentEntity.FILENAME, entity.getFileName());
		values.put(AttachmentEntity.FILESIZE, entity.getFileSize());

		// add bgpaint of fields
		bgpaint.clear();
		// mandatory fields
		for (String field : MANDATORY_FIELDS) {
			bgpaint.put(field, Constants.BGP_MANDATORY);
		}
	}

	public String getFileContent() {
		try {
			return ValueManager.encodeHexString(entity.getFileContent());
		} catch (Exception ex) {
			return Constants.EMPTY;
		}
	}

	public void onUploadFile(ActionEvent event) {
		if (event instanceof BaseActionEventUpload) {
			BaseActionEventUpload bae = (BaseActionEventUpload) event;
			// check size
			Integer size = bae.getHexBytes().length;
			if (size > Constants.MB_1) {
				Statusbar.outputAlert(String.format(Helper.getMessages("file_size"), size, Constants.MB_1), Helper.getLiteral("error")).setLeftTopReferenceCentered();
				return;
			}
			// filename without directory structure
			String filename = bae.getClientFileName();
			entity.setFileName(filename.substring(filename
					.lastIndexOf(Constants.FILESEPARATOR) + 1));
			entity.setFileSize(size);
			// Mime type detection
			ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bae
					.getHexBytes());
			Collection<?> mimeTypes = MimeUtil.getMimeTypes(byteInputStream);
			if (mimeTypes.size() > 0) {
				entity.setMimeType(((MimeType) mimeTypes.toArray()[0])
						.toString());
			} else {
				entity.setMimeType(Constants.UNKNOWN_MIME_TYPE);
			}
			// Content
			entity.setFileContent(bae.getHexBytes());
			// update relevant values
			values.put(AttachmentEntity.MIMETYPE, entity.getMimeType());
			values.put(AttachmentEntity.FILENAME, entity.getFileName());
			values.put(AttachmentEntity.FILESIZE, entity.getFileSize());
		}
	}
}