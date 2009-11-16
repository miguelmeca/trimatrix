package trimatrix.db;

import java.util.List;
import java.util.Map;

import trimatrix.relations.IRelationObject;

public interface IRelationDAO<E extends IRelationObject> {
	
	public abstract void save(E transientInstance);

	public abstract void delete(IRelationObject persistentInstance);

	public abstract E findById(java.lang.String id);

	public abstract List<E> findByExample(IRelationObject instance);

	public abstract List<E> findByProperty(String propertyName,
			Object value);

	public List<E> findByProperties(Map<String, Object> properties);
	
	public abstract List<E> findAll();

	public abstract E merge(IRelationObject detachedInstance);

	public abstract void attachDirty(E instance);

	public abstract void attachClean(E instance);

	public abstract void reload(IRelationObject instance);
	
	public int deleteByPartners(String partnerId);
}
