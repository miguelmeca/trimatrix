package trimatrix.logic;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclnt.jsfserver.defaultscreens.Statusbar;

import trimatrix.db.DAOLayer;
import trimatrix.db.ImportTemplates;
import trimatrix.db.ImportTemplatesId;
import trimatrix.services.ServiceLayer;

public class ImportLogic {
	public static final Log logger = LogFactory.getLog(ImportLogic.class);
	private DAOLayer daoLayer;
	private ServiceLayer serviceLayer;

	public List<ImportTemplates> getMyTemplates(String entity) {
		ImportTemplatesId id = new ImportTemplatesId();
		id.setEntity(entity);
		id.setPersonId(serviceLayer.getDictionaryService().getMyPerson().getId());
		ImportTemplates example = new ImportTemplates(id);
		return daoLayer.getImportTemplatesDAO().findByExample(example);
	}

	public boolean saveTemplate(String entity, String description, int startingRow, int[] mapping) {
		try {
			String strMapping = Arrays.toString(mapping);
			ImportTemplatesId id = new ImportTemplatesId(entity, serviceLayer.getDictionaryService().getMyPerson().getId(), description);
			ImportTemplates template = new ImportTemplates(id);
			template.setStartingRow(startingRow);
			template.setMapping(strMapping);
			daoLayer.getImportTemplatesDAO().save(template);
			Statusbar.outputSuccess("Template saved!");
			return true;
		} catch (Exception ex) {
			Statusbar.outputAlert("Template not saved!", "Error", ex.toString());
			return false;
		}
	}

	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}

	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}
}
