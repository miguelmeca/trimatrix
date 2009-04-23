package trimatrix.db;

import java.util.List;

public interface IUsersDAO {

	public abstract void save(Users transientInstance);

	public abstract void delete(Users persistentInstance);

	public abstract Users findById(java.lang.String id);

	public abstract List<Users> findByExample(Users instance);

	public abstract List<Users> findByProperty(String propertyName, Object value);

	public abstract List<Users> findAll();

	public abstract Users merge(Users detachedInstance);

	public abstract void attachDirty(Users instance);

	public abstract void attachClean(Users instance);

}