package trimatrix.logic;

import org.springframework.context.ApplicationContext;

public final class LogicLayer {
	private LogonLogic logonLogic;
	private EntityListLogic entityListLogic;
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
	
	public static LogicLayer getFromApplicationContext(ApplicationContext ctx) {
		return (LogicLayer) ctx.getBean("logicLayer");
	}
}
