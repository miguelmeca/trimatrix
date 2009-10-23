package trimatrix.logic;

import java.util.List;

import org.eclnt.workplace.WorkplaceFunctionTree.FunctionNode;

import trimatrix.entities.EntityLayer;
import trimatrix.entities.IEntityData;
import trimatrix.structures.SFunctionTree;
import trimatrix.utils.Constants;

public class FunctionTreeLogic {
	private EntityLayer entityLayer;

	public List<IEntityData> getMyAthletes() {
		return entityLayer.getPersonEntity().getData(Constants.Entity.MYATHLETES);
	}
	
	public void setAuthority(SFunctionTree functionTree, FunctionNode node) {
		node.setParam(Constants.CREATE, Constants.FALSE);
		if(functionTree.create) {
			node.setParam(Constants.CREATE, Constants.TRUE);
		}
		node.setParam(Constants.CHANGE, Constants.FALSE);
		if(functionTree.edit) {
			node.setParam(Constants.CHANGE, Constants.TRUE);
		}
		node.setParam(Constants.DELETE, Constants.FALSE);
		if(functionTree.delete) {
			node.setParam(Constants.DELETE, Constants.TRUE);
		}
	}
	
	public void setAuthority(FunctionNode node, boolean create, boolean  edit, boolean delete) {
		node.setParam(Constants.CREATE, Constants.FALSE);
		if(create) {
			node.setParam(Constants.CREATE, Constants.TRUE);
		}
		node.setParam(Constants.CHANGE, Constants.FALSE);
		if(edit) {
			node.setParam(Constants.CHANGE, Constants.TRUE);
		}
		node.setParam(Constants.DELETE, Constants.FALSE);
		if(delete) {
			node.setParam(Constants.DELETE, Constants.TRUE);
		}
	}
	
	
	public void setEntityLayer(EntityLayer entityLayer) {
		this.entityLayer = entityLayer;
	}	
}
