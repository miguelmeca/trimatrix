package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.impl.ARRAYGRIDListBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.UserPreferences;
import trimatrix.ui.RelationListUI.MyARRAYGRIDItem;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;

@CCGenClass(expressionBase = "#{d.PreferencesPanelUI}")
public class PreferencesPanelUI extends MyWorkpageDispatchedBean implements Serializable {

	private UserPreferences preferences;

	public int getSbvisibleamount() {
		return preferences.getSbvisibleamount();
	}

	public void setSbvisibleamount(int sbvisibleamount) {
		preferences.setSbvisibleamount(sbvisibleamount);
	}

	public String getCompetitionCategories() {
		return preferences.getCompetitionCategories();
	}

	public void setCompetitionCategories(String competitionCategories) {
		preferences.setCompetitionCategories(competitionCategories);
	}

	public PreferencesPanelUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		// init data
		init();
	}

	private void init() {
		preferences = getLogic().getPreferencesLogic().getPreferences();
	}

	public void onSave(ActionEvent event) {
		try {
			getLogic().getPreferencesLogic().savePreferences(preferences);		
			Statusbar.outputSuccess("Preferences saved!");
		} catch(Exception ex) {
			Statusbar.outputAlert(ex.toString(), "Saving preferences failed!");
		}
		init();		
	}
}
