package trimatrix.entities;

import org.springframework.context.ApplicationContext;

public class EntityLayer {
	private PersonEntity personEntity;
	private UserEntity userEntity;	
	
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

	public static EntityLayer getFromApplicationContext(ApplicationContext ctx) {
		return (EntityLayer) ctx.getBean("entityLayer");
	}
}
