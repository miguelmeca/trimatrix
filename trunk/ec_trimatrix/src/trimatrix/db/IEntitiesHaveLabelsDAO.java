package trimatrix.db;

import java.util.List;

public interface IEntitiesHaveLabelsDAO {

	public abstract void save(EntitiesHaveLabels transientInstance);

	public abstract void delete(EntitiesHaveLabels persistentInstance);

	public abstract EntitiesHaveLabels findById(
			trimatrix.db.EntitiesHaveLabelsId id);

	public abstract List<EntitiesHaveLabels> findByExample(
			EntitiesHaveLabels instance);

	public abstract List<EntitiesHaveLabels> findByProperty(
			String propertyName, Object value);
	
	public abstract List<EntitiesHaveLabels> findByEntity(String entity);
	
	public abstract List<EntitiesHaveLabels> findByPerson(String person);
	
	public abstract List<EntitiesHaveLabels> findByLabel(String label);

	public abstract List<EntitiesHaveLabels> findAll();

	public abstract EntitiesHaveLabels merge(EntitiesHaveLabels detachedInstance);

	public abstract void attachDirty(EntitiesHaveLabels instance);

	public abstract void attachClean(EntitiesHaveLabels instance);

}