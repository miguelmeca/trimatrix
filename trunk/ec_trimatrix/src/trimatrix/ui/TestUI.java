package trimatrix.ui;

import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.faces.event.ActionEvent;
import javax.mail.MessagingException;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.util.valuemgmt.ValueManager;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.TextAnchor;

import trimatrix.structures.SAttachment;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;
import trimatrix.utils.MailSender;
import trimatrix.utils.maths.Helper;
import trimatrix.utils.maths.RegressionFunctions;
import trimatrix.utils.maths.RegressionFunctions.RegressionResult;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseField;
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfAnnotation;
import com.lowagie.text.pdf.PdfBorderDictionary;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfFormField;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPCellEvent;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PushbuttonField;
import com.lowagie.text.pdf.TextField;

@SuppressWarnings("serial")
@CCGenClass (expressionBase="#{d.TestUI}")

public class TestUI extends MyWorkpageDispatchedBean implements Serializable
{   
    protected RegressionFunctions regression;
    
    //double[] xyArr_1 = { 8, 0.09, 10, 0.14, 12, 0.49, 14, 0.95, 16, 1.74, 18, 4.47, 20, 10.21, 22, 13.25 };
    
    double[] xyArr_org_1 = { 8, 1.64, 10, 1.69, 12, 2.04, 14, 2.5, 16, 3.29, 18, 6.02, 20, 11.76, 22, 14.80 };	
    double[] xyArr_org_2 = { 8, 120, 10, 131, 12, 144, 14, 157, 16, 171, 18, 179, 20, 190, 22, 193 };
    
    double[] xyArr_1 = null;	
    double[] xyArr_2 = null;
	
    protected String m_offset = "0.0";
    public String getOffset() { return m_offset; }
    public void setOffset(String value) { m_offset = value; }
    
    protected String m_diagram;
    public String getDiagram() { return m_diagram; }
    public void setDiagram(String value) { m_diagram = value; }
    
    protected String m_diagram2;
    public String getDiagram2() { return m_diagram2; }
    public void setDiagram2(String value) { m_diagram2 = value; }

    protected String m_valuesLactat;
    public String getValuesLactat() { return m_valuesLactat; }
    public void setValuesLactat(String value) { m_valuesLactat = value; }

    protected String m_valuesHr;
    public String getValuesHr() { return m_valuesHr; }
    public void setValuesHr(String value) { m_valuesHr = value; }
    
    protected String m_valuesSpeed;
    public String getValuesSpeed() { return m_valuesSpeed; }
    public void setValuesSpeed(String value) { m_valuesSpeed = value; }

    protected String m_formula;
    public String getFormula() { return m_formula; }
    public void setFormula(String value) { m_formula = value; }
    
    protected String m_correlation;
    public String getCorrelation() { return m_correlation; }
    public void setCorrelation(String value) { m_correlation = value; }

    protected String m_speed;
    public String getSpeed() { return m_speed; }
    public void setSpeed(String value) { m_speed = value; }

    protected String m_hr;
    public String getHr() { return m_hr; }
    public void setHr(String value) { m_hr = value; }
    
    protected String m_lactat;
    public String getLactat() { return m_lactat; }
    public void setLactat(String value) { m_lactat = value; }

    protected String m_name;
    public String getName() { return m_name; }
    public void setName(String value) { m_name = value; }

    protected String m_email;
    public String getEmail() { return m_email; }
    public void setEmail(String value) { m_email = value; }

    public TestUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher, true);	
		getWorkpage().setId("123456");
		setLabelRowDynamic();
    }
    
    public void onCalculate(ActionEvent event) {
    	if(regression==null) return;
    	if(m_speed!=null && m_speed.length()>0) {
    		Double speed = null;
        	try {
        		speed = Double.valueOf(m_speed);
        	} catch (Exception ex) {
        		Statusbar.outputError("Speed is not valid", ex.toString());
        		return;
        	}
        	if(speed==null) return;
        	m_lactat = String.valueOf(regression.getResult().getY(speed)); 
        	try {
				m_hr = String.valueOf(Helper.getYFromMultiLinearFunction(xyArr_2, speed));
			} catch (Exception ex) {
				Statusbar.outputError("Error at calculating heart rate", ex.toString());
        		return;
			}
        	return;
    	}
    	if(m_lactat!=null && m_lactat.length()>0) {
    		Double lactat = null;
        	try {
        		lactat = Double.valueOf(m_lactat);
        	} catch (Exception ex) {
        		Statusbar.outputError("Lactat is not valid", ex.toString());
        		return;
        	}
        	if(lactat==null) return;
        	double speed = regression.getResult().getX(lactat);
        	m_speed = String.valueOf(speed); 
        	try {
				m_hr = String.valueOf(Helper.getYFromMultiLinearFunction(xyArr_2, speed));
			} catch (Exception ex) {
				Statusbar.outputError("Error at calculating heart rate", ex.toString());
        		return;
			}
        	return;
    	}    	   	
    }
    
    public void onRefreshDiagram2(ActionEvent event) {  
    	if(m_valuesSpeed==null || m_valuesSpeed.length()==0) {
    		Statusbar.outputError("Speed values missing");
    		return;
    	}
    	if(m_valuesLactat==null || m_valuesLactat.length()==0) {
    		Statusbar.outputError("Lactate values missing");
    		return;
    	}
    	
    	if(m_valuesHr==null || m_valuesHr.length()==0) {
    		Statusbar.outputError("Heartrate values missing");
    		return;
    	}    	
    	
    	String[] arrSpeed = m_valuesSpeed.split(" ");
    	String[] arrLactat = m_valuesLactat.split(" ");
    	String[] arrHr = m_valuesHr.split(" ");
    	
    	if(!(arrSpeed.length==arrLactat.length && arrSpeed.length==arrHr.length)) {
    		Statusbar.outputError("Different count of values");
    		return;
    	}
    	
    	int len = arrSpeed.length;
    	xyArr_1 = new double[len*2];
    	xyArr_2 = new double[len*2];
    	
    	try {
    		for(int i=0;i<len;i++){
        		int index = i * 2;
        		xyArr_1[index] = Double.valueOf(arrSpeed[i]);
        		xyArr_1[index+1] = Double.valueOf(arrLactat[i]);
        		xyArr_2[index] = Double.valueOf(arrSpeed[i]);
        		xyArr_2[index+1] = Double.valueOf(arrHr[i]);
        		
        	}
    	} catch (Exception ex) {
    		Statusbar.outputError("Error converting values", ex.toString());
    		return;
    	}    		
    	paintDiagram();
    }

    public void onRefreshDiagram(ActionEvent event) {   	
    	xyArr_1 = xyArr_org_1;
    	xyArr_2 = xyArr_org_2;
    	paintDiagram();
    }
    
	private void paintDiagram() {
		// paint chart 1	
    	double offset;
    	try {
    		offset = Double.valueOf(m_offset);
    	} catch (Exception ex) {
    		Statusbar.outputError("Offset is not valid", ex.toString());
    		return;
    	}
    	
    	double[] xyArr = new double[xyArr_1.length];
    	for(int i=1;i<xyArr_1.length;i+=2){
			xyArr[i-1] = xyArr_1[i-1];
			xyArr[i] = xyArr_1[i]-offset;
		}	    	
    	
    	int arrLen = xyArr_1.length;	   	

    	XYSeries series1 = new XYSeries("Messpunkte");
		for(int i=1;i<xyArr_1.length;i+=2){
			series1.add(xyArr[i-1], xyArr[i] + offset);
		}

		regression = new RegressionFunctions(RegressionFunctions.EXP_REGRESSION, xyArr, offset);
		RegressionResult result = regression.getResult();
		
		double[] xyArrReg = xyArr.clone();
		
		for(int i=1;i<xyArrReg.length;i+=2){
			xyArrReg[i] = regression.getResult().getY(xyArrReg[i-1])-offset;
		}		
		
		double corr = regression.getPearsonCorrelation(xyArr, xyArrReg);
		m_correlation = String.valueOf(corr);
		
		m_formula = result.getFormel();
		XYSeries series2 = DatasetUtilities.sampleFunction2DToSeries(result.getFunction2D(), 8, 22, 140, "Laktat");
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);

		final JFreeChart chart = ChartFactory.createXYLineChart(
				"Laktat", "Geschwindigkeit[km/h]", "Laktat[mmol/l]",
				dataset, PlotOrientation.VERTICAL, true, true, false);

		// get a reference to the plot for further customisation...
		final XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesLinesVisible(0, false);
		renderer.setSeriesShapesVisible(1, false);
		plot.setRenderer(renderer);

		// add some annotations...
		XYTextAnnotation annotation = null;
		Font font = new Font("SansSerif", Font.PLAIN, 9);
		annotation = new XYTextAnnotation("Laktatkurve", 190, 20);
		annotation.setFont(font);
		annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
		plot.addAnnotation(annotation);
	
		// Convert to png/binary for UI
        try {
        	ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ChartUtilities.writeChartAsPNG(bos, chart, 400, 300);
			bos.close();
			byte[] image = bos.toByteArray();
	        m_diagram = ValueManager.encodeHexString(image);
		} catch (Exception ex) {
			Statusbar.outputError("Error creating Diagram",ex.toString());
		}
		
		// paint chart 2	
		xyArr = xyArr_2;
		series1 = new XYSeries("Messpunkte");
		for(int i=1;i<xyArr_1.length;i+=2){
			series1.add(xyArr[i-1], xyArr[i]);
		}
		dataset.removeAllSeries();
		dataset.addSeries(series1);

		final JFreeChart chart2 = ChartFactory.createXYLineChart(
				"Herzfrequenz", "Geschwindigkeit[km/h]", "Herzfrequenz[1/min]",
				dataset, PlotOrientation.VERTICAL, true, true, false);

		// get a reference to the plot for further customisation...
		final XYPlot plot2 = chart2.getXYPlot();
		plot2.setBackgroundPaint(Color.lightGray);
		plot2.setDomainGridlinePaint(Color.white);
		plot2.setRangeGridlinePaint(Color.white);

		final XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer(true, true);
		plot2.setRenderer(renderer2);

		// add some annotations...
		annotation = new XYTextAnnotation("Pulswerte", 190, 20);
		annotation.setFont(font);
		annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
		plot2.addAnnotation(annotation);
	
		// Convert to png/binary for UI
        try {
        	ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ChartUtilities.writeChartAsPNG(bos, chart2, 400, 300);
			bos.close();
			byte[] image = bos.toByteArray();
	        m_diagram2 = ValueManager.encodeHexString(image);
		} catch (Exception ex) {
			Statusbar.outputError("Error creating Diagram2",ex.toString());
		}
	}

    public void onRefreshProtocol(ActionEvent event) {
    	Connection conn = null;

        try
        {
            String userName = "trimatrix";
            String password = "trimatrix";
            String url = "jdbc:mysql://localhost/trimatrix";
            Class.forName ("com.mysql.jdbc.Driver").newInstance ();
            conn = DriverManager.getConnection (url, userName, password);
            System.out.println ("Database connection established");   
                  
            Statement s = conn.createStatement ();
            s.executeQuery ("SELECT speed, heartrate, lactate FROM test");
            ResultSet rs = s.getResultSet ();
            int count = 0;
            while (rs.next ())
            {
                m_valuesSpeed = rs.getString ("speed");
                m_valuesHr = rs.getString ("heartrate");
                m_valuesLactat = rs.getString ("lactate");
                ++count;
            }
            rs.close ();
            s.close ();
            System.out.println (count + " rows were retrieved");
            
        }
        catch (Exception e)
        {
            System.err.println ("Cannot connect to database server " + e.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close ();
                    System.out.println ("Database connection terminated");
                }
                catch (Exception e) { /* ignore close errors */ }
            }
        }
    }

    public void onSend(ActionEvent event) {
    	// Create AcroForm
    	PdfWriter writer = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Document document = new Document();
		try {			
			writer = PdfWriter.getInstance(document, baos);
			document.open();
			document.add(new Paragraph("Testprotokoll für " + m_name));			
			
			PdfPTable table = new PdfPTable(2);
			table.getDefaultCell().setPadding(5f); 
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			PdfPCell cell;					

			// speed	
			table.addCell("Geschwindigkeit"); 
			TextField speedField = new TextField(writer, 
				new Rectangle(0,0,100,10), "speed");
			speedField.setBackgroundColor(Color.WHITE);
			speedField.setBorderColor(Color.BLACK);
			speedField.setBorderWidth(1);
			speedField.setBorderStyle(PdfBorderDictionary.STYLE_SOLID);
			speedField.setText("");
			speedField.setAlignment(Element.ALIGN_LEFT);
			speedField.setOptions(BaseField.REQUIRED);				
			cell = new PdfPCell();
			cell.setMinimumHeight(10);
			cell.setCellEvent(new FieldCell(speedField.getTextField(), 300, writer));
			table.addCell(cell);
			
			// add empty row
			table.addCell("");
			table.addCell("");
			
			// heart rate
			table.addCell("Herzfrequenz"); 
			TextField hrField = new TextField(writer, 
				new Rectangle(0,0,100,10), "hr");
			hrField.setBackgroundColor(Color.WHITE);
			hrField.setBorderColor(Color.BLACK);
			hrField.setBorderWidth(1);
			hrField.setBorderStyle(PdfBorderDictionary.STYLE_SOLID);
			hrField.setText("");
			hrField.setAlignment(Element.ALIGN_LEFT);
			hrField.setOptions(BaseField.REQUIRED);				
			cell = new PdfPCell();
			cell.setMinimumHeight(10);
			cell.setCellEvent(new FieldCell(hrField.getTextField(), 300, writer));
			table.addCell(cell);
			
			// add empty row
			table.addCell("");
			table.addCell("");
			
			// lactate
			table.addCell("Laktat"); 
			TextField lacField = new TextField(writer, 
				new Rectangle(0,0,100,10), "lactate");
			lacField.setBackgroundColor(Color.WHITE);
			lacField.setBorderColor(Color.BLACK);
			lacField.setBorderWidth(1);
			lacField.setBorderStyle(PdfBorderDictionary.STYLE_SOLID);
			lacField.setText("");
			lacField.setAlignment(Element.ALIGN_LEFT);
			lacField.setOptions(BaseField.REQUIRED);				
			cell = new PdfPCell();
			cell.setMinimumHeight(10);
			cell.setCellEvent(new FieldCell(lacField.getTextField(), 300, writer));
			table.addCell(cell);
			
			// add empty row
			table.addCell("");
			table.addCell("");
			
			// add commet textfield
			table.addCell("Kommentar");
			TextField comment = new TextField(writer, 
				new Rectangle(0, 0,100,	100), "comment");
			comment.setBorderColor(Color.BLACK);
			comment.setBorderWidth(1);
			comment.setBorderStyle(PdfBorderDictionary.STYLE_SOLID);
			comment.setText("");
			comment.setOptions(BaseField.MULTILINE | 
				BaseField.DO_NOT_SCROLL);
			cell = new PdfPCell();
			cell.setMinimumHeight(100);
			cell.setCellEvent(new FieldCell(comment.getTextField(), 
				300, writer));
			table.addCell(cell);
			
			
			// check comment characters length javascript
			writer.addJavaScript(
				"var commentField = " +
				"this.getField('commentField');" +
				"commentField" +
				".setAction('Keystroke','checkLength()');" +
				"function checkLength(){" +
				"if(!event.willCommit && " +
				"event.value.length > 100){" +
				"app.alert('Warning! Comment can not " +
				"be more than 100 characters.');" +
				"event.change = '';" +
				"}}");			
			
			
			// submit button	
			PushbuttonField submitBtn = new PushbuttonField(writer,
					new Rectangle(0, 0, 35, 15),"submitPOST");
			submitBtn.setBackgroundColor(Color.GRAY);
			submitBtn.
				setBorderStyle(PdfBorderDictionary.STYLE_BEVELED);
			submitBtn.setText("POST");
			submitBtn.setOptions(BaseField.
				VISIBLE_BUT_DOES_NOT_PRINT);
			PdfFormField submitField = submitBtn.getField();
			submitField.setAction(PdfAction
			.createSubmitForm(
			"http://localhost:50000/trimatrix/servlet/AcroForm",
			null, PdfAction.SUBMIT_HTML_FORMAT));
			//submitField.setAction(PdfAction.javaScript("app.alert(this.submitForm('http://localhost:50000/trimatrix/servlet/AcroForm'))", writer));
					
			cell = new PdfPCell();
			cell.setMinimumHeight(15);
			cell.setCellEvent(new FieldCell(submitField, 35, writer));
			table.addCell(cell);
					
			// reset button
			PushbuttonField resetBtn = new PushbuttonField(writer,
					new Rectangle(0, 0, 35, 15), "reset");
			resetBtn.setBackgroundColor(Color.GRAY);
			resetBtn.setBorderStyle(
				PdfBorderDictionary.STYLE_BEVELED);
			resetBtn.setText("RESET");
			resetBtn
			.setOptions(
				BaseField.VISIBLE_BUT_DOES_NOT_PRINT);
			PdfFormField resetField = resetBtn.getField();
			resetField.setAction(PdfAction.createResetForm(null, 0));
			cell = new PdfPCell();
			cell.setMinimumHeight(15);
			cell.setCellEvent(new FieldCell(resetField, 35, writer));
			table.addCell(cell);		
					
			document.add(table);
			document.close();			
		} catch (Exception ex) {	
			Statusbar.outputError("Error creating PDF",ex.toString());
			return;
		} 
		if(baos==null || baos.size()==0) return;		
		// Send Mail		
		String[] recipients = {m_email};
		SAttachment attachment = new SAttachment("Testprotokoll.pdf",baos.toByteArray(),Constants.PDF_MIME_TYPE);
		try {
			MailSender.postMail(recipients, "Testprotokoll", "Anbei finden sie das Testprotokoll für " + m_name, Constants.TYPE_TEXTHTML, attachment);
		} catch (MessagingException mex) {
			Statusbar.outputError("Error sending Mail",mex.toString());
			return;
		}
		Statusbar.outputSuccess("Email successfully sended");
    } 
    
    
class FieldCell implements PdfPCellEvent{
		
		PdfFormField formField;
		PdfWriter writer;
		int width;
		
		public FieldCell(PdfFormField formField, int width, 
			PdfWriter writer){
			this.formField = formField;
			this.width = width;
			this.writer = writer;
		}
		
		public void cellLayout(PdfPCell cell, Rectangle rect, 
			PdfContentByte[] canvas){
			try{
				// delete cell border
				PdfContentByte cb = canvas[PdfPTable
					.LINECANVAS];
				cb.reset();
				
				formField.setWidget(
					new Rectangle(rect.getLeft(), 
						rect.getBottom(), 
						rect.getLeft()+width, 
						rect.getTop()), 
						PdfAnnotation
						.HIGHLIGHT_NONE);
				
				writer.addAnnotation(formField);
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}
}
