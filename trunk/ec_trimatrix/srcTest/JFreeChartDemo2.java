import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayOutputStream;

import org.eclnt.util.valuemgmt.ValueManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

import trimatrix.utils.maths.RegressionFunctions;
import trimatrix.utils.maths.RegressionFunctions.RegressionResult;

public class JFreeChartDemo2 extends ApplicationFrame {

	/**
	 * A demonstration application showing a normal distribution.
	 * 
	 * @param title
	 *            the frame title.
	 */
	public JFreeChartDemo2(final String title) {

		super(title);
		double[] xyArr__ = { 65, 1.55, 120, 1.64, 131, 1.69, 144, 2.04, 157,
				2.5, 171, 3.29, 179, 6.02, 190, 11.76, 193, 14.75 };
		double[] xyArr = { 120, 0.09, 131, 0.14, 144, 0.49, 157, 0.95, 171,
				1.74, 179, 4.47, 190, 10.21, 193, 13.25 };
		double[] xyArr_ = { 0, 1.55, 8, 1.64, 10, 1.69, 12, 2.04, 14, 2.5, 16,
				3.29, 18, 6.02, 20, 11.76, 22, 14.75 };
		XYSeries series1 = new XYSeries("Messpunkte");
		double offset = 1.55;
		int i = 0;
		series1.add(xyArr[i++], xyArr[i++] + offset);
		series1.add(xyArr[i++], xyArr[i++] + offset);
		series1.add(xyArr[i++], xyArr[i++] + offset);
		series1.add(xyArr[i++], xyArr[i++] + offset);
		series1.add(xyArr[i++], xyArr[i++] + offset);
		series1.add(xyArr[i++], xyArr[i++] + offset);
		series1.add(xyArr[i++], xyArr[i++] + offset);
		series1.add(xyArr[i++], xyArr[i++] + offset);

		RegressionFunctions regression = new RegressionFunctions(
				RegressionFunctions.EXP_REGRESSION, xyArr, offset);
		RegressionResult result = regression.getResult();
		System.out.println(result.getFormel());
		XYSeries series2 = DatasetUtilities.sampleFunction2DToSeries(result.getFunction2D(), 120, 200, 80, "Laktat");
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);

		final JFreeChart chart = ChartFactory.createXYLineChart(
				"Leistungstest", "Herzfrequenz[1/min]", "Laktat[mmol/l]",
				dataset, PlotOrientation.VERTICAL, true, true, false);

		// get a reference to the plot for further customisation...
		final XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.lightGray);
		// plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesLinesVisible(0, false);
		renderer.setSeriesShapesVisible(1, false);
		plot.setRenderer(renderer);

		// add some annotations...
		XYTextAnnotation annotation = null;
		Font font = new Font("SansSerif", Font.PLAIN, 9);
		annotation = new XYTextAnnotation("Laktatkurve", 190, 20);
		annotation.setFont(font);
		annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
		plot.addAnnotation(annotation);

		XYPointerAnnotation annotation1 = new XYPointerAnnotation(
				"Messpunkt 1", 120, 1.64, -Math.PI / 4.0);
		annotation1.setTextAnchor(TextAnchor.TOP_LEFT);
		annotation1.setPaint(Color.black);
		annotation1.setArrowPaint(Color.red);
		renderer.addAnnotation(annotation1);
	
		// Convert to png/binary for UI
        try {
        	ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ChartUtilities.writeChartAsPNG(bos, chart, 400, 300);
			bos.close();
			byte[] image = bos.toByteArray();
	        String strImage = ValueManager.encodeHexString(image);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
        
        

		
		
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);

	}

	// ****************************************************************************
	// * JFREECHART DEVELOPER GUIDE *
	// * The JFreeChart Developer Guide, written by David Gilbert, is available
	// *
	// * to purchase from Object Refinery Limited: *
	// * *
	// * http://www.object-refinery.com/jfreechart/guide.html *
	// * *
	// * Sales are used to provide funding for the JFreeChart project - please *
	// * support us so that we can continue developing free software. *
	// ****************************************************************************

	/**
	 * Starting point for the demonstration application.
	 * 
	 * @param args
	 *            ignored.
	 */
	public static void main(final String[] args) {

		final JFreeChartDemo2 demo = new JFreeChartDemo2(
				"Leistungstestprotokoll");
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);

	}

}