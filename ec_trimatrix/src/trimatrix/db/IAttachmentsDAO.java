package trimatrix.db;

import java.util.List;

public interface IAttachmentsDAO {

	public abstract void save(Attachments transientInstance);

	public abstract void delete(Attachments persistentInstance);

	public abstract Attachments findById(java.lang.String id);

	public abstract List<Attachments> findByExample(Attachments instance);

	public abstract List<Attachments> findByProperty(String propertyName,
			Object value);

	public abstract List<Attachments> findAll();

	public abstract Attachments merge(Attachments detachedInstance);

	public abstract void attachDirty(Attachments instance);

	public abstract void attachClean(Attachments instance);

	public abstract void reload(Attachments attachments);

}