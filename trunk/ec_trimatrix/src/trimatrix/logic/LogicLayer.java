package trimatrix.logic;

import org.springframework.context.ApplicationContext;

public final class LogicLayer {
	private LogonLogic logonLogic;
	private EntityListLogic entityListLogic;
	private FunctionTreeLogic functionTreeLogic;
	
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
	
	public static LogicLayer getFromApplicationContext(ApplicationContext ctx) {
		return (LogicLayer) ctx.getBean("logicLayer");
	}
}
