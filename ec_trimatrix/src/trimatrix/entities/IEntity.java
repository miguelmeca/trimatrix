package trimatrix.entities;

import java.util.List;

import trimatrix.structures.SGridMetaData;

public interface IEntity {

	public abstract List<SGridMetaData> getGridMetaData();
	
	public abstract List<IEntityData> getData();
	
	public abstract boolean delete(String id);	
	
	public abstract IEntityObject create();
	
	public abstract IEntityObject get(String id);
	
	public abstract void save(IEntityObject entityObject);
	
	public abstract void reload(IEntityObject entityObject);

}