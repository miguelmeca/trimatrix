package trimatrix.relations;

import java.util.List;

import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;

public interface IRelation {
public abstract List<SGridMetaData> getGridMetaData();

	public abstract boolean delete(String id);	
	
	public abstract IRelationObject get(String id);
	
	public abstract void save(IRelationObject relationObject);
	
    public abstract IRelationObject create();	
	
	public abstract void reload(IRelationObject relationObject);
	
	public abstract List<IRelationData> getData(Constants.Relation relation);
	
	public abstract List<IRelationData> getData();
}
