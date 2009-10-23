package trimatrix.logic;

import org.springframework.context.ApplicationContext;

public final class LogicLayer {
	private LogonLogic logonLogic;
	private EntityListLogic entityListLogic;
	private FunctionTreeLogic functionTreeLogic;
	private RelationListLogic relationListLogic;
	private LabelLogic labelLogic;
	private ZonesLogic zonesLogic;
	
	public LogonLogic getLogonLogic() {
		return logonLogic;
	}
	public void setLogonLogic(LogonLogic logonLogic) {
		this.logonLogic = logonLogic;
	}
	public EntityListLogic getEntityListLogic() {
		return entityListLogic;
	}
	public void setEntityListLogic(EntityListLogic entityListLogic) {
		this.entityListLogic = entityListLogic;
	}	
	public FunctionTreeLogic getFunctionTreeLogic() {
		return functionTreeLogic;
	}
	public void setFunctionTreeLogic(FunctionTreeLogic functionTreeLogic) {
		this.functionTreeLogic = functionTreeLogic;
	}	
	public RelationListLogic getRelationListLogic() {
		return relationListLogic;
	}
	public void setRelationListLogic(RelationListLogic relationListLogic) {
		this.relationListLogic = relationListLogic;
	}	
	public LabelLogic getLabelLogic() {
		return labelLogic;
	}
	public void setLabelLogic(LabelLogic labelLogic) {
		this.labelLogic = labelLogic;		
	}	
	public ZonesLogic getZonesLogic() {
		return zonesLogic;
	}
	public void setZonesLogic(ZonesLogic zonesLogic) {
		this.zonesLogic = zonesLogic;
	}
	public static LogicLayer getFromApplicationContext(ApplicationContext ctx) {
		return (LogicLayer) ctx.getBean("logicLayer");
	}
}
