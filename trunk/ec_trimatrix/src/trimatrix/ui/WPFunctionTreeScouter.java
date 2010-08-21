package trimatrix.ui;

import java.util.HashMap;
import java.util.Iterator;
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
import org.eclnt.jsfserver.elements.util.ValidValuesBinding.ValidValue;
import org.eclnt.jsfserver.managedbean.IDispatcher;
import org.eclnt.workplace.IWorkpage;
import org.eclnt.workplace.IWorkpageContainer;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.eclnt.workplace.WorkpageContainer;
import org.eclnt.workplace.WorkplaceFunctionTree;

import trimatrix.db.Results;
import trimatrix.entities.IEntityData;
import trimatrix.logic.FunctionTreeLogic;
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SFunctionTree;
import trimatrix.ui.utils.MyWorkpage;
import trimatrix.utils.Constants;
import trimatrix.utils.Context;
import trimatrix.utils.Helper;
import trimatrix.utils.Constants.Entity;

@SuppressWarnings("serial")
public class WPFunctionTreeScouter extends WorkplaceFunctionTree {
	public static final Log logger = LogFactory.getLog(WPFunctionTreeScouter.class);
	private static final Constants.Role role = Constants.Role.SCOUTER;
	private FunctionTreeLogic FUNCTIONTREELOGIC = null;
	private String pageId = null;

	/*
	 * @author reich Extend FunctionNode to get a node with drag & drop
	 * functionality
	 */
	public class DropableFunctionNode extends FunctionNode {
		private String entityName = null;
		private String entityId = null;

		public DropableFunctionNode(FIXGRIDTreeItem parent, String page, String dropReceive, String entityValue) {
			super(parent, page);
			setDropReceive(dropReceive);
			if (entityValue != null) {
				String[] entityValueArray = entityValue.split(":");
				entityName = entityValueArray[0];
				if (entityValueArray.length > 1)
					entityId = entityValueArray[1];
			}
		}

		public DropableFunctionNode(FIXGRIDTreeItem page, String dropReceive, String entityValue) {
			super(page);
			setDropReceive(dropReceive);
			if (entityValue != null) {
				String[] entityValueArray = entityValue.split(":");
				entityName = entityValueArray[0];
				if (entityValueArray.length > 1)
					entityId = entityValueArray[1];
			}
		}

		@Override
		public void processTREENDOEAction(ActionEvent event) {
			super.processTREENDOEAction(event);
			if (FUNCTIONTREELOGIC == null) {
				FUNCTIONTREELOGIC = ((Dispatcher) getOwningDispatcher()).logicLayer.getFunctionTreeLogic();
			}
			if (event instanceof BaseActionEventDrop) {
				BaseActionEventDrop baed = (BaseActionEventDrop) event;
				String[] dragInfo = baed.getDragInfo().split(":");
				if (dragInfo == null || dragInfo.length < 2)
					return;
				// get current workpage
				WorkpageContainer wc = (WorkpageContainer) getWorkpageContainer();
				// get dispatched bean of workpage
				if (dragInfo[1].equals(Constants.P_ENTITYLIST)) {
					final EntityListUI dispatchedBean = (EntityListUI) wc.getCurrentWorkpage().getDispatcher().getDispatchedBean(EntityListUI.class);
					if (dispatchedBean == null)
						return;
					// get entity
					final Constants.Entity entity = dispatchedBean.getEntity();
					if (entity == null)
						return;
					// get selected item
					if(dispatchedBean.m_gridList.getSelectedItem()==null) return;
					final IEntityData datum = dispatchedBean.m_gridList.getSelectedItem().datum;
					if (datum == null) return;
					// Put general competition into scouted competitions
					if (Entity.SCOUTCOMPETITIONS.name().equalsIgnoreCase(entityName) && entity == Constants.Entity.COMPETITION) {
						/*
						 * Create CompetitionScout entity and open the page so
						 * the Scouter could add certain relevant data
						 */
						YESNOPopup ynp = YESNOPopup.createInstance(Helper.getMessages("create_relation"), String.format(Helper.getMessages("confirm_competitions_overtake"), datum.toString()),
								new IYesNoCancelListener() {
									public void reactOnCancel() {}

									public void reactOnNo() {}

									public void reactOnYes() {
										if (!FUNCTIONTREELOGIC.createCompetitionScout(datum.getId()))
											return;
										// open entity
										IWorkpageDispatcher wpd = (IWorkpageDispatcher) getOwningDispatcher();
										IWorkpageContainer wpc = getWorkpageContainer();
										IWorkpage wp = new MyWorkpage(wpd, Constants.Page.ENTITYDETAIL.getUrl(), datum.getId(), datum.toString(), null, true, null, null, null);
										wp.setParam(Constants.P_ENTITY, Entity.SCOUTCOMPETITIONS.name());
										wp.setParam(Constants.P_MODE, Constants.Mode.SINGLECHANGE.name());
										FUNCTIONTREELOGIC.setAuthority(wp, true, true, true);
										wpc.addWorkpage(wp);
									}
								});
						ynp.getModalPopup().setLeftTopReferenceCentered();
					} else if (Entity.MYSCOUTEDATHLETES.name().equalsIgnoreCase(entityName) && entity == Constants.Entity.SCOUTCOMPETITIONS && entityId != null) {
						YESNOPopup ynp = YESNOPopup.createInstance(Helper.getMessages("create_result"), Helper.getMessages("confirm_result_create"), new IYesNoCancelListener() {

							public void reactOnCancel() {}

							public void reactOnNo() {}

							public void reactOnYes() {
								Results result = null;
								try {
									// check if relation already exists
									result = FUNCTIONTREELOGIC.checkResultRelation(entityId, datum.getId());
									if(result==null) {
										result = FUNCTIONTREELOGIC.createResultRelation(entityId, datum.getId());
									} else {
										Statusbar.outputAlert(Helper.getMessages("result_exist"), Helper.getLiteral("info")).setLeftTopReferenceCentered();
									}
								} catch (Exception ex) {
									Statusbar.outputAlert(Helper.getMessages("result_create_failure"), Helper.getLiteral("error"), ex.toString()).setLeftTopReferenceCentered();
								}
								if (result == null)
									return;
								// open entity
								IWorkpageDispatcher wpd = (IWorkpageDispatcher) getOwningDispatcher();
								IWorkpageContainer wpc = getWorkpageContainer();
								IWorkpage wp = new MyWorkpage(wpd, Constants.Page.ENTITYDETAIL.getUrl(), result.getId(), result.toString(), null, true, null, null, null);
								wp.setParam(Constants.P_ENTITY, Entity.RESULT.name());
								wp.setParam(Constants.P_MODE, Constants.Mode.SINGLECHANGE.name());
								FUNCTIONTREELOGIC.setAuthority(wp, true, true, true);
								wpc.addWorkpage(wp);
							}
						});
						ynp.getModalPopup().setLeftTopReferenceCentered();
					} else if (Entity.MYSCOUTEDATHLETES.name().equalsIgnoreCase(entityName) && entity == Constants.Entity.COMPETITION && entityId != null) {
						YESNOPopup ynp = YESNOPopup.createInstance(Helper.getMessages("create_relation_result"), Helper.getMessages("confirm_relation_result_create"), new IYesNoCancelListener() {

							public void reactOnCancel() {}

							public void reactOnNo() {}

							public void reactOnYes() {
								Results result = null;
								try {
									// put competition in your workspace
									FUNCTIONTREELOGIC.createCompetitionScout(datum.getId());
									//check if relation already exists
									result = FUNCTIONTREELOGIC.checkResultRelation(entityId, datum.getId());
									if(result==null) {
										result = FUNCTIONTREELOGIC.createResultRelation(entityId, datum.getId());
									} else {
										Statusbar.outputAlert(Helper.getMessages("result_exist"), Helper.getLiteral("warn")).setLeftTopReferenceCentered();
									}
								} catch (Exception ex) {
									Statusbar.outputAlert(Helper.getMessages("result_create_failure"), Helper.getLiteral("error"), ex.toString()).setLeftTopReferenceCentered();
								}
								if (result == null)
									return;
								// open entity
								IWorkpageDispatcher wpd = (IWorkpageDispatcher) getOwningDispatcher();
								IWorkpageContainer wpc = getWorkpageContainer();
								IWorkpage wp = new MyWorkpage(wpd, Constants.Page.ENTITYDETAIL.getUrl(), result.getId(), result.toString(), null, true, null, null, null);
								wp.setParam(Constants.P_ENTITY, Entity.RESULT.name());
								wp.setParam(Constants.P_MODE, Constants.Mode.SINGLECHANGE.name());
								wpc.addWorkpage(wp);
							}
						});
						ynp.getModalPopup().setLeftTopReferenceCentered();
					}
				} else {
					logger.warn(dragInfo[1] + " is not defined as source for drag and drop!");
					return;
				}
			}
		}
	}

	public WPFunctionTreeScouter(IDispatcher owner) {
		super(owner);
	}

	public void reload() {
		loadFunctionTree();
	}

	@Override
	protected void loadFunctionTree() {
		if (FUNCTIONTREELOGIC == null) {
			FUNCTIONTREELOGIC = ((Dispatcher) getOwningDispatcher()).logicLayer.getFunctionTreeLogic();
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
			if(Helper.isEmpty(functionTree.page)) topNode = true;
			// get parent node
			if (functionTree.parent == 0) {
				parentNode = (FunctionNode) getFtree().getRootNode();
			} else {
				parentNode = functionNodeMap.get(functionTree.parent);
			}
			// top node or real node
			if (!topNode) {
				try {
					page = Constants.Page.valueOf(functionTree.page);
				} catch (Exception ex) {
					logger.warn(ex.toString());
					continue;
				}
				node = new DropableFunctionNode(parentNode, page.getUrl(), Constants.P_ENTITY, functionTree.entity);
				node.setId(functionTree.entity);
				node.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);
				node.setOpenMultipleInstances(false);
				if (!Helper.isEmpty(functionTree.entity)) {
					node.setParam(Constants.P_ENTITY, functionTree.entity);
				}
				// authorization
				FUNCTIONTREELOGIC.setAuthority(functionTree, node);
				// special handling for some nodes
				if (functionTree.key == Constants.FunctionNode.SCOUTED_OWN) {
					// reset status
					node.setStatus(FIXGRIDTreeItem.STATUS_CLOSED);
					// add athletes
					List<IEntityData> athletes = FUNCTIONTREELOGIC.getMyScoutedAthletes();
					for (IEntityData athlete : athletes) {
						FunctionNode athlete_node = new DropableFunctionNode(node, Constants.Page.ENTITYDETAIL.getUrl(), Constants.P_ENTITY, functionTree.entity + ":" + athlete.getId());
						athlete_node.setId(athlete.getId());
						athlete_node.setStatus(FIXGRIDTreeItem.STATUS_CLOSED);
						athlete_node.setOpenMultipleInstances(false);
						athlete_node.setText(athlete.toString());
						athlete_node.setParam(Constants.P_ENTITY, Constants.Entity.MYSCOUTEDATHLETES.name());
						// authorization as parent
						FUNCTIONTREELOGIC.setAuthority(functionTree, athlete_node);
						// add results per athlete
						FunctionNode results_node = new FunctionNode(athlete_node, Constants.Page.ENTITYLIST.getUrl());
						pageId = Constants.Entity.MYRESULTS.name() + ":" + athlete.getId();
						results_node.setId(pageId);
						results_node.setStatus(FIXGRIDTreeItem.STATUS_CLOSED);
						results_node.setOpenMultipleInstances(false);
						results_node.setText(Helper.getLiteral("results"));
						if(athlete!=null) results_node.setWindowTitle(Helper.getLiteral("results") + Constants.WHITESPACE + athlete);
						results_node.setParam(Constants.P_PERSON, athlete.getId());
						results_node.setParam(Constants.P_ENTITY, Constants.Entity.MYRESULTS.name());
						// authorization => coach is allowed to manually change values
						FUNCTIONTREELOGIC.setAuthority(results_node, true, true, true);
						// node per type of result e.g. triathlon
						buildResultsTypeNodes(functionTree, results_node, athlete.getId(), athlete.toString());
					}
				} else if(functionTree.key == Constants.FunctionNode.RESULTS_SCOUT) {
					// reset status
					node.setStatus(FIXGRIDTreeItem.STATUS_CLOSED);
					// node per type of result e.g. triathlon
					buildResultsTypeNodes(functionTree, node, null, null);
				}

			} else {
				node = new FunctionNode(parentNode);
			}
			node.setText(functionTree.description);
			// build map
			functionNodeMap.put(functionTree.node, node);
		}
	}

	/**
	 * Build node per type of result e.g. triathlon
	 * @param results_node
	 * @param athleteId
	 */
	public void buildResultsTypeNodes(SFunctionTree functionTree, FunctionNode results_node, String athleteId, String athleteName) {
		Iterator<ValidValue> iterator = FUNCTIONTREELOGIC.getCompetitionTypes();
		while(iterator.hasNext()) {
			ValidValue value = iterator.next();
			Constants.Entity entity = athleteId==null ? Constants.Entity.SCOUTRESULTS : Constants.Entity.MYRESULTS;
			String pageId = athleteId==null ? entity.name() + ":" + value.getValue() : entity.name() + ":" + value.getValue() + ":" + athleteId;
			FunctionNode results_type_node = new FunctionNode(results_node, Constants.Page.ENTITYLIST.getUrl());
			results_type_node.setId(pageId);
			results_type_node.setStatus(FIXGRIDTreeItem.STATUS_CLOSED);
			results_type_node.setOpenMultipleInstances(false);
			results_type_node.setText(value.getText());
			if(!Helper.isEmpty(athleteName))  results_type_node.setWindowTitle(value.getText() + Constants.WHITESPACE + athleteName);
			if(athleteId!=null) results_type_node.setParam(Constants.P_PERSON, athleteId);
			results_type_node.setParam(Constants.P_FILTER, value.getValue());
			results_type_node.setParam(Constants.P_ENTITY, entity.name());
			// authorization => coach is allowed to manually change values
			FUNCTIONTREELOGIC.setAuthority(functionTree, results_type_node);
			// node per subtype of result e.g. triathlon-sprint
			buildResultsSubtypeNodes(functionTree, results_type_node, athleteId, athleteName, value.getValue());
		}
	}

	/**
	 * Build node per subtype of result e.g. triathlon-sprint
	 * @param results_node
	 */
	public void buildResultsSubtypeNodes(SFunctionTree functionTree, FunctionNode results_type_node, String athleteId, String athleteName, String type) {
		Iterator<ValidValue> iterator = FUNCTIONTREELOGIC.getCompetitionSubtypes(type);
		while(iterator.hasNext()) {
			ValidValue value = iterator.next();
			Constants.Entity entity = athleteId==null ? Constants.Entity.SCOUTRESULTS : Constants.Entity.MYRESULTS;
			String pageId = athleteId==null ? entity.name() + ":" + value.getValue() : entity.name() + ":" + type + ":" + value.getValue() + ":" + athleteId;
			FunctionNode results_subtype_node = new FunctionNode(results_type_node, Constants.Page.ENTITYLIST.getUrl());
			results_subtype_node.setId(pageId);
			results_subtype_node.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);
			results_subtype_node.setOpenMultipleInstances(false);
			results_subtype_node.setText(value.getText());
			if(!Helper.isEmpty(athleteName)) results_subtype_node.setWindowTitle(value.getText() + Constants.WHITESPACE + athleteName);
			if(athleteId!=null) results_subtype_node.setParam(Constants.P_PERSON, athleteId);
			results_subtype_node.setParam(Constants.P_FILTER, type + ":" + value.getValue());
			results_subtype_node.setParam(Constants.P_ENTITY, entity.name());
			// authorization => coach is allowed to manually change values
			FUNCTIONTREELOGIC.setAuthority(functionTree, results_subtype_node);
		}
	}
}
