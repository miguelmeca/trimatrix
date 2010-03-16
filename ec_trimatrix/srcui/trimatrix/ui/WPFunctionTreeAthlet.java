package trimatrix.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclnt.jsfserver.elements.impl.FIXGRIDTreeItem;
import org.eclnt.jsfserver.managedbean.IDispatcher;
import org.eclnt.workplace.WorkplaceFunctionTree;

import trimatrix.logic.FunctionTreeLogic;
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SFunctionTree;
import trimatrix.utils.Constants;
import trimatrix.utils.Context;
import trimatrix.utils.Helper;
import trimatrix.utils.Constants.Page;

@SuppressWarnings("serial")
public class WPFunctionTreeAthlet extends WorkplaceFunctionTree {
	public static final Log logger = LogFactory.getLog(WPFunctionTreeAthlet.class);
	private static final Constants.Role role = Constants.Role.ATHLETE;
	private FunctionTreeLogic FUNCTIONTREELOGIC = null;
	
	public WPFunctionTreeAthlet(IDispatcher owner) {
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
			if(Helper.isEmpty(functionTree.page)) topNode = true;
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
				// special handling for zones
				if(functionTree.page.equalsIgnoreCase(Page.ZONESDETAIL.name())) {
					String personId = FUNCTIONTREELOGIC.getMyPersonId();
					node.setId("zones:" + personId);
					node.setParam(Constants.P_PERSON, personId);
					node.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);
					node.setOpenMultipleInstances(false);
				} else {					
					node.setId(functionTree.entity);
					node.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);
					node.setOpenMultipleInstances(false);
					if(functionTree.entity != null && functionTree.entity.length() > 0) {
						node.setParam(Constants.P_ENTITY, functionTree.entity);
					}
				}
				// authorization
				FUNCTIONTREELOGIC.setAuthority(functionTree, node);
			} else {
				node = new FunctionNode(parentNode);
			}
			node.setText(functionTree.description);
			// build map
			functionNodeMap.put(functionTree.node, node);
		}
	}
}
