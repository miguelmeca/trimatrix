package utils;

import junit.framework.Assert;

import org.junit.Test;

import trimatrix.utils.Constants;

public class ConstantsTest {
	@Test
	public void testEntity() {
		Assert.assertEquals(Constants.Entity.PERSON, Constants.Entity.MYATHLETES.getBase());
		Assert.assertEquals(Constants.Entity.PERSON, Constants.Entity.MYCOACHES.getBase());
		Assert.assertEquals(Constants.Entity.PERSON, Constants.Entity.PERSON.getBase());
	}
	
	@Test
	public void testRelation() {
		Assert.assertEquals(Constants.Relation.PERSONPERSON, Constants.Relation.COACH.getBase());
		Assert.assertEquals(Constants.Relation.PERSONPERSON, Constants.Relation.PERSONPERSON.getBase());
		Assert.assertEquals(Constants.Relation.PERSONDOCTOR, Constants.Relation.DOCTOR.getBase());
		Assert.assertEquals(Constants.Relation.PERSONDOCTOR, Constants.Relation.PERSONDOCTOR.getBase());
	
		Assert.assertEquals(Constants.Entity.PERSON, Constants.Relation.PERSONDOCTOR.getPartner1());
		Assert.assertEquals(Constants.Entity.DOCTOR, Constants.Relation.PERSONDOCTOR.getPartner2());
		Assert.assertEquals(Constants.Entity.PERSON, Constants.Relation.DOCTOR.getPartner1());
		Assert.assertEquals(Constants.Entity.DOCTOR, Constants.Relation.DOCTOR.getPartner2());
		
		Assert.assertEquals(Constants.Entity.PERSON, Constants.Relation.PERSONPERSON.getPartner1());
		Assert.assertEquals(Constants.Entity.PERSON, Constants.Relation.PERSONPERSON.getPartner2());
		Assert.assertEquals(Constants.Entity.PERSON, Constants.Relation.COACH.getPartner1());
		Assert.assertEquals(Constants.Entity.PERSON, Constants.Relation.COACH.getPartner2());
	}
}
