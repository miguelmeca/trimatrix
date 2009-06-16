package trimatrix.db;



/**
 * AbstractListVariants entity provides the base persistence definition of the ListVariants entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractListVariants  implements java.io.Serializable {


    // Fields    

     private ListVariantsId id;
     private String columnsSequence;
     private String columnsWidth;


    // Constructors

    /** default constructor */
    public AbstractListVariants() {
    }

	/** minimal constructor */
    public AbstractListVariants(ListVariantsId id) {
        this.id = id;
    }
    
    /** full constructor */
    public AbstractListVariants(ListVariantsId id, String columnsSequence, String columnsWidth) {
        this.id = id;
        this.columnsSequence = columnsSequence;
        this.columnsWidth = columnsWidth;
    }

   
    // Property accessors

    public ListVariantsId getId() {
        return this.id;
    }
    
    public void setId(ListVariantsId id) {
        this.id = id;
    }

    public String getColumnsSequence() {
        return this.columnsSequence;
    }
    
    public void setColumnsSequence(String columnsSequence) {
        this.columnsSequence = columnsSequence;
    }

    public String getColumnsWidth() {
        return this.columnsWidth;
    }
    
    public void setColumnsWidth(String columnsWidth) {
        this.columnsWidth = columnsWidth;
    }
   








}