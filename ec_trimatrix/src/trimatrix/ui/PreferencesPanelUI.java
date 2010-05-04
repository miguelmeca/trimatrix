package trimatrix.ui;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.UserDefaults;
import trimatrix.db.UserPreferences;
import trimatrix.logic.helper.DayInfo;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;

@CCGenClass(expressionBase = "#{d.PreferencesPanelUI}")
public class PreferencesPanelUI extends MyWorkpageDispatchedBean implements Serializable {
    private Set<String> deletedDefaultIds = new HashSet<String>();

    private DayInfo dayInfo;
	public DayInfo getDayInfo() {return dayInfo;}
	public void setDayInfo(DayInfo dayInfo) {this.dayInfo = dayInfo;}

	protected FIXGRIDListBinding<GridDefaultsItem> m_gridDefaults = new FIXGRIDListBinding<GridDefaultsItem>();
    public FIXGRIDListBinding<GridDefaultsItem> getGridDefaults() { return m_gridDefaults; }
    public void setGridDefaults(FIXGRIDListBinding<GridDefaultsItem> value) { m_gridDefaults = value; }

    public class GridDefaultsItem extends FIXGRIDItem implements java.io.Serializable {
    	private UserDefaults userDefaults;

		public GridDefaultsItem(UserDefaults userDefaults) {
			this.userDefaults = userDefaults;
		}

		public UserDefaults getUserDefaults() {return userDefaults;}
		public void setUserDefaults(UserDefaults userDefaults) {this.userDefaults = userDefaults;}
    }

    private void buildGrid() {
    	m_gridDefaults.getItems().clear();
    	deletedDefaultIds.clear();
    	// load data from db
    	List<UserDefaults> userDefaults = getLogic().getPreferencesLogic().getUserDefaults();
    	for(UserDefaults userDefault : userDefaults) {
    		m_gridDefaults.getItems().add(new GridDefaultsItem(userDefault));
    	}
    }

    public void onAddDefaultItem(ActionEvent event) {
    	m_gridDefaults.getItems().add(new GridDefaultsItem(getLogic().getPreferencesLogic().createUserDefault()));
    }

    public void onRemoveDefaultItem(ActionEvent event) {
    	GridDefaultsItem selected = m_gridDefaults.getSelectedItem();
    	if(selected==null) return;
    	deletedDefaultIds.add(selected.getUserDefaults().getId());
    	m_gridDefaults.getItems().remove(selected);
    }

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
		dayInfo = getLogic().getPreferencesLogic().getDayInfo(preferences.getDayinfos());
		// defaults
		buildGrid();
	}

	public void onSave(ActionEvent event) {
		try {
			// set dayinfos
			preferences.setDayinfos(getLogic().getPreferencesLogic().getDayInfoString(dayInfo));
			// save preferences
			getLogic().getPreferencesLogic().savePreferences(preferences);
			// delete marked defaults
			getLogic().getPreferencesLogic().deleteDefaults(deletedDefaultIds);
			// save defaults
			for(GridDefaultsItem item : m_gridDefaults.getItems()) {
				getLogic().getPreferencesLogic().updateDefault(item.getUserDefaults());
			}
			Statusbar.outputSuccess("Preferences saved!");
		} catch(Exception ex) {
			Statusbar.outputAlert(ex.toString(), "Saving preferences failed!");
		}
		init();
	}



}
