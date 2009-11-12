package trimatrix.structures;

import java.io.Serializable;

import trimatrix.utils.Constants;

public class SAuthorization implements Serializable {
	public static final SAuthorization NONE = new SAuthorization(false, false, false);
	public static final SAuthorization ALL = new SAuthorization(true, true, true);
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
	
	public SAuthorization(boolean create, boolean change, boolean delete) {
		this.create = create;		
		this.change = change;	
		this.delete = delete;		
	}
	
	
}
