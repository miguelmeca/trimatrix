import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.CustomXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.urls.StandardXYURLGenerator;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class JFreeDemo3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws ParseException {
	//  Create a sample dataset
        final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        final XYSeries dataSeries = new XYSeries("Curve data");
        final ArrayList toolTips = new ArrayList();
        dataSeries.add(sdf.parse("01-Jul-2002").getTime(), 5.22);
        toolTips.add("1D - 5.22");
        dataSeries.add(sdf.parse("02-Jul-2002").getTime(), 5.18);
        toolTips.add("2D - 5.18");
        dataSeries.add(sdf.parse("03-Jul-2002").getTime(), 5.23);
        toolTips.add("3D - 5.23");
        dataSeries.add(sdf.parse("04-Jul-2002").getTime(), 5.15);
        toolTips.add("4D - 5.15");
        dataSeries.add(sdf.parse("05-Jul-2002").getTime(), 5.22);
        toolTips.add("5D - 5.22");
        dataSeries.add(sdf.parse("06-Jul-2002").getTime(), 5.25);
        toolTips.add("6D - 5.25");
        dataSeries.add(sdf.parse("07-Jul-2002").getTime(), 5.31);
        toolTips.add("7D - 5.31");
        dataSeries.add(sdf.parse("08-Jul-2002").getTime(), 5.36);
        toolTips.add("8D - 5.36");
        final XYSeriesCollection xyDataset = new XYSeriesCollection(dataSeries);
        final CustomXYToolTipGenerator ttg = new CustomXYToolTipGenerator();
        ttg.addToolTipSeries(toolTips);

        //  Create the chart
        final StandardXYURLGenerator urlg = new StandardXYURLGenerator("xy_details.jsp");
        final ValueAxis timeAxis = new DateAxis("");
        final NumberAxis valueAxis = new NumberAxis("");
        valueAxis.setAutoRangeIncludesZero(false);  // override default
        final XYPlot plot = new XYPlot(xyDataset, timeAxis, valueAxis, null);
        final StandardXYItemRenderer sxyir = new StandardXYItemRenderer(
            StandardXYItemRenderer.LINES + StandardXYItemRenderer.SHAPES,
            ttg, urlg);
        sxyir.setShapesFilled(true);
        plot.setRenderer(sxyir);
        final JFreeChart chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
        chart.setBackgroundPaint(java.awt.Color.white);

        // ****************************************************************************
        // * JFREECHART DEVELOPER GUIDE                                               *
        // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
        // * to purchase from Object Refinery Limited:                                *
        // *                                                                          *
        // * http://www.object-refinery.com/jfreechart/guide.html                     *
        // *                                                                          *
        // * Sales are used to provide funding for the JFreeChart project - please    * 
        // * support us so that we can continue developing free software.             *
        // ****************************************************************************
        
        // save it to an image
        try {
            final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
            final File file1 = new File("xychart100.png");
            ChartUtilities.saveChartAsPNG(file1, chart, 600, 400, info);

            // write an HTML page incorporating the image with an image map
            final File file2 = new File("xychart100.html");
            final OutputStream out = new BufferedOutputStream(new FileOutputStream(file2));
            final PrintWriter writer = new PrintWriter(out);
            writer.println("<HTML>");
            writer.println("<HEAD><TITLE>JFreeChart Image Map Demo</TITLE></HEAD>");
            writer.println("<BODY>");
//            ChartUtilities.writeImageMap(writer, "chart", info);
            writer.println("<IMG SRC=\"xychart100.png\" "
                           + "WIDTH=\"600\" HEIGHT=\"400\" BORDER=\"0\" USEMAP=\"#chart\">");
            writer.println("</BODY>");
            writer.println("</HTML>");
            writer.close();

        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
        return;
	}

}
