package trimatrix.ui;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.bufferedcontent.BufferedContentMgr;
import org.eclnt.jsfserver.bufferedcontent.DefaultBufferedContent;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoCancelListener;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.elements.impl.FIXGRIDTreeBinding;
import org.eclnt.jsfserver.elements.impl.FIXGRIDTreeItem;
import org.eclnt.jsfserver.elements.util.Trigger;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.util.valuemgmt.ValueManager;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.eclnt.workplace.WorkpageDefaultLifecycleListener;
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

import trimatrix.db.Doctors;
import trimatrix.db.Persons;
import trimatrix.db.Tests;
import trimatrix.db.TestsAnalysis;
import trimatrix.db.TestsErgo;
import trimatrix.db.TestsProtocol;
import trimatrix.db.TestsSwim;
import trimatrix.db.TestsSwimProtocol;
import trimatrix.db.TestsSwimProtocolId;
import trimatrix.db.TestsTreadmill;
import trimatrix.db.Zones;
import trimatrix.db.ZonesDefinition;
import trimatrix.entities.TestEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.logic.ZonesLogic;
import trimatrix.logic.TestLogic.LactateSamples;
import trimatrix.logic.helper.Split;
import trimatrix.reports.excel.CalendarOverview;
import trimatrix.ui.utils.ISelectionCallback;
import trimatrix.ui.utils.WorkpageRefreshEvent;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;
import trimatrix.utils.HelperTime;
import trimatrix.utils.Constants.Entity;
import trimatrix.utils.Constants.Mode;
import trimatrix.utils.json.DoubleList;
import trimatrix.utils.json.IntegerList;
import trimatrix.utils.maths.PolynomialFunctions;
import trimatrix.utils.maths.RegressionFunctions;
import trimatrix.utils.maths.UnivariateRealFunctions;
import trimatrix.utils.maths.AFunctions.IResult;

@CCGenClass(expressionBase = "#{d.TestDetailUI}")
public class TestDetailUI extends AEntityDetailUI implements Serializable {
    protected int m_tabIndex;
    public int getTabIndex() { return m_tabIndex; }
    public void setTabIndex(int value) { m_tabIndex = value; }

	protected final String[] MANDATORY_FIELDS_SWIM = new String[] { TestEntity.DISTANCE, TestEntity.SPLITS };
	protected final String[] MANDATORY_FIELDS_TREADMILL = new String[] { TestEntity.SPEED_INIT, TestEntity.INCLINE_INIT };
	protected final String[] MANDATORY_FIELDS_ERGO = new String[] { TestEntity.CADENCE_LOW, TestEntity.CADENCE_HIGH, TestEntity.POWER_INIT, TestEntity.POWER_STEP };

	private boolean isDirtySwimProtocol = false;
	private String personId;

	public void onDoctorSearch(ActionEvent event) {
		IEntitySelectionUI entitySelectionUI = getEntitySelectionUI(Constants.Entity.DOCTOR);
		entitySelectionUI.buildData(Entity.DOCTOR);
		entitySelectionUI.prepareCallback(new ISelectionCallback() {
			public void cancel() {
				m_popup.close();
			}

			public void selected(String id) {
				Doctors doctor = (Doctors) ENTITYLISTLOGIC.get(Constants.Entity.DOCTOR, id);
				entity.setDoctor(doctor);
				setDoctorDescription(entity);
				m_popup.close();
			}
		});
		m_popup = getWorkpage().createModalPopupInWorkpageContext();
		m_popup.setLeftTopReferenceCentered();
		m_popup.open(Constants.Page.DOCTORSELECTION.getUrl(), Helper.getLiteral("doctor_search"), 800, 600, this);
	}

	public void onAthleteSearch(ActionEvent event) {
		IEntitySelectionUI entitySelectionUI = getEntitySelectionUI(Constants.Entity.PERSON);
		entitySelectionUI.buildData(Entity.MYATHLETES);
		entitySelectionUI.prepareCallback(new ISelectionCallback() {
			public void cancel() {
				m_popup.close();
			}

			public void selected(String id) {
				Persons person = (Persons) ENTITYLISTLOGIC.get(Constants.Entity.PERSON, id);
				entity.setAthlete(person);
				setAthleteDescription(entity);
				m_popup.close();
			}
		});
		m_popup = getWorkpage().createModalPopupInWorkpageContext();
		m_popup.setLeftTopReferenceCentered();
		m_popup.open(Constants.Page.PERSONSELECTION.getUrl(), Helper.getLiteral("athlete_search"), 800, 600, this);
	}

	public void onTypeChange(ActionEvent event) {
		entity.setType((String) values.get(TestEntity.TYPE));
	}

	public void onTimeFlush(ActionEvent event) {
		// get clientname to separate by source
		String clientname = (String) event.getComponent().getAttributes().get(Constants.CLIENTNAME);
		String field;
		if(TestEntity.TREADMILL_STEP_TIME.equalsIgnoreCase(clientname)) {
			field = TestEntity.TREADMILL_STEP_TIME;
		} else if(TestEntity.ERGO_STEP_TIME.equalsIgnoreCase(clientname)) {
			field = TestEntity.ERGO_STEP_TIME;
		} else {
			return;
		}
		// correct input
		String input = (String)values.get(field);
		String corrInput = HelperTime.correctTimeInputShort(input);
		// if null don't write back the value
		if(corrInput==null) return;
		values.put(field, corrInput);
	}

	private Tests entity;

	public TestDetailUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher, new String[] { TestEntity.PERSON, TestEntity.TYPE, TestEntity.DATE, TestEntity.DESCRIPTION}, true);
		// create buffered content
		diagramBufferedContent = new DiagramBufferedContent();
        BufferedContentMgr.add(diagramBufferedContent);
        getWorkpage().addLifecycleListener(new WorkpageDefaultLifecycleListener()
        {
            public void reactOnDestroyed()
            {
                super.reactOnDestroyed();
                BufferedContentMgr.remove(diagramBufferedContent);
            }
        });
		// get wrapping entity detail UI bean
		entityDetailUI = getEntityDetailUI();
		entityDetailUI.setEntityDetailUI(this);
		// set resultion
		setHeight(getLogic().getTestLogic().getHeightForDia());
		setWidth(getLogic().getTestLogic().getWidthForDia());
		// person
		personId = getWorkpage().getParam(Constants.P_PERSON);
		// init data
		init(entityDetailUI.getEntityObject());
		// labeling
		setLabelRowDynamic();
	}

	protected ValidValuesBinding m_testTypesVvb = getServiceLayer().getValueListBindingService().getVVBinding(Constants.ValueList.TESTTYPE);

	public ValidValuesBinding getTestTypesVvb() {
		return m_testTypesVvb;
	}

	public void init(Object entityObject) {
		// set entity object
		this.entity = (Tests) entityObject;
		// set enabled state and set fields
		init();
	}

	public void init() {
		// set back dirty flag for swim protocols
		isDirtySwimProtocol = false;
		// set fields
		fillMaps();
		// set state
		setState();
		// analysis
		if(isAnalysis()) {
			onRefresh(null);
		}
		// prefill athlete
		if(mode==Mode.NEW) {
			if(!Helper.isEmpty(personId)) {
				Persons person = (Persons) ENTITYLISTLOGIC.get(Constants.Entity.PERSON, personId);
				entity.setAthlete(person);
				setAthleteDescription(entity);
			}
		}
	}

	@Override
	public void prepareSave() {
		// ergo
		if (entity.getTestsErgo() == null && isErgo()) {
			// create ergo
			TestsErgo ergo = new TestsErgo(entity.getId());
			// getDaoLayer().getTestsErgoDAO().save(ergo);
			entity.setTestsErgo(ergo);
			// delete treadmill
			if (entity.getTestsTreadmill() != null) {
				getDaoLayer().getTestsTreadmillDAO().delete(entity.getTestsTreadmill());
				entity.setTestsTreadmill(null);
			}
			// delete swim
			if (entity.getTestsSwim() != null) {
				getDaoLayer().getTestsSwimDAO().delete(entity.getTestsSwim());
				entity.setTestsSwim(null);
			}
			return;
		}
		// treadmill
		if (entity.getTestsTreadmill() == null && isTreadmill()) {
			// create treadmill
			TestsTreadmill treadmill = new TestsTreadmill(entity.getId());
			// getDaoLayer().getTestsTreadmillDAO().save(treadmill);
			entity.setTestsTreadmill(treadmill);
			// delete ergo
			if (entity.getTestsErgo() != null) {
				getDaoLayer().getTestsErgoDAO().delete(entity.getTestsErgo());
				entity.setTestsErgo(null);
			}
			// delete swim
			if (entity.getTestsSwim() != null) {
				getDaoLayer().getTestsSwimDAO().delete(entity.getTestsSwim());
				entity.setTestsSwim(null);
			}
			return;
		}
		// swim
		if (entity.getTestsSwim() == null && isSwim()) {
			// create swim
			TestsSwim swim = new TestsSwim(entity.getId());
			// getDaoLayer().getTestsSwimDAO().save(swim);
			entity.setTestsSwim(swim);
			// delete treadmill
			if (entity.getTestsTreadmill() != null) {
				getDaoLayer().getTestsTreadmillDAO().delete(entity.getTestsTreadmill());
				entity.setTestsTreadmill(null);
			}
			// delete ergo
			if (entity.getTestsErgo() != null) {
				getDaoLayer().getTestsErgoDAO().delete(entity.getTestsErgo());
				entity.setTestsErgo(null);
			}
			return;
		}

		// delete swim protocolls
		// TODO move to logic e.g. TestLogic
		if (isDirtySwimProtocol) {
			getLogic().getTestLogic().deleteAllSwimProtocolls(entity.getId());
			isDirtySwimProtocol = false;
		}
	}

	public void validate() throws MandatoryCheckException, EmailNotValidException {
		// mandatory check
		checkMandatory();
		// details mandatory fields
		checkDetailGrid();
		// protocols mandatory fields
		if (isProtocol()) {
			checkProtocolGrid();
		}
		// fill values to entities properties
		fillEntityProperties();
	}

	private void fillEntityProperties() {
		// detail
		entity.setDescription((String) values.get(TestEntity.DESCRIPTION));
		Timestamp timestamp = null;
		Date date = (Date) values.get(TestEntity.DATE);
		if (date != null) {
			timestamp = new Timestamp(date.getTime());
		}
		entity.setDate(timestamp);
		entity.setType((String) values.get(TestEntity.TYPE));
		setAthleteDescription(entity);
		setDoctorDescription(entity);
		// ergo
		if (isErgo()) {
			TestsErgo ergo = entity.getTestsErgo();
			if(ergo==null) {
				ergo = new TestsErgo(entity.getId());
				entity.setTestsErgo(ergo);
			}
			ergo.setPowerInit((Integer) values.get(TestEntity.POWER_INIT));
			ergo.setPowerStep((Integer) values.get(TestEntity.POWER_STEP));
			ergo.setCadenceLow((Integer) values.get(TestEntity.CADENCE_LOW));
			ergo.setCadenceHigh((Integer) values.get(TestEntity.CADENCE_HIGH));
			ergo.setStepTime((String) values.get(TestEntity.ERGO_STEP_TIME));
		}
		// treadmill
		if (isTreadmill()) {
			TestsTreadmill treadmill = entity.getTestsTreadmill();
			if(treadmill==null) {
				treadmill = new TestsTreadmill(entity.getId());
				entity.setTestsTreadmill(treadmill);
			}
			treadmill.setSpeedVariable((Boolean) values.get(TestEntity.SPEED_VARIABLE));
			treadmill.setSpeedInit((Double) values.get(TestEntity.SPEED_INIT));
			treadmill.setSpeedStep((Double) values.get(TestEntity.SPEED_STEP));
			treadmill.setInclineVariable((Boolean) values.get(TestEntity.INCLINE_VARIABLE));
			treadmill.setInclineInit((Integer) values.get(TestEntity.INCLINE_INIT));
			treadmill.setInclineStep((Integer) values.get(TestEntity.INCLINE_STEP));
			treadmill.setStepTime((String) values.get(TestEntity.TREADMILL_STEP_TIME));
		}
		// swim
		if (isSwim()) {
			TestsSwim swim = entity.getTestsSwim();
			if(swim==null) {
				swim = new TestsSwim(entity.getId());
				entity.setTestsSwim(swim);
			}
			swim.setAssistantName((String) values.get(TestEntity.ASSISTANT_NAME));
			swim.setBaths((String) values.get(TestEntity.BATHS));
			swim.setPool((String) values.get(TestEntity.POOL));
			Timestamp timestamp2 = null;
			Date date2 = (Date) values.get(TestEntity.DATE2);
			if (date2 != null) {
				timestamp2 = new Timestamp(date2.getTime());
			}
			swim.setDate2(timestamp2);
			swim.setDistance((Integer) values.get(TestEntity.DISTANCE));
			swim.setSplits((Integer) values.get(TestEntity.SPLITS));
		}
		// protocol
		if (isProtocol()) {
			TestsProtocol protocol = entity.getTestsProtocol();
			protocol.setDescription((String) values.get(TestEntity.PROTOCOL_DESCRIPTION));
			protocol.setModel((String) values.get(TestEntity.MODEL));
			protocol.setModelLactate((String) values.get(TestEntity.MODEL_LACTATE));
			protocol.setModelSpiro((String) values.get(TestEntity.MODEL_SPIRO));
			protocol.setPerformanceMax((String) values.get(TestEntity.PERFORMANCE_MAX));
			if (isSwim()) {
				fillSwimProtocol();
			} else {
				fillProtocol();
			}
		}
		// analysis
		if (isAnalysis()) {
			TestsAnalysis analysis = entity.getTestsAnalysis();
			analysis.setFunction((String) values.get(TestEntity.ANALYSIS_FUNCTION));
			Double analysisOffset = (Double) values.get(TestEntity.ANALYSIS_OFFSET);
			analysis.setOffset(analysisOffset==null?0d:analysisOffset);
			String analysisDegreeStr = (String) values.get(TestEntity.ANALYSIS_DEGREE);
			analysis.setDegree((analysisDegreeStr==null?0:Integer.valueOf(analysisDegreeStr)));
			Boolean analysisLactateHr = (Boolean) values.get(TestEntity.ANALYSIS_LACTATE_HR);
			analysis.setLactateHr(analysisLactateHr==null?false:analysisLactateHr);
		}
	}

	private void fillMaps() {
		// add values of fields
		values.clear();
		values.put(TestEntity.DESCRIPTION, entity.getDescription());
		Date date = null;
		Timestamp timestamp = entity.getDate();
		if (timestamp != null) {
			date = new Date(timestamp.getTime());
		}
		values.put(TestEntity.DATE, date);
		values.put(TestEntity.TYPE, entity.getType());
		setAthleteDescription(entity);
		setDoctorDescription(entity);

		// ergo
		TestsErgo ergo = entity.getTestsErgo();
		if (ergo != null) {
			values.put(TestEntity.POWER_INIT, ergo.getPowerInit());
			values.put(TestEntity.POWER_STEP, ergo.getPowerStep());
			values.put(TestEntity.CADENCE_LOW, ergo.getCadenceLow());
			values.put(TestEntity.CADENCE_HIGH, ergo.getCadenceHigh());
			values.put(TestEntity.ERGO_STEP_TIME, ergo.getStepTime());
		}

		// treadmill
		TestsTreadmill treadmill = entity.getTestsTreadmill();
		if (treadmill != null) {
			values.put(TestEntity.SPEED_VARIABLE, treadmill.getSpeedVariable());
			values.put(TestEntity.SPEED_INIT, treadmill.getSpeedInit());
			values.put(TestEntity.SPEED_STEP, treadmill.getSpeedStep());
			values.put(TestEntity.INCLINE_VARIABLE, treadmill.getInclineVariable());
			values.put(TestEntity.INCLINE_INIT, treadmill.getInclineInit());
			values.put(TestEntity.INCLINE_STEP, treadmill.getInclineStep());
			values.put(TestEntity.TREADMILL_STEP_TIME, treadmill.getStepTime());
		} else {
			// reset checkboxes for gui update
			values.put(TestEntity.INCLINE_VARIABLE, false);
			values.put(TestEntity.SPEED_VARIABLE, false);
		}

		// swim
		TestsSwim swim = entity.getTestsSwim();
		if (swim != null) {
			Date date2 = null;
			Timestamp timestamp2 = swim.getDate2();
			if (timestamp2 != null) {
				date2 = new Date(timestamp2.getTime());
			}
			values.put(TestEntity.DATE2, date2);
			values.put(TestEntity.ASSISTANT_NAME, swim.getAssistantName());
			values.put(TestEntity.BATHS, swim.getBaths());
			values.put(TestEntity.POOL, swim.getPool());
			values.put(TestEntity.DISTANCE, swim.getDistance());
			values.put(TestEntity.SPLITS, swim.getSplits());
		}
		// protocol
		TestsProtocol protocol = entity.getTestsProtocol();
		if (protocol != null) {
			values.put(TestEntity.PROTOCOL_DESCRIPTION, protocol.getDescription());
			values.put(TestEntity.MODEL, protocol.getModel());
			values.put(TestEntity.MODEL_LACTATE, protocol.getModelLactate());
			values.put(TestEntity.MODEL_SPIRO, protocol.getModelSpiro());
			values.put(TestEntity.PERFORMANCE_MAX, protocol.getPerformanceMax());
			try {
				if (isSwim()) {
					buildSwimProtocolGrid();
				} else {
					buildProtocolGrid();
				}
			} catch (Exception ex) {
				Statusbar.outputAlert(ex.toString(), "Testprotokoll schreiben").setLeftTopReferenceCentered();
			}
		}

		// analysis
		TestsAnalysis analysis = entity.getTestsAnalysis();
		if(analysis != null) {
			values.put(TestEntity.ANALYSIS_FUNCTION, analysis.getFunction());
			values.put(TestEntity.ANALYSIS_OFFSET, analysis.getOffset());
			values.put(TestEntity.ANALYSIS_DEGREE, analysis.getDegree().toString());
			values.put(TestEntity.ANALYSIS_LACTATE_HR, analysis.getLactateHr());
		}

		// add bgpaint of fields
		bgpaint.clear();
		// mandatory fields
		for (String field : MANDATORY_FIELDS) {
			bgpaint.put(field, Constants.BGP_MANDATORY);
		}
		// mandatory fields for ergo test
		for (String field : MANDATORY_FIELDS_ERGO) {
			bgpaint.put(field, Constants.BGP_MANDATORY);
		}
		// mandatory fields for treadmill test
		for (String field : MANDATORY_FIELDS_TREADMILL) {
			bgpaint.put(field, Constants.BGP_MANDATORY);
		}
		// mandatory fields for swim test
		for (String field : MANDATORY_FIELDS_SWIM) {
			bgpaint.put(field, Constants.BGP_MANDATORY);
		}
	}

	public void onChangeData(ActionEvent ae) {
		Integer splits = (Integer) values.get(TestEntity.SPLITS);
		if (splits != null && splits > maxSplits)
			values.put(TestEntity.SPLITS, maxSplits);
	}

	private void setAthleteDescription(Tests test) {
		Persons athlete = test.getAthlete();
		String athleteDescription = Constants.EMPTY;
		if (athlete != null) {
			athleteDescription = athlete.toString();
		}
		values.put(TestEntity.PERSON, athleteDescription);
	}

	private void setDoctorDescription(Tests test) {
		Doctors doctor = test.getDoctor();
		String doctorDescription = Constants.EMPTY;
		if (doctor != null) {
			doctorDescription = doctor.toString();
		}
		values.put(TestEntity.DOCTOR, doctorDescription);
	}

	public boolean isErgo() {
		if (entity.getType() == null || !entity.getType().equals("ergo"))
			return false;
		return true;
	}

	public boolean isTreadmill() {
		if (entity.getType() == null || !entity.getType().equals("treadmill"))
			return false;
		return true;
	}

	public boolean isSwim() {
		if (entity.getType() == null || !entity.getType().equals("swim"))
			return false;
		return true;
	}

	public boolean isTypeEnabled() {
		// type just enabled as long as no protocol is created
		if (!isProtocol() && super.m_enabled)
			return true;
		return false;
	}

	public boolean isProtocol() {
		if (entity.getTestsProtocol() != null)
			return true;
		return false;
	}

	public boolean isAnalysis() {
		if (entity.getTestsAnalysis() != null)
			return true;
		return false;
	}

	public void onProtocolCreate(ActionEvent event) {
		try {
			// check mandatory fields
			validate();
			// create protocol
			TestsProtocol protocol = new TestsProtocol(entity.getId());
			protocol.setCreatedAt(new java.sql.Timestamp((new java.util.Date()).getTime()));
			protocol.setCreatedBy(getServiceLayer().getDictionaryService().getMyUser().getId());
			entity.setTestsProtocol(protocol);
			fillEntityProperties();
		} catch (MandatoryCheckException mce) {
			Statusbar.outputAlert(Helper.getMessages("mandatory"), Helper.getLiteral("warn"), String.format(Helper.getMessages("field_missing"), mce.getField()) ).setLeftTopReferenceCentered();
		} catch (EmailNotValidException env) {
			Statusbar.outputAlert(Helper.getMessages("email_invalid"), Helper.getLiteral("error"), String.format(Helper.getMessages("email_invalid_detail"),env.getEmail())).setLeftTopReferenceCentered();
		}
	}

	public void onAnalysisCreate(ActionEvent event) {
		try {
			// check mandatory fields
			validate();
			// create protocol
			TestsAnalysis analysis = new TestsAnalysis(entity.getId());
			analysis.setCreatedAt(new java.sql.Timestamp((new java.util.Date()).getTime()));
			analysis.setCreatedBy(getServiceLayer().getDictionaryService().getMyUser().getId());
			entity.setTestsAnalysis(analysis);
			fillEntityProperties();
		} catch (MandatoryCheckException mce) {
			Statusbar.outputAlert(Helper.getMessages("mandatory"), Helper.getLiteral("warn"), String.format(Helper.getMessages("field_missing"), mce.getField()) ).setLeftTopReferenceCentered();
		} catch (EmailNotValidException env) {
			Statusbar.outputAlert(Helper.getMessages("email_invalid"), Helper.getLiteral("error"), String.format(Helper.getMessages("email_invalid_detail"),env.getEmail())).setLeftTopReferenceCentered();
		}
	}

	// ------------------------------------------------------------------------
	// logic for protocols
	// ------------------------------------------------------------------------
	public static final int maxSplits = 8;

	public double getMaxSpeed() {
		String maxPerformance = (String) values.get(TestEntity.PERFORMANCE_MAX);
		Integer distance = (Integer) values.get(TestEntity.DISTANCE);
		if (maxPerformance == null || distance == null)
			return 0d;
		return HelperTime.calculateMeterPerSecond(distance, maxPerformance);
	}

	protected FIXGRIDListBinding<GridTreadmillItem> m_gridTreadmill = new FIXGRIDListBinding<GridTreadmillItem>();

	public FIXGRIDListBinding<GridTreadmillItem> getGridTreadmill() {
		return m_gridTreadmill;
	}

	protected FIXGRIDListBinding<GridErgoItem> m_gridErgo = new FIXGRIDListBinding<GridErgoItem>();

	public FIXGRIDListBinding<GridErgoItem> getGridErgo() {
		return m_gridErgo;
	}

	protected FIXGRIDTreeBinding<GridSwimItem> m_gridSwim = new FIXGRIDTreeBinding<GridSwimItem>(true);

	public FIXGRIDTreeBinding<GridSwimItem> getGridSwim() {
		return m_gridSwim;
	}

	public class AGridItem extends FIXGRIDItem implements java.io.Serializable {
		protected Integer step;
		protected String step_time = Constants.EMPTYTIMESHORT;
		protected String time_total = Constants.EMPTYTIMESHORT;
		protected Double lactate;
		protected Integer hr;
		protected Integer o2_absorption;
		protected Integer co2_emission;
		protected Double rq;

		public AGridItem(Integer step) {
			this.step = step;
		}

		public Integer getStep() {
			return step;
		}

		public void setStep(Integer step) {
			this.step = step;
		}

		public String getStep_time() {
			return step_time;
		}

		public void setStep_time(String step_time) {
			this.step_time = step_time;
		}

		public String getTime_total() {
			return time_total;
		}

		public void setTime_total(String time_total) {
			this.time_total = time_total;
		}

		public Double getLactate() {
			return lactate;
		}

		public void setLactate(Double lactate) {
			this.lactate = lactate;
		}

		public Integer getHr() {
			return hr;
		}

		public void setHr(Integer hr) {
			this.hr = hr;
		}

		public Integer getO2_absorption() {
			return o2_absorption;
		}

		public void setO2_absorption(Integer o2_absorption) {
			this.o2_absorption = o2_absorption;
		}

		public Integer getCo2_emission() {
			return co2_emission;
		}

		public void setCo2_emission(Integer co2_emission) {
			this.co2_emission = co2_emission;
		}

		public Double getRq() {
			return rq;
		}

		public void setRq(Double rq) {
			this.rq = rq;
		}

		public void onChangeItem(ActionEvent event) {
			// get clientname to separate by source
			String clientname = (String) event.getComponent().getAttributes().get(Constants.CLIENTNAME);
			if(clientname!=null && clientname.startsWith("step_time_")) {
				// correct input
				String corrInput = HelperTime.correctTimeInputShort(getStep_time());
				// if null don't write back the value
				if(corrInput!=null) {
					setStep_time(corrInput);
				}
			}
		}

	}

	public class GridTreadmillItem extends AGridItem {
		private Double speed;
		private Integer incline;

		public GridTreadmillItem(Integer step) {
			super(step);
			TestsTreadmill treadmill = entity.getTestsTreadmill();
			step_time = treadmill.getStepTime();
		}

		public Double getSpeed() {
			TestsTreadmill treadmill = entity.getTestsTreadmill();
			if (!treadmill.getSpeedVariable())
				return treadmill.getSpeedInit();
			return treadmill.getSpeedInit() + treadmill.getSpeedStep() * (step - 1);
		}

		public Integer getIncline() {
			TestsTreadmill treadmill = entity.getTestsTreadmill();
			if (!treadmill.getInclineVariable())
				return treadmill.getInclineInit();
			return treadmill.getInclineInit() + treadmill.getInclineStep() * (step - 1);
		}

		@Override
		public String getTime_total() {
			if (step == 1) {
				return getStep_time();
			}
			// get previous step
			AGridItem preItem = m_gridTreadmill.getItems().get(step - 2);
			return HelperTime.calculateDuration(preItem.getTime_total(), getStep_time(), false, false);
		}

		public boolean getLastItemEnabled() {
			return m_gridTreadmill.getItems().size() == step && getEnabled();
		}
	}

	public class GridErgoItem extends AGridItem {
		private Integer power;
		private String cadence;

		public GridErgoItem(Integer step) {
			super(step);
			TestsErgo ergo = entity.getTestsErgo();
			step_time = ergo.getStepTime();
		}

		public Integer getPower() {
			TestsErgo ergo = entity.getTestsErgo();
			return ergo.getPowerInit() + ergo.getPowerStep() * (step - 1);
		}

		public String getCadence() {
			TestsErgo ergo = entity.getTestsErgo();
			return "[" + ergo.getCadenceLow() + " - " + ergo.getCadenceHigh() + "]";
		}

		@Override
		public String getTime_total() {
			if (step == 1) {
				return getStep_time();
			}
			// get previous step
			AGridItem preItem = m_gridErgo.getItems().get(step - 2);
			return HelperTime.calculateDuration(preItem.getTime_total(), getStep_time(), false, false);
		}

		public boolean getLastItemEnabled() {
			return m_gridErgo.getItems().size() == step && getEnabled();
		}
	}

	public class GridSwimItem extends FIXGRIDTreeItem implements java.io.Serializable {
		private Integer step;
		private Integer attempt;
		private Boolean topNode;
		private Boolean valid;
		private LactateSamples lactateSamples;
		private Integer hr;
		private Integer intensity; // just on top node
		private String time;
		private String comment;
		private Split[] splits = new Split[maxSplits];
		private int validNode;

		public GridSwimItem(FIXGRIDTreeItem parent, Integer step) {
			super(parent);
			// is top node
			if (parent.getLevelInt() < 0) {
				topNode = true;
				valid = false;
			} else {
				topNode = false;
				setStatus(STATUS_ENDNODE);
				valid = true;
				// set valid node to top node
				((GridSwimItem) getParentNode()).setValidNode(step - 1);
				// build splits
				int splitCount = entity.getTestsSwim().getSplits();
				for (int i = 1; i <= splitCount; i++) {
					Split split = getLogic().getTestLogic().createSplit();
					splits[i - 1] = split;
				}
				lactateSamples = getLogic().getTestLogic().createLactateSamples();
				// set valid node at top node
				((GridSwimItem) getParentNode()).setValidNode(getParentNode().getChildNodes().size());
			}
			this.step = step;
		}

		public GridSwimItem(FIXGRIDTreeItem parent, TestsSwimProtocol protocol) {
			super(parent);
			// is top node
			if (parent.getLevelInt() < 0) {
				step = protocol.getId().getStep();
				topNode = true;
				valid = false;
				intensity = protocol.getIntensity();
			} else {
				step = protocol.getId().getAttempt();
				topNode = false;
				setStatus(STATUS_ENDNODE);
				valid = protocol.getValid();
				// set valid node to top node
				if (valid)
					((GridSwimItem) getParentNode()).setValidNode(step - 1);
				time = protocol.getTime();
				lactateSamples = getLogic().getTestLogic().createLactateSamples(protocol.getLactate());
				if (protocol.getHr() != null)
					hr = Integer.valueOf(protocol.getHr());
				// splits, if null, create array with split count elements
				Integer count = entity.getTestsSwim().getSplits();
				String strSplits = protocol.getSplits();
				splits = getLogic().getTestLogic().buildArray(strSplits, count);
				comment = protocol.getComment();
			}
		}

		public Integer getStep() {
			return step;
		}

		public void setStep(Integer step) {
			this.step = step;
		}

		public Integer getIntensity() {
			if (isTopNode()) {
				return intensity;
			} else {
				return ((GridSwimItem) this.getParentNode()).intensity;
			}
		}

		public void setIntensity(Integer intensity) {
			// limit intensity [0-100]
			if (intensity < 0) {
				this.intensity = 0;
				return;
			}
			if (intensity > 100) {
				this.intensity = 100;
				return;
			}
			this.intensity = intensity;
			// workaround to refresh nodes below
			closeAllNodes();
			expandNode();

		}

		public String getTargetTime() {
			if (isTopNode()) {
				String maxPerformance = (String) values.get(TestEntity.PERFORMANCE_MAX);
				if (maxPerformance == null)	return null;
				return (HelperTime.getTimeByPercentage(maxPerformance, intensity!=null?200 - intensity:200));
			} else {
				return ((GridSwimItem) this.getParentNode()).getTargetTime();
			}
		}

		public Double getSpeed() {
			Integer distance = (Integer) values.get(TestEntity.DISTANCE);
			if (distance == null)
				return 0d;
			return HelperTime.calculateMeterPerSecond(distance, getTime());
		}

		public Double getTargetSpeed() {
			Integer distance = (Integer) values.get(TestEntity.DISTANCE);
			if (distance == null)
				return 0d;
			return HelperTime.calculateMeterPerSecond(distance, getTargetTime());
		}

		public String getTime() {
			if (topNode) {
				return ((GridSwimItem) this.getChildNodes().get(validNode)).getTime();
			}
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public Integer getHr() {
			if (topNode) {
				return ((GridSwimItem) this.getChildNodes().get(validNode)).getHr();
			}
			return hr;
		}

		public void setHr(Integer hr) {
			this.hr = hr;
		}

		public String getLactate() {
			if (topNode) {
				return ((GridSwimItem) this.getChildNodes().get(validNode)).lactateSamples.getSingleValue();
			}
			return lactateSamples.toString();
		}

		public void setLactate(String lactate) {
			this.lactateSamples.setLactateSamples(lactate);
		}

		public Split[] getSplits() {
			if (topNode) {
				return ((GridSwimItem) this.getChildNodes().get(validNode)).getSplits();
			}
			return splits;
		}

		public void setSplits(Split[] splits) {
			this.splits = splits;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public boolean isValid() {
			return valid;
		}

		public void setValid(boolean valid) {
			this.valid = valid;
		}

		public int getValidNode() {
			return validNode;
		}

		public void setValidNode(int validNode) {
			this.validNode = validNode;
		}

		public boolean isEnabled() {
			return m_enabled && valid && !topNode;
		}

		public boolean isEnabledAndTopNode() {
			return m_enabled && topNode;
		}

		public void onAddSubItem(ActionEvent ae) {
			int step = getChildNodes().size() + 1;
			for (FIXGRIDTreeItem item : getChildNodes()) {
				// just newly added item is active
				((GridSwimItem) item).valid = false;
				m_gridSwim.ensureItemToBeDisplayed((GridSwimItem) item);
			}
			new GridSwimItem(this, step);
		}

		public void onChangeItem(ActionEvent event) {
			// get clientname to separate by source
			String clientname = (String) event.getComponent().getAttributes().get(Constants.CLIENTNAME);
			if(Helper.isEmpty(clientname)) return;
			if(clientname.startsWith("time_")) {
				// correct input
				String corrInput = HelperTime.correctTimeInputShort(getTime(), true);
				// if null don't write back the value
				if(corrInput!=null) {
					setTime(corrInput);
				}
			} else if(clientname.startsWith("splittime_")) {
				String[] arrClientName = clientname.split("_");
				Integer index = Integer.valueOf(arrClientName[1]);
				// correct input
				String corrInput = HelperTime.correctTimeInputShort(splits[index].getTime(), true);
				// if null don't write back the value
				if(corrInput!=null) {
					splits[index].setTime(corrInput);
				}
			}
		}

		public void onMarkItem(ActionEvent ae) {
			if (valid) {
				// set all invalid
				for (FIXGRIDTreeItem item : getParentNode().getChildNodes()) {
					((GridSwimItem) item).valid = false;
					m_gridSwim.ensureItemToBeDisplayed((GridSwimItem) item);
				}
				// set actual item valid
				valid = true;
				((GridSwimItem) getParentNode()).setValidNode(step - 1);
			} else {
				// mark last node
				int last = getParentNode().getChildNodes().size() - 1;
				GridSwimItem item = (GridSwimItem) getParentNode().getChildNodes().get(last);
				item.valid = true;
				((GridSwimItem) getParentNode()).setValidNode(last);
				m_gridSwim.ensureItemToBeDisplayed(item);
			}
		}

		public boolean isTopNode() {
			return topNode;
		}

		public String getBgPaintTop() {
			return topNode ? Constants.BGP_MANDATORY : null;
		}

		public String getBgpaint() {
			if (topNode)
				return null;
			if (!valid)
				return null;
			return Constants.BGP_MANDATORY;
		}
	}

	public String[] getSplitHeader() {
		String[] header = new String[maxSplits];
		int splitCount = entity.getTestsSwim().getSplits();
		int distance = entity.getTestsSwim().getDistance();
		if (splitCount > 0) {
			int part = distance / splitCount;
			for (int i = 1; i <= splitCount; i++) {
				Integer key = part * i;
				header[i - 1] = key.toString();
			}
		}
		return header;
	}

	public void onAddItem(ActionEvent ae) {
		// treadmill
		if (isTreadmill()) {
			int step = m_gridTreadmill.getRows().size() + 1;
			GridTreadmillItem item = new GridTreadmillItem(step);
			m_gridTreadmill.getItems().add(item);
			return;
		}
		// ergo
		if (isErgo()) {
			int step = m_gridErgo.getRows().size() + 1;
			GridErgoItem item = new GridErgoItem(step);
			m_gridErgo.getItems().add(item);
			return;
		}
		// swim
		if (isSwim()) {
			int step = m_gridSwim.getRootNode().getChildNodes().size() + 1;
			GridSwimItem item = new GridSwimItem(m_gridSwim.getRootNode(), step);
			// add one subnode
			item.onAddSubItem(null);
			return;
		}

	}

	public void onRemoveItem(ActionEvent ae) {
		if (!isSwim()) {
			FIXGRIDListBinding<? extends FIXGRIDItem> grid = null;
			// treadmill
			if (isTreadmill()) {
				grid = m_gridTreadmill;
			}
			// ergo
			if (isErgo()) {
				grid = m_gridErgo;
			}
			if (grid == null)
				return;

			FIXGRIDItem selected = grid.getSelectedItem();
			if (selected == null)
				return;
			grid.getItems().remove(selected);
			// recalculate step numbers and total time
			int step = 1;
			for (FIXGRIDItem item : grid.getItems()) {
				// check if swim protocol
				if (!isSwim()) {
					((AGridItem) item).setStep(step++);
				} else {

				}
			}
		} else {
			// swim
			GridSwimItem selected = m_gridSwim.getSelectedItem();
			if (selected == null)
				return;
			FIXGRIDTreeItem root = selected.getParentNode();
			if (root == null)
				return;
			selected.removeNode();
			// recalculate step numbers and total time
			int step = 1;
			for (FIXGRIDTreeItem item : root.getChildNodes()) {
				((GridSwimItem) item).setStep(step++);
			}
			// set dirty flag
			isDirtySwimProtocol = true;
		}
	}

	private void fillSwimProtocol() {
		TestsSwim swim = entity.getTestsSwim();
		List<TestsSwimProtocol> protocols = new ArrayList<TestsSwimProtocol>();
		for (FIXGRIDTreeItem step : m_gridSwim.getRootNode().getChildNodes()) {
			for (FIXGRIDTreeItem attempt : step.getChildNodes()) {
				GridSwimItem item = (GridSwimItem) attempt;
				TestsSwimProtocolId id = new TestsSwimProtocolId(swim.getId(), ((GridSwimItem) step).getStep(), item.getStep());
				TestsSwimProtocol protocol = new TestsSwimProtocol(id);
				protocol.setValid(item.isValid());
				protocol.setIntensity(item.getIntensity());
				protocol.setTime(item.getTime());
				if (item.getLactate() != null)
					protocol.setLactate(item.getLactate().toString());
				if (item.getHr() != null)
					protocol.setHr(item.getHr().toString());
				Integer count = entity.getTestsSwim().getSplits();
				protocol.setSplits(getLogic().getTestLogic().buildString(item.getSplits(), count));
				protocol.setComment(item.getComment());
				protocols.add(protocol);
			}
		}
		// set steps for analyze method
		entity.getTestsProtocol().setCountSteps(m_gridSwim.getRootNode().getChildNodes().size());
		swim.setSteps(protocols);
	}

	public void onChangeMaxPerformance(ActionEvent event) {
		// correct input
		String corrInput = HelperTime.correctTimeInputShort((String)values.get(TestEntity.PERFORMANCE_MAX), true);
		// if null don't write back the value
		if(corrInput!=null) {
			values.put(TestEntity.PERFORMANCE_MAX, corrInput);
		}
	}

	private void fillProtocol() {
		FIXGRIDListBinding<? extends AGridItem> grid = null;
		// treadmill
		if (isTreadmill()) {
			grid = m_gridTreadmill;
		}
		// ergo
		if (isErgo()) {
			grid = m_gridErgo;
		}
		if (grid == null)
			return;

		int count_steps = grid.getItems().size();
		if(count_steps>0) {
			List<Double> lactates = new ArrayList<Double>();
			List<Integer> hrs = new ArrayList<Integer>();
			List<Integer> o2_absorptions = new ArrayList<Integer>();
			List<Integer> co2_emissions = new ArrayList<Integer>();
			List<Double> rqs = new ArrayList<Double>();
			// build lists
			for (AGridItem item : grid.getItems()) {
				lactates.add(item.getLactate());
				//arrayLactates[item.step-1] = item.getLactate();
				hrs.add(item.getHr());
				o2_absorptions.add(item.getO2_absorption());
				co2_emissions.add(item.getCo2_emission());
				rqs.add(item.getRq());
			}
			// transform lists to JSON format
			String strLactates = new DoubleList(lactates).toString();
			String strHrs = new IntegerList(hrs).toString();
			String strO2_absorptions = new IntegerList(o2_absorptions).toString();
			String strCo2_emissions = new IntegerList(co2_emissions).toString();
			String strRqs = new DoubleList(rqs).toString();

			TestsProtocol protocol = entity.getTestsProtocol();
			// fill protocol entity
			protocol.setCountSteps(count_steps);
			protocol.setTimeLastStep(grid.getItems().get(grid.getItems().size()-1).step_time);
			protocol.setLactate(strLactates);
			protocol.setHr(strHrs);
			protocol.setO2Absorption(strO2_absorptions);
			protocol.setCo2Emission(strCo2_emissions);
			protocol.setRq(strRqs);
		}
	}

	private void buildSwimProtocolGrid() throws Exception {
		int step = 0;
		GridSwimItem top = null;
		m_gridSwim = new FIXGRIDTreeBinding<GridSwimItem>(true);
		for (TestsSwimProtocol protocol : entity.getTestsSwim().getSteps()) {
			// create top node for new step
			if (step != protocol.getId().getStep()) {
				step = protocol.getId().getStep();
				// top node
				top = new GridSwimItem(m_gridSwim.getRootNode(), protocol);
			}
			// sub node
			new GridSwimItem(top, protocol);
		}
	}

	private void buildProtocolGrid() throws Exception {
		TestsProtocol protocol = entity.getTestsProtocol();

		DoubleList lactates = DoubleList.getInstance(protocol.getLactate());
		IntegerList hrs = IntegerList.getInstance(protocol.getHr());
		IntegerList o2_absorptions = IntegerList.getInstance(protocol.getO2Absorption());
		IntegerList co2_emissions = IntegerList.getInstance(protocol.getCo2Emission());
		DoubleList rqs = DoubleList.getInstance(protocol.getRq());

		// treadmill
		if (isTreadmill()) {
			m_gridTreadmill.getItems().clear();
			for (int i = 0; i < protocol.getCountSteps(); i++) {
				GridTreadmillItem item = new GridTreadmillItem(i + 1);
				item.setLactate(lactates.get(i));
				item.setHr(hrs.get(i));
				item.setO2_absorption(o2_absorptions.get(i));
				item.setCo2_emission(co2_emissions.get(i));
				item.setRq(rqs.get(i));
				m_gridTreadmill.getItems().add(item);
			}
			// set last time step
			m_gridTreadmill.getItems().get(m_gridTreadmill.getItems().size()-1).setStep_time(protocol.getTimeLastStep());
			return;
		}
		// ergo
		if (isErgo()) {
			m_gridErgo.getItems().clear();
			for (int i = 0; i < protocol.getCountSteps(); i++) {
				GridErgoItem item = new GridErgoItem(i + 1);
				item.setLactate(lactates.get(i));
				item.setHr(hrs.get(i));
				item.setO2_absorption(o2_absorptions.get(i));
				item.setCo2_emission(co2_emissions.get(i));
				item.setRq(rqs.get(i));
				m_gridErgo.getItems().add(item);
			}
			// set last time step
			m_gridErgo.getItems().get(m_gridErgo.getItems().size()-1).setStep_time(protocol.getTimeLastStep());
			return;
		}
	}

	private void checkProtocolGrid() throws MandatoryCheckException {
		// treadmill
		if (isTreadmill()) {
			for (GridTreadmillItem item : m_gridTreadmill.getItems()) {
				if (item.getLactate() == null)
					throw new MandatoryCheckException("Lactate");
				if (item.getHr() == null)
					throw new MandatoryCheckException("Heartrate");
			}
			return;
		}
		// ergo
		if (isErgo()) {
			for (GridErgoItem item : m_gridErgo.getItems()) {
				if (item.getLactate() == null)
					throw new MandatoryCheckException("Lactate");
				if (item.getHr() == null)
					throw new MandatoryCheckException("Heartrate");
			}
			return;
		}
		// swim
		if (isSwim()) {
			GridSwimItem item = null;
			for (FIXGRIDTreeItem topItem : m_gridSwim.getRootNode().getChildNodes()) {
				item = (GridSwimItem) topItem;
				if (item.getIntensity() == null)
					throw new MandatoryCheckException("Intensity");
				for (FIXGRIDTreeItem node : topItem.getChildNodes()) {
					item = (GridSwimItem) node;
					if (item.isValid()) {
						if (item.getTime() == null || item.getTime().length() == 0)
							throw new MandatoryCheckException("Time");
						if (item.getLactate() == null)
							throw new MandatoryCheckException("Lactate");
					}
				}
			}
		}
	}

	private void checkDetailGrid() throws MandatoryCheckException {
		// treadmill
		if (isTreadmill()) {
			checkMandatory(MANDATORY_FIELDS_TREADMILL);
			return;
		}
		// ergo
		if (isErgo()) {
			checkMandatory(MANDATORY_FIELDS_ERGO);
			return;
		}
		// swim
		if (isSwim()) {
			checkMandatory(MANDATORY_FIELDS_SWIM);
			return;
		}
	}

	// ------------------------------------------------------------------------
	// logic for analysis
	// ------------------------------------------------------------------------
	final String LACTATE = "Laktat";
	final String HR = "Herzfrequenz";
	final String SPEED = "Geschwindigkeit";
	final String TIME = "Zeit";
	final String POWER = "Watt";
	final String UNIT_HR = "bpm";
	final String UNIT_WATT = "W";
	final String UNIT_MMOL = "mmol/l";
	final String UNIT_KMH = "km/h";
	final String UNIT_MMSS = "mm:ss";
	final String UNIT_SEC = "sec";
	final String UNIT_MS = "m/s";

	private byte[] m_diagram;
	private IResult result;
	private double[] hrs;
	private double[] hrsLac;
	private String descriptionX = null;
	public String getDescriptionX() { return descriptionX; }
	private String unitX = null;
	public String getUnitX() { return unitX; }
	private String descriptionY = null;
	public String getDescriptionY() { return descriptionY; }
	private String unitY = null;
	public String getUnitY() { return unitY; }
	private boolean highToLow = false;

	protected int m_height;

	public int getHeight() {
		return m_height;
	}

	public void setHeight(int value) {
		m_height = value;
	}

	protected int m_width;

	public int getWidth() {
		return m_width;
	}

	public void setWidth(int value) {
		m_width = value;
	}

	protected boolean m_inverse;

	public boolean getInverse() {
		return m_inverse;
	}

	public boolean getLactateHr() {
		Boolean lactateHr = (Boolean)values.get(TestEntity.ANALYSIS_LACTATE_HR);
		return lactateHr==null?false:lactateHr;
	}

	public void setInverse(boolean value) {
		m_inverse = value;
	}

	protected double m_valueY;

	public double getValueY() {
		return m_valueY;
	}

	public void setValueY(double value) {
		m_valueY = value;
	}

	protected double m_valueX;

	public double getValueX() {
		return m_valueX;
	}

	public void setValueX(double value) {
		m_valueX = value;
	}

	protected Integer m_hr;

	public Integer getHr() {
		return m_hr;
	}

	public void setHr(Integer value) {
		m_hr = value;
	}

	public int getMaxWidth() {
		//Statusbar.outputMessage("Actual width: " + Helper.getWidth());
		return Helper.getWidth();
	}

	public ValidValuesBinding getFunctionsVvb() {
		return getServiceLayer().getValueListBindingService().FUNCTIONS;
	}

	public void onRefresh(ActionEvent event) {
		getLogic().getTestLogic().setResultionForDia(getHeight(), getWidth());
		if (result == null)
			result = analyze();
		// build the chart based on the computed result
		if (result == null)
			return;
		m_diagram = buildDiagram(result, hrs, m_width, m_height, descriptionX, unitX, descriptionY, unitY, highToLow, m_inverse);
	}

	public void onSetZones(ActionEvent event) {
		if (result == null) {
			Statusbar.outputAlert(Helper.getMessages("refresh_for_init"), Helper.getLiteral("info")).setLeftTopReferenceCentered();
			return;
		}
		YESNOPopup popup = YESNOPopup.createInstance(Helper.getMessages("write_zone"), Helper.getMessages("confirm_write_zone"), new IYesNoCancelListener() {
			public void reactOnCancel() {}
			public void reactOnNo() {}
			public void reactOnYes() {
				// get training zones of ahlete
				String athleteId = entity.getAthlete().getId();
				List<ZonesLogic.ZoneInfo> zoneInfos = getLogic().getZonesLogic().getAthletesZone(athleteId);
				if (zoneInfos.size() == 0) {
					Statusbar.outputAlert(Helper.getMessages("athlete_no_coach_zone"), Helper.getLiteral("info")).setLeftTopReferenceCentered();
					return;
				}
				boolean error = false;
				Double x = null;
				Integer hrLow = null;
				Integer hrHigh = null;
				Integer hrTargetLow = null;
				Integer hrTargetHigh = null;
				Double speedLow = null;
				Double speedHigh = null;
				for (ZonesLogic.ZoneInfo zoneInfo : zoneInfos) {
					ZonesDefinition definition = zoneInfo.getDefinition();
					// create zone if empty
					Zones zone = zoneInfo.getZone() != null ? zoneInfo.getZone() : getLogic().getZonesLogic().createZone(athleteId, zoneInfo.getDefinition().getId());
					/*
					 * calculate values, special for swim test
					 * when swim test, just speed no hr
					*/
					try {
						if (!isSwim()) {
							// get speed or power
							x = result.getX(definition.getLactateLow()==null?0d:definition.getLactateLow());
							hrLow = getLactateHr()?(int) Math.round(x):(int) Math.round(trimatrix.utils.maths.Helper.getYFromMultiLinearFunction(hrs, x));
							x = result.getX(definition.getLactateHigh()==null?0d:definition.getLactateHigh());
							hrHigh = getLactateHr()?(int) Math.round(x):(int) Math.round(trimatrix.utils.maths.Helper.getYFromMultiLinearFunction(hrs, x));
							x = result.getX(definition.getLactateTargetLow()==null?0d:definition.getLactateTargetLow());
							hrTargetLow = getLactateHr()?(int) Math.round(x):(int) Math.round(trimatrix.utils.maths.Helper.getYFromMultiLinearFunction(hrs, x));
							x = result.getX(definition.getLactateTargetHigh()==null?0d:definition.getLactateTargetHigh());
							hrTargetHigh = getLactateHr()?(int) Math.round(x):(int) Math.round(trimatrix.utils.maths.Helper.getYFromMultiLinearFunction(hrs, x));
							if(isTreadmill()) {
								zone.setHrLowRun(hrLow);
								zone.setHrHighRun(hrHigh);
								zone.setHrTargetLowRun(hrTargetLow);
								zone.setHrTargetHighRun(hrTargetHigh);
								zone.setAutoHrRun(true);
								zone.setTestIdRun(entity.getId());
							} else if(isErgo()) {
								zone.setHrLowBike(hrLow);
								zone.setHrHighBike(hrHigh);
								zone.setHrTargetLowBike(hrTargetLow);
								zone.setHrTargetHighBike(hrTargetHigh);
								zone.setAutoHrBike(true);
								zone.setTestIdBike(entity.getId());
							}
						} else {
							x = result.getX(definition.getLactateLow());
							speedLow = x / entity.getTestsSwim().getDistance();
							x = result.getX(definition.getLactateHigh());
							speedHigh = x / entity.getTestsSwim().getDistance();
							zone.setSpeedLowSwim(speedLow);
							zone.setSpeedHighSwim(speedHigh);
							zone.setAutoSpeedSwim(true);
							zone.setTestIdSwim(entity.getId());
						}
						getLogic().getZonesLogic().saveZone(zone);
					} catch (Exception ex) {
						Statusbar.outputAlert(String.format(Helper.getMessages("writing_back_zone_failure"), definition.getShortcut()), Helper.getLiteral("error"), ex.toString()).setLeftTopReferenceCentered();
						error = false;
						continue;
					}
				}
				// refresh beans
		        getWorkpage().throwWorkpageProcessingEvent(new WorkpageRefreshEvent(Entity.ZONE));
		        if(!error) Statusbar.outputSuccess(Helper.getMessages("writing_back_zone_success"));
			}
		});
		popup.getModalPopup().setLeftTopReferenceCentered();
	}

	public void onChangeXY(ActionEvent event) {
		// cast
		// BaseActionEventFlush baef = (BaseActionEventFlush)event;
		String clientname = (String) event.getComponent().getAttributes().get(Constants.CLIENTNAME);
		try {
			// x value changed
			if ("x".equals(clientname)) {
				m_valueY = result.getY(m_valueX);
			}
			// y value changed
			if ("y".equals(clientname)) {
				m_valueX = result.getX(m_valueY);
			}
			if (hrs != null)
				m_hr = (int) Math.round(trimatrix.utils.maths.Helper.getYFromMultiLinearFunction(hrs, m_valueX));
		} catch (Exception ex) {
			Statusbar.outputAlert(Helper.getMessages("problem_hr_value"), Helper.getLiteral("error"), ex.toString()).setLeftTopReferenceCentered();
		}
	}

	public void resetResult(ActionEvent event) {
		// force recalculating of chart
		result = null;
	}

	public String getDiagram() {
		if (m_diagram == null || m_diagram.length == 0)
			return null;
		return ValueManager.encodeHexString(m_diagram);
	}

	public String getFormel() {
		if (result == null)
			return null;
		return result.getFormel();
	}

	public String getCorrelation() {
		if (result == null)
			return null;
		try {
			return String.valueOf(result.getCorrelation());
		} catch (Exception ex) {
			return ex.toString();
		}
	}

	private IResult analyze() {
		double[] xyArr = null;
		this.hrs = null;
		this.hrsLac = null;
		// treadmill
		if (isTreadmill()) {
			descriptionX = getLactateHr()?HR:SPEED;
			unitX = getLactateHr()?UNIT_HR:UNIT_KMH;
			descriptionY = LACTATE;
			unitY = UNIT_MMOL;

			TestsTreadmill treadmill = entity.getTestsTreadmill();
			TestsProtocol treadmillProt = entity.getTestsProtocol();
			int steps = treadmillProt.getCountSteps();
			// lactates
			DoubleList lactates = DoubleList.getInstance(treadmillProt.getLactate());
			xyArr = new double[steps * 2];
			// heartrate
			IntegerList hrs = IntegerList.getInstance(treadmillProt.getHr());
			this.hrs = new double[steps * 2];
			this.hrsLac = new double[steps * 2];

			for (int i = 0; i < steps; i++) {
				// x value
				xyArr[2 * i] = getLactateHr()?hrs.get(i):treadmill.getSpeedInit() + treadmill.getSpeedStep() * i;
				// y value
				xyArr[2 * i + 1] = lactates.get(i);
				// hr value
				this.hrs[2 * i] = xyArr[2 * i];
				this.hrs[2 * i + 1] = hrs.get(i);
				// hr lactate value
				this.hrsLac[2 * i] = lactates.get(i);
				this.hrsLac[2 * i + 1] = hrs.get(i);
			}
		}
		// ergo
		if (isErgo()) {
			descriptionX = getLactateHr()?HR:POWER;
			unitX = getLactateHr()?UNIT_HR:UNIT_WATT;
			descriptionY = LACTATE;
			unitY = UNIT_MMOL;

			TestsErgo ergo = entity.getTestsErgo();
			TestsProtocol ergoProt = entity.getTestsProtocol();
			int steps = ergoProt.getCountSteps();
			DoubleList lactates = DoubleList.getInstance(ergoProt.getLactate());
			xyArr = new double[steps * 2];
			// heartrate
			IntegerList hrs = IntegerList.getInstance(ergoProt.getHr());
			this.hrs = new double[steps * 2];
			this.hrsLac = new double[steps * 2];

			for (int i = 0; i < steps; i++) {
				// x value
				xyArr[2 * i] = getLactateHr()?hrs.get(i):ergo.getPowerInit() + ergo.getPowerStep() * i;
				// y value
				xyArr[2 * i + 1] = lactates.get(i);
				// hr value
				this.hrs[2 * i] = xyArr[2 * i];
				this.hrs[2 * i + 1] = hrs.get(i);
				// hr lactate value
				this.hrsLac[2 * i] = lactates.get(i);
				this.hrsLac[2 * i + 1] = hrs.get(i);
			}
		}
		// swim
		if (isSwim()) {
			/*
			 * Normally it's disabled to have swim tests with HR!
			 * But I've just prepared this feature for future!
			 */
			descriptionX = getLactateHr()?HR:TIME;
			unitX = getLactateHr()?UNIT_HR:UNIT_SEC;
			descriptionY = LACTATE;
			unitY = UNIT_MMOL;

			highToLow = true;

			TestsSwim swim = entity.getTestsSwim();
			List<TestsSwimProtocol> protocols = swim.getSteps();
			int steps = entity.getTestsProtocol().getCountSteps();
			xyArr = new double[steps * 2];
			this.hrs = new double[steps * 2];
			this.hrsLac = new double[steps * 2];
			int i = 0;
			boolean deleteHr = true;
			for (TestsSwimProtocol protocol : protocols) {
				if (protocol.getValid()) {
					Double hr = protocol.getHr()!=null?Double.valueOf(protocol.getHr()):0d;
					if(hr!=0d) deleteHr = false;
					// x value
					xyArr[2 * i] = getLactateHr()?hr:HelperTime.calculateSecondsMsecs(protocol.getTime());
					// y value
					xyArr[2 * i + 1] = getLogic().getTestLogic().createLactateSamples(protocol.getLactate()).getSingleDoubleValue();
					// hr value
					this.hrs[2 * i] = xyArr[2 * i];
					this.hrs[2 * i + 1] = hr;
					// hr lactate value
					this.hrsLac[2 * i] = xyArr[2 * i + 1];
					this.hrsLac[2 * i + 1] = hr;
					// Incrementor
					i++;
				}
			}
			// delete HR if not set
			if(deleteHr) {
				this.hrs = null; this.hrsLac = null;
			}


		}
		String analysisFunc = (String)values.get(TestEntity.ANALYSIS_FUNCTION);
		Double analysisOffset = (Double)values.get(TestEntity.ANALYSIS_OFFSET);
		if(analysisOffset==null) analysisOffset = 0d;
		String analysisDegreeStr = (String)values.get(TestEntity.ANALYSIS_DEGREE);
		Integer analysisDegree = analysisDegreeStr==null?0:Integer.valueOf(analysisDegreeStr); //special handling because spinner returns values as String
		if (Constants.EXP.equals(analysisFunc)) {
			RegressionFunctions function = new RegressionFunctions(RegressionFunctions.EXP_REGRESSION, xyArr, analysisOffset);
			return function.getResult();
		} else if (Constants.POLY.equals(analysisFunc)) {
			try {
				PolynomialFunctions function = new PolynomialFunctions(xyArr, analysisDegree);
				return function.getResult();
			} catch (Exception ex) {
				Statusbar.outputAlert(Helper.getMessages("problem_poly_value"), Helper.getLiteral("error"), ex.toString()).setLeftTopReferenceCentered();
				return null;
			}
		} else if (Constants.SPLINE.equals(analysisFunc)) {
			try {
				UnivariateRealFunctions function = new UnivariateRealFunctions(xyArr, UnivariateRealFunctions.Type.SPLINE);
				return function.getResult();
			} catch (Exception ex) {
				Statusbar.outputAlert(Helper.getMessages("problem_poly_value"), Helper.getLiteral("error"), ex.toString()).setLeftTopReferenceCentered();
				return null;
			}
		} else if (Constants.NEVILLE.equals(analysisFunc)) {
			try {
				UnivariateRealFunctions function = new UnivariateRealFunctions(xyArr, UnivariateRealFunctions.Type.NEVILLE);
				return function.getResult();
			} catch (Exception ex) {
				Statusbar.outputAlert(Helper.getMessages("problem_poly_value"), Helper.getLiteral("error"), ex.toString()).setLeftTopReferenceCentered();
				return null;
			}
		} else if (Constants.DIVDIFF.equals(analysisFunc)) {
			try {
				UnivariateRealFunctions function = new UnivariateRealFunctions(xyArr, UnivariateRealFunctions.Type.DIVIDEDDIFFERENCE);
				return function.getResult();
			} catch (Exception ex) {
				Statusbar.outputAlert(Helper.getMessages("problem_poly_value"), Helper.getLiteral("error"), ex.toString()).setLeftTopReferenceCentered();
				return null;
			}
		}
		Statusbar.outputAlert(Helper.getMessages("type_function_error"), Helper.getLiteral("error")).setLeftTopReferenceCentered();
		return null;
	}

	public boolean isPolynomial() {
		return ((String)values.get(TestEntity.ANALYSIS_FUNCTION)).equals(Constants.POLY);
	}

	public boolean isExponential() {
		return ((String)values.get(TestEntity.ANALYSIS_FUNCTION)).equals(Constants.EXP);
	}

	// TODO put into logic layer

	private byte[] buildDiagram(IResult resultLactate, double[] hrs, int width, int height, String descriptionX, String unitX, String descriptionY, String unitY, boolean highToLow, boolean inverse) {
		// constants
		final String MEASUREPOINTS = "Messpunkte";
		final int GRANULARITY = 10;

		byte[] diagram = null;

		// measuring points
		double[] measurePoints = resultLactate.getXYValues();

		XYSeries seriesPoints = new XYSeries(MEASUREPOINTS, false);
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
		String title = (String)values.get(TestEntity.DESCRIPTION);
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

		if(descriptionX.equals(TIME)) {
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
		if (hrs !=null && !getLactateHr()) {
			XYSeries seriesHR = new XYSeries(HR);
			for (int i = 1; i < hrs.length; i += 2) {
				if(inverse) {
					seriesHR.add(hrsLac[i - 1], hrsLac[i]);
				} else {
					seriesHR.add(hrs[i - 1], hrs[i]);
				}
			}
			XYSeriesCollection datasetHR = new XYSeriesCollection();
			datasetHR.addSeries(seriesHR);
			ValueAxis axis2 = new NumberAxis(HR);
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

	// ------------------------------------------------------------------------
	// logic for diagramm download
	// ------------------------------------------------------------------------
	protected DiagramBufferedContent diagramBufferedContent;
	public DiagramBufferedContent getDiagramBufferdContent() { return diagramBufferedContent; }

	class DiagramBufferedContent extends DefaultBufferedContent {
		public byte[] getContent() { return m_diagram; }
        public String getContentType() { return "image/png"; }
    }

	private Trigger downloadTrigger = new Trigger();
	public Trigger getDownloadTrigger() {return downloadTrigger;}

	public void onDiagramDownload(ActionEvent ae) {
        if(m_diagram==null) onRefresh(null);
		// start download
        downloadTrigger.trigger();
    }
}
