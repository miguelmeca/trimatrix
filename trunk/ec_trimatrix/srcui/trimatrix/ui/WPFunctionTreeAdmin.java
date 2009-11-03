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

@SuppressWarnings("serial")
public class WPFunctionTreeAdmin extends WorkplaceFunctionTree {
	public static final Log logger = LogFactory.getLog(WPFunctionTreeAdmin.class);
	private static final Constants.Role role = Constants.Role.ADMIN;
	
	public WPFunctionTreeAdmin(IDispatcher owner) {
		super(owner);
	}	
	
	// TODO adapt logic
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
			Constants.Page page = null;
			// topnode?
			if(functionTree.parent==0) {
				node = new FunctionNode(getFtree().getRootNode());
			} else {
				FunctionNode parentNode = functionNodeMap.get(functionTree.parent);
				if(functionTree.page != null && functionTree.page.length() > 0) {
					try {
						page = Constants.Page.valueOf(functionTree.page);						
					} catch (Exception ex) {
						logger.warn(ex.toString());
						continue;
					}				
					node = new FunctionNode(parentNode, page.getUrl());
					node.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);
					node.setOpenMultipleInstances(true);
					if(functionTree.entity != null && functionTree.entity.length() > 0) {
						node.setParam(Constants.P_ENTITY, functionTree.entity);
					}
					// create authorization
					FUNCTIONTREELOGIC.setAuthority(functionTree, node);
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
