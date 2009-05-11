package trimatrix.structures;

import trimatrix.utils.Constants;

public final class SFunctionTree {
	public static final String ATHLETES = "athletes_own";
	
	public Integer node;
	public Integer parent;
	public Integer order;
	public Constants.FunctionNode key;
	public String page;
	public String entity;
	public Boolean edit;
	public Boolean create;
	public Boolean delete;
	public String description;
	public String description_long;
}
