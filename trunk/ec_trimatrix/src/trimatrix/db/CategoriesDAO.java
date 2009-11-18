package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for Categories entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see trimatrix.db.Categories
  * @author MyEclipse Persistence Tools 
 */

@SuppressWarnings("unchecked")
public class CategoriesDAO extends HibernateDaoSupport implements IViewDAO<Categories, CategoriesId> {
    private static final Log log = LogFactory.getLog(CategoriesDAO.class);
	//property constants
    public static String CATEGORY = "id.category";
    public static String SCOUTID = "id.scoutId";

	protected void initDao() {
		//do nothing
	}
         
    public Categories findById( trimatrix.db.CategoriesId id) {
        log.debug("getting Categories instance with id: " + id);
        try {
            Categories instance = (Categories) getHibernateTemplate()
                    .get("trimatrix.db.Categories", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }   
    
	public List<Categories> findByExample(Categories instance) {
        log.debug("finding Categories instance by example");
        try {
            List<Categories> results = (List<Categories>) getHibernateTemplate().findByExample(instance); 
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List<Categories> findByProperty(String propertyName, Object value) {
      log.debug("finding Categories instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Categories as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Categories> findAll() {
		log.debug("finding all Categories instances");
		try {
			String queryString = "from Categories";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public static IViewDAO<Categories, CategoriesId> getFromApplicationContext(ApplicationContext ctx) {
    	return (IViewDAO<Categories, CategoriesId>) ctx.getBean("CategoriesDAO");
	}
}