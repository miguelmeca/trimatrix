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
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.resources.ResourceManager;

import trimatrix.reports.Report;
import trimatrix.reports.excel.Styles.Style;
import trimatrix.utils.Constants;

public class PerformanceChart extends Report {
	private static final String[] titles = {
        "Final Position","Date","Competition","Category","Fastest swimsplit","Corridor","Swimsplit/Position", "Behind fastest swimmer","Wetsuit",
        "Fastest runsplit", "Runsplit/Position", "Behind fastest runner","Comment"};

	private Data data;

	// disable simple constructor
	private PerformanceChart() {
		throw new UnsupportedOperationException();
	}

	public PerformanceChart(Data data) {
		this.data = data;
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

			// title row
			Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(45);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellValue(ResourceManager.getRuntimeInstance().readProperty(Constants.LITERALS, "performance_chart") + Constants.WHITESPACE + data.name);
			titleCell.setCellStyle(styles.get(Style.TITLE));
			sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$M$1"));

			// header row
			Row headerRow = sheet.createRow(1);
			headerRow.setHeightInPoints(120);
			Cell headerCell;
			for (int i = 0; i < titles.length; i++) {
				headerCell = headerRow.createCell(i);
				headerCell.setCellValue(titles[i]);
				headerCell.setCellStyle(styles.get(Style.HEADER));
			}

			// data rows
			int actualRow = 2;
		    for (Data.Item item : data.items) {
		    	Row row = sheet.createRow(actualRow++);
		    	row.setHeightInPoints(20);
		    	Cell cell = row.createCell(0);
		        cell.setCellValue(item.position);
		        cell.setCellStyle(styles.get(Style.CELL_BOLD));
			}

		    //footer row
		    Row footerRow = sheet.createRow(actualRow);
		    footerRow.setHeightInPoints(20);
		    Cell footerCell = footerRow.createCell(0);
		    footerCell.setCellValue("DNF = Did Not Finish, WC = World Cup, CSR = Championship Race");
		    footerCell.setCellStyle(styles.get(Style.FOOTER));
		    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$" + (actualRow+1) + ":$M$" + (actualRow+1)));

			wb.write(out);
			return out.toByteArray();
		} catch (Exception ex) {
			Statusbar.outputError("Error creating performance chart!", ex.toString());
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

	@Override
	public String getExtension() {
		return "xls";
	}

	@Override
	public String getFilename() {
		return "performance_chart.xls";
	}

	public static class Data {
		public String name;
		public List<Item> items = new ArrayList<Item>();

		public static class Item {
			public String position;
		}
	}
}
