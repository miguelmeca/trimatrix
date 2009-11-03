package trimatrix.relations;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import trimatrix.db.IRelationDAO;
import trimatrix.entities.AEntity;
import trimatrix.services.SQLExecutorService;
import trimatrix.utils.Dictionary;

public abstract class ARelation implements IRelation {
	public static final Log logger = LogFactory.getLog(AEntity.class);
	
	protected SQLExecutorService sqlExecutorService;
	protected Dictionary dictionaryService;
	protected IRelationDAO<? extends IRelationObject> relationsDAO;
	
	public IRelationObject get(String id) {
		return relationsDAO.findById(id);
	}
	
	public boolean delete(String id) {
		try {
			IRelationObject relation = get(id);
			relationsDAO.delete(relation);
		} catch (Exception ex) {
			logger.warn(ex.toString());
			return false;
		}
		return true;			
	}	
	
	public void setSqlExecutorService(SQLExecutorService sqlExecutorService) {
		this.sqlExecutorService = sqlExecutorService;
	}
	
	public void setDictionaryService(Dictionary dictionaryService) {
		this.dictionaryService = dictionaryService;
	}	
	
	public void setRelationsDAO(IRelationDAO<? extends IRelationObject> relationsDAO) {
		this.relationsDAO = relationsDAO;
	}
}
