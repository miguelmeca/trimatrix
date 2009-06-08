package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * PersonsHaveAttachments entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see trimatrix.db.PersonsHaveAttachments
 * @author MyEclipse Persistence Tools
 */

public class PersonsHaveAttachmentsDAO extends HibernateDaoSupport implements IPersonsHaveAttachmentsDAO {
	private static final Log log = LogFactory
			.getLog(PersonsHaveAttachmentsDAO.class);
	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveAttachmentsDAO#save(trimatrix.db.PersonsHaveAttachments)
	 */
	public void save(PersonsHaveAttachments transientInstance) {
		log.debug("saving PersonsHaveAttachments instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveAttachmentsDAO#delete(trimatrix.db.PersonsHaveAttachments)
	 */
	public void delete(PersonsHaveAttachments persistentInstance) {
		log.debug("deleting PersonsHaveAttachments instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveAttachmentsDAO#findById(java.lang.String)
	 */
	public PersonsHaveAttachments findById(java.lang.String id) {
		log.debug("getting PersonsHaveAttachments instance with id: " + id);
		try {
			PersonsHaveAttachments instance = (PersonsHaveAttachments) getHibernateTemplate()
					.get("trimatrix.db.PersonsHaveAttachments", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveAttachmentsDAO#findByExample(trimatrix.db.PersonsHaveAttachments)
	 */
	@SuppressWarnings("unchecked")
	public List<PersonsHaveAttachments> findByExample(PersonsHaveAttachments instance) {
		log.debug("finding PersonsHaveAttachments instance by example");
		try {
			List<PersonsHaveAttachments> results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveAttachmentsDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public List<PersonsHaveAttachments> findByProperty(String propertyName, Object value) {
		log.debug("finding PersonsHaveAttachments instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PersonsHaveAttachments as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveAttachmentsDAO#findByPerson(java.lang.Object)
	 */
	public List<PersonsHaveAttachments> findByPerson(Object person) {
		return findByProperty(PERSON, person);
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveAttachmentsDAO#findByAttachment(java.lang.Object)
	 */
	public List<PersonsHaveAttachments> findByAttachment(Object attachment) {
		return findByProperty(ATTACHMENT, attachment);
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveAttachmentsDAO#findByReltypKey(java.lang.Object)
	 */
	public List<PersonsHaveAttachments> findByReltypKey(Object reltypKey) {
		return findByProperty(RELTYP_KEY, reltypKey);
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveAttachmentsDAO#findByStandard(java.lang.Object)
	 */
	public List<PersonsHaveAttachments> findByStandard(Object standard) {
		return findByProperty(STANDARD, standard);
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveAttachmentsDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<PersonsHaveAttachments> findAll() {
		log.debug("finding all PersonsHaveAttachments instances");
		try {
			String queryString = "from PersonsHaveAttachments";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveAttachmentsDAO#merge(trimatrix.db.PersonsHaveAttachments)
	 */
	public PersonsHaveAttachments merge(PersonsHaveAttachments detachedInstance) {
		log.debug("merging PersonsHaveAttachments instance");
		try {
			PersonsHaveAttachments result = (PersonsHaveAttachments) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveAttachmentsDAO#attachDirty(trimatrix.db.PersonsHaveAttachments)
	 */
	public void attachDirty(PersonsHaveAttachments instance) {
		log.debug("attaching dirty PersonsHaveAttachments instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveAttachmentsDAO#attachClean(trimatrix.db.PersonsHaveAttachments)
	 */
	public void attachClean(PersonsHaveAttachments instance) {
		log.debug("attaching clean PersonsHaveAttachments instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveAttachmentsDAO#reload(trimatrix.db.PersonsHaveAttachments)
	 */
	public void reload(PersonsHaveAttachments relation) {
		String id = relation.getId();
		log.debug("reloading PersonsHaveAttachments instance with id: " + id);
		try {
			getHibernateTemplate().load(relation, id);			
		} catch (RuntimeException re) {
			log.error("load failed", re);
			throw re;
		}
	}

	public static IPersonsHaveAttachmentsDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IPersonsHaveAttachmentsDAO) ctx
				.getBean("PersonsHaveAttachmentsDAO");
	}
}