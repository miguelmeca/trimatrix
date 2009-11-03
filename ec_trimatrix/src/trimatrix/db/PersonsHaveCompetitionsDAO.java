package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import trimatrix.relations.IRelationObject;

/**
 * A data access object (DAO) providing persistence and search support for
 * PersonsHaveCompetitions entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see trimatrix.db.PersonsHaveCompetitions
 * @author MyEclipse Persistence Tools
 */

public class PersonsHaveCompetitionsDAO extends HibernateDaoSupport implements IRelationDAO<PersonsHaveCompetitions> {
	private static final Log log = LogFactory
			.getLog(PersonsHaveCompetitionsDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(PersonsHaveCompetitions transientInstance) {
		log.debug("saving PersonsHaveCompetitions instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IRelationObject persistentInstance) {
		log.debug("deleting PersonsHaveCompetitions instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PersonsHaveCompetitions findById(java.lang.String id) {
		log.debug("getting PersonsHaveCompetitions instance with id: " + id);
		try {
			PersonsHaveCompetitions instance = (PersonsHaveCompetitions) getHibernateTemplate()
					.get("trimatrix.db.PersonsHaveCompetitions", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PersonsHaveCompetitions> findByExample(
			IRelationObject instance) {
		log.debug("finding PersonsHaveCompetitions instance by example");
		try {
			List<PersonsHaveCompetitions> results = (List<PersonsHaveCompetitions>) getHibernateTemplate()
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PersonsHaveCompetitions> findByProperty(String propertyName, Object value) {
		log.debug("finding PersonsHaveCompetitions instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PersonsHaveCompetitions as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PersonsHaveCompetitions> findAll() {
		log.debug("finding all PersonsHaveCompetitions instances");
		try {
			String queryString = "from PersonsHaveCompetitions";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PersonsHaveCompetitions merge(
			IRelationObject detachedInstance) {
		log.debug("merging PersonsHaveCompetitions instance");
		try {
			PersonsHaveCompetitions result = (PersonsHaveCompetitions) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PersonsHaveCompetitions instance) {
		log.debug("attaching dirty PersonsHaveCompetitions instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PersonsHaveCompetitions instance) {
		log.debug("attaching clean PersonsHaveCompetitions instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}	

	@SuppressWarnings("unchecked")
	public int deleteByPartners(String partnerId) {
		int count = 0;
    	log.debug("finding PersonsHaveCompetitions instance with partner1 or partner2 : "+ partnerId);
          try {
             String queryString = "from PersonsHaveCompetitions as model where model.person = '" + partnerId + "' or model.competition = '" + partnerId + "'";
             List<PersonsHaveCompetitions> relations = getHibernateTemplate().find(queryString);
             // delete instance
             for (PersonsHaveCompetitions relation : relations) {
            	 delete(relation);
            	 count++;
             }
          } catch (RuntimeException re) {
             log.error("delete by partners failed", re);
             throw re;
          }
          return count;
	}

	public void reload(IRelationObject relation) {
		String id = relation.getId();
		log.debug("reloading PersonsHaveCompetitions instance with id: " + id);
		try {
			getHibernateTemplate().load(relation, id);			
		} catch (RuntimeException re) {
			log.error("load failed", re);
			throw re;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static IRelationDAO<PersonsHaveCompetitions> getFromApplicationContext(
			ApplicationContext ctx) {
		return (IRelationDAO<PersonsHaveCompetitions>) ctx
				.getBean("PersonsHaveCompetitionsDAO");
	}
}