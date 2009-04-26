package trimatrix.db;

import java.util.List;

public interface IPersonsDAO {

	public abstract void save(Persons transientInstance);

	public abstract void delete(Persons persistentInstance);

	public abstract Persons findById(java.lang.String id);

	public abstract List<Persons> findByExample(Persons instance);

	public abstract List<Persons> findByProperty(String propertyName, Object value);

	public abstract List<Persons> findAll();

	public abstract Persons merge(Persons detachedInstance);

	public abstract void attachDirty(Persons instance);

	public abstract void attachClean(Persons instance);
	
	public abstract void reload(Persons instance);

}