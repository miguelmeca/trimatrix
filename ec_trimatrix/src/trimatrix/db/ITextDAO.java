package trimatrix.db;

import java.util.List;

public interface ITextDAO<E, K> {
	public abstract void save(E transientInstance);

	public abstract void delete(E persistentInstance);

	public abstract E findById(K id);

	public abstract E findById(String key, String languageKey);

	public abstract List<E> findByExample(E instance);

	public abstract List<E> findByProperty(String propertyName, Object value);

	public abstract List<E> findAll();

	public abstract E merge(E detachedInstance);

	public abstract void attachDirty(E instance);

	public abstract void attachClean(E instance);
}
