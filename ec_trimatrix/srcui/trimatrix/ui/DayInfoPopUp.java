package trimatrix.ui;

import java.io.Serializable;
import org.eclnt.editor.annotations.CCGenClass;

@CCGenClass (expressionBase="#{d.DayInfoPopUp}")

public class DayInfoPopUp implements Serializable
{
    protected Date m_actualDate;
    public Date getActualDate() { return m_actualDate; }
    public void setActualDate(Date value) { m_actualDate = value; }

    protected int m_restHr;
    public int getRestHr() { return m_restHr; }
    public void setRestHr(int value) { m_restHr = value; }

    public void onSave(ActionEvent event) {}

    public void onPreviousDay(ActionEvent event) {}

    public void onNextDay(ActionEvent event) {}

}
