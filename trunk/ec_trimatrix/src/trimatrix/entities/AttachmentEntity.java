package trimatrix.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import trimatrix.db.Attachments;
import trimatrix.db.Doctors;
import trimatrix.db.IAttachmentsDAO;
import trimatrix.db.IDoctorsDAO;
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;
import trimatrix.utils.Constants.Entity;

public class AttachmentEntity implements IEntity {
	// Constants	 
	public static final String DESCRIPTION = "description";	
	public static final String CATEGORY = "category";
    public static final String OWNER = "owner";  
    public static final String MIMETYPE = "mimetype"; 
    public static final String FILENAME = "filename"; 
    public static final String FILESIZE = "filesize"; 
    
	// Variables
	private SQLExecutorService sqlExecutorService;
	private Dictionary dictionaryService;
	private IAttachmentsDAO entitiesDAO;
	private HibernateTransactionManager transactionManager;

	public IEntityObject create() {
		String id = UUID.randomUUID().toString();
		Attachments entity = new Attachments();
		entity.setId(id);
		// default values
		entity.setDeleted(false);
		entity.setTest(false);		
		return entity;
	}

	public boolean delete(final String id) {
		// all in one transaction		
		final TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
		Boolean result = (Boolean)transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				try {
					Attachments entity = entitiesDAO.findById(id);
					if(entity==null) return false;
					entity.setDeleted(true);
					entitiesDAO.merge(entity);
					// TODO delete relations
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

	public IEntityObject get(String id) {
		Attachments entity = entitiesDAO.findById(id);
		if(entity==null) return null;
		if(entity.getDeleted()) {
			Dictionary.logger.warn("Attachment marked as deleted");
			return null;
		}
		if(entity.getTest()) {
			Dictionary.logger.warn("Attachment marked for test");
			return null;
		}
		return entity;
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

	public List<IEntityData> getData() {
		return sqlExecutorService.getDoctorEntities();
	}

	public List<SGridMetaData> getGridMetaData() {
		List<SGridMetaData> gridMetaData = new ArrayList<SGridMetaData>();
        gridMetaData.add(new SGridMetaData("Beschreibung", DESCRIPTION, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Kategorie", CATEGORY, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Eigentümer", OWNER, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("MIME Typ", MIMETYPE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Dateiname", FILENAME, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Dateigröße", FILESIZE, SGridMetaData.Component.FIELD));
        return gridMetaData;
	}

	public void reload(IEntityObject entityObject) {
		Attachments entity = (Attachments)entityObject;
		entitiesDAO.reload(entity);		
	}

	public void save(IEntityObject entityObject) {
		Attachments entity = (Attachments)entityObject;
		// set creation data
		Timestamp now = new java.sql.Timestamp((new java.util.Date()).getTime());
		if(entity.getCreatedAt() == null) entity.setCreatedAt(now);
		if(entity.getCreatedBy() == null) entity.setCreatedBy(dictionaryService.getMyUser().getId());
		entity.setModifiedAt(now);
		entity.setModifiedBy(dictionaryService.getMyUser().getId());
		entitiesDAO.merge(entity);		
	}
	
	public static class Data implements IEntityData {
		public String id;
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

	public void setSqlExecutorService(SQLExecutorService sqlExecutorService) {
		this.sqlExecutorService = sqlExecutorService;
	}

	public void setDictionaryService(Dictionary dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public void setEntitiesDAO(IAttachmentsDAO entitiesDAO) {
		this.entitiesDAO = entitiesDAO;
	}

	public void setTransactionManager(HibernateTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
}
