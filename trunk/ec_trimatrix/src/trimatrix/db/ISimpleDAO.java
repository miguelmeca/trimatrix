package trimatrix.db;

import java.util.List;

public interface ISimpleDAO<E> {

	public abstract void save(E transientInstance);

	public abstract void delete(E persistentInstance);
	
	public abstract E findById(String id);

	public abstract List<E> findByExample(E instance);

	public abstract List<E> findByProperty(String propertyName, Object value);

	public abstract List<E> findAll();

	public abstract E merge(E detachedInstance);

	public abstract void attachDirty(E instance);

	public abstract void attachClean(E instance);

}