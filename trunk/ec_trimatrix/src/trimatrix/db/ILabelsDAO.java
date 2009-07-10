package trimatrix.db;

import java.util.List;

public interface ILabelsDAO {

	// property constants
	public static final String PERSON_ID = "personId";
	public static final String DESCRIPTION = "description";
	public static final String COLOR = "color";

	public abstract void save(Labels transientInstance);

	public abstract void delete(Labels persistentInstance);

	public abstract Labels findById(java.lang.String id);

	@SuppressWarnings("unchecked")
	public abstract List<Labels> findByExample(Labels instance);

	@SuppressWarnings("unchecked")
	public abstract List<Labels> findByProperty(String propertyName,
			Object value);

	public abstract List<Labels> findByPersonId(Object personId);

	public abstract List<Labels> findByDescription(Object description);

	public abstract List<Labels> findByColor(Object color);

	@SuppressWarnings("unchecked")
	public abstract List<Labels> findAll();

	public abstract Labels merge(Labels detachedInstance);

	public abstract void attachDirty(Labels instance);

	public abstract void attachClean(Labels instance);

}