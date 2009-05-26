package trimatrix.relations;

public class RelationLayer {
	private PersonPersonRelation personPersonRelation;
	private PersonDoctorRelation personDoctorRelation;
	
	public PersonPersonRelation getPersonPersonRelation() {
		return personPersonRelation;
	}	

	public void setPersonPersonRelation(PersonPersonRelation personPersonRelation) {
		this.personPersonRelation = personPersonRelation;
	}

	public PersonDoctorRelation getPersonDoctorRelation() {
		return personDoctorRelation;
	}

	public void setPersonDoctorRelation(PersonDoctorRelation personDoctorRelation) {
		this.personDoctorRelation = personDoctorRelation;
	}		
}
