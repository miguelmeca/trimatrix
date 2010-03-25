package trimatrix.zul;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericAutowireComposer;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class ScheduleZUL extends GenericAutowireComposer {
    private Textbox firstName; //auto-wired
    private Textbox lastName; //auto-wired
    private Label fullName; //auto-wired

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
		Window window = (Window)comp;
		window.setTitle("Trainingseinheit");
		if(param.containsKey("id")) {
			String[] ids = (String[])param.get("id");
			if(ids!=null && ids.length==1) System.out.println(ids[0]);
		}
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

}