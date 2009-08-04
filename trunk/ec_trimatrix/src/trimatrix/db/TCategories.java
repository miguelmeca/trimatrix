package trimatrix.db;



/**
 * TCategories entity. @author MyEclipse Persistence Tools
 */

public class TCategories  implements java.io.Serializable {


    // Fields    

     private TCategoriesId id;
     private String description;
     private String descriptionLong;


    // Constructors

    /** default constructor */
    public TCategories() {
    }

	/** minimal constructor */
    public TCategories(TCategoriesId id) {
        this.id = id;
    }
    
    /** full constructor */
    public TCategories(TCategoriesId id, String description, String descriptionLong) {
        this.id = id;
        this.description = description;
        this.descriptionLong = descriptionLong;
    }

   
    // Property accessors

    /* (non-Javadoc)
	 * @see trimatrix.db.ITCategories#getId()
	 */
    public TCategoriesId getId() {
        return this.id;
    }
    
    /* (non-Javadoc)
	 * @see trimatrix.db.ITCategories#setId(trimatrix.db.TCategoriesId)
	 */
    public void setId(TCategoriesId id) {
        this.id = id;
    }

    /* (non-Javadoc)
	 * @see trimatrix.db.ITCategories#getDescription()
	 */
    public String getDescription() {
        return this.description;
    }
    
    /* (non-Javadoc)
	 * @see trimatrix.db.ITCategories#setDescription(java.lang.String)
	 */
    public void setDescription(String description) {
        this.description = description;
    }

    /* (non-Javadoc)
	 * @see trimatrix.db.ITCategories#getDescriptionLong()
	 */
    public String getDescriptionLong() {
        return this.descriptionLong;
    }
    
    /* (non-Javadoc)
	 * @see trimatrix.db.ITCategories#setDescriptionLong(java.lang.String)
	 */
    public void setDescriptionLong(String descriptionLong) {
        this.descriptionLong = descriptionLong;
    }
   








}