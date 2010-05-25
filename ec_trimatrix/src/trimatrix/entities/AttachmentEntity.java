package trimatrix.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.resources.ResourceManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import sun.security.action.GetLongAction;
import trimatrix.db.Attachments;
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;
import trimatrix.utils.Constants.Entity;

public class AttachmentEntity extends AEntity {
	// Constants
	public static final String ICON = "icon";
	public static final String DESCRIPTION = "description";
	public static final String CATEGORY = "category";
    public static final String OWNER = "owner";
    public static final String MIMETYPE = "mimetype";
    public static final String FILENAME = "filename";
    public static final String FILESIZE = "filesize";

	public IEntityObject create() {
		String id = UUID.randomUUID().toString();
		Attachments entity = new Attachments();
		entity.setId(id);
		// default values
		entity.setIntern(false);
		entity.setDeleted(false);
		entity.setTest(false);
		entity.setOwner(dictionaryService.getMyUser().getPerson());
		return entity;
	}

	@Override
	public boolean delete(final String id) {
		// all in one transaction
		final TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
		Boolean result = (Boolean)transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				try {
					Attachments entity = (Attachments)entitiesDAO.findById(id);
					if(entity==null) return false;
					// check if user admin or owner
					if (dictionaryService.getMyRoles().contains(Constants.Role.ADMIN.getName())||entity.getOwner().getId().equals(dictionaryService.getMyPerson().getId())) {
						entity.setDeleted(true);
						entitiesDAO.merge(entity);
						int deleted = daoLayer.deleteRelationsByPartner(id);
						Statusbar.outputSuccess(String.format(Helper.getMessages("del_entity_success"), deleted));
					} else {
						Statusbar.outputAlert(Helper.getMessages("del_entity_admin")).setLeftTopReferenceCentered();
						return false;
					}
				} catch (Exception ex) {
					status.setRollbackOnly();
					return false;
				}
				logger.info("AttachmentEntity : Deletion of attachment successful => " + id );
				return true;
			}
		});
		return result;
	}

	@Override
	public IEntityData getData(String id) {
		List<IEntityData> result = sqlExecutorService.getAttachmentEntities(SQLExecutorService.ID, id);
		if (result.size()==0) return null;
		return result.get(0);
	}

	public List<IEntityData> getData(Entity entity, String filter) {
		if (entity == Constants.Entity.ATTACHMENT) {
        	return sqlExecutorService.getAttachmentEntities();
        } else if (entity == Constants.Entity.MYATTACHMENTS) {
        	return sqlExecutorService.getAttachmentRelationEntities(dictionaryService.getMyPerson().getId(), Constants.Relation.ATTACHMENT);
        } else {
        	return Constants.EMPTYENTITYDATA;
        }
	}

	public List<IEntityData> getData(Entity entity, String personId, String filter) {
		if (entity == Constants.Entity.ATTACHMENT) {
        	return sqlExecutorService.getAttachmentRelationEntities(personId, Constants.Relation.ATTACHMENT);
        } else {
        	return Constants.EMPTYENTITYDATA;
        }
	}

	public List<IEntityData> getData() {
		return sqlExecutorService.getDoctorEntities();
	}

	public List<SGridMetaData> getGridMetaData() {
		List<SGridMetaData> gridMetaData = new ArrayList<SGridMetaData>();
		gridMetaData.add(new SGridMetaData(Constants.EMPTY, ICON, "25", SGridMetaData.Component.ICON));
        gridMetaData.add(new SGridMetaData("Beschreibung", DESCRIPTION, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Kategorie", CATEGORY, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Eigentümer", OWNER, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("MIME Typ", MIMETYPE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Dateiname", FILENAME, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Dateigröße", FILESIZE, SGridMetaData.Component.FIELD));
        return gridMetaData;
	}

	public static class Data implements IEntityData {
		public String id;
		public String icon;
		public String description;
		public String category;
		public String owner;
		public String mimetype;
		public String filename;
		public Integer filesize;

		/* (non-Javadoc)
		 * @see trimatrix.entities.IEntityData#getId()
		 */
		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			// same as DB entity implementation
			return description.replace(Constants.NULL, Constants.EMPTY).trim();
		}

		public String getIcon() {
			// get extension from filename
			if (filename == null || filename.length() == 0) return Constants.DEFAULT_ICON;
			String extension = filename.substring(filename.lastIndexOf(Constants.POINT) + 1);
			String icon = Constants.PATH_MIMEICONS + extension + Constants.POINT + Constants.GIF_EXTENSION;
			if (!Helper.isFileInWebRoot(icon)) return Constants.DEFAULT_ICON;
			// all ok return gif of extension
			return icon;
		}

		public String getDescription() {
			return description;
		}

		public String getCategory() {
			return category;
		}

		public String getOwner() {
			return owner;
		}

		public String getMimetype() {
			return mimetype;
		}

		public String getFilename() {
			return filename;
		}

		public Integer getFilesize() {
			return filesize;
		}
	}
}
