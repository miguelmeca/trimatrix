package trimatrix.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import trimatrix.db.Attachments;
import trimatrix.db.TCategories;
import trimatrix.db.TCategoriesId;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;
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
						Statusbar.outputSuccess(String.format("Successfully deleted entity incl. %s relations!", deleted));
					} else {
						Statusbar.outputAlert("Do delete this object you have to be admin or owner of this object!");
						return false;
					}
				} catch (Exception ex) {
					status.setRollbackOnly();
					return false;
				}				
				Dictionary.logger.info("AttachmentEntity : Deletion of attachment successful => " + id );
				return true;
			}			
		});		
		return result;		
	}	
	
	public IEntityData getData(String id) {
		Data datum = new Data();
		Attachments entity = (Attachments)get(id);
		if(entity!=null) {
			datum.id = entity.getId();	
			String categoryKey = entity.getCategoryKey();
			String language = dictionaryService.getLanguage();
			TCategories category = daoLayer.getTcategoriesDAO().findById(new TCategoriesId(categoryKey, language));
			if(category!=null) {
				datum.category = category.getDescription();
			} else {
				Dictionary.logger.warn("Category " + categoryKey + " not found!");
			}
			datum.description = entity.getDescription();
			datum.owner = entity.getOwner().toString();
			datum.mimetype = entity.getMimeType();
			datum.filename = entity.getFileName();
			datum.filesize = entity.getFileSize();
		}
		return datum;
	}	

	public List<IEntityData> getData(Entity entity) {
		if (entity == Constants.Entity.ATTACHMENT) {
        	return sqlExecutorService.getAttachmentEntities();
        } else if (entity == Constants.Entity.MYATTACHMENTS) {
        	return sqlExecutorService.getAttachmentRelationEntities(dictionaryService.getMyPerson().getId(), Constants.Relation.ATTACHMENT);
        } else {
        	return Constants.EMPTYENTITYLIST;
        }
	}
	
	public List<IEntityData> getData(Entity entity, String personId) {
		if (entity == Constants.Entity.ATTACHMENT) {
        	return sqlExecutorService.getAttachmentRelationEntities(personId, Constants.Relation.ATTACHMENT);
        } else {
        	return Constants.EMPTYENTITYLIST;
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
			if (!Dictionary.isFileInWebRoot(icon)) return Constants.DEFAULT_ICON;
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
