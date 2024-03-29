package trimatrix.logic;

import org.springframework.context.ApplicationContext;

public final class LogicLayer {
	private LogonLogic logonLogic;
	private EntityListLogic entityListLogic;
	private FunctionTreeLogic functionTreeLogic;
	private RelationListLogic relationListLogic;
	private LabelLogic labelLogic;
	private ZonesLogic zonesLogic;
	private TestLogic testLogic;
	private CompetitionLogic competitionLogic;
	private ScheduleLogic scheduleLogic;
	private PreferencesLogic preferencesLogic;
	private ImportLogic importLogic;

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
	public TestLogic getTestLogic() {
		return testLogic;
	}
	public void setTestLogic(TestLogic testLogic) {
		this.testLogic = testLogic;
	}
	public CompetitionLogic getCompetitionLogic() {
		return competitionLogic;
	}
	public void setCompetitionLogic(CompetitionLogic competitionLogic) {
		this.competitionLogic = competitionLogic;
	}
	public ScheduleLogic getScheduleLogic() {
		return scheduleLogic;
	}
	public void setScheduleLogic(ScheduleLogic scheduleLogic) {
		this.scheduleLogic = scheduleLogic;
	}
	public PreferencesLogic getPreferencesLogic() {
		return preferencesLogic;
	}
	public void setPreferencesLogic(PreferencesLogic preferencesLogic) {
		this.preferencesLogic = preferencesLogic;
	}
	public ImportLogic getImportLogic() {
		return importLogic;
	}
	public void setImportLogic(ImportLogic importLogic) {
		this.importLogic = importLogic;
	}
	public static LogicLayer getFromApplicationContext(ApplicationContext ctx) {
		return (LogicLayer) ctx.getBean("logicLayer");
	}
}
