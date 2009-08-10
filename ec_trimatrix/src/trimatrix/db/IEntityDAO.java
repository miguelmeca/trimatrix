package trimatrix.db;

import java.util.List;

import trimatrix.entities.IEntityObject;

public interface IEntityDAO<E extends IEntityObject> {

	public abstract void save(E transientInstance);

	public abstract void delete(E persistentInstance);

	public abstract E findById(java.lang.String id);

	public abstract List<E> findByExample(E instance);

	public abstract List<E> findByProperty(String propertyName,
			Object value);

	public abstract List<E> findAll();

	public abstract E merge(IEntityObject detachedInstance);

	public abstract void attachDirty(E instance);

	public abstract void attachClean(E instance);

	public abstract void reload(IEntityObject instance);

}