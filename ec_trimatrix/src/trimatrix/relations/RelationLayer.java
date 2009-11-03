package trimatrix.relations;

import org.springframework.context.ApplicationContext;

public class RelationLayer {
	private PersonPersonRelation personPersonRelation;
	private PersonDoctorRelation personDoctorRelation;
	private PersonAttachmentRelation personAttachmentRelation;
	private PersonCompetitionRelation personCompetitionRelation;
	
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
	
	public PersonCompetitionRelation getPersonCompetitionRelation() {
		return personCompetitionRelation;
	}

	public void setPersonCompetitionRelation(
			PersonCompetitionRelation personCompetitionRelation) {
		this.personCompetitionRelation = personCompetitionRelation;
	}

	public static RelationLayer getFromApplicationContext(ApplicationContext ctx) {
		return (RelationLayer) ctx.getBean("relationLayer");
	}
}
