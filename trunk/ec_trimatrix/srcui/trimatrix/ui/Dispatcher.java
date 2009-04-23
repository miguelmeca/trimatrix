package trimatrix.ui;

import org.eclnt.workplace.WorkpageDispatcher;

import trimatrix.logic.LogicLayer;
import trimatrix.services.ServiceLayer;
import trimatrix.utils.Context;
import trimatrix.utils.Dictionary;

/*
 * The dispatcher is references in faces-config.xml. When changing the package
 * of the dispatcher - please also update the faces-config.xml link!
 */
@SuppressWarnings("serial")
public class Dispatcher extends WorkpageDispatcher
{
	/*
     * This method needs to be implemented - is serves tools (e.g. Layout
     * Editor) to find out which objects are exposed by this dispatcher.
     */
    public static DispatcherInfo getStaticDispatcherInfo() 
    { 
        return new DispatcherInfo(Dispatcher.class); 
    }
    
	/**
     * Returns the expression under which the dispatcher can be reached.
     */
    protected String getRootExpression()
    {
        return "#{d}";
    }
    
    protected LogicLayer logicLayer = LogicLayer.getFromApplicationContext(Context.getInstance());
	public LogicLayer getLogicLayer() {	return logicLayer; }
	public void setLogicLayer(LogicLayer logicLayer) { this.logicLayer = logicLayer; }
	
	protected ServiceLayer serviceLayer = ServiceLayer.getFromApplicationContext(Context.getInstance());
	public ServiceLayer getServiceLayer() {	return serviceLayer; }
	public void setServiceLayer(ServiceLayer serviceLayer) { this.serviceLayer = serviceLayer; }

	protected Dictionary dictionary = Dictionary.getFromApplicationContext(Context.getInstance());
	public Dictionary getDictionary() { return dictionary; }
	public void setDictionary(Dictionary dictionary) { this.dictionary = dictionary; }	
}
