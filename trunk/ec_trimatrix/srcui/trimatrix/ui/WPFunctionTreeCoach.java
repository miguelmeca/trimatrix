package trimatrix.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoCancelListener;
import org.eclnt.jsfserver.elements.events.BaseActionEventDrop;
import org.eclnt.jsfserver.elements.impl.FIXGRIDTreeItem;
import org.eclnt.jsfserver.managedbean.IDispatcher;
import org.eclnt.workplace.WorkpageContainer;
import org.eclnt.workplace.WorkplaceFunctionTree;
import org.springframework.dao.DataIntegrityViolationException;

import trimatrix.db.PersonsHaveAttachments;
import trimatrix.db.PersonsHaveDoctors;
import trimatrix.entities.IEntityData;
import trimatrix.logic.FunctionTreeLogic;
import trimatrix.relations.PersonAttachmentRelation;
import trimatrix.relations.PersonDoctorRelation;
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SFunctionTree;
import trimatrix.utils.Constants;
import trimatrix.utils.Context;
import trimatrix.utils.Helper;

@SuppressWarnings("serial")
public class WPFunctionTreeCoach extends WorkplaceFunctionTree {
	public static final Log logger = LogFactory.getLog(WPFunctionTreeCoach.class);
	private static final Constants.Role role = Constants.Role.COACH;	
	private FunctionTreeLogic FUNCTIONTREELOGIC = null;
	
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
            if(FUNCTIONTREELOGIC==null){
    			FUNCTIONTREELOGIC = ((Dispatcher)getOwningDispatcher()).logicLayer.getFunctionTreeLogic();
    		}
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
                		YESNOPopup ynp = YESNOPopup.createInstance(
                				"Create relation", 
                				"Do you really want to create a relation between " + getText() + " and " + datum.toString() + "?", 
                				new IYesNoCancelListener(){

                					public void reactOnCancel() {}

                					public void reactOnNo() {}

                					public void reactOnYes() {				
                						FUNCTIONTREELOGIC.createPersonAttachmentRelation(entityId, datum.getId());
                					}						
                				}
                		);
                		ynp.getModalPopup().setLeftTopReferenceCentered();
                	} else if (entity.getBase()==Constants.Entity.DOCTOR) {
                		YESNOPopup ynp = YESNOPopup.createInstance(
                				"Create relation", 
                				"Do you really want to create a relation between " + getText() + " and " + datum.toString() + "?", 
                				new IYesNoCancelListener(){

                					public void reactOnCancel() {}

                					public void reactOnNo() {}

                					public void reactOnYes() {				
                						FUNCTIONTREELOGIC.createPersonDoctorRelation(entityId, datum.getId());                						
                					}						
                				}
                		);
                		ynp.getModalPopup().setLeftTopReferenceCentered();
                	}  					
                	
                } else {
                	logger.warn(dragInfo[1] + " is not defined as source for drag and drop!");
                	return;
                } 
            }
        }
    }
	
	public WPFunctionTreeCoach(IDispatcher owner) {
		super(owner);
	}	
	
	public void reload() {
		loadFunctionTree();
	}
	
	@Override
	protected void loadFunctionTree() {
		if(FUNCTIONTREELOGIC==null){
			FUNCTIONTREELOGIC = ((Dispatcher)getOwningDispatcher()).logicLayer.getFunctionTreeLogic();
		}
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
			if(functionTree.page == null || functionTree.page.length() == 0) {
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
					logger.warn(ex.toString());
					continue;
				}	
				node = new FunctionNode(parentNode, page.getUrl());
				node.setId(Constants.EMPTY);
				node.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);
				node.setOpenMultipleInstances(true);
				if(functionTree.entity != null && functionTree.entity.length() > 0) {
					node.setParam(Constants.P_ENTITY, functionTree.entity);
				}
				// authorization
				FUNCTIONTREELOGIC.setAuthority(functionTree, node);
				// special handling for some nodes
				if (functionTree.key == Constants.FunctionNode.ATHLETES_OWN) {
					// reset status
					node.setStatus(FIXGRIDTreeItem.STATUS_OPENED);
					// add athletes
					List<IEntityData> athletes = FUNCTIONTREELOGIC.getMyAthletes();
					for (IEntityData athlete : athletes) {
						FunctionNode athlete_node = new DropableFunctionNode(node, Constants.Page.ENTITYDETAIL.getUrl(),Constants.P_ENTITY, athlete.getId());	
						athlete_node.setId(athlete.getId());
						athlete_node.setStatus(FIXGRIDTreeItem.STATUS_OPENED);
						athlete_node.setOpenMultipleInstances(true);
						athlete_node.setText(athlete.toString());							
						athlete_node.setParam(Constants.P_ENTITY, Constants.Entity.MYATHLETES.name());
						// authorization as parent
						FUNCTIONTREELOGIC.setAuthority(functionTree, athlete_node);
						// add doctors per athlete
						FunctionNode doctor_node = new FunctionNode(athlete_node, Constants.Page.ENTITYLIST.getUrl());	
						doctor_node.setId(athlete.getId());
						doctor_node.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);
						doctor_node.setOpenMultipleInstances(true);
						doctor_node.setText("Doctors");	
						doctor_node.setParam(Constants.P_PERSON, athlete.getId());
						doctor_node.setParam(Constants.P_ENTITY, Constants.Entity.DOCTOR.name());
						// authorization as parent
						FUNCTIONTREELOGIC.setAuthority(functionTree, doctor_node);
						// add attachments per athlete
						FunctionNode attachment_node = new FunctionNode(athlete_node, Constants.Page.ENTITYLIST.getUrl());	
						attachment_node.setId(athlete.getId());
						attachment_node.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);
						attachment_node.setOpenMultipleInstances(true);
						attachment_node.setText("Attachments");	
						attachment_node.setParam(Constants.P_PERSON, athlete.getId());
						attachment_node.setParam(Constants.P_ENTITY, Constants.Entity.ATTACHMENT.name());
						// authorization as parent
						FUNCTIONTREELOGIC.setAuthority(functionTree, attachment_node);
						// add tests per athlete
						FunctionNode test_node = new FunctionNode(athlete_node, Constants.Page.ENTITYLIST.getUrl());	
						test_node.setId(athlete.getId());
						test_node.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);
						test_node.setOpenMultipleInstances(true);
						test_node.setText("Tests");	
						test_node.setParam(Constants.P_PERSON, athlete.getId());
						test_node.setParam(Constants.P_ENTITY, Constants.Entity.TEST.name());
						// authorization as parent
						FUNCTIONTREELOGIC.setAuthority(functionTree, test_node);
						// add zones per athlete
						FunctionNode zones_node = new FunctionNode(athlete_node, Constants.Page.ZONESDETAIL.getUrl());	
						zones_node.setId(athlete.getId());
						zones_node.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);
						zones_node.setOpenMultipleInstances(true);						
						zones_node.setText(Helper.getLiteral("exercise_zones"));	
						zones_node.setParam(Constants.P_PERSON, athlete.getId());
						// authorization => coach is allowed to manually change values
						FUNCTIONTREELOGIC.setAuthority(zones_node, false, true, false);						
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
