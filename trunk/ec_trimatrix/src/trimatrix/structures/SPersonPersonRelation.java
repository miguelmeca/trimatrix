package trimatrix.structures;

import trimatrix.db.Persons;

public class SPersonPersonRelation {
	public String  id;
	public Persons partner1;
	public Persons partner2;
	public Boolean default_rel;
	public String description;
	public String description_inverse;
}
