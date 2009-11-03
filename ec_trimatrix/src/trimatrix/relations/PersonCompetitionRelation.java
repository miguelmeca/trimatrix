package trimatrix.relations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import trimatrix.db.Competitions;
import trimatrix.db.Persons;
import trimatrix.db.PersonsHaveCompetitions;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants.Relation;

public class PersonCompetitionRelation extends ARelation {
	// Constants	 
	public static final String PERSON = "person";
	public static final String RELTYP = "reltyp";
    public static final String COMPETITION = "competition"; 
    
	public PersonsHaveCompetitions create() {		
		String id = UUID.randomUUID().toString();
		PersonsHaveCompetitions relation = new PersonsHaveCompetitions();
		relation.setId(id);
		// default values
		relation.setStandard(false);		
		return relation;
	}	
	
	public boolean delete(String partner1, String partner2) {
		boolean result = true;
		// find relevant entities
		IRelationObject example = new PersonsHaveCompetitions();
		example.setPartner1(partner1);
		example.setPartner2(partner2);
		List<? extends IRelationObject> relations = relationsDAO.findByExample(example);
		for (IRelationObject relation : relations) {
			if(!delete(relation.getId())) {
				result = false;
			}
		}
		return result;
	}

	public List<SGridMetaData> getGridMetaData() {
		List<SGridMetaData> gridMetaData = new ArrayList<SGridMetaData>();
        gridMetaData.add(new SGridMetaData("Person", PERSON, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Beziehung", RELTYP, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Wettkampf", COMPETITION, SGridMetaData.Component.FIELD));
        return gridMetaData;
	}

	public void save(IRelationObject relationObject) {
		PersonsHaveCompetitions relation = (PersonsHaveCompetitions)relationObject;
		relationsDAO.merge(relation);		
	}
	
	public void reload(IRelationObject relationObject) {
		PersonsHaveCompetitions relation = (PersonsHaveCompetitions)relationObject;
		relationsDAO.reload(relation);
	}	
	
	public List<IRelationData> getData() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IRelationData> getData(Relation relation) {
		// TODO return sqlExecutorService.getPersonCompetitionRelation(relation);
		return null;
	}
	
	public static class Data implements IRelationData {
		public String  id;
		public Persons person;
		public Competitions competition;
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

		public Competitions getPartner2() {
			return competition;
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
			return (person + " " + description + " " + competition);
		}	
	}		
}
