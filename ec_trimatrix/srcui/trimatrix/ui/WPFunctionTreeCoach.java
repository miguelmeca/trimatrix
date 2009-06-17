package trimatrix.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoCancelListener;
import org.eclnt.jsfserver.elements.events.BaseActionEventDrop;
import org.eclnt.jsfserver.elements.impl.FIXGRIDTreeItem;
import org.eclnt.jsfserver.managedbean.IDispatcher;
import org.eclnt.workplace.WorkpageContainer;
import org.eclnt.workplace.WorkplaceFunctionTree;

import trimatrix.db.PersonsHaveAttachments;
import trimatrix.entities.IEntityData;
import trimatrix.logic.FunctionTreeLogic;
import trimatrix.relations.PersonAttachmentRelation;
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SFunctionTree;
import trimatrix.utils.Constants;
import trimatrix.utils.Context;
import trimatrix.utils.Dictionary;

@SuppressWarnings("serial")
public class WPFunctionTreeCoach extends WorkplaceFunctionTree {
	private static final Constants.Role role = Constants.Role.COACH;	
	
	/**
	 * @author reich
	 * Extend FunctionNode to get a node with drag & drop functionality
	 */
	public class DropableFunctionNode extends FunctionNode {
		private String entityId;
		
        public DropableFunctionNode(FIXGRIDTreeItem parent, String page, String dropReceive, String entityId) {
            super(parent, page);
            setDropReceive(dropReceive);
            this.entityId = entityId;
        }
        public DropableFunctionNode(FIXGRIDTreeItem page, String dropReceive, String entityId) {
            super(page);
            setDropReceive(dropReceive);
            this.entityId = entityId;
        }
        @Override
        public void processTREENDOEAction(ActionEvent event) {
            super.processTREENDOEAction(event);
            if (event instanceof BaseActionEventDrop) {
                BaseActionEventDrop baed = (BaseActionEventDrop)event;                
                String[] dragInfo = baed.getDragInfo().split(":");
                if(dragInfo==null || dragInfo.length<2) return;
                // get current workpage
                WorkpageContainer wc = (WorkpageContainer)getWorkpageContainer();
                // get dispatched bean of workpage
                if (dragInfo[1].equals(Constants.P_ENTITYLIST)) {
                	final EntityListUI dispatchedBean = (EntityListUI) wc.getCurrentWorkpage().getDispatcher().getDispatchedBean(EntityListUI.class);
                	if (dispatchedBean==null) return;                	
                	// get entity
                	Constants.Entity entity = dispatchedBean.getEntity();
                	if (entity==null) return;
                	// get selected item
                	final IEntityData datum = dispatchedBean.m_gridList.getSelectedItem().datum;    
                	if (datum==null) return;
                	
                	if (entity.getBase()==Constants.Entity.ATTACHMENT) {
                		YESNOPopup.createInstance(
                				"Create relation", 
                				"Do you really want to create a relation between " + getText() + " and " + datum.toString() + "?", 
                				new IYesNoCancelListener(){

                					public void reactOnCancel() {}

                					public void reactOnNo() {}

                					public void reactOnYes() {				
                						PersonAttachmentRelation relation = dispatchedBean.getOwningDispatcher().getRelationLayer().getPersonAttachmentRelation();
                						PersonsHaveAttachments pha = relation.create();
                						pha.setPerson(entityId);
                						pha.setAttachment(datum.getId());
                						pha.setReltypKey(Constants.Relation.ATTACHMENT.type());
                						relation.save(pha);
                					}						
                				}
                		);
                	}
                					
                	
                } else {
                	Dictionary.logger.warn(dragInfo[1] + " is not defined as source for drag and drop!");
                	return;
                } 
            }
        }
    }
	
	public WPFunctionTreeCoach(IDispatcher owner) {
		super(owner);
	}	
	
	@Override
	protected void loadFunctionTree() {
		FunctionTreeLogic FUNCTIONTREELOGIC = ((Dispatcher)getOwningDispatcher()).logicLayer.getFunctionTreeLogic();
		// reset functiontree
		getFtree().getRootNode().removeAllChildNodes(true);
		
		Map<Integer, FunctionNode> functionNodeMap = new HashMap<Integer, FunctionNode>();
		SQLExecutorService sqlExecutorService = SQLExecutorService.getFromApplicationContext(Context.getInstance());
		List<SFunctionTree> functionTreeList = sqlExecutorService.getFunctionTree(role);
		
		for (SFunctionTree functionTree : functionTreeList) {
			FunctionNode node = null;
			FunctionNode parentNode = null;
			Constants.Page page = null;
			
			boolean topNode = false;
			// topnode?
			if(functionTree.page == null && functionTree.page.length() == 0) {
				topNode = true;
			}
			// get parent node
			if(functionTree.parent==0) {
				parentNode = (FunctionNode)getFtree().getRootNode();
			} else {
				parentNode = functionNodeMap.get(functionTree.parent);				
			}
			// top node or real node
			if(!topNode) {
				try {
					page = Constants.Page.valueOf(functionTree.page);						
				} catch (Exception ex) {
					Dictionary.logger.warn(ex.toString());
					continue;
				}	
				node = new FunctionNode(parentNode, page.getUrl());
				node.setId(Constants.EMPTY);
				node.setStatus(FunctionNode.STATUS_ENDNODE);
				node.setOpenMultipleInstances(true);
				if(functionTree.entity != null && functionTree.entity.length() > 0) {
					node.setParam(Constants.P_ENTITY, functionTree.entity);
				}
				// authorization
				FUNCTIONTREELOGIC.setAuthority(functionTree, node);
				// special handling for some nodes
				if (functionTree.key == Constants.FunctionNode.ATHLETES_OWN) {
					// reset status
					node.setStatus(FunctionNode.STATUS_OPENED);
					// add athletes
					List<IEntityData> athletes = FUNCTIONTREELOGIC.getMyAthletes();
					for (IEntityData athlete : athletes) {
						FunctionNode athlete_node = new DropableFunctionNode(node, Constants.Page.ENTITYDETAIL.getUrl(),Constants.P_ENTITY, athlete.getId());	
						athlete_node.setId(athlete.getId());
						athlete_node.setStatus(FunctionNode.STATUS_OPENED);
						athlete_node.setOpenMultipleInstances(true);
						athlete_node.setText(athlete.toString());							
						athlete_node.setParam(Constants.P_ENTITY, Constants.Entity.PERSON.name());
						// authorization as parent
						FUNCTIONTREELOGIC.setAuthority(functionTree, athlete_node);
						// add attachments per athlete
						FunctionNode attachment_node = new FunctionNode(athlete_node, Constants.Page.ENTITYLIST.getUrl());	
						attachment_node.setId(athlete.getId());
						attachment_node.setStatus(FunctionNode.STATUS_ENDNODE);
						attachment_node.setOpenMultipleInstances(true);
						attachment_node.setText("Attachments");	
						attachment_node.setParam(Constants.P_PERSON, athlete.getId());
						attachment_node.setParam(Constants.P_ENTITY, Constants.Entity.ATTACHMENT.name());
						// authorization as parent
						FUNCTIONTREELOGIC.setAuthority(functionTree, attachment_node);
					}								
				}
			} else {
				node = new FunctionNode(parentNode);
			}
			node.setText(functionTree.description);			
			// build map
			functionNodeMap.put(functionTree.node, node);
		}
	}	
}
