package trimatrix.ui;

import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;


public interface IEntityDetailUI {
	/**
	 * To set the entity object and the fields enabled or disabled
	 * @param entityObject entity object
	 */
	public void init(Object entityObject);
	/**
	 * To set fields enabled or disabled
	 */
	public void init();
	/**
	 * Validate the data (mandatory fields)
	 * and write back data to entity
	 * @throws MandatoryCheckException
	 * @throws EmailNotValidException 
	 */
	public void validate() throws MandatoryCheckException, EmailNotValidException;
	
	public void prepareSave();
	
	public void postSave();	
}
