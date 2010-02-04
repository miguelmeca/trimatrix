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
import trimatrix.utils.Helper;

public class PerformanceChart extends Report {
	private static final String[] titles = {
        "Final Position","Date","Competition","Category","Fastest swimsplit","Swim cutoff","Swimsplit/Position", "Behind fastest swimmer","Wetsuit",
        "Fastest runsplit","Run cutoff", "Runsplit/Position", "Behind fastest runner","Comment"};

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
			sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$N$1"));

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
		        //cell.setCellValue(item.getDate().toString());
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
		        cell.setCellValue(item.getBest_swimmer());
		        cell.setCellStyle(styles.get(Style.CELL));
		        // swim cutoff
		        cell = row.createCell(5);
		        cell.setCellValue(item.getSwim_cutoff());
		        cell.setCellStyle(styles.get(Style.CELL));
		        sheet.addMergedRegion(CellRangeAddress.valueOf("$F$" + (actualRow-1) + ":$F$" + actualRow));
		        // swimsplit / position
		        cell = row.createCell(6);
		        cell.setCellValue(item.getSwim_split());
		        if(Constants.RED.equals(item.getSwim_color())) {
		        	cell.setCellStyle(styles.get(Style.CELL_RED));
		        } else if(Constants.GREEN.equals(item.getSwim_color())) {
		        	cell.setCellStyle(styles.get(Style.CELL_GREEN));
		        } else {
		        	cell.setCellStyle(styles.get(Style.CELL));
		        }
		        cell = row2.createCell(6);
		        cell.setCellValue(item.getSwim_pos());
		        cell.setCellStyle(styles.get(Style.CELL));
		        // behind fastest swimmer
		        cell = row.createCell(7);
		        cell.setCellValue(item.getSwim_def());
		        cell.setCellStyle(styles.get(Style.CELL));
		        cell = row2.createCell(7);
		        if(item.getSwim_def_per()!=null) cell.setCellValue(Helper.round(item.getSwim_def_per(),2).toString());
		        cell.setCellStyle(styles.get(Style.CELL));
		        // wetsuit
		        cell = row.createCell(8);
		        if(item.getSwimsuit()) {
		        	cell.setCellStyle(styles.get(Style.CELL_BLACK));
		        } else {
		        	cell.setCellStyle(styles.get(Style.CELL));
		        }
		        sheet.addMergedRegion(CellRangeAddress.valueOf("$I$" + (actualRow-1) + ":$I$" + actualRow));
		        // fastest runsplit
		        cell = row.createCell(9);
		        cell.setCellValue(item.getBest_run_split());
		        cell.setCellStyle(styles.get(Style.CELL));
		        cell = row2.createCell(4);
		        cell.setCellValue(item.getBest_runner());
		        cell.setCellStyle(styles.get(Style.CELL));
		        // run cutoff
		        cell = row.createCell(10);
		        cell.setCellValue(item.getRun_cutoff());
		        cell.setCellStyle(styles.get(Style.CELL));
		        sheet.addMergedRegion(CellRangeAddress.valueOf("$K$" + (actualRow-1) + ":$K$" + actualRow));
		        // runsplit / position
		        cell = row.createCell(11);
		        cell.setCellValue(item.getRun_split());
		        if(Constants.RED.equals(item.getRun_color())) {
		        	cell.setCellStyle(styles.get(Style.CELL_RED));
		        } else if(Constants.GREEN.equals(item.getRun_color())) {
		        	cell.setCellStyle(styles.get(Style.CELL_GREEN));
		        } else {
		        	cell.setCellStyle(styles.get(Style.CELL));
		        }
		        cell = row2.createCell(11);
		        cell.setCellValue(item.getRun_pos());
		        cell.setCellStyle(styles.get(Style.CELL));
		        // behind fastest runner
		        cell = row.createCell(12);
		        cell.setCellValue(item.getRun_def());
		        cell.setCellStyle(styles.get(Style.CELL));
		        cell = row2.createCell(12);
		        if(item.getRun_def_per()!=null) cell.setCellValue(Helper.round(item.getRun_def_per(),2).toString());
		        cell.setCellStyle(styles.get(Style.CELL));
		        // comment
		        cell = row.createCell(13);
		        cell.setCellValue(item.getComment());
		        cell.setCellStyle(styles.get(Style.CELL));
		        sheet.addMergedRegion(CellRangeAddress.valueOf("$N$" + (actualRow-1) + ":$N$" + actualRow));
			}

		    //footer row
		    Row footerRow = sheet.createRow(actualRow);
		    footerRow.setHeightInPoints(20);
		    Cell footerCell = footerRow.createCell(0);
		    footerCell.setCellValue("DNF = Did Not Finish, WC = World Cup, CSR = Championship Race");
		    footerCell.setCellStyle(styles.get(Style.FOOTER));
		    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$" + (actualRow+1) + ":$N$" + (actualRow+1)));

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
