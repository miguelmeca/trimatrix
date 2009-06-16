package trimatrix.db;

import java.util.List;

public interface IListVariantsDAO {

	public abstract void save(ListVariants transientInstance);

	public abstract void delete(ListVariants persistentInstance);

	public abstract ListVariants findById(trimatrix.db.ListVariantsId id);

	public abstract List<ListVariants> findByExample(ListVariants instance);

	public abstract List<ListVariants> findByProperty(String propertyName, Object value);

	public abstract List<ListVariants> findAll();

	public abstract ListVariants merge(ListVariants detachedInstance);

	public abstract void attachDirty(ListVariants instance);

	public abstract void attachClean(ListVariants instance);

}