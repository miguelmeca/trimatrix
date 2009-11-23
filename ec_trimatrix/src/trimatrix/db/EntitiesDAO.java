package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Entities entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see trimatrix.db.Entities
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("unchecked")
public class EntitiesDAO extends HibernateDaoSupport implements IViewDAO<Entities, EntitiesId> {
	private static final Log log = LogFactory.getLog(EntitiesDAO.class);

	// property constants
	public static final String ID = "id.id";
	
	@Override
	protected void initDao() {
		// do nothing
	}

	public Entities findById(trimatrix.db.EntitiesId id) {
		log.debug("getting Entities instance with id: " + id);
		try {
			Entities instance = (Entities) getHibernateTemplate().get(
					"trimatrix.db.Entities", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Entities> findByExample(Entities instance) {
		log.debug("finding Entities instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Entities> findByProperty(String propertyName, Object value) {
		log.debug("finding Entities instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Entities as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Entities> findAll() {
		log.debug("finding all Entities instances");
		try {
			String queryString = "from Entities";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public static IViewDAO<Entities, EntitiesId> getFromApplicationContext(ApplicationContext ctx) {
		return (IViewDAO<Entities, EntitiesId>) ctx.getBean("EntitiesDAO");
	}
}