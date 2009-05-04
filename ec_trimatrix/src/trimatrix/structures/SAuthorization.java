package trimatrix.structures;

import trimatrix.utils.Constants;

public class SAuthorization {
	public boolean create;
	public boolean change;
	public boolean delete;
	
	public SAuthorization(String create, String change, String delete) {
		this.create = false;		
		if(create.equals(Constants.TRUE)) {
			this.create = true;
		}
		this.change = false;		
		if(change.equals(Constants.TRUE)) {
			this.change = true;
		}
		this.delete = false;		
		if(delete.equals(Constants.TRUE)) {
			this.delete = true;
		}
	}
	
	
}
