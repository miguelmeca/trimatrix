package trimatrix.db;

import java.util.List;

public interface IPersonsHaveRelationsDAO {

	// property constants
	public static final String PARTNER1 = "partner1";
	public static final String PARTNER2 = "partner2";
	public static final String RELTYP_KEY = "reltypKey";
	public static final String DEFAULT_ = "default_";

	public abstract void save(PersonsHaveRelations transientInstance);

	public abstract void delete(PersonsHaveRelations persistentInstance);

	public abstract PersonsHaveRelations findById(java.lang.String id);

	@SuppressWarnings("unchecked")
	public abstract List<PersonsHaveRelations> findByExample(
			PersonsHaveRelations instance);

	@SuppressWarnings("unchecked")
	public abstract List<PersonsHaveRelations> findByProperty(
			String propertyName, Object value);

	public abstract List<PersonsHaveRelations> findByPartner1(Object partner1);

	public abstract List<PersonsHaveRelations> findByPartner2(Object partner2);

	public abstract List<PersonsHaveRelations> findByReltypKey(Object reltypKey);

	public abstract List<PersonsHaveRelations> findByDefault_(Object default_);

	@SuppressWarnings("unchecked")
	public abstract List<PersonsHaveRelations> findAll();

	public abstract PersonsHaveRelations merge(
			PersonsHaveRelations detachedInstance);

	public abstract void attachDirty(PersonsHaveRelations instance);

	public abstract void attachClean(PersonsHaveRelations instance);

}