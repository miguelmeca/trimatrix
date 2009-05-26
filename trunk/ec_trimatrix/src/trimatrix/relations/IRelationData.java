package trimatrix.relations;

import trimatrix.entities.IEntityObject;

public interface IRelationData {
	public abstract String getId();
	public abstract IEntityObject getPartner1();
	public abstract IEntityObject getPartner2();
	public abstract Boolean getStandard();
	public abstract String getReltyp();
	public abstract String getDescription();
}
