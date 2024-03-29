package trimatrix.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.springframework.orm.hibernate3.HibernateTransactionManager;

import trimatrix.db.DAOLayer;
import trimatrix.db.IEntityDAO;
import trimatrix.reports.Report;
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SGridMetaData;
import trimatrix.structures.SSearchMetaData;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;
import trimatrix.utils.Helper;
import trimatrix.utils.SearchRange;

public abstract class AEntity implements IEntity{
	public static final Log logger = LogFactory.getLog(AEntity.class);

	// Variables
	protected SQLExecutorService sqlExecutorService;

	protected Dictionary dictionaryService;
	protected DAOLayer daoLayer;
	protected HibernateTransactionManager transactionManager;
	protected IEntityDAO<? extends IEntityObject> entitiesDAO;

	protected Class<? extends IEntityObject> claz;

	public AEntity(Class<? extends IEntityObject> claz) {
		this.claz = claz;
	}

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
			logger.warn(this.getClass().getSimpleName() + " marked as deleted");
			return null;
		}
		if(entity.getTest()) {
			logger.warn(this.getClass().getSimpleName() + " marked for test");
			return null;
		}
		return entity;
	}

	public IEntityObject save(IEntityObject entityObject) throws Exception {
		// set creation data
		Timestamp now = new java.sql.Timestamp((new java.util.Date()).getTime());
		if(entityObject.getCreatedAt() == null) entityObject.setCreatedAt(now);
		if(entityObject.getCreatedBy() == null) entityObject.setCreatedBy(dictionaryService.getMyUser().getId());
		// modification timestamp now handled by <timestamp> in hbm File
		//entity.setModifiedAt(now);
		//IEntityObject entityObject2 = get(entityObject.getId());
		//if(entityObject2!=null) entityObject.setModifiedAt(entityObject2.getModifiedAt());
		entityObject.setModifiedBy(dictionaryService.getMyUser().getId());
		return entitiesDAO.merge(entityObject);

	}

	public boolean delete(String id) {
		IEntityObject entity = entitiesDAO.findById(id);
		if(entity==null) return false;
		entity.setDeleted(true);
		try {
			entitiesDAO.merge(entity);
			// no standard handling for deletion of relationtships see docu
		} catch (Exception ex) {
			logger.error("Deletion failed : " + ex.toString());
			Statusbar.outputAlert(Helper.getLiteral("delete"), Helper.getLiteral("error"), ex.toString()).setLeftTopReferenceCentered();
			return false;
		}
		return true;
	}

	public List<IEntityData> getData(SearchRange sr, String filter) {
		return getData(sqlExecutorService.getEntityIds(sr, claz));
	}

	public List<IEntityData> getData(List<String> ids) {
		List<IEntityData> data = new ArrayList<IEntityData>();
		for(String id : ids) {
			IEntityData datum = getData(id);
			data.add(datum);
		}
		return data;
	}

	public List<SGridMetaData> getGridMetaData(String filter) {
		// if not implemented delegate to general function
		return getGridMetaData();
	}

	public Map<String, SSearchMetaData> getSearchMetaData() {
		return Collections.EMPTY_MAP;
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

	public IEntityObject copy(IEntityObject entityObject) {
		return null;
	}

	public boolean isCopyable(IEntityObject entityObject) {
		return false;
	}

	public Report getPrintReport(Constants.Entity entity, String filter, List<IEntityData> data) {
		return null;
	}
}
