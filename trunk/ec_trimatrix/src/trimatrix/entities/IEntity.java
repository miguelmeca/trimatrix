package trimatrix.entities;

import java.util.List;
import java.util.Map;

import trimatrix.reports.Report;
import trimatrix.structures.SGridMetaData;
import trimatrix.structures.SSearchMetaData;
import trimatrix.utils.Constants;
import trimatrix.utils.SearchRange;

public interface IEntity {

	public abstract List<SGridMetaData> getGridMetaData();

	public abstract List<SGridMetaData> getGridMetaData(String filter);

	public abstract Map<String, SSearchMetaData> getSearchMetaData();

	public abstract List<IEntityData> getData(Constants.Entity entity,
			String personId, String filter);

	public abstract List<IEntityData> getData(List<String> ids);

	public abstract List<IEntityData> getData(Constants.Entity entity, String filter);

	public abstract List<IEntityData> getData(SearchRange srange);

	public abstract IEntityData getData(String id);

	public abstract List<IEntityData> getData();

	public abstract boolean delete(String id);

	public abstract IEntityObject create();

	public abstract IEntityObject get(String id);

	public abstract IEntityObject save(IEntityObject entityObject)
			throws Exception;

	public abstract void reload(IEntityObject entityObject);

	public abstract IEntityObject copy(IEntityObject entityObject);

	public abstract boolean isCopyable(IEntityObject entityObject);

	public abstract Report getPrintReport(Constants.Entity entity, String filter, List<IEntityData> data);

}