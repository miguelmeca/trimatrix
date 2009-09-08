package trimatrix.relations;

import trimatrix.db.IRelationDAO;
import trimatrix.services.SQLExecutorService;
import trimatrix.utils.Dictionary;
import trimatrix.utils.Helper;

public abstract class ARelation implements IRelation {
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
			Helper.logger.warn(ex.toString());
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
