package trimatrix.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTransactionManager;

import trimatrix.db.DAOLayer;
import trimatrix.services.SQLExecutorService;
import trimatrix.utils.Dictionary;

public abstract class AEntity implements IEntity{	
	// Variables
	protected SQLExecutorService sqlExecutorService;
	protected Dictionary dictionaryService;
	protected DAOLayer daoLayer;
	protected HibernateTransactionManager transactionManager;
	
	public IEntityData getData(String id) {
		return null;
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
}
