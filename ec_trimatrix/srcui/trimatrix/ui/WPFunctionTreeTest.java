package trimatrix.ui;

import org.eclnt.jsfserver.elements.impl.FIXGRIDTreeItem;
import org.eclnt.jsfserver.managedbean.IDispatcher;
import org.eclnt.workplace.WorkplaceFunctionTree;

@SuppressWarnings("serial")
public class WPFunctionTreeTest extends WorkplaceFunctionTree {
	
	public WPFunctionTreeTest(IDispatcher owner) {
		super(owner);
	}	
	
	// TODO adapt logic
	@Override
	protected void loadFunctionTree() {
		FunctionNode top;
        
        getFtree().getRootNode().removeAllChildNodes(true);
        
        top = new FunctionNode(getFtree().getRootNode(),"/test/test.jsp");
        top.setText("Test"); top.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);
	}
}
