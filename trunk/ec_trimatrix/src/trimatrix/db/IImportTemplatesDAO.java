package trimatrix.db;

import java.util.List;

public interface IImportTemplatesDAO {

	public abstract void save(ImportTemplates transientInstance);

	public abstract void delete(ImportTemplates persistentInstance);

	public abstract ImportTemplates findById(trimatrix.db.ImportTemplatesId id);

	public abstract List<ImportTemplates> findByExample(ImportTemplates instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract ImportTemplates merge(ImportTemplates detachedInstance);

	public abstract void attachDirty(ImportTemplates instance);

	public abstract void attachClean(ImportTemplates instance);

}