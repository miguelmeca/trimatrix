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
	
		Assert.assertEquals(Constants.Entity.PERSON, Constants.Relation.PERSONDOCTOR.getParnter1());
		Assert.assertEquals(Constants.Entity.DOCTOR, Constants.Relation.PERSONDOCTOR.getParnter2());
		Assert.assertEquals(Constants.Entity.PERSON, Constants.Relation.DOCTOR.getParnter1());
		Assert.assertEquals(Constants.Entity.DOCTOR, Constants.Relation.DOCTOR.getParnter2());
		
		Assert.assertEquals(Constants.Entity.PERSON, Constants.Relation.PERSONPERSON.getParnter1());
		Assert.assertEquals(Constants.Entity.PERSON, Constants.Relation.PERSONPERSON.getParnter2());
		Assert.assertEquals(Constants.Entity.PERSON, Constants.Relation.COACH.getParnter1());
		Assert.assertEquals(Constants.Entity.PERSON, Constants.Relation.COACH.getParnter2());
	}
}
