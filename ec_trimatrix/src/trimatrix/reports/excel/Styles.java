package trimatrix.reports.excel;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import trimatrix.utils.Helper;

/**
 * Calls that holds all relevant styles for ecxel reports in a static way
 * @author reich
 *
 */
public class Styles {

	public static enum Style {
		TITLE, FOOTER, HEADER,
		CELL, CELL_NO_WRAP, CELL_BOLD, CELL_RED, CELL_GREEN, CELL_BLACK
	}

	private static Map<Color, XSSFCellStyle> colorStyleMap = new HashMap<Color, XSSFCellStyle>();
	public static void resetStyles() {
		colorStyleMap.clear();
		headerStyle = null;
		headerBoldStyle = null;
	}
	public static XSSFCellStyle getStyleForColor(XSSFWorkbook wb, String strColor) {
		Color background = Color.decode(strColor);
		Color foreground = Color.decode(Helper.getBlackOrWhite(background));
		if(colorStyleMap.containsKey(background)) return colorStyleMap.get(background);
		XSSFCellStyle style = wb.createCellStyle();
		XSSFFont font = wb.createFont();
		font.setBold(true);
		font.setColor(new XSSFColor(foreground));
	    style.setFillForegroundColor(new XSSFColor(background));
	    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	    style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	    style.setFont(font);
	    colorStyleMap.put(background, style);
	    return style;
	}
	private static XSSFCellStyle headerStyle;
	public static XSSFCellStyle getHeaderStyle(XSSFWorkbook wb) {
		if(headerStyle!=null) return headerStyle;
		XSSFCellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(new XSSFColor(Color.LIGHT_GRAY));
	    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	    style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	    headerStyle = style;
	    return style;
	}
	private static XSSFCellStyle headerBoldStyle;
	public static XSSFCellStyle getHeaderBoldStyle(XSSFWorkbook wb) {
		if(headerBoldStyle!=null) return headerBoldStyle;
		XSSFCellStyle style = wb.createCellStyle();
		XSSFFont font = wb.createFont();
		font.setBold(true);
		style.setFillForegroundColor(new XSSFColor(Color.LIGHT_GRAY));
	    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	    style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	    style.setFont(font);
	    headerBoldStyle = style;
	    return style;
	}


	public static Map<Style, CellStyle> createStyles(Workbook wb){
        Map<Style, CellStyle> styles = new HashMap<Style, CellStyle>();

        CellStyle style;
        // Fonts
        Font font_18_b = wb.createFont();
        font_18_b.setFontHeightInPoints((short)18);
        font_18_b.setBoldweight(Font.BOLDWEIGHT_BOLD);

        Font font_12_b = wb.createFont();
        font_12_b.setFontHeightInPoints((short)12);
        font_12_b.setBoldweight(Font.BOLDWEIGHT_BOLD);

        Font font_12_b_white = wb.createFont();
        font_12_b_white.setFontHeightInPoints((short)12);
        font_12_b_white.setColor(IndexedColors.WHITE.getIndex());
        font_12_b_white.setBoldweight(Font.BOLDWEIGHT_BOLD);

        Font font_10_white = wb.createFont();
        font_10_white.setFontHeightInPoints((short)10);
        font_10_white.setColor(IndexedColors.WHITE.getIndex());

        Font font_11_white = wb.createFont();
        font_11_white.setFontHeightInPoints((short)11);
        font_11_white.setColor(IndexedColors.WHITE.getIndex());

        // title
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(font_18_b);
        styles.put(Style.TITLE, style);

        // footer
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(font_12_b_white);
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
        styles.put(Style.FOOTER, style);

        // header
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(CellStyle.VERTICAL_BOTTOM);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(font_11_white);
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
        styles.put(Style.HEADER, style);

        // cell
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
        styles.put(Style.CELL, style);

        // cell no wrap
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setWrapText(false);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styles.put(Style.CELL_NO_WRAP, style);

        // cell_bold
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
        style.setFont(font_12_b);
        styles.put(Style.CELL_BOLD, style);

        // cell green
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFont(font_10_white);
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
        styles.put(Style.CELL_GREEN, style);

        // cell red
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFont(font_10_white);
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
        styles.put(Style.CELL_RED, style);

        // cell black
        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.BLACK.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.WHITE.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.WHITE.getIndex());
        styles.put(Style.CELL_BLACK, style);

        return styles;
    }
}
