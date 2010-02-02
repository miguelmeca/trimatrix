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

import trimatrix.entities.ResultEntity;
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
			Row row, row2;
			Cell cell;
		    for (ResultEntity.Data item : data.items) {
		    	row = sheet.createRow(actualRow++);
		    	row2 = sheet.createRow(actualRow++);
		    	row.setHeightInPoints(20);
		    	row2.setHeightInPoints(20);
		    	// position
		    	cell = row.createCell(0);
		        cell.setCellValue(item.getFinal_position());
		        cell.setCellStyle(styles.get(Style.CELL_BOLD));
		        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$" + (actualRow-1) + ":$A$" + actualRow));
		        // date
		        cell = row.createCell(1);
		        cell.setCellValue("01.01.2010");
		        cell.setCellStyle(styles.get(Style.CELL));
		        sheet.addMergedRegion(CellRangeAddress.valueOf("$B$" + (actualRow-1) + ":$B$" + actualRow));
		        // competition
		        cell = row.createCell(2);
		        cell.setCellValue(item.getCompetition());
		        cell.setCellStyle(styles.get(Style.CELL));
		        sheet.addMergedRegion(CellRangeAddress.valueOf("$C$" + (actualRow-1) + ":$C$" + actualRow));
		        // category
		        cell = row.createCell(3);
		        cell.setCellValue(item.getCategory_tria());
		        cell.setCellStyle(styles.get(Style.CELL));
		        sheet.addMergedRegion(CellRangeAddress.valueOf("$D$" + (actualRow-1) + ":$D$" + actualRow));
		        // fastest swimsplit
		        cell = row.createCell(4);
		        cell.setCellValue(item.getBest_swim_split());
		        cell.setCellStyle(styles.get(Style.CELL));
		        cell = row2.createCell(4);
		        cell.setCellValue("Haskin");
		        cell.setCellStyle(styles.get(Style.CELL));

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
		public List<ResultEntity.Data> items = new ArrayList<ResultEntity.Data>();
	}
}
