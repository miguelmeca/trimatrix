package trimatrix.reports.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.eclnt.jsfserver.bufferedcontent.DefaultBufferedContent;
import org.eclnt.jsfserver.defaultscreens.Statusbar;

import trimatrix.entities.IEntityData;
import trimatrix.reports.excel.Styles.Style;

public class PerformanceChart extends DefaultBufferedContent {
	
	// disable simple constructor	
	private PerformanceChart() {
		throw new UnsupportedOperationException();
	}
	
	public PerformanceChart(IEntityData data) {
		
	}
		
	@Override
	public byte[] getContent() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			Workbook wb = new HSSFWorkbook();
			Map<Styles.Style, CellStyle> styles = Styles.createStyles(wb);

		    Sheet sheet = wb.createSheet("Performance Chart");
		    PrintSetup printSetup = sheet.getPrintSetup();
		    printSetup.setLandscape(true);
		    sheet.setFitToPage(true);
		    sheet.setHorizontallyCenter(true);

		    //title row
		    Row titleRow = sheet.createRow(0);
		    titleRow.setHeightInPoints(45);
		    Cell titleCell = titleRow.createCell(0);
		    titleCell.setCellValue("Performance Chart Markus Reich");
		    titleCell.setCellStyle(styles.get(Style.TITLE));
		    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$M$1"));
			wb.write(out);
			return out.toByteArray();
		} catch (Exception ex) {
			Statusbar.outputError("Error creating performance chart!", ex.toString());
		} finally {try {out.close();} catch (IOException e) {}}
		return new byte[0];	
	}

	@Override
	public String getContentType() {
		return "application/xls";
	}	
}
