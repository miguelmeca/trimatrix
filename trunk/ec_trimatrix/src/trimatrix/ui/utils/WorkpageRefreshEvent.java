package trimatrix.ui.utils;

import org.eclnt.workplace.WorkpageProcessingEvent;

import trimatrix.utils.Constants.Entity;

public class WorkpageRefreshEvent extends WorkpageProcessingEvent {
	private Entity entity;
	public Entity getEntity() { return entity; }
	
	public WorkpageRefreshEvent(Entity entity) {
		this.entity = entity;
	}	
}
