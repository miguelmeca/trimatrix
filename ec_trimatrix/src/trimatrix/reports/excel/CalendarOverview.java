package trimatrix.reports.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclnt.jsfserver.defaultscreens.Statusbar;

import trimatrix.reports.Report;

public class CalendarOverview extends Report {

	@Override
	public String getExtension() {
		return "xlsx";
	}

	@Override
	public String getFilename() {
		return "calendar_overview.xlsx";
	}

	@Override
	public byte[] getContent() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			XSSFWorkbook wb = new XSSFWorkbook();
		    XSSFSheet sheet = wb.createSheet();
		    XSSFRow row = sheet.createRow(0);
		    XSSFCell cell = row.createCell(0);
		    cell.setCellValue("custom XSSF colors");

		    XSSFCellStyle style1 = wb.createCellStyle();
		    style1.setFillForegroundColor(new XSSFColor(new java.awt.Color(128, 0, 128)));
		    style1.setFillPattern(CellStyle.SOLID_FOREGROUND);

		    wb.write(out);
			return out.toByteArray();
		} catch (Exception ex) {
			Statusbar.outputError("Error creating calendar overview!", ex.toString());
		} finally {
			try {
				out.close();
			} catch (IOException e) {
			}
		}
		return new byte[0];
	}

	@Override
	public String getContentType() {
		return "application/xls";
	}

}
