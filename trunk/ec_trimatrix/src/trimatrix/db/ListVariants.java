package trimatrix.db;



/**
 * ListVariants entity. @author MyEclipse Persistence Tools
 */
public class ListVariants extends AbstractListVariants implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public ListVariants() {
    }

	/** minimal constructor */
    public ListVariants(ListVariantsId id) {
        super(id);        
    }
    
    /** full constructor */
    public ListVariants(ListVariantsId id, String columnsSequence, String columnsWidth) {
        super(id, columnsSequence, columnsWidth);        
    }
   
}
