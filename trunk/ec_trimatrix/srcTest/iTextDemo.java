import java.awt.Color;
import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
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


public class iTextDemo {
	public iTextDemo()throws Exception{
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, 
			new FileOutputStream("TextFieldForm.pdf"));
		document.open();
		
		PdfPTable table = new PdfPTable(2);
		table.getDefaultCell().setPadding(5f);
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		int[] widths = {180,450};
		table.setWidths(widths);
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
				PdfContentByte cb = canvas[PdfPTable.LINECANVAS];
				cb.reset();
				
				formField.setWidget(
					new Rectangle(rect.getLeft(), 
						rect.getBottom(), 
						rect.getLeft()+width, 
						rect.getTop()), 
						PdfAnnotation.HIGHLIGHT_NONE);				
				writer.addAnnotation(formField);
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}

	public static void main(String[] args) {
		try{
			iTextDemo demo = new iTextDemo();
		}catch(Exception e){
			System.out.println(e);
		}
	}

}
