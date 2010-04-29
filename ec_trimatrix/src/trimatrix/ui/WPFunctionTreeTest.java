package trimatrix.ui;

import org.eclnt.jsfserver.elements.impl.FIXGRIDTreeItem;
import org.eclnt.jsfserver.managedbean.IDispatcher;
import org.eclnt.workplace.WorkplaceFunctionTree;

import trimatrix.utils.Constants;

@SuppressWarnings("serial")
public class WPFunctionTreeTest extends WorkplaceFunctionTree {
	
	public WPFunctionTreeTest(IDispatcher owner) {
		super(owner);
	}	
	
	// TODO adapt logic
	@Override
	protected void loadFunctionTree() {
		FunctionNode test, test2;
		FunctionNode label;
        
        getFtree().getRootNode().removeAllChildNodes(true);
        
        test = new FunctionNode(getFtree().getRootNode(),"/test/test.jsp");
        test.setText("Test"); test.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);
        
        test2 = new FunctionNode(getFtree().getRootNode(),"/test/calendar.jsp");
        test2.setText("Kalendar"); test2.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);
        
        label = new FunctionNode(getFtree().getRootNode(),"/labelsearchresult.jsp");
        label.setParam(Constants.P_LABEL, "55620350-6d49-11de-a69b-604b59d93789");
        label.setText("Label"); label.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);
	}
}
