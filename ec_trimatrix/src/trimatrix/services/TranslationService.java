package trimatrix.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import trimatrix.db.DAOLayer;
import trimatrix.db.IText;
import trimatrix.utils.Dictionary;
import trimatrix.utils.Helper;

public class TranslationService {
	public static final Log logger = LogFactory.getLog(TranslationService.class);

	private DAOLayer daoLayer;
	private Dictionary dictionaryService;

	public static enum TYPE {
		SCHEDULETYPES
	}

	public String getDescription(String key, TYPE type ) {
		if(Helper.isEmpty(key)) return null;
		IText text = null;
		switch (type) {
		case SCHEDULETYPES:
			text = daoLayer.getTscheduletypesDAO().findById(key, Helper.getLanguageServer());
			break;
		}
		if(text==null) {
			logger.warn("No Translation for " + type.toString() + " : " + key);
			return key;
		}
		return text.getDescription();

	}

	public String getDescription(String key, String language, TYPE type ) {
		IText text = daoLayer.getTscheduletypesDAO().findById(key, language);
		return text.getDescription();
	}

	public static TranslationService getFromApplicationContext(ApplicationContext ctx) {
		return (TranslationService) ctx.getBean("translationService");
	}

	public void setDictionaryService(Dictionary dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}
}
