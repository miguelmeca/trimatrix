package trimatrix.relations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import trimatrix.db.Persons;
import trimatrix.db.PersonsHaveRelations;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants.Relation;

public class PersonPersonRelation extends ARelation {
	// Constants	 
	public static final String PARTNER1 = "partner1";
	public static final String RELTYP   = "reltyp";
    public static final String PARTNER2 = "partner2"; 
    
	public boolean delete(String partner1, String partner2) {
		boolean result = true;
		// find relevant entities
		PersonsHaveRelations example = new PersonsHaveRelations();
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

	public PersonsHaveRelations create() {		
		String id = UUID.randomUUID().toString();
		PersonsHaveRelations relation = new PersonsHaveRelations();
		relation.setId(id);
		// default values
		relation.setStandard(false);		
		return relation;
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
		relationsDAO.merge(relation);		
	}
	
	public void reload(IRelationObject relationObject) {
		PersonsHaveRelations relation = (PersonsHaveRelations)relationObject;
		relationsDAO.reload(relation);
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
		public Persons partner1;
		public Persons partner2;
		public Boolean standard;
		public String  reltyp;
		public String  description;
		public String  description_inverse;		

		public String getId() {
			return id;
		}		

		public Persons getPartner1() {
			return partner1;
		}

		public Persons getPartner2() {
			return partner2;
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
			return (partner1 + " " + description + " " + partner2);
		}	
	}	
}
