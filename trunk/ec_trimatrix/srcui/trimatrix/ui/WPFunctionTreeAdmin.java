package trimatrix.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclnt.jsfserver.managedbean.IDispatcher;
import org.eclnt.workplace.WorkplaceFunctionTree;

import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SFunctionTree;
import trimatrix.utils.Constants;
import trimatrix.utils.Context;

@SuppressWarnings("serial")
public class WPFunctionTreeAdmin extends WorkplaceFunctionTree {
	private static final Constants.Role role = Constants.Role.ADMIN;
	
	public WPFunctionTreeAdmin(IDispatcher owner) {
		super(owner);
	}	
	
	// TODO adapt logic
	@Override
	protected void loadFunctionTree() {
		// reset functiontree
		getFtree().getRootNode().removeAllChildNodes(true);
		
		Map<Integer, FunctionNode> functionNodeMap = new HashMap<Integer, FunctionNode>();
		SQLExecutorService sqlExecutorService = SQLExecutorService.getFromApplicationContext(Context.getInstance());
		List<SFunctionTree> functionTreeList = sqlExecutorService.getFunctionTree(role);
		
		for (SFunctionTree functionTree : functionTreeList) {
			FunctionNode node;
			// topnode?
			if(functionTree.parent==0) {
				node = new FunctionNode(getFtree().getRootNode());
			} else {
				FunctionNode parentNode = functionNodeMap.get(functionTree.parent);
				if(functionTree.page != null && functionTree.page.length() > 0) {
					Constants.Page page = Constants.Page.valueOf(functionTree.page);
					node = new FunctionNode(parentNode, page.url());
					node.setStatus(FunctionNode.STATUS_ENDNODE);
					node.setOpenMultipleInstances(true);
					if(functionTree.entity != null && functionTree.entity.length() > 0) {
						node.setParam(Constants.P_ENTITY, functionTree.entity);
					}
					// create authorization
					node.setParam(Constants.CREATE, Constants.FALSE);
					if(functionTree.create) {
						node.setParam(Constants.CREATE, Constants.TRUE);
					}
					node.setParam(Constants.CHANGE, Constants.FALSE);
					if(functionTree.edit) {
						node.setParam(Constants.CHANGE, Constants.TRUE);
					}
					node.setParam(Constants.DELETE, Constants.FALSE);
					if(functionTree.delete) {
						node.setParam(Constants.DELETE, Constants.TRUE);
					}
				} else {
					node = new FunctionNode(parentNode);
				}							
			}
			node.setText(functionTree.description);
			// build map
			functionNodeMap.put(functionTree.node, node);
		}
	}
}