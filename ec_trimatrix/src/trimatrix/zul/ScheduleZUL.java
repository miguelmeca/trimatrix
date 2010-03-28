package trimatrix.zul;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.zkoss.web.servlet.xel.RequestContexts;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericAutowireComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class ScheduleZUL extends GenericAutowireComposer {
    private Textbox firstName;
    private Textbox lastName;
    private Label fullName;
    private Datebox dateBox;
    private Radiogroup units;
    private Grid grid;
    private ListModelList lmList;

    private Button btn1, btn2, btn3;

    public void onFirstName(Event event) {
        fullName.setValue(firstName.getValue()+" "+lastName.getValue());
    }

    @Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		Locale locale = RequestContexts.getCurrent().getRequest().getLocale();
		dateBox.setValue(new Date());
		Window window = (Window)comp;
		window.setTitle("Trainingseinheit Markus Reich");
		// build units
		Radio unit1 = new Radio();
		unit1.setValue("Laufeinheit");
		unit1.setLabel("Laufeinheit");
		unit1.setImage("../images/icons/accept.png");
		Radio unit2 = new Radio();
		unit2.setValue("Radeinheit");
		unit2.setLabel("Radeinheit");
		unit2.setImage("../images/icons/accept_light.png");
		units.getChildren().add(unit1);
		units.getChildren().add(unit2);
		// build grid
//		Column colDuration = new Column();
//		Row runRow = new Row();
//
//		Textbox tbxDuration = new Textbox();
//		tbxDuration.setValue("00:30:00");
//
//		Label lblDuration = new Label();
//		lblDuration.setValue("Duration");
//
//		colDuration.getChildren().add(lblDuration);
//		runRow.getChildren().add(tbxDuration);
//
//
//		grid.getRows().getChildren().add(runRow);

		grid.setRowRenderer(new RowRenderer() {
			public void render(Row row, Object data) throws Exception {
				if (data == null) return;

				Columns columns = row.getGrid().getColumns();
				if (columns.getChildren() == null) return;

				int colCount = columns.getChildren().size();

				Map<String, Object> params = new HashMap<String, Object>();
				params.put("items", createStringList(colCount, String.valueOf(data)));
				row.appendChild(Executions.createComponents("items.zul", row, params));
			}
		});

		lmList = new ListModelList();
		grid.setModel(lmList);


		EventListener onClickEvt = new EventListener() {
			public void onEvent(Event event) throws Exception {

				Columns columns = grid.getColumns();
				if (columns.getChildren() == null) return;

				columns.detach();

				Button btn = (Button) event.getTarget();
				int num = Integer.parseInt(btn.getLabel());

				Map<String, Object> params = new HashMap<String, Object>();
				params.put("columns", createStringList(num, "column"));
				grid.appendChild(Executions.createComponents("columns.zul", grid, params));

				lmList.clear();
				lmList.addAll(createStringList(num, "item"));
			}
		};

		btn1.addEventListener("onClick", onClickEvt);
		btn2.addEventListener("onClick", onClickEvt);
		btn3.addEventListener("onClick", onClickEvt);


		if(param.containsKey("id")) {
			String[] ids = (String[])param.get("id");
			if(ids!=null && ids.length==1) {
				System.out.println(ids[0]);
			}
		} else {
			//Messagebox.show("ID not set!");
			Executions.forward("/zul/timeout.zul");
			window.onClose();
			//Clients.evalJavaScript("window.close()");
		}


	}

	public void onPrevious(Event event) {
		dateBox.setValue(addDaysToDate(dateBox.getValue(), -1));
    }

	public void onNext(Event event) {
		dateBox.setValue(addDaysToDate(dateBox.getValue(), 1));
    }

	public void onLastName(Event event) {
        fullName.setValue(firstName.getValue()+" "+lastName.getValue());
    }

	public void onSave(Event event) {
		fullName.setValue("Tester");
		try {
			Messagebox.show("handle event in java");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	public void onChangeUnit(Event event) {
        Radio selected = units.getSelectedItem();
        try {
        	Messagebox.show(selected.getLabel() + " selected!");
        } catch (Exception e) {
			e.printStackTrace();
		}
    }

	private Date addDaysToDate(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_WEEK, day);
		return cal.getTime();
	}

	private List<String> createStringList(int num, String startWith){
		List<String> result = new ArrayList<String>();

		for (int i = 0; i < num; i++) {
			result.add(startWith + (i + 1));
		}
		return result;
	}

}