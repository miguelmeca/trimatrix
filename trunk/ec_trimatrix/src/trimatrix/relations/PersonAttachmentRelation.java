package trimatrix.relations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import trimatrix.db.Attachments;
import trimatrix.db.Persons;
import trimatrix.db.PersonsHaveAttachments;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Helper;
import trimatrix.utils.Constants.Relation;

public class PersonAttachmentRelation extends ARelation {
	// Constants
	public static final String PERSON = "person";
	public static final String RELTYP = "reltyp";
    public static final String ATTACHMENT = "attachment";

	public PersonsHaveAttachments create() {
		String id = UUID.randomUUID().toString();
		PersonsHaveAttachments relation = new PersonsHaveAttachments();
		relation.setId(id);
		// default values
		relation.setStandard(false);
		return relation;
	}

	public boolean delete(String partner1, String partner2) {
		boolean result = true;
		// find relevant entities
		IRelationObject example = new PersonsHaveAttachments();
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

	public List<SGridMetaData> getGridMetaData() {
		List<SGridMetaData> gridMetaData = new ArrayList<SGridMetaData>();
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("person"), PERSON, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("relation"), RELTYP, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("attachment"), ATTACHMENT, SGridMetaData.Component.FIELD));
        return gridMetaData;
	}

	public void save(IRelationObject relationObject) {
		PersonsHaveAttachments relation = (PersonsHaveAttachments)relationObject;
		relationsDAO.merge(relation);
	}

	public void reload(IRelationObject relationObject) {
		PersonsHaveAttachments relation = (PersonsHaveAttachments)relationObject;
		relationsDAO.reload(relation);
	}

	public List<IRelationData> getData() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IRelationData> getData(Relation relation) {
		return sqlExecutorService.getPersonAttachmentRelation(relation);
	}

	public static class Data implements IRelationData {
		public String  id;
		public Persons person;
		public Attachments attachment;
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

		public Attachments getPartner2() {
			return attachment;
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
			return (person + " " + description + " " + attachment);
		}
	}
}
