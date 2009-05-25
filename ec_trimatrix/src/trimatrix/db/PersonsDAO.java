package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Persons entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see trimatrix.db.Persons
 * @author MyEclipse Persistence Tools
 */

public class PersonsDAO extends HibernateDaoSupport implements IPersonsDAO {
	private static final Log log = LogFactory.getLog(PersonsDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(Persons transientInstance) {
		log.debug("saving Persons instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void loadAll() {
		log.debug("LoadAll Persons instance");
		try {
			getHibernateTemplate().loadAll(Persons.class);
			log.debug("loadAll successful");
		} catch (RuntimeException re) {
			log.error("LoadAll failed", re);
			throw re;
		}
	}

	public void delete(Persons persistentInstance) {
		log.debug("deleting Persons instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Persons findById(java.lang.String id) {
		log.debug("getting Persons instance with id: " + id);
		try {
			Persons instance = (Persons) getHibernateTemplate().get(
					"trimatrix.db.Persons", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Persons> findByExample(Persons instance) {
		log.debug("finding Persons instance by example");
		try {
			List<Persons> results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Persons> findByProperty(String propertyName, Object value) {
		log.debug("finding Persons instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Persons as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Persons> findAll() {
		log.debug("finding all Persons instances");
		try {
			String queryString = "from Persons";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Persons merge(Persons detachedInstance) {
		log.debug("merging Persons instance");
		try {
			Persons result = (Persons) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Persons instance) {
		log.debug("attaching dirty Persons instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Persons instance) {
		log.debug("attaching clean Persons instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void reload(Persons person) {
		String id = person.getId();
		log.debug("reloading Persons instance with id: " + id);
		try {
			getHibernateTemplate().load(person, id);			
		} catch (RuntimeException re) {
			log.error("load failed", re);
			throw re;
		}
	}
}