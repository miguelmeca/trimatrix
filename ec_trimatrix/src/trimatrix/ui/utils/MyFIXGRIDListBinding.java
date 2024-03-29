package trimatrix.ui.utils;

import java.util.List;

import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;

public class MyFIXGRIDListBinding<T extends FIXGRIDItem> extends FIXGRIDListBinding<T> {

	private IFIXGRIDCallback<T> callback;

	public MyFIXGRIDListBinding(IFIXGRIDCallback<T> callback, boolean changeIndexIsSupported) {
		super(changeIndexIsSupported);
		this.callback = callback;
	}

	public MyFIXGRIDListBinding(IFIXGRIDCallback<T> callback) {
		super();
		this.callback = callback;
	}

    //Old, still valid way of load on demand - overriding the getRows() method
    //Disadvantage: in getRows() you must not change the size of the item list (getItems()).
//	@Override
//	public List<T> getRows() {
//		List<T> result = super.getRows();
//		// check if all items are read
//		boolean foundNullItem = false;
//		for (int i = 0; i < result.size(); i++) {
//			if (result.get(i) == null) {
//				foundNullItem = true;
//				int gridIndex = getSbvalue() + i;
//				T item = callback.createItem(gridIndex);
//				getItems().set(gridIndex, item);
//			}
//		}
//		return foundNullItem ? super.getRows() : result;
//	}

	// "New" way of load on demand: executing load on demand when grid rendering starts.
    @Override
    public void renderingBegins() {
        super.renderingBegins();
        List<T> result = super.getRows();
        // check if all items that are transferred to the client are read
        for (int i=0; i<result.size(); i++) {
            if (result.get(i) == null) {
            	int gridIndex = getSbvalue() + i;
   				T item = callback.createItem(gridIndex);
   				getItems().set(gridIndex, item);
        	}
        }
    }
}
