package trimatrix.relations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import trimatrix.db.IPersonsDAO;
import trimatrix.db.IPersonsHaveRelationsDAO;
import trimatrix.db.Persons;
import trimatrix.db.PersonsDAO;
import trimatrix.db.PersonsHaveRelations;
import trimatrix.entities.PersonEntity;
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Dictionary;
import trimatrix.utils.Constants.Relation;

public class PersonPersonRelation implements IRelation {
	// Constants	 
	public static final String PARTNER1 = "partner1";
	public static final String RELTYP   = "reltyp";
    public static final String PARTNER2 = "partner2"; 
    
	// Variables
	private SQLExecutorService sqlExecutorService;
	private Dictionary dictionaryService;
	private IPersonsDAO personsDAO;
	private IPersonsHaveRelationsDAO personsHaveRelationsDAO;
	
	public boolean delete(String id) {
		try {
			PersonsHaveRelations relation = get(id);
			personsHaveRelationsDAO.delete(relation);
		} catch (Exception ex) {
			Dictionary.logger.warn(ex.toString());
			return false;
		}
		return true;
			
	}

	public PersonsHaveRelations create() {		
		String id = UUID.randomUUID().toString();
		PersonsHaveRelations relation = new PersonsHaveRelations();
		relation.setId(id);
		// default values
		relation.setDefault_(false);		
		return relation;
	}
	
	public PersonsHaveRelations get(String id) {
		return personsHaveRelationsDAO.findById(id);
	}

	public List<SGridMetaData> getGridMetaData() {
		List<SGridMetaData> gridMetaData = new ArrayList<SGridMetaData>();
        gridMetaData.add(new SGridMetaData("Partner 1", PARTNER1, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Beziehung", RELTYP, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Partner 2", PARTNER2, SGridMetaData.Component.FIELD));
        return gridMetaData;
	}

	public void save(IRelationObject relationObject) {
		PersonsHaveRelations relation = (PersonsHaveRelations)relationObject;
		personsHaveRelationsDAO.merge(relation);		
	}
	
	public void reload(IRelationObject relationObject) {
		PersonsHaveRelations relation = (PersonsHaveRelations)relationObject;
		personsHaveRelationsDAO.reload(relation);
	}	
	
	public List<IRelationData> getData() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IRelationData> getData(Relation relation) {
		return sqlExecutorService.getPersonPersonRelation(relation);
	}

	public static class Data implements IRelationData {
		public String  id;
		public String  partner1;
		public String  partner2;
		public Persons person1;
		public Persons person2;
		public Boolean default_rel;
		public String  reltyp;
		public String  description;
		public String  description_inverse;		

		public String getId() {
			return id;
		}		

		public String getPartner1() {
			return partner1;
		}

		public String getPartner2() {
			return partner2;
		}

		public Persons getPerson1() {
			return person1;
		}

		public Persons getPerson2() {
			return person2;
		}

		public Boolean getDefault_rel() {
			return default_rel;
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
			return (partner1 + " " + description + " " + partner2);
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

	public void setPersonsHaveRelationsDAO(
			IPersonsHaveRelationsDAO personsHaveRelationsDAO) {
		this.personsHaveRelationsDAO = personsHaveRelationsDAO;
	}	
}
