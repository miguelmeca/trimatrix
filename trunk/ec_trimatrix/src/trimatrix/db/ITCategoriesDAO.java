package trimatrix.db;

import java.util.List;

public interface ITCategoriesDAO {

	public abstract void save(TCategories transientInstance);

	public abstract void delete(TCategories persistentInstance);

	public abstract TCategories findById(trimatrix.db.TCategoriesId id);

	public abstract List findByExample(TCategories instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract TCategories merge(TCategories detachedInstance);

	public abstract void attachDirty(TCategories instance);

	public abstract void attachClean(TCategories instance);

}