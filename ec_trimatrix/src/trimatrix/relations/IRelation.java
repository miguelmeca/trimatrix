package trimatrix.relations;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;

public interface IRelation {
	public abstract List<SGridMetaData> getGridMetaData();

	public abstract boolean delete(String id);
	
	public abstract boolean delete(String partner1, String partner2);

	public abstract IRelationObject get(String id);

	public abstract void save(IRelationObject relationObject)
			throws DataIntegrityViolationException;

	public abstract IRelationObject create();

	public abstract void reload(IRelationObject relationObject);

	public abstract List<IRelationData> getData(Constants.Relation relation);
	
	public abstract List<IRelationData> getData();
}
