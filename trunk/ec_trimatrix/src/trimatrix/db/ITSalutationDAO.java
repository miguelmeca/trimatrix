package trimatrix.db;

import java.util.List;

public interface ITSalutationDAO {

	public abstract void save(TSalutation transientInstance);

	public abstract void delete(TSalutation persistentInstance);

	public abstract TSalutation findById(trimatrix.db.TSalutationId id);

	public abstract TSalutation findById(String key, String languageKey);

	@SuppressWarnings("unchecked")
	public abstract List<TSalutation> findByExample(TSalutation instance);

	@SuppressWarnings("unchecked")
	public abstract List<TSalutation> findByProperty(String propertyName,
			Object value);

	@SuppressWarnings("unchecked")
	public abstract List<TSalutation> findAll();

	public abstract TSalutation merge(TSalutation detachedInstance);

	public abstract void attachDirty(TSalutation instance);

	public abstract void attachClean(TSalutation instance);

}