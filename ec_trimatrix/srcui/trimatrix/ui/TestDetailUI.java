package trimatrix.ui;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONSerializer;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.elements.impl.FIXGRIDTreeBinding;
import org.eclnt.jsfserver.elements.impl.FIXGRIDTreeItem;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.util.valuemgmt.ValueManager;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
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
import trimatrix.entities.TestEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.ui.tests.AGridItem;
import trimatrix.ui.tests.LactateSamples;
import trimatrix.ui.tests.Split;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;
import trimatrix.utils.maths.PolynomialFunctions;
import trimatrix.utils.maths.RegressionFunctions;
import trimatrix.utils.maths.AFunctions.IResult;

@CCGenClass(expressionBase = "#{d.TestDetailUI}")
public class TestDetailUI extends AEntityDetailUI implements Serializable {
	public void onDoctorSearch(ActionEvent event) {
		IEntitySelectionUI entitySelectionUI = getEntitySelectionUI(Constants.Entity.DOCTOR);
		entitySelectionUI
				.prepareCallback(new EntitySelectionUI.ISelectionCallback() {
					public void cancel() {
						m_popup.close();
					}

					public void idSelected(String id) {
						Doctors doctor = (Doctors) ENTITYLISTLOGIC.get(
								Constants.Entity.DOCTOR, id);
						entity.setDoctor(doctor);
						setDoctorDescription(entity);
						m_popup.close();
					}
				});
		m_popup = getWorkpage().createModalPopupInWorkpageContext();
		m_popup.setLeftTopReferenceCentered();
		m_popup.open(Constants.Page.DOCTORSELECTION.getUrl(), Helper
				.getLiteral("doctor_search"), 800, 600, this);
	}

	public void onAthleteSearch(ActionEvent event) {
		IEntitySelectionUI entitySelectionUI = getEntitySelectionUI(Constants.Entity.PERSON);
		entitySelectionUI
				.prepareCallback(new EntitySelectionUI.ISelectionCallback() {
					public void cancel() {
						m_popup.close();
					}

					public void idSelected(String id) {
						Persons person = (Persons) ENTITYLISTLOGIC.get(
								Constants.Entity.PERSON, id);
						entity.setAthlete(person);
						setAthleteDescription(entity);
						m_popup.close();
					}
				});
		m_popup = getWorkpage().createModalPopupInWorkpageContext();
		m_popup.setLeftTopReferenceCentered();
		m_popup.open(Constants.Page.PERSONSELECTION.getUrl(), Helper
				.getLiteral("athlete_search"), 800, 600, this);
	}

	public void onTypeChange(ActionEvent event) {
		entity.setType((String) values.get(TestEntity.TYPE));
	}

	private Tests entity;

	public TestDetailUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher, new String[] { TestEntity.PERSON, TestEntity.TYPE,
				TestEntity.DATE }, true);
		// get wrapping entity detail UI bean
		entityDetailUI = getEntityDetailUI();
		entityDetailUI.setEntityDetailUI(this);
		// init data
		init(entityDetailUI.getEntityObject());
		// labeling
		setLabelRowDynamic();
	}

	protected ValidValuesBinding m_testTypesVvb = getServiceLayer()
			.getValueListBindingService().getVVBinding(
					Constants.ValueList.TESTTYPE);

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
		// set fields
		fillMaps();
		// set state
		setState();
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
				getDaoLayer().getTestsTreadmillDAO().delete(
						entity.getTestsTreadmill());
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
				getDaoLayer().getTestsTreadmillDAO().delete(
						entity.getTestsTreadmill());
				entity.setTestsTreadmill(null);
			}
			// delete ergo
			if (entity.getTestsErgo() != null) {
				getDaoLayer().getTestsErgoDAO().delete(entity.getTestsErgo());
				entity.setTestsErgo(null);
			}
			return;
		}
	}

	public void validate() throws MandatoryCheckException,
			EmailNotValidException {
		// mandatory check
		checkMandatory();
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
			ergo.setPowerInit((Integer) values.get(TestEntity.POWER_INIT));
			ergo.setPowerStep((Integer) values.get(TestEntity.POWER_STEP));
			ergo.setCadenceLow((Integer) values.get(TestEntity.CADENCE_LOW));
			ergo.setCadenceHigh((Integer) values.get(TestEntity.CADENCE_HIGH));
			ergo.setStepTime((String) values.get(TestEntity.ERGO_STEP_TIME));
		}
		// treadmill
		if (isTreadmill()) {
			TestsTreadmill treadmill = entity.getTestsTreadmill();
			treadmill.setSpeedVariable((Boolean) values
					.get(TestEntity.SPEED_VARIABLE));
			treadmill.setSpeedInit((Double) values.get(TestEntity.SPEED_INIT));
			treadmill.setSpeedStep((Double) values.get(TestEntity.SPEED_STEP));
			treadmill.setInclineVariable((Boolean) values
					.get(TestEntity.INCLINE_VARIABLE));
			treadmill.setInclineInit((Integer) values
					.get(TestEntity.INCLINE_INIT));
			treadmill.setInclineStep((Integer) values
					.get(TestEntity.INCLINE_STEP));
			treadmill.setStepTime((String) values
					.get(TestEntity.TREADMILL_STEP_TIME));
		}
		// swim
		if (isSwim()) {
			TestsSwim swim = entity.getTestsSwim();
			swim.setAssistantName((String) values
					.get(TestEntity.ASSISTANT_NAME));
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
			protocol.setDescription((String) values
					.get(TestEntity.PROTOCOL_DESCRIPTION));
			protocol.setModel((String) values.get(TestEntity.MODEL));
			protocol.setModelLactate((String) values
					.get(TestEntity.MODEL_LACTATE));
			protocol.setModelSpiro((String) values.get(TestEntity.MODEL_SPIRO));
			protocol.setPerformanceMax((String) values
					.get(TestEntity.PERFORMANCE_MAX));
			if (isSwim()) {
				fillSwimProtocol();
			} else {
				fillProtocol();
			}
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
			values.put(TestEntity.INCLINE_VARIABLE, treadmill
					.getInclineVariable());
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
			values.put(TestEntity.PROTOCOL_DESCRIPTION, protocol
					.getDescription());
			values.put(TestEntity.MODEL, protocol.getModel());
			values.put(TestEntity.MODEL_LACTATE, protocol.getModelLactate());
			values.put(TestEntity.MODEL_SPIRO, protocol.getModelSpiro());
			values
					.put(TestEntity.PERFORMANCE_MAX, protocol
							.getPerformanceMax());
			try {
				if (isSwim()) {
					buildSwimProtocolGrid();
				} else {
					buildProtocolGrid();
				}
			} catch (Exception ex) {
				Statusbar.outputAlert(ex.toString(), "Testprotokoll schreiben");
			}
		}

		// add bgpaint of fields
		bgpaint.clear();
		// mandatory fields
		for (String field : MANDATORY_FIELDS) {
			bgpaint.put(field, Constants.BGP_MANDATORY);
		}
	}

	public void onChangeData(ActionEvent ae) {
		if (((Integer) values.get(TestEntity.SPLITS)) > maxSplits)
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
		// create protocol
		TestsProtocol protocol = new TestsProtocol(entity.getId());
		protocol.setCreatedAt(new java.sql.Timestamp((new java.util.Date())
				.getTime()));
		protocol.setCreatedBy(getServiceLayer().getDictionaryService()
				.getMyUser().getId());
		entity.setTestsProtocol(protocol);
	}

	public void onAnalysisCreate(ActionEvent event) {
		// create protocol
		TestsAnalysis analysis = new TestsAnalysis(entity.getId());
		analysis.setCreatedAt(new java.sql.Timestamp((new java.util.Date())
				.getTime()));
		analysis.setCreatedBy(getServiceLayer().getDictionaryService()
				.getMyUser().getId());
		entity.setTestsAnalysis(analysis);
	}

	// ------------------------------------------------------------------------
	// logic for protocols
	// ------------------------------------------------------------------------
	public static final int maxSplits = 8;

	protected FIXGRIDListBinding<GridTreadmillItem> m_gridTreadmill = new FIXGRIDListBinding<GridTreadmillItem>(
			true);

	public FIXGRIDListBinding<GridTreadmillItem> getGridTreadmill() {
		return m_gridTreadmill;
	}

	protected FIXGRIDListBinding<GridErgoItem> m_gridErgo = new FIXGRIDListBinding<GridErgoItem>(
			true);

	public FIXGRIDListBinding<GridErgoItem> getGridErgo() {
		return m_gridErgo;
	}

	protected FIXGRIDTreeBinding<GridSwimItem> m_gridSwim = new FIXGRIDTreeBinding<GridSwimItem>(
			true);

	public FIXGRIDTreeBinding<GridSwimItem> getGridSwim() {
		return m_gridSwim;
	}

	public class GridTreadmillItem extends AGridItem {
		Double speed;
		Integer incline;

		public GridTreadmillItem(Integer step) {
			super(step);
		}

		public Double getSpeed() {
			TestsTreadmill treadmill = entity.getTestsTreadmill();
			if (!treadmill.getSpeedVariable())
				return treadmill.getSpeedInit();
			return treadmill.getSpeedInit() + treadmill.getSpeedStep()
					* (step - 1);
		}

		public Integer getIncline() {
			TestsTreadmill treadmill = entity.getTestsTreadmill();
			if (!treadmill.getInclineVariable())
				return treadmill.getInclineInit();
			return treadmill.getInclineInit() + treadmill.getInclineStep()
					* (step - 1);
		}

		@Override
		public String getStep_time() {
			TestsTreadmill treadmill = entity.getTestsTreadmill();
			return treadmill.getStepTime();
		}

		@Override
		public String getTime_total() {
			if (step == 1) {
				return getStep_time();
			}
			// get previous step
			AGridItem preItem = m_gridTreadmill.getItems().get(step - 2);
			return Helper.calculateDuration(preItem.getTime_total(),
					getStep_time());
		}
	}

	public class GridErgoItem extends AGridItem {
		Integer power;
		String cadence;

		public GridErgoItem(Integer step) {
			super(step);
		}

		public Integer getPower() {
			TestsErgo ergo = entity.getTestsErgo();
			return ergo.getPowerInit() + ergo.getPowerStep() * (step - 1);
		}

		public String getCadence() {
			TestsErgo ergo = entity.getTestsErgo();
			return "[" + ergo.getCadenceLow() + " - " + ergo.getCadenceHigh()
					+ "]";
		}

		@Override
		public String getStep_time() {
			TestsErgo ergo = entity.getTestsErgo();
			return ergo.getStepTime();
		}

		@Override
		public String getTime_total() {
			if (step == 1) {
				return getStep_time();
			}
			// get previous step
			AGridItem preItem = m_gridErgo.getItems().get(step - 2);
			return Helper.calculateDuration(preItem.getTime_total(),
					getStep_time());
		}
	}

	public class GridSwimItem extends FIXGRIDTreeItem implements
			java.io.Serializable {
		Integer step;
		Boolean topNode;
		Boolean valid;
		LactateSamples lactateSamples;
		Integer hr;
		Integer intensity; // just on top node
		String time;
		String comment;
		Split[] splits = new Split[maxSplits];
		int validNode;

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
					Split split = new Split();
					splits[i - 1] = split;
				}
				lactateSamples = new LactateSamples();
				// set valid node at top node
				((GridSwimItem) getParentNode()).setValidNode(getParentNode()
						.getChildNodes().size());
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
				lactateSamples = new LactateSamples(protocol.getLactate());
				if (protocol.getHr() != null)
					hr = Integer.valueOf(protocol.getHr());
				// splits, if null, create array with split count elements
				Integer count = entity.getTestsSwim().getSplits();
				String strSplits = protocol.getSplits();
				splits = Split.buildArray(strSplits, count);
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
			closeAllNodes();
		}

		public String getTargetTime() {
			if (isTopNode()) {
				String maxPerformance = entity.getTestsProtocol()
						.getPerformanceMax();
				if (maxPerformance == null)
					return null;
				return (Helper.percentageOfTime(maxPerformance, intensity));
			} else {
				return ((GridSwimItem) this.getParentNode()).getTargetTime();
			}
		}

		public String getTime() {
			if (topNode) {
				return ((GridSwimItem) this.getChildNodes().get(validNode))
						.getTime();
			}
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public Integer getHr() {
			if (topNode) {
				return ((GridSwimItem) this.getChildNodes().get(validNode))
						.getHr();
			}
			return hr;
		}

		public void setHr(Integer hr) {
			this.hr = hr;
		}

		public String getLactate() {
			if (topNode) {
				return ((GridSwimItem) this.getChildNodes().get(validNode)).lactateSamples
						.getSingleValue();
			}
			return lactateSamples.toString();
		}

		public void setLactate(String lactate) {
			this.lactateSamples.setLactateSamples(lactate);
		}

		public Split[] getSplits() {
			if (topNode) {
				return ((GridSwimItem) this.getChildNodes().get(validNode))
						.getSplits();
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

		public void onChangeItem(ActionEvent ae) {
			// if(!topNode)
			// m_gridSwim.ensureItemToBeDisplayed((GridSwimItem)getParentNode());
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
				GridSwimItem item = (GridSwimItem) getParentNode()
						.getChildNodes().get(last);
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
			selected.removeNode();
			// recalculate step numbers and total time
			int step = 1;
			for (FIXGRIDTreeItem item : root.getChildNodes()) {
				((GridSwimItem) item).setStep(step++);
			}
		}
	}

	private void fillSwimProtocol() {
		TestsSwim swim = entity.getTestsSwim();
		List<TestsSwimProtocol> protocols = new ArrayList<TestsSwimProtocol>();
		for (FIXGRIDTreeItem step : m_gridSwim.getRootNode().getChildNodes()) {
			for (FIXGRIDTreeItem attempt : step.getChildNodes()) {
				GridSwimItem item = (GridSwimItem) attempt;
				TestsSwimProtocolId id = new TestsSwimProtocolId(swim.getId(),
						((GridSwimItem) step).getStep(), item.getStep());
				TestsSwimProtocol protocol = new TestsSwimProtocol(id);
				protocol.setValid(item.isValid());
				protocol.setIntensity(item.getIntensity());
				protocol.setTime(item.getTime());
				if (item.getLactate() != null)
					protocol.setLactate(item.getLactate().toString());
				if (item.getHr() != null)
					protocol.setHr(item.getHr().toString());
				Integer count = entity.getTestsSwim().getSplits();
				protocol.setSplits(Split.buildString(item.getSplits(), count));
				protocol.setComment(item.getComment());
				protocols.add(protocol);
			}
		}
		swim.setSteps(protocols);
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

		List<Double> lactates = new ArrayList<Double>();
		List<Integer> hrs = new ArrayList<Integer>();
		List<Integer> o2_absorptions = new ArrayList<Integer>();
		List<Integer> co2_emissions = new ArrayList<Integer>();
		List<Double> rqs = new ArrayList<Double>();
		// build lists
		for (AGridItem item : grid.getItems()) {
			lactates.add(item.getLactate());
			hrs.add(item.getHr());
			o2_absorptions.add(item.getO2_absorption());
			co2_emissions.add(item.getCo2_emission());
			rqs.add(item.getRq());
		}
		// transform lists to JSON format
		String strLactates = ((JSONArray) JSONSerializer.toJSON(lactates))
				.toString();
		String strHrs = ((JSONArray) JSONSerializer.toJSON(hrs)).toString();
		String strO2_absorptions = ((JSONArray) JSONSerializer
				.toJSON(o2_absorptions)).toString();
		String strCo2_emissions = ((JSONArray) JSONSerializer
				.toJSON(co2_emissions)).toString();
		String strRqs = ((JSONArray) JSONSerializer.toJSON(rqs)).toString();
		TestsProtocol protocol = entity.getTestsProtocol();
		// fill protocol entity
		protocol.setCountSteps(count_steps);
		protocol.setLactate(strLactates);
		protocol.setHr(strHrs);
		protocol.setO2Absorption(strO2_absorptions);
		protocol.setCo2Emission(strCo2_emissions);
		protocol.setRq(strRqs);
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
		// handle JSON
		JSONArray lactates = null;
		String lactate = protocol.getLactate();
		if (lactate != null)
			lactates = (JSONArray) JSONSerializer.toJSON(lactate);

		JSONArray hrs = null;
		String hr = protocol.getHr();
		if (hr != null)
			hrs = (JSONArray) JSONSerializer.toJSON(hr);

		JSONArray o2_absorptions = null;
		String o2_absorption = protocol.getO2Absorption();
		if (o2_absorption != null)
			o2_absorptions = (JSONArray) JSONSerializer.toJSON(o2_absorption);

		JSONArray co2_emissions = null;
		String co2_emission = protocol.getCo2Emission();
		if (co2_emission != null)
			co2_emissions = (JSONArray) JSONSerializer.toJSON(co2_emission);

		JSONArray rqs = null;
		String rq = protocol.getRq();
		if (rq != null)
			rqs = (JSONArray) JSONSerializer.toJSON(rq);
		// treadmill
		if (isTreadmill()) {
			m_gridTreadmill.getItems().clear();
			for (int i = 0; i < protocol.getCountSteps(); i++) {
				GridTreadmillItem item = new GridTreadmillItem(i + 1);
				if (lactates != null
						&& lactates.get(i).getClass() != JSONNull.class)
					item.setLactate(lactates.getDouble(i));
				if (hrs != null && hrs.get(i).getClass() != JSONNull.class)
					item.setHr(hrs.getInt(i));
				if (o2_absorptions != null
						&& o2_absorptions.get(i).getClass() != JSONNull.class)
					item.setO2_absorption(o2_absorptions.getInt(i));
				if (co2_emissions != null
						&& co2_emissions.get(i).getClass() != JSONNull.class)
					item.setCo2_emission(co2_emissions.getInt(i));
				if (rqs != null && rqs.get(i).getClass() != JSONNull.class)
					item.setRq(rqs.getDouble(i));
				m_gridTreadmill.getItems().add(item);
			}
			return;
		}
		// ergo
		if (isErgo()) {
			m_gridErgo.getItems().clear();
			for (int i = 0; i < protocol.getCountSteps(); i++) {
				GridErgoItem item = new GridErgoItem(i + 1);
				if (lactates != null
						&& lactates.get(i).getClass() != JSONNull.class)
					item.setLactate(lactates.getDouble(i));
				if (hrs != null && hrs.get(i).getClass() != JSONNull.class)
					item.setHr(hrs.getInt(i));
				if (o2_absorptions != null
						&& o2_absorptions.get(i).getClass() != JSONNull.class)
					item.setO2_absorption(o2_absorptions.getInt(i));
				if (co2_emissions != null
						&& co2_emissions.get(i).getClass() != JSONNull.class)
					item.setCo2_emission(co2_emissions.getInt(i));
				if (rqs != null && rqs.get(i).getClass() != JSONNull.class)
					item.setRq(rqs.getDouble(i));
				m_gridErgo.getItems().add(item);
			}
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
			for (FIXGRIDTreeItem topItem : m_gridSwim.getRootNode()
					.getChildNodes()) {
				item = (GridSwimItem) topItem;
				if (item.getIntensity() == null)
					throw new MandatoryCheckException("Intensity");
				for (FIXGRIDTreeItem node : topItem.getChildNodes()) {
					item = (GridSwimItem) node;
					if (item.isValid()) {
						if (item.getTime() == null
								|| item.getTime().length() == 0)
							throw new MandatoryCheckException("Time");
						if (item.getLactate() == null)
							throw new MandatoryCheckException("Lactate");
					}
				}
			}
		}
	}

	// ------------------------------------------------------------------------
	// logic for analysis
	// ------------------------------------------------------------------------
	final String LACTATE = "Laktat";
	final String HR = "Puls";
	final String SPEED = "Geschwindigkeit";
	final String POWER = "Watt";
	final String UNIT_HR = "bpm";
	final String UNIT_WATT = "W";
	final String UNIT_MMOL = "mmol/l";
	final String UNIT_KMH = "km/h";
	final String UNIT_MMSS = "mm:ss";

	private byte[] m_diagram;
	private IResult result;
	private double[] hrs;
	private String descriptionX = null;
	private String unitX = null;
	private String descriptionY = null;
	private String unitY = null;

	protected int m_height = 300;

	public int getHeight() {
		return m_height;
	}

	public void setHeight(int value) {
		m_height = value;
	}

	protected int m_width = 400;

	public int getWidth() {
		return m_width;
	}

	public void setWidth(int value) {
		m_width = value;
	}

	protected double m_offset = 0;

	public double getOffset() {
		return m_offset;
	}

	public void setOffset(double value) {
		m_offset = value;
	}
	
	protected String m_function = getServiceLayer().getValueListBindingService().FUNCTIONS.getValidValues().next().getValue(); // init with first value    
	public String getFunction() { return m_function; }
    public void setFunction(String value) { m_function = value; }
    
    protected int m_degree;
    public int getDegree() { return m_degree; }
    public void setDegree(int value) { m_degree = value; }    

    protected double m_valueY;
    public double getValueY() { return m_valueY; }
    public void setValueY(double value) { m_valueY = value; }

    protected double m_valueX;
    public double getValueX() { return m_valueX; }
    public void setValueX(double value) { m_valueX = value; }
    
    protected Integer m_hr;
    public Integer getHr() { return m_hr; }
    public void setHr(Integer value) { m_hr = value; }
    
    public int getMaxWidth() { 
    	Statusbar.outputMessage("Actual width: " + Helper.getWidth());
    	return Helper.getWidth(); 
    }

	public ValidValuesBinding getFunctionsVvb() {		
		return getServiceLayer().getValueListBindingService().FUNCTIONS;
	}

	public void onRefresh(ActionEvent event) {
		if (result == null)
			result = analyze();
		// build the chart based on the computed result
		if(result==null) return;
		m_diagram = buildDiagram(result, hrs, m_width, m_height, descriptionX,
				unitX, descriptionY, unitY);
	}

	public void onChangeXY(ActionEvent event) {
		//cast
		//BaseActionEventFlush baef = (BaseActionEventFlush)event;
		String clientname = (String)event.getComponent().getAttributes().get(Constants.CLIENTNAME);
		try {
			// x value changed
			if("x".equals(clientname)) {
				m_valueY = result.getY(m_valueX);
			}
			// y value changed
			if("y".equals(clientname)) {
				m_valueX = result.getX(m_valueY);
			}
			if(hrs!=null) m_hr = (int)Math.round(trimatrix.utils.maths.Helper.getYFromMultiLinearFunction(hrs, m_valueX));
		} catch (Exception ex) {
			Statusbar.outputError("Problem to get calculate heartrate value!" , ex.toString());
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
		return String.valueOf(result.getCorrelation());
	}

	private IResult analyze() {
		double[] xyArr = null;
		this.hrs = null;
		// treadmill
		if (isTreadmill()) {
			descriptionX = SPEED;
			unitX = UNIT_KMH;
			descriptionY = LACTATE;
			unitY = UNIT_MMOL;

			TestsTreadmill treadmill = entity.getTestsTreadmill();
			TestsProtocol treadmillProt = entity.getTestsProtocol();
			int steps = treadmillProt.getCountSteps();
			// lactates
			JSONArray lactates = null;
			String lactate = treadmillProt.getLactate();
			if (lactate != null)
				lactates = (JSONArray) JSONSerializer.toJSON(lactate);
			xyArr = new double[steps * 2];
			// heartrate
			JSONArray hrs = null;
			String hr = treadmillProt.getHr();
			if(hr != null) {
				hrs = (JSONArray) JSONSerializer.toJSON(hr);
			}
			this.hrs = new double[steps*2];
			
			for (int i = 0; i < steps; i++) {
				// x value
				xyArr[2 * i] = treadmill.getSpeedInit()
						+ treadmill.getSpeedStep() * i;
				// y value
				xyArr[2 * i + 1] = lactates.getDouble(i);
				// hr value
				this.hrs[2 * i] = xyArr[2 * i];
				this.hrs[2 * i + 1] = hrs.getInt(i);
			}
		}
		// ergo
		if (isErgo()) {
			descriptionX = POWER;
			unitX = UNIT_WATT;
			descriptionY = LACTATE;
			unitY = UNIT_MMOL;

			TestsErgo ergo = entity.getTestsErgo();
			TestsProtocol ergoProt = entity.getTestsProtocol();
			int steps = ergoProt.getCountSteps();
			JSONArray lactates = null;
			String lactate = ergoProt.getLactate();
			if (lactate != null)
				lactates = (JSONArray) JSONSerializer.toJSON(lactate);
			xyArr = new double[steps * 2];
			// heartrate
			JSONArray hrs = null;
			String hr = ergoProt.getHr();
			if(hr != null) {
				hrs = (JSONArray) JSONSerializer.toJSON(hr);
			}
			this.hrs = new double[steps*2];
			
			for (int i = 0; i < steps; i++) {
				// x value
				xyArr[2 * i] = ergo.getPowerInit() + ergo.getPowerStep() * i;
				// y value
				xyArr[2 * i + 1] = lactates.getDouble(i);
				// hr value
				this.hrs[2 * i] = xyArr[2 * i];
				this.hrs[2 * i + 1] = hrs.getInt(i);
			}			
		}
		// swim
		if (isSwim()) {
			descriptionX = SPEED;
			unitX = UNIT_MMSS;
			descriptionY = LACTATE;
			unitY = UNIT_MMOL;

			// a bit complex
		}
		
		if(Constants.EXP.equals(m_function)) {
			RegressionFunctions function = new RegressionFunctions(
					RegressionFunctions.EXP_REGRESSION, xyArr, m_offset);
			return function.getResult();
		} 
		if(Constants.POLY.equals(m_function)) {
			try {
				PolynomialFunctions function = new PolynomialFunctions(xyArr, m_degree);
				return function.getResult();
			} catch (Exception ex) {
				Statusbar.outputError("Problem computing result for polnomial function", ex.toString());
				return null;
			}			
		}		
		Statusbar.outputError("Type of function does not exist!");
		return null;
	}

	// TODO put into logic layer

	private byte[] buildDiagram(IResult resultLactate, double[] hrs, int width, int height,
			String descriptionX, String unitX, String descriptionY, String unitY) {
		// constants
		final String MEASUREPOINTS = "Messpunkte";
		final int GRANULARITY = 10;

		byte[] diagram = null;

		// measuring points
		double[] measurePoints = resultLactate.getXYValues();
		XYSeries seriesPoints = new XYSeries(MEASUREPOINTS);
		for (int i = 1; i < measurePoints.length; i += 2) {
			seriesPoints.add(measurePoints[i - 1], measurePoints[i]);
		}

		int low = (int) (Math.floor(seriesPoints.getMinX()));
		int high = (int) (Math.ceil(seriesPoints.getMaxX()));
		int grain = (high - low) * GRANULARITY;

		// curve
		XYSeries seriesCurve = DatasetUtilities.sampleFunction2DToSeries(
				resultLactate.getFunction2D(), low, high, grain, descriptionY);

		// build data set for chart
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(seriesPoints);
		dataset.addSeries(seriesCurve);

		// build chart
		final JFreeChart chart = ChartFactory.createXYLineChart(null,
				descriptionX + "[" + unitX + "]", descriptionY + "[" + unitY
						+ "]", dataset, PlotOrientation.VERTICAL, true, true,
				false);

		// get a reference to the plot for further customization
		final XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesLinesVisible(0, false);
		renderer.setSeriesShapesVisible(1, false);
		plot.setRenderer(renderer);
		
		// add hr values
		if(hrs!=null) {
			XYSeries seriesHR = new XYSeries("Herzfrequenz");
			for(int i=1;i<hrs.length;i+=2){
				seriesHR.add(hrs[i-1], hrs[i]);
			}
			XYSeriesCollection datasetHR = new XYSeriesCollection();
			datasetHR.addSeries(seriesHR);
	        ValueAxis axis2 = new NumberAxis("Herzfrequenz");	        
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
			Statusbar.outputError("Error creating diagram!", ex.toString());
			return null;
		}
		return diagram;
	}
}
