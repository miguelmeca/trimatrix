package trimatrix.relations;

public class RelationLayer {
	private PersonPersonRelation personPersonRelation;
	private PersonDoctorRelation personDoctorRelation;
	private PersonAttachmentRelation personAttachmentRelation;
	
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
	
	public PersonAttachmentRelation getPersonAttachmentRelation() {
		return personAttachmentRelation;
	}

	public void setPersonAttachmentRelation(
			PersonAttachmentRelation personAttachmentRelation) {
		this.personAttachmentRelation = personAttachmentRelation;
	}
}
