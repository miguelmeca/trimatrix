package trimatrix.entities;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;

public interface IEntity {	

	public abstract List<SGridMetaData> getGridMetaData();
	
	public abstract List<SGridMetaData> getGridMetaData(String filter);

	public abstract List<IEntityData> getData(Constants.Entity entity,
			String personId, String filter);
	
	public abstract List<IEntityData> getData(List<String> ids);

	public abstract List<IEntityData> getData(Constants.Entity entity, String filter);
	
	public abstract IEntityData getData(String id);

	public abstract List<IEntityData> getData();

	public abstract boolean delete(String id);

	public abstract IEntityObject create();

	public abstract IEntityObject get(String id);

	public abstract IEntityObject save(IEntityObject entityObject)
			throws DataIntegrityViolationException;

	public abstract void reload(IEntityObject entityObject);
	
	public abstract IEntityObject copy(IEntityObject entityObject);
	
	public abstract boolean isCopyable(IEntityObject entityObject);

}