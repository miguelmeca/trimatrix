package trimatrix.entities;

import org.springframework.context.ApplicationContext;

public class EntityLayer {
	private PersonEntity personEntity;
	private UserEntity userEntity;	
	private DoctorEntity doctorEntity;
	private AttachmentEntity attachmentEntity;
	
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

	public static EntityLayer getFromApplicationContext(ApplicationContext ctx) {
		return (EntityLayer) ctx.getBean("entityLayer");
	}
}
