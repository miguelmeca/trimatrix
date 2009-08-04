package trimatrix.entities;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;

public interface IEntity {	

	public abstract List<SGridMetaData> getGridMetaData();

	public abstract List<IEntityData> getData(Constants.Entity entity,
			String personId);
	
	public abstract List<IEntityData> getData(List<String> ids);

	public abstract List<IEntityData> getData(Constants.Entity entity);
	
	public abstract IEntityData getData(String id);

	public abstract List<IEntityData> getData();

	public abstract boolean delete(String id);

	public abstract IEntityObject create();

	public abstract IEntityObject get(String id);

	public abstract void save(IEntityObject entityObject)
			throws DataIntegrityViolationException;

	public abstract void reload(IEntityObject entityObject);

}