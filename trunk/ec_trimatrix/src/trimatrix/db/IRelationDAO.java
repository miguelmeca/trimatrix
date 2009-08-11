package trimatrix.db;

import java.util.List;

import trimatrix.relations.IRelationObject;

public interface IRelationDAO<E extends IRelationObject> {
	
	public abstract void save(E transientInstance);

	public abstract void delete(IRelationObject persistentInstance);

	public abstract E findById(java.lang.String id);

	public abstract List<E> findByExample(IRelationObject instance);

	public abstract List<E> findByProperty(String propertyName,
			Object value);

	public abstract List<E> findAll();

	public abstract E merge(IRelationObject detachedInstance);

	public abstract void attachDirty(E instance);

	public abstract void attachClean(E instance);

	public abstract void reload(IRelationObject instance);
	
	public int deleteByPartners(String partnerId);
}
