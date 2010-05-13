package trimatrix.reports.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.impl.SCHEDULEITEMComponentTag;

import trimatrix.reports.Report;
import trimatrix.ui.ScheduleUI;
import trimatrix.ui.ScheduleUI.ScheduleItem;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;

public class CalendarOverview extends Report {

	private final String header[] = {Helper.getLiteral("cw"), Helper.getLiteral("monday"), Helper.getLiteral("tuesday"), Helper.getLiteral("wednesday"),
			Helper.getLiteral("thursday"), Helper.getLiteral("friday"), Helper.getLiteral("saturday"), Helper.getLiteral("sunday")
	};
	private String header2[] = new String[8];
	private List<ScheduleItem> scheduleItems;

	private static final int rows = 5 + ScheduleUI.getNumberOfBlocks();
	private static final int hours = ScheduleUI.getNumberOfBlocks() / 2;
	private static final int cols = 8;

	private String time[] = new String[hours];

	public CalendarOverview(Integer weekNumber, String[] day, List<ScheduleItem> scheduleItems) {
		header2[0] = weekNumber.toString();
		header2[1] = day[0];
		header2[2] = day[1];
		header2[3] = day[2];
		header2[4] = day[3];
		header2[5] = day[4];
		header2[6] = day[5];
		header2[7] = day[6];
		this.scheduleItems = this.scheduleItems;
		// build time column
		int startingHour = ScheduleUI.getStartingHour();
		for(int i = 0;i<hours;i++) {
			time[i] = startingHour + ":00";
			startingHour++;
		}

	}

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
			int x, y;

			String time[] =

			{"05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00",
					"14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"
			};

			XSSFWorkbook wb = new XSSFWorkbook();
		    XSSFSheet sheet = wb.createSheet(header2[0]);

		    // build cells matrix
		    XSSFCell[][] scheduleMatrix = new XSSFCell[rows][cols];
		    for(x = 1; x<=rows; x++) {
		    	XSSFRow row = sheet.createRow(x-1);
		    	for(y = 1; y<=cols; y++) {
		    		XSSFCell cell = row.createCell(y-1);
		    		scheduleMatrix[x-1][y-1] = cell;
		    	}
		    }

		    // set headers
		    for(x = 0;x<cols;x++) {
		    	scheduleMatrix[0][x].setCellValue(header[x]);
		    	scheduleMatrix[1][x].setCellValue(header2[x]);
		    }
		    // set time
		    y = 0;
		    for(x = 2;x<37;x=x+2) {
		    	scheduleMatrix[x][0].setCellValue(time[y]);
		    	y++;
		    }

		    // set schedule items
		    for (ScheduleItem item : scheduleItems) {
				String background = item.getColor();
				String foreground = Helper.getBlackOrWhite(background);
				x = item.getStartWeekDay() + 1;
				y = 5;
				scheduleMatrix[x][y].setCellValue(item.getDescription());
//				st.setScheduleleft(item.getStartInMinutes() + "");
//				st.setSchedulewidth(expressionBase + "ScheduleUI.scheduleItems["
//						+ counter + "].duration}");
//				st.setText("\n" + getLogic().getScheduleLogic().getSummary(item.schedule));
//				String icon = (item.getDone()==null || item.getDone()==false) ?  Constants.ACCEPT_LIGHT : Constants.ACCEPT;
//				st.setBgpaint("image(0,0,/images/icons/accept.png,lefttop);roundedborder(0,0,100%,100%,10,10," + background
//						+ ",2);rectangle(0,0,100%,16," + background
//						+ ");write(20,0," + item.getTypeDesc() + ",12," + foreground
//						+ ",bold,lefttop);image(0,0," + icon + ",lefttop)");
//				st.setBackground(item.getColor() + "60"); // Add Transparency
//				st.setForeground(foreground);
//				st.setPopupmenu("SCHEDULEITEM");
//				st.setActionListener(expressionBase + "ScheduleUI.scheduleItems["
//						+ counter + "].onScheduleItemAction}");
//				st.setDragsend("schedule:" + item.getId());
//				// st.setFlushonselect("true");
//				st.setInvokeevent("doubleclick");
//				schedule.getChildren().add(st.createBaseComponent());
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
