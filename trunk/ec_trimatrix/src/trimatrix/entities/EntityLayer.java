package trimatrix.entities;

import org.springframework.context.ApplicationContext;

public class EntityLayer {
	private PersonEntity personEntity;
	private UserEntity userEntity;	
	private DoctorEntity doctorEntity;
	private AttachmentEntity attachmentEntity;
	private TestEntity testEntity;
	private CompetitionEntity competitionEntity;
	private ResultEntity resultEntity;
	
	public PersonEntity getPersonEntity() {
		return personEntity;
	}

	public void setPersonEntity(PersonEntity personEntity) {
		this.personEntity = personEntity;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}	

	public DoctorEntity getDoctorEntity() {
		return doctorEntity;
	}

	public void setDoctorEntity(DoctorEntity doctorEntity) {
		this.doctorEntity = doctorEntity;
	}	

	public AttachmentEntity getAttachmentEntity() {
		return attachmentEntity;
	}

	public void setAttachmentEntity(AttachmentEntity attachmentEntity) {
		this.attachmentEntity = attachmentEntity;
	}	

	public TestEntity getTestEntity() {
		return testEntity;
	}

	public void setTestEntity(TestEntity testEntity) {
		this.testEntity = testEntity;
	}

	public CompetitionEntity getCompetitionEntity() {
		return competitionEntity;
	}

	public void setCompetitionEntity(CompetitionEntity competitionEntity) {
		this.competitionEntity = competitionEntity;
	}

	public ResultEntity getResultEntity() {
		return resultEntity;
	}

	public void setResultEntity(ResultEntity resultEntity) {
		this.resultEntity = resultEntity;
	}

	public static EntityLayer getFromApplicationContext(ApplicationContext ctx) {
		return (EntityLayer) ctx.getBean("entityLayer");
	}
}
