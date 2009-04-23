package trimatrix.services;

import org.springframework.context.ApplicationContext;

import trimatrix.db.TLanguages;
import trimatrix.db.TLanguagesDAO;
import trimatrix.db.TLanguagesId;

public class TranslationService {
	private TLanguagesDAO languagesDAO;	
	
	public static final int LANGUAGES = 0;
	public String getDescriptionFromDB(String value, int table, String language) {
		switch (table) {
		case LANGUAGES:
		    TLanguages languages = languagesDAO.findById(new TLanguagesId(value, language));
		    if(languages!=null && languages.getDescription().length() > 0) return languages.getDescription();
			break;
		}
		return value;
	}	
	
	public void setLanguagesDAO(TLanguagesDAO languagesDAO) {
		this.languagesDAO = languagesDAO;
	}
	
	public static TranslationService getFromApplicationContext(ApplicationContext ctx) {
		return (TranslationService) ctx.getBean("translationService");
	}
}
