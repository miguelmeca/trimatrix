import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.Map;
import java.util.HashMap;
import java.io.FileOutputStream;

public class ExcelDemo {
	private static final String[] titles = {
        "Final Position","Date","Competition","Category","Fastest swimsplit","Corridor","Swimsplit/Position", "Behind fastest swimmer","Wetsuit",
        "Fastest runsplit", "Runsplit/Position", "Behind fastest runner","Comment"};

private static Object[][] sample_data = {
        {"6","12.09.09","Cold Coast/Australia","Pro W","21:30"  ,"20:00","22:05","00:35","","34:08"  ,"35:05","00:47","Kommentar"},
        {"" ,""        ,"CSR"                 ,""     ,"Haskins","22:00","7"    ,"2,38%","","Dittmer","7"    ,"2,23%",""}
};

public static void main(String[] args) throws Exception {
    Workbook wb = new HSSFWorkbook();

    Map<String, CellStyle> styles = createStyles(wb);

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
    titleCell.setCellStyle(styles.get("title"));
    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$M$1"));

    //header row
    Row headerRow = sheet.createRow(1);
    headerRow.setHeightInPoints(120);
    Cell headerCell;
    for (int i = 0; i < titles.length; i++) {
        headerCell = headerRow.createCell(i);
        headerCell.setCellValue(titles[i]);
        headerCell.setCellStyle(styles.get("header"));
    }

    int rownum = 2;
    for (int i = 0; i < 10; i++) {
        Row row = sheet.createRow(rownum++);
        row.setHeightInPoints(20);
        for (int j = 0; j < titles.length; j++) {
            Cell cell = row.createCell(j);
            cell.setCellStyle(styles.get("cell"));
        }
    }

    //set sample data
    for (int i = 0; i < sample_data.length; i++) {
        Row row = sheet.getRow(2 + i);
        for (int j = 0; j < sample_data[i].length; j++) {
            row.getCell(j).setCellValue((String)sample_data[i][j]);
        }
    }

    //footer row
    Row footerRow = sheet.createRow(12);
    footerRow.setHeightInPoints(20);
    Cell footerCell = footerRow.createCell(0);
    footerCell.setCellValue("DNF = Did Not Finish, WC = World Cup, CSR = Championship Race");
    footerCell.setCellStyle(styles.get("footer"));
    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$13:$M$13"));

    //finally set column widths, the width is measured in units of 1/256th of a character width
    //sheet.setColumnWidth(0, 30*256); //30 characters wide
    for (int i = 0; i < titles.length; i++) {
    	sheet.autoSizeColumn(i);
    }

    // merge cells
    sheet.addMergedRegion(CellRangeAddress.valueOf("$A$3:$A$4"));
    sheet.addMergedRegion(CellRangeAddress.valueOf("$B$3:$B$4"));
    sheet.addMergedRegion(CellRangeAddress.valueOf("$D$3:$D$4"));
    sheet.addMergedRegion(CellRangeAddress.valueOf("$I$3:$I$4"));
    sheet.addMergedRegion(CellRangeAddress.valueOf("$M$3:$M$4"));

    sheet.getRow(2).getCell(5).setCellStyle(styles.get("cell_green"));
    sheet.getRow(3).getCell(5).setCellStyle(styles.get("cell_green"));
    sheet.getRow(2).getCell(6).setCellStyle(styles.get("cell_red"));
    sheet.getRow(2).getCell(7).setCellStyle(styles.get("cell_red"));

    // Write the output to a file
    String file = "testingdata/performance.xls";
    FileOutputStream out = new FileOutputStream(file);
    wb.write(out);
    out.close();
}
	/**
     * Create a library of cell styles
     */
    private static Map<String, CellStyle> createStyles(Workbook wb){
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        CellStyle style;
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short)18);
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(titleFont);
        styles.put("title", style);

        Font footerFont = wb.createFont();
        footerFont.setFontHeightInPoints((short)12);
        footerFont.setColor(IndexedColors.WHITE.getIndex());
        footerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(footerFont);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styles.put("footer", style);

        Font monthFont = wb.createFont();
        monthFont.setFontHeightInPoints((short)11);
        monthFont.setColor(IndexedColors.WHITE.getIndex());
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(CellStyle.VERTICAL_BOTTOM);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(monthFont);
        style.setWrapText(true);
        style.setRotation((short) 90);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styles.put("header", style);

        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setWrapText(true);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styles.put("cell", style);


        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setWrapText(true);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styles.put("cell_green", style);

        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setWrapText(true);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styles.put("cell_red", style);


        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
        styles.put("formula", style);

        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
        styles.put("formula_2", style);

        return styles;
    }
}
