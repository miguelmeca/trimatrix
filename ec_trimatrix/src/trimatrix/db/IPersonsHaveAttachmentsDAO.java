package trimatrix.db;

import java.util.List;

public interface IPersonsHaveAttachmentsDAO {

	// property constants
	public static final String PERSON = "person";
	public static final String ATTACHMENT = "attachment";
	public static final String RELTYP_KEY = "reltypKey";
	public static final String STANDARD = "standard";

	public abstract void save(PersonsHaveAttachments transientInstance);

	public abstract void delete(PersonsHaveAttachments persistentInstance);

	public abstract PersonsHaveAttachments findById(java.lang.String id);

	public abstract List<PersonsHaveAttachments> findByExample(
			PersonsHaveAttachments instance);

	public abstract List<PersonsHaveAttachments> findByProperty(
			String propertyName, Object value);

	public abstract List<PersonsHaveAttachments> findByPerson(Object person);

	public abstract List<PersonsHaveAttachments> findByAttachment(
			Object attachment);

	public abstract List<PersonsHaveAttachments> findByReltypKey(
			Object reltypKey);

	public abstract List<PersonsHaveAttachments> findByStandard(Object standard);

	public abstract List<PersonsHaveAttachments> findAll();

	public abstract PersonsHaveAttachments merge(
			PersonsHaveAttachments detachedInstance);

	public abstract void attachDirty(PersonsHaveAttachments instance);

	public abstract void attachClean(PersonsHaveAttachments instance);

	public abstract void reload(PersonsHaveAttachments relation);

}