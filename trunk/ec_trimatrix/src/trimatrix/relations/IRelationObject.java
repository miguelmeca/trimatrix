package trimatrix.relations;

public interface IRelationObject {
	public String getId();
	public String getPartner1();
	public Boolean getStandard();
	public String getReltypKey();
	public void setPartner1(String id);
	public void setPartner2(String id);	 
    public void setReltypKey(String reltypKey);
    public void setStandard(Boolean standard);
}
