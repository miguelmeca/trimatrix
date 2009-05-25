package trimatrix.db;

import java.util.List;

public interface IPersonsHaveDoctorsDAO {

	public abstract void save(PersonsHaveDoctors transientInstance);

	public abstract void delete(PersonsHaveDoctors persistentInstance);

	public abstract PersonsHaveDoctors findById(java.lang.String id);

	@SuppressWarnings("unchecked")
	public abstract List<PersonsHaveDoctors> findByExample(
			PersonsHaveDoctors instance);

	@SuppressWarnings("unchecked")
	public abstract List<PersonsHaveDoctors> findByProperty(
			String propertyName, Object value);

	@SuppressWarnings("unchecked")
	public abstract List<PersonsHaveDoctors> findAll();

	public abstract PersonsHaveDoctors merge(PersonsHaveDoctors detachedInstance);

	public abstract void attachDirty(PersonsHaveDoctors instance);

	public abstract void attachClean(PersonsHaveDoctors instance);

	public abstract void reload(PersonsHaveDoctors relation);

}