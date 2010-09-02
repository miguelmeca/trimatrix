package trimatrix.logic;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.function.Function2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import trimatrix.db.DAOLayer;
import trimatrix.db.UserPreferences;
import trimatrix.entities.TestEntity;
import trimatrix.logic.helper.Split;
import trimatrix.services.ServiceLayer;
import trimatrix.ui.ScheduleUI;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;
import trimatrix.utils.HelperTime;
import trimatrix.utils.maths.AFunctions.IResult;

public class TestLogic {
	public static final Log logger = LogFactory.getLog(TestLogic.class);

	private DAOLayer daoLayer;
	private ServiceLayer serviceLayer;
	private LogicLayer logicLayer;

	public int getHeightForDia() {
		UserPreferences preferences = serviceLayer.getDictionaryService().getMyUser().getPreferences();
		if(preferences==null || preferences.getHeightTestsDia() == null || preferences.getHeightTestsDia()<=0) return 300; // standard value
		return preferences.getHeightTestsDia();
	}

	public int getWidthForDia() {
		UserPreferences preferences = serviceLayer.getDictionaryService().getMyUser().getPreferences();
		if(preferences==null || preferences.getWidthTestsDia()==null || preferences.getWidthTestsDia()<=0) return 400; // standard value
		return preferences.getWidthTestsDia();
	}

	public void setResultionForDia(int height, int width) {
		UserPreferences preferences = serviceLayer.getDictionaryService().getMyUser().getPreferences();
		if(preferences==null) return;
		preferences.setHeightTestsDia(height);
		preferences.setWidthTestsDia(width);
		try {
			logicLayer.getPreferencesLogic().savePreferences(preferences);
		} catch (Exception ex) {
			logger.error("Resolution couldn't be saved in user preferences", ex);
		}
	}

	public void deleteAllSwimProtocolls(String id) {
		int size = serviceLayer.getSqlExecutorService().deleteAllSwimProtocols(id);
		logger.debug(size + " : Swim protocols deleted!");
	}

	public LactateSamples createLactateSamples() {
		return new LactateSamples();
	}

	public LactateSamples createLactateSamples(String samples) {
		return new LactateSamples(samples);
	}

	public Split createSplit() {
		return new Split();
	}

	public String buildString(Split[] splits, Integer count) {
        if(splits==null || splits.length==0) return Constants.EMPTY;
        StringBuilder result = new StringBuilder();
        for(int i=0;i<count;i++) {
        	if(result.length()>0) result.append(',');
            result.append('{');
            result.append(splits[i].getTime());
            result.append(';');
            result.append(splits[i].getStrokes());
            result.append('}');
        }
        result.insert(0,'[');
        result.append(']');
        return result.toString();
    }

    public Split[] buildArray(String splits, Integer count) {
    	Split[] result = new Split[count];
    	if(splits==null || splits.length()==0) {
      		for(int i=0;i<result.length;i++) {
    			result[i] = new Split();
    		}
        } else {
        	splits = splits.substring(2, splits.length()-2);
            String[] parts = splits.split("\\},\\{");
            result = new Split[count];
            for(int i = 0;i<count;i++) {
            	if(i>=parts.length) {
            		result[i] = new Split();
            	}
                String[] elements = parts[i].split(";");
                String time = elements[0];
                Integer strokes = 0;
                try {
                	strokes = Integer.valueOf(elements[1]);
                } catch (NumberFormatException nfe) {/* strokes set to zero */ }
                result[i] = new Split(time, strokes);
            }
        }
        return result;
    }

	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}

	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}

	public void setLogicLayer(LogicLayer logicLayer) {
		this.logicLayer = logicLayer;
	}

	public class LactateSamples {
		LactateList lactates;
		Double lactate;
		boolean singleValue;

		public LactateSamples() {
			setLactateSamples("0");
		}

		public LactateSamples(String samples) {
			setLactateSamples(samples);
		}

		public void setLactateSamples(String samples) {
			if(samples==null || samples.length()==0) samples = "0";
			// 1. Check if single value
			if (!samples.contains("@")) {
				singleValue = true;
				try {
					lactate = Double.valueOf(samples.replaceAll(",", "."));
				} catch (NumberFormatException nfe) {
					logger.warn("Lactate " + lactate + " is not of type double!");
					lactate = 0d;
				}
				return;
			}
			// 2. Multi values
			singleValue = false;
			lactates = new LactateList();
			Pattern pattern = Pattern.compile("(\\d*[,.]?\\d*@\\d*[,.]?\\d*)");
			Matcher matcher = pattern.matcher(samples);
			while (matcher.find()) {
				String[] parts = matcher.group().split("@");
				Double lactate = 0d;
				Double minutes = 0d;
				try {
					lactate = Double.valueOf(parts[0].replaceAll(",", "."));
				} catch (NumberFormatException nfe) {
					logger.warn("Lactate " + lactate + " is not of type double!");
				}
				try {
					minutes = Double.valueOf(parts[1].replaceAll(",", "."));
				} catch (NumberFormatException nfe) {
					logger.warn("Minutes " + minutes + " is not of type double!");
				}
				lactates.add(new Lactate(minutes, lactate));
			}
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public String getSingleValue() {
			return Helper.getNumberFormat().format(getSingleDoubleValue());
		}

		public Double getSingleDoubleValue() {
			Double lactate;
			if(isSingleValue()) {
				lactate = this.lactate;
			} else {
				lactate = lactates.maxLactate;
			}
			return lactate;
		}

		@Override
		public String toString() {
			StringBuilder result = new StringBuilder();
			if(isSingleValue()) {
				result.append(Helper.getNumberFormat().format(lactate));
			} else {
				for(Lactate lactate : lactates) {
					if(result.length()>0) result.append(Constants.WHITESPACE);
					result.append(lactate.toString());
				}
			}
			return result.toString();
		}

		public class Lactate {
			Double minutes = 0d;
			Double lactate = 0d;

			public Lactate(Double minutes, Double lactate) {
				this.minutes = minutes;
				this.lactate = lactate;
			}

			public Double getMinutes() {
				return minutes;
			}

			public void setMinutes(Double minutes) {
				this.minutes = minutes;
			}

			public Double getLactate() {
				return lactate;
			}

			public void setLactate(Double lactate) {
				this.lactate = lactate;
			}

			@Override
			public String toString() {
				return Helper.getNumberFormat().format(lactate) + "@" + Helper.getNumberFormat().format(minutes);
			}
		}

		public class LactateList extends ArrayList<Lactate> {
			Double maxLactate;
			Double minLactate;
			@Override
			public boolean add(Lactate e) {
				if(!super.add(e)) return false;
				if(maxLactate==null || e.getLactate() > maxLactate) maxLactate = e.getLactate();
				if(minLactate==null || e.getLactate() < minLactate) minLactate = e.getLactate();
				return true;
			}
			public Double getMaxLactate() {
				return maxLactate;
			}
			public Double getMinLactate() {
				return minLactate;
			}
		}
	}

	public byte[] buildDiagram(IResult resultLactate, double[] hrs, double[] hrsLac, int width, int height, String title, String descriptionX, String unitX, String descriptionY, String unitY, boolean highToLow, boolean inverse, boolean lactateHr, boolean time) {
		// constants
		final int GRANULARITY = 10;

		byte[] diagram = null;

		// measuring points
		double[] measurePoints = resultLactate.getXYValues();

		XYSeries seriesPoints = new XYSeries(Helper.getLiteral("measure_points"), false);
		for (int i = 1; i < measurePoints.length; i += 2) {
			if(inverse) {
				seriesPoints.add(measurePoints[i], measurePoints[i - 1]);
			} else {
				seriesPoints.add(measurePoints[i - 1], measurePoints[i]);
			}
		}

		int low = (int) (Math.floor(seriesPoints.getMinX()));
		int high = (int) (Math.ceil(seriesPoints.getMaxX()));
		int grain = (high - low) * GRANULARITY;

		// curve
		Function2D function = null;
		if(inverse) {
			function = resultLactate.getInvFunction2D();
		} else {
			function = resultLactate.getFunction2D();
		}
		XYSeries seriesCurve = DatasetUtilities.sampleFunction2DToSeries(function, low, high, grain, descriptionY);

		// build data set for chart
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(seriesPoints);
		dataset.addSeries(seriesCurve);

		// build chart
		String xAxisLabel = null;
		String yAxisLabel = null;
		if(inverse) {
			yAxisLabel = descriptionX + "[" + unitX + "]";
			xAxisLabel = descriptionY + "[" + unitY + "]";
		} else {
			xAxisLabel = descriptionX + "[" + unitX + "]";
			yAxisLabel = descriptionY + "[" + unitY + "]";
		}
		final JFreeChart chart = ChartFactory.createXYLineChart(title, xAxisLabel, yAxisLabel, dataset, PlotOrientation.VERTICAL, true, true, false);

		// get a reference to the plot for further customization
		final XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		// change low and high
		if (highToLow) {
			plot.getDomainAxis().setInverted(true);
		}

		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesLinesVisible(0, false);
		renderer.setSeriesShapesVisible(1, false);
		plot.setRenderer(renderer);

		if(time) {
			NumberAxis axis = null;
			if(inverse) {
				axis = (NumberAxis)plot.getRangeAxis();
			} else {
				axis = (NumberAxis)plot.getDomainAxis();
			}

			axis.setNumberFormatOverride(new NumberFormat() {

				@Override
				public Number parse(String source, ParsePosition parsePosition) {
					return 0;
				}

				@Override
				public StringBuffer format(long number, StringBuffer toAppendTo, FieldPosition pos) {
					return new StringBuffer(HelperTime.calculateTime((double)number, false));
				}

				@Override
				public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos) {
					return new StringBuffer(HelperTime.calculateTime(number, false));
				}
			});
		}

		// add hr values
		if (hrs !=null && !lactateHr) {
			XYSeries seriesHR = new XYSeries(Helper.getLiteral("hr_txt"));
			for (int i = 1; i < hrs.length; i += 2) {
				if(inverse) {
					seriesHR.add(hrsLac[i - 1], hrsLac[i]);
				} else {
					seriesHR.add(hrs[i - 1], hrs[i]);
				}
			}
			XYSeriesCollection datasetHR = new XYSeriesCollection();
			datasetHR.addSeries(seriesHR);
			ValueAxis axis2 = new NumberAxis("hr_txt");
			plot.setRangeAxis(1, axis2);
			plot.setDataset(1, datasetHR);
			plot.mapDatasetToRangeAxis(1, 1);
			XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer(true, true);
			renderer2.setSeriesPaint(0, Color.black);
			plot.setRenderer(1, renderer2);
		}

		// add some annotations
		// XYTextAnnotation annotation = new XYTextAnnotation(descriptionY,
		// seriesCurve.getMaxX() - 10, high);
		// annotation.setFont(Constants.FONT_SS_PLAIN_9);
		// annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
		// plot.addAnnotation(annotation);

		// Convert to png/binary for UI
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ChartUtilities.writeChartAsPNG(bos, chart, width, height);
			bos.close();
			diagram = bos.toByteArray();
		} catch (Exception ex) {
			Statusbar.outputAlert(Helper.getMessages("problem_diagram"), Helper.getLiteral("error"), ex.toString()).setLeftTopReferenceCentered();
			return null;
		}
		return diagram;
	}

}
