package trimatrix.entities;

import java.util.List;

import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;

public interface IEntity {

	public abstract List<SGridMetaData> getGridMetaData();
	
	public abstract List<IEntityData> getData(Constants.Entity entity);
	
	public abstract List<IEntityData> getData();
		
	public abstract boolean delete(String id);	
	
	public abstract IEntityObject create();
	
	public abstract IEntityObject get(String id);
	
	public abstract void save(IEntityObject entityObject);
	
	public abstract void reload(IEntityObject entityObject);

}