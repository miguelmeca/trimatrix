package trimatrix.ui;

import org.eclnt.workplace.IWorkpageContainer;
import org.eclnt.workplace.Workpage;
import org.eclnt.workplace.WorkpageDispatcher;

import trimatrix.db.DAOLayer;
import trimatrix.logic.LogicLayer;
import trimatrix.relations.RelationLayer;
import trimatrix.services.ServiceLayer;
import trimatrix.utils.Constants;
import trimatrix.utils.Context;

/*
 * The dispatcher is references in faces-config.xml. When changing the package
 * of the dispatcher - please also update the faces-config.xml link!
 */

@SuppressWarnings("serial")
public class Dispatcher extends WorkpageDispatcher
{
	
	/**
	 * This constructor is called initially 
	 */
	public Dispatcher() {
        super();
        // Limit nr. of max opened pages
        getWorkpageContainer().setMaxNumberOfWorkpages(Constants.MAXWORKPAGES);
        	
    }
	
	/**
	 * This constructor is called when a new workpage is opened
	 * @param workpageContainer
	 */
	public Dispatcher(IWorkpageContainer workpageContainer) {
		super(workpageContainer);
//		int openedPages = workpageContainer.getAllWorkpages().size();
//		System.out.println(openedPages + " Workpages opened!");
//		for(Object obj : workpageContainer.getAllWorkpages()) {
//			Workpage wp = (Workpage)obj;
//			System.out.println(wp.getTitle());
//		}		
//		if(openedPages >= Constants.MAXWORKPAGES) {
//			Workpage wp = (Workpage)workpageContainer.getAllWorkpages().get(0);
//			workpageContainer.getAllWorkpages().remove(0);
//			wp.closeForced();
//		}
	}
	
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
    @Override
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
	
	protected RelationLayer relationLayer = RelationLayer.getFromApplicationContext(Context.getInstance());
	public RelationLayer getRelationLayer() {	return relationLayer; }
	public void setRelationLayer(RelationLayer relationLayer) { this.relationLayer = relationLayer; }
	
	protected DAOLayer daoLayer = DAOLayer.getFromApplicationContext(Context.getInstance());
	public DAOLayer getDaoLayer() {	return daoLayer; }
	public void setDaoLayer(DAOLayer daoLayer) { this.daoLayer = daoLayer; }
}
