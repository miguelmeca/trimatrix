package trimatrix.db;

import java.util.List;

public interface IViewDAO<E, K> {
	public abstract E findById(K id);

	public abstract List<E> findByExample(E instance);

	public abstract List<E> findByProperty(String propertyName, Object value);

	public abstract List<E> findAll();
}