package trimatrix.relations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import trimatrix.db.Doctors;
import trimatrix.db.Persons;
import trimatrix.db.PersonsHaveDoctors;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants.Relation;

public class PersonDoctorRelation extends ARelation {
	// Constants	 
	public static final String PERSON = "person";
	public static final String RELTYP = "reltyp";
    public static final String DOCTOR = "doctor"; 
    
	public boolean delete(String partner1, String partner2) {
		boolean result = true;
		// find relevant entities
		PersonsHaveDoctors example = new PersonsHaveDoctors();
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

	public PersonsHaveDoctors create() {		
		String id = UUID.randomUUID().toString();
		PersonsHaveDoctors relation = new PersonsHaveDoctors();
		relation.setId(id);
		// default values
		relation.setStandard(false);		
		return relation;
	}
	
	public List<SGridMetaData> getGridMetaData() {
		List<SGridMetaData> gridMetaData = new ArrayList<SGridMetaData>();
        gridMetaData.add(new SGridMetaData("Person", PERSON, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Beziehung", RELTYP, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Arzt", DOCTOR, SGridMetaData.Component.FIELD));
        return gridMetaData;
	}

	public void save(IRelationObject relationObject) {
		PersonsHaveDoctors relation = (PersonsHaveDoctors)relationObject;
		relationsDAO.merge(relation);		
	}
	
	public void reload(IRelationObject relationObject) {
		PersonsHaveDoctors relation = (PersonsHaveDoctors)relationObject;
		relationsDAO.reload(relation);
	}	
	
	public List<IRelationData> getData() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IRelationData> getData(Relation relation) {
		return sqlExecutorService.getPersonDoctorRelation(relation);
	}

	public static class Data implements IRelationData {
		public String  id;
		public Persons person;
		public Doctors doctor;
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

		public Doctors getPartner2() {
			return doctor;
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
			return (person + " " + description + " " + doctor);
		}	

	}		
}
