package trimatrix.db;

import java.util.List;

public interface IDoctorsDAO {

	//property constants
	public static final String NAME = "name";
	public static final String STREET = "street";
	public static final String HOUSENUMBER = "housenumber";
	public static final String POSTCODE = "postcode";
	public static final String CITY = "city";
	public static final String STATE = "state";
	public static final String COUNTRY_KEY = "countryKey";
	public static final String EMAIL = "email";
	public static final String HOMEPAGE = "homepage";
	public static final String TELEPHONE = "telephone";
	public static final String MOBILE = "mobile";
	public static final String FAX = "fax";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";
	public static final String DELETED = "deleted";
	public static final String TEST = "test";

	public abstract void save(Doctors transientInstance);

	public abstract void delete(Doctors persistentInstance);

	public abstract Doctors findById(java.lang.String id);

	@SuppressWarnings("unchecked")
	public abstract List<Doctors> findByExample(Doctors instance);

	@SuppressWarnings("unchecked")
	public abstract List<Doctors> findByProperty(String propertyName,
			Object value);

	@SuppressWarnings("unchecked")
	public abstract List<Doctors> findAll();

	public abstract Doctors merge(Doctors detachedInstance);

	public abstract void attachDirty(Doctors instance);

	public abstract void attachClean(Doctors instance);

	public abstract void reload(Doctors doctor);

}