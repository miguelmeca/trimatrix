package trimatrix.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclnt.jsfserver.managedbean.IDispatcher;
import org.eclnt.workplace.WorkplaceFunctionTree;

import trimatrix.logic.FunctionTreeLogic;
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SFunctionTree;
import trimatrix.utils.Constants;
import trimatrix.utils.Context;
import trimatrix.utils.Dictionary;

@SuppressWarnings("serial")
public class WPFunctionTreeAthlet extends WorkplaceFunctionTree {
	private static final Constants.Role role = Constants.Role.ATHLETE;
	
	public WPFunctionTreeAthlet(IDispatcher owner) {
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
			} else {
				node = new FunctionNode(parentNode);
			}
			node.setText(functionTree.description);
			// build map
			functionNodeMap.put(functionTree.node, node);
		}
	}
}