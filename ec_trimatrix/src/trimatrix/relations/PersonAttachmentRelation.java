package trimatrix.relations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import trimatrix.db.Attachments;
import trimatrix.db.IAttachmentsDAO;
import trimatrix.db.IPersonsDAO;
import trimatrix.db.IPersonsHaveAttachmentsDAO;
import trimatrix.db.Persons;
import trimatrix.db.PersonsHaveAttachments;
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Dictionary;
import trimatrix.utils.Constants.Relation;

public class PersonAttachmentRelation implements IRelation {
	// Constants	 
	public static final String PERSON = "person";
	public static final String RELTYP = "reltyp";
    public static final String ATTACHMENT = "attachment"; 
    
	// Variables
	private SQLExecutorService sqlExecutorService;
	private Dictionary dictionaryService;
	private IPersonsDAO personsDAO;
	private IAttachmentsDAO attachmentsDAO;
	private IPersonsHaveAttachmentsDAO personsHaveAttachmentsDAO;
	
	public boolean delete(String id) {
		try {
			PersonsHaveAttachments relation = get(id);
			personsHaveAttachmentsDAO.delete(relation);
		} catch (Exception ex) {
			Dictionary.logger.warn(ex.toString());
			return false;
		}
		return true;
			
	}

	public PersonsHaveAttachments create() {		
		String id = UUID.randomUUID().toString();
		PersonsHaveAttachments relation = new PersonsHaveAttachments();
		relation.setId(id);
		// default values
		relation.setStandard(false);		
		return relation;
	}
	
	public PersonsHaveAttachments get(String id) {
		return personsHaveAttachmentsDAO.findById(id);
	}

	public List<SGridMetaData> getGridMetaData() {
		List<SGridMetaData> gridMetaData = new ArrayList<SGridMetaData>();
        gridMetaData.add(new SGridMetaData("Person", PERSON, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Beziehung", RELTYP, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Anhang", ATTACHMENT, SGridMetaData.Component.FIELD));
        return gridMetaData;
	}

	public void save(IRelationObject relationObject) {
		PersonsHaveAttachments relation = (PersonsHaveAttachments)relationObject;
		personsHaveAttachmentsDAO.merge(relation);		
	}
	
	public void reload(IRelationObject relationObject) {
		PersonsHaveAttachments relation = (PersonsHaveAttachments)relationObject;
		personsHaveAttachmentsDAO.reload(relation);
	}	
	
	public List<IRelationData> getData() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IRelationData> getData(Relation relation) {
		return sqlExecutorService.getPersonAttachmentRelation(relation);
	}

	public static class Data implements IRelationData {
		public String  id;
		public Persons person;
		public Attachments attachment;
		public Boolean standard;
		public String  reltyp;
		public String  description;
		public String  description_inverse;		

		public String getId() {
			return id;
		}		

		public Persons getPartner1() {
			return person;
		}

		public Attachments getPartner2() {
			return attachment;
		}

		public Boolean getStandard() {
			return standard;
		}

		public String getDescription() {
			return description;
		}

		public String getDescription_inverse() {
			return description_inverse;
		}
		
		public String getReltyp() {
			return reltyp;
		}

		@Override
		public String toString() {
			// same as DB relation implementation
			return (person + " " + description + " " + attachment);
		}	
	}
	
	public void setSqlExecutorService(SQLExecutorService sqlExecutorService) {
		this.sqlExecutorService = sqlExecutorService;
	}
	
	public void setDictionaryService(Dictionary dictionaryService) {
		this.dictionaryService = dictionaryService;
	}	

	public void setPersonsDAO(IPersonsDAO personsDAO) {
		this.personsDAO = personsDAO;
	}

	public void setAttachmentsDAO(IAttachmentsDAO attachmentsDAO) {
		this.attachmentsDAO = attachmentsDAO;
	}

	public void setPersonsHaveAttachmentsDAO(
			IPersonsHaveAttachmentsDAO personsHaveAttachmentsDAO) {
		this.personsHaveAttachmentsDAO = personsHaveAttachmentsDAO;
	}	
}
