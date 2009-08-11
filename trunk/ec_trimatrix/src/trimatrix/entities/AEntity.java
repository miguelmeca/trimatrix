package trimatrix.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTransactionManager;

import trimatrix.db.DAOLayer;
import trimatrix.db.IEntityDAO;
import trimatrix.services.SQLExecutorService;
import trimatrix.utils.Dictionary;

public abstract class AEntity implements IEntity{	
	// Variables
	protected SQLExecutorService sqlExecutorService;
	protected Dictionary dictionaryService;
	protected DAOLayer daoLayer;
	protected HibernateTransactionManager transactionManager;
	protected IEntityDAO<? extends IEntityObject> entitiesDAO;
	
	public IEntityData getData(String id) {
		return null;
	}
	
	public void reload(IEntityObject entityObject) {
		entitiesDAO.reload(entityObject);		
	}
	
	public IEntityObject get(String id) {
		IEntityObject entity = entitiesDAO.findById(id);
		if(entity==null) return null;
		if(entity.getDeleted()) {
			Dictionary.logger.warn(this.getClass().getSimpleName() + " marked as deleted");
			return null;
		}
		if(entity.getTest()) {
			Dictionary.logger.warn(this.getClass().getSimpleName() + " marked for test");
			return null;
		}
		return entity;
	}

	public void save(IEntityObject entityObject) {
		// set creation data
		Timestamp now = new java.sql.Timestamp((new java.util.Date()).getTime());
		if(entityObject.getCreatedAt() == null) entityObject.setCreatedAt(now);
		if(entityObject.getCreatedBy() == null) entityObject.setCreatedBy(dictionaryService.getMyUser().getId());
		// modification timestamp now handled by <timestamp> in hbm File
		//entity.setModifiedAt(now); 
		entityObject.setModifiedBy(dictionaryService.getMyUser().getId());
		entitiesDAO.merge(entityObject);		
	}
	
	public boolean delete(String id) {
		IEntityObject entity = entitiesDAO.findById(id);
		if(entity==null) return false;
		entity.setDeleted(true);
		try {
			entitiesDAO.merge(entity);
			// no standard handling for deletion of relationtships see docu
		} catch (Exception ex) {
			return false;
		}
		return true;		
	}
	
	public List<IEntityData> getData(List<String> ids) {
		List<IEntityData> data = new ArrayList<IEntityData>();
		for(String id : ids) {
			IEntityData datum = getData(id);
			data.add(datum);
		}
		return data;
	}
	
	public void setSqlExecutorService(SQLExecutorService sqlExecutorService) {
		this.sqlExecutorService = sqlExecutorService;
	}

	public void setDictionaryService(Dictionary dictionaryService) {
		this.dictionaryService = dictionaryService;
	}
	
	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}

	public void setTransactionManager(HibernateTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	public void setEntitiesDAO(IEntityDAO<? extends IEntityObject> entitiesDAO) {
		this.entitiesDAO = entitiesDAO;
	}	
}
