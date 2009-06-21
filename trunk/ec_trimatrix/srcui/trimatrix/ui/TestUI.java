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
import trimatrix.utils.RegressionFunctions;
import trimatrix.utils.RegressionFunctions.RegressionResult;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseField;
import com.lowagie.text.pdf.PdfAnnotation;
import com.lowagie.text.pdf.PdfBorderDictionary;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfFormField;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPCellEvent;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.TextField;

@SuppressWarnings("serial")
@CCGenClass (expressionBase="#{d.TestUI}")

public class TestUI extends MyWorkpageDispatchedBean implements Serializable
{
    protected RegressionFunctions regression;
	
	protected String m_diagram;
    public String getDiagram() { return m_diagram; }
    public void setDiagram(String value) { m_diagram = value; }

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
		super(dispatcher);
    }
    
    public void onCalculate(ActionEvent event) {
    	if(regression==null) return;
    	if(m_speed==null) return;
    	Double speed = null;
    	try {
    		speed = Double.valueOf(m_speed);
    	} catch (Exception ex) {
    		Statusbar.outputError("Speed is not valid", ex.toString());
    		return;
    	}
    	if(speed==null) return;
    	m_lactat = String.valueOf(regression.getY(speed));    	
    }

    public void onRefreshDiagram(ActionEvent event) {
    	// paint chart
		double[] xyArr = { 120, 0.09, 131, 0.14, 144, 0.49, 157, 0.95, 171, 1.74, 179, 4.47, 190, 10.21, 193, 13.25 };

		XYSeries series1 = new XYSeries("Messpunkte");
		double offset = 1.55;
		int i = 0;
		series1.add(xyArr[i++], xyArr[i++] + offset);
		series1.add(xyArr[i++], xyArr[i++] + offset);
		series1.add(xyArr[i++], xyArr[i++] + offset);
		series1.add(xyArr[i++], xyArr[i++] + offset);
		series1.add(xyArr[i++], xyArr[i++] + offset);
		series1.add(xyArr[i++], xyArr[i++] + offset);
		series1.add(xyArr[i++], xyArr[i++] + offset);
		series1.add(xyArr[i++], xyArr[i++] + offset);

		regression = new RegressionFunctions(RegressionFunctions.EXP_REGRESSION, xyArr, offset);
		RegressionResult result = regression.getResult();
		m_formula = result.getFormel();
		XYSeries series2 = DatasetUtilities.sampleFunction2DToSeries(regression
				.getRegressionFunction2D(), 120, 200, 80, "Laktat");
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);

		final JFreeChart chart = ChartFactory.createXYLineChart(
				"Leistungstest", "Herzfrequenz[1/min]", "Laktat[mmol/l]",
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
			ChartUtilities.writeChartAsPNG(bos, chart, 600, 400);
			bos.close();
			byte[] image = bos.toByteArray();
	        m_diagram = ValueManager.encodeHexString(image);
		} catch (Exception ex) {
			Statusbar.outputError("Error creating Diagram",ex.toString());
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
				new Rectangle(0,0,150,10), "speed");
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
			
			// heart rate
			table.addCell("Herzfrequenz"); 
			TextField hrField = new TextField(writer, 
				new Rectangle(0,0,150,10), "hr");
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
			
			// lactate
			table.addCell("Laktat"); 
			TextField lacField = new TextField(writer, 
				new Rectangle(0,0,150,10), "hr");
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
			MailSender.postMail(recipients, "Testprotokoll", "Anbei finden sie das Testprotokoll für " + m_name, attachment);
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
