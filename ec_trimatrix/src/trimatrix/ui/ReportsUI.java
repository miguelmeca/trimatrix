package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;

@CCGenClass (expressionBase="#{d.ReportsUI}")

public class ReportsUI implements Serializable
{
    public void onLoadReport(ActionEvent event) {
    	setBrowserUrl(getReportUrl());
    }

    protected String m_browserUrl = "http://localhost:50000/birt/frameset?__report=test.rptdesign&sample=my+parameter";
    public String getBrowserUrl() { return m_browserUrl; }
    public void setBrowserUrl(String value) { m_browserUrl = value; }

    protected String m_reportUrl;
    public String getReportUrl() { return m_reportUrl; }
    public void setReportUrl(String value) { m_reportUrl = value; }

}
