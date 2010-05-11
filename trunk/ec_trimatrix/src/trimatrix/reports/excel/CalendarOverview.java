package trimatrix.reports.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclnt.jsfserver.defaultscreens.Statusbar;

import trimatrix.reports.Report;
import trimatrix.utils.Helper;

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
			String calWeek = "19";
			int rows = 41;
			int cols = 8;

			String header[] = {Helper.getLiteral("cw"), Helper.getLiteral("monday"), Helper.getLiteral("tuesday"), Helper.getLiteral("wednesday"),
					Helper.getLiteral("thursday"), Helper.getLiteral("friday"), Helper.getLiteral("saturday"), Helper.getLiteral("sunday")
			};
			String header2[] = {calWeek.toString(), Helper.formatDate(new Date(), "dd.MM."), Helper.formatDate(new Date(), "dd.MM."),
					Helper.formatDate(new Date(), "dd.MM."), Helper.formatDate(new Date(), "dd.MM."), Helper.formatDate(new Date(), "dd.MM."),
					Helper.formatDate(new Date(), "dd.MM."), Helper.formatDate(new Date(), "dd.MM.")
			};

			String time[] = {"05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00",
					"14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"
			};

			XSSFWorkbook wb = new XSSFWorkbook();
		    XSSFSheet sheet = wb.createSheet(calWeek);

		    // build cells matrix
		    XSSFCell[][] scheduleMatrix = new XSSFCell[rows][cols];
		    for(int x = 1; x<=rows; x++) {
		    	XSSFRow row = sheet.createRow(x-1);
		    	for(int y = 1; y<=cols; y++) {
		    		XSSFCell cell = row.createCell(y-1);
		    		scheduleMatrix[x-1][y-1] = cell;
		    	}
		    }

		    // set headers
		    for(int x = 0;x<cols;x++) {
		    	scheduleMatrix[0][x].setCellValue(header[x]);
		    	scheduleMatrix[1][x].setCellValue(header2[x]);
		    }
		    // set time
		    int y = 0;
		    for(int x = 2;x<37;x=x+2) {
		    	scheduleMatrix[x][0].setCellValue(time[y]);
		    	y++;
		    }

		    //cell.setCellStyle(Styles.getStyleForColor(wb, new Color(128, 0, 128)));

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
