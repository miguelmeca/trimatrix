package trimatrix.logic;

import java.util.List;

import trimatrix.db.DAOLayer;
import trimatrix.db.PersonsHaveRelations;
import trimatrix.relations.IRelationData;
import trimatrix.relations.RelationLayer;
import trimatrix.utils.Constants;

public class RelationListLogic {
	private DAOLayer daoLayer;
	private RelationLayer relationLayer;
	
	public List<IRelationData> getPersonPersonRelations(Constants.Relation relation) {
		return relationLayer.getPersonPersonRelation().getData(relation);
	}
	
	public boolean deletePersonPersonRelation(String id) {
		return relationLayer.getPersonPersonRelation().delete(id);
	}
	
	public void savePersonPersonRelation(PersonsHaveRelations relation) {
		relationLayer.getPersonPersonRelation().save(relation);
	}
		
	public void setRelationLayer(RelationLayer relationLayer) {
		this.relationLayer = relationLayer;
	}
	
	public PersonsHaveRelations createPersonPersonRelation() {
		return relationLayer.getPersonPersonRelation().create();
	}

	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}	
}
