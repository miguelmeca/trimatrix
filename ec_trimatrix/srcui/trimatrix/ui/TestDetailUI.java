package trimatrix.ui;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Doctors;
import trimatrix.db.Persons;
import trimatrix.db.Tests;
import trimatrix.db.TestsErgo;
import trimatrix.db.TestsProtocol;
import trimatrix.db.TestsSwim;
import trimatrix.db.TestsTreadmill;
import trimatrix.entities.TestEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;

@CCGenClass (expressionBase="#{d.TestDetailUI}")

public class TestDetailUI extends AEntityDetailUI implements Serializable
{	
    public void onDoctorSearch(ActionEvent event) {
    	IEntitySelectionUI entitySelectionUI = getEntitySelectionUI(Constants.Entity.DOCTOR);
       	entitySelectionUI.prepareCallback(new EntitySelectionUI.ISelectionCallback(){
			public void cancel() {
				m_popup.close();				
			}
			public void idSelected(String id) {
				Doctors doctor = (Doctors)ENTITYLISTLOGIC.get(Constants.Entity.DOCTOR, id);
				entity.setDoctor(doctor);
				setDoctorDescription(entity);	
				m_popup.close();
			}});    	
    	m_popup = getWorkpage().createModalPopupInWorkpageContext();    
    	m_popup.setLeftTopReferenceCentered();
    	m_popup.open(Constants.Page.DOCTORSELECTION.getUrl(), Helper.getLiteral("doctor_search"), 800, 600, this);
    }

    public void onAthleteSearch(ActionEvent event) {
    	IEntitySelectionUI entitySelectionUI = getEntitySelectionUI(Constants.Entity.PERSON);
       	entitySelectionUI.prepareCallback(new EntitySelectionUI.ISelectionCallback(){
			public void cancel() {
				m_popup.close();				
			}
			public void idSelected(String id) {
				Persons person = (Persons)ENTITYLISTLOGIC.get(Constants.Entity.PERSON, id);
				entity.setAthlete(person);
				setAthleteDescription(entity);	
				m_popup.close();
			}});    	
    	m_popup = getWorkpage().createModalPopupInWorkpageContext();    
    	m_popup.setLeftTopReferenceCentered();
    	m_popup.open(Constants.Page.PERSONSELECTION.getUrl(), Helper.getLiteral("athlete_search"), 800, 600, this);
    }
    
    public void onTypeChange(ActionEvent event) {
    	entity.setType((String)values.get(TestEntity.TYPE));
    }
    
	private Tests entity;	
	
	public TestDetailUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher, new String[] {TestEntity.PERSON, TestEntity.TYPE, TestEntity.DATE}, true);
		// get wrapping entity detail UI bean
		entityDetailUI = getEntityDetailUI();		
		entityDetailUI.setEntityDetailUI(this);		
        // init data
        init(entityDetailUI.getEntityObject());
        // labeling
        setLabelRowDynamic();
	}
	
	protected ValidValuesBinding m_testTypesVvb = getServiceLayer().getValueListBindingService().getVVBinding(Constants.ValueList.TESTTYPE);
    public ValidValuesBinding getTestTypesVvb() { return m_testTypesVvb; }
	
    public void init(Object entityObject) {
    	// set entity object
    	this.entity = (Tests)entityObject;        	 	
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
    	if(entity.getTestsErgo()==null && isErgo()) {
			// create ergo
			TestsErgo ergo = new TestsErgo(entity.getId());
			getDaoLayer().getTestsErgoDAO().save(ergo);		
			entity.setTestsErgo(ergo);
			// delete treadmill
			if(entity.getTestsTreadmill()!=null) {
				getDaoLayer().getTestsTreadmillDAO().delete(entity.getTestsTreadmill()); 
				entity.setTestsTreadmill(null);
			}
			// delete swim
			if(entity.getTestsSwim()!=null) {
				getDaoLayer().getTestsSwimDAO().delete(entity.getTestsSwim()); 
				entity.setTestsSwim(null);
			}			
			return;
		} 
    	// treadmill
    	if(entity.getTestsTreadmill()==null && isTreadmill()) {
			// create treadmill
			TestsTreadmill treadmill = new TestsTreadmill(entity.getId());
			getDaoLayer().getTestsTreadmillDAO().save(treadmill);		
			entity.setTestsTreadmill(treadmill);
			// delete ergo
			if(entity.getTestsErgo()!=null) {
				getDaoLayer().getTestsErgoDAO().delete(entity.getTestsErgo()); 
				entity.setTestsErgo(null);
			}
			// delete swim
			if(entity.getTestsSwim()!=null) {
				getDaoLayer().getTestsSwimDAO().delete(entity.getTestsSwim()); 
				entity.setTestsSwim(null);
			}			
			return;
		} 
    	// swim
    	if(entity.getTestsSwim()==null && isSwim()) {
			// create swim
			TestsSwim swim = new TestsSwim(entity.getId());
			getDaoLayer().getTestsSwimDAO().save(swim);		
			entity.setTestsSwim(swim);
			// delete treadmill
			if(entity.getTestsTreadmill()!=null) {
				getDaoLayer().getTestsTreadmillDAO().delete(entity.getTestsTreadmill()); 
				entity.setTestsTreadmill(null);
			}
			// delete ergo
			if(entity.getTestsErgo()!=null) {
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
		// fill values to entities properties
		fillEntityProperties();		
	}
    
    private void fillEntityProperties() {
		// detail
		entity.setDescription((String)values.get(TestEntity.DESCRIPTION));
		Timestamp timestamp = null;
		Date date = (Date)values.get(TestEntity.DATE);
		if (date!=null) {
			timestamp = new Timestamp(date.getTime());
		} 
		entity.setDate(timestamp);
		entity.setType((String)values.get(TestEntity.TYPE));
		setAthleteDescription(entity);
		setDoctorDescription(entity);
		// ergo		
		if(isErgo()) {
			TestsErgo ergo = entity.getTestsErgo();
			ergo.setPowerInit((Integer)values.get(TestEntity.POWER_INIT));
			ergo.setPowerStep((Integer)values.get(TestEntity.POWER_STEP));
			ergo.setCadenceLow((Integer)values.get(TestEntity.CADENCE_LOW));
			ergo.setCadenceHigh((Integer)values.get(TestEntity.CADENCE_HIGH));
			ergo.setStepTime((String)values.get(TestEntity.ERGO_STEP_TIME));			
		}
		// treadmill
		if(isTreadmill()) {
			TestsTreadmill treadmill = entity.getTestsTreadmill();
			treadmill.setSpeedVariable((Boolean)values.get(TestEntity.SPEED_VARIABLE));
			treadmill.setSpeedInit((Double)values.get(TestEntity.SPEED_INIT));			
			treadmill.setSpeedStep((Double)values.get(TestEntity.SPEED_STEP));
			treadmill.setInclineVariable((Boolean)values.get(TestEntity.INCLINE_VARIABLE));
			treadmill.setInclineInit((Integer)values.get(TestEntity.INCLINE_INIT));
			treadmill.setInclineStep((Integer)values.get(TestEntity.INCLINE_STEP));
			treadmill.setStepTime((String)values.get(TestEntity.TREADMILL_STEP_TIME));
		}
		// swim
		if(isSwim()) {
			TestsSwim swim = entity.getTestsSwim();
			swim.setAssistantName((String)values.get(TestEntity.ASSISTANT_NAME));
			swim.setBaths((String)values.get(TestEntity.BATHS));
			swim.setPool((String)values.get(TestEntity.POOL));
			Timestamp timestamp2 = null;
			Date date2 = (Date)values.get(TestEntity.DATE2);
			if (date2!=null) {
				timestamp2 = new Timestamp(date2.getTime());
			} 
			swim.setDate2(timestamp2);
			swim.setDistance((Integer)values.get(TestEntity.DISTANCE));
			swim.setSplits((Integer)values.get(TestEntity.SPLITS));
		}
		// protocol
		if(isProtocol()) {
			TestsProtocol protocol = entity.getTestsProtocol();
			protocol.setDescription((String)values.get(TestEntity.PROTOCOL_DESCRIPTION));
			protocol.setModel((String)values.get(TestEntity.MODEL));
			protocol.setModelLactate((String)values.get(TestEntity.MODEL_LACTATE));
			protocol.setModelSpiro((String)values.get(TestEntity.MODEL_SPIRO));			
			fillProtocol();		
		}		
	}
	
	private void fillMaps() {
		// add values of fields
		values.clear();
		values.put(TestEntity.DESCRIPTION, entity.getDescription());
		Date date = null;
		Timestamp timestamp = entity.getDate();
		if (timestamp!=null) {
			date =  new Date(timestamp.getTime());
		} 
		values.put(TestEntity.DATE, date);
		values.put(TestEntity.TYPE, entity.getType());
		setAthleteDescription(entity);	
		setDoctorDescription(entity);	
		
		// ergo
		TestsErgo ergo = entity.getTestsErgo();
		if(ergo!=null) {
			values.put(TestEntity.POWER_INIT, ergo.getPowerInit());
			values.put(TestEntity.POWER_STEP, ergo.getPowerStep());
			values.put(TestEntity.CADENCE_LOW, ergo.getCadenceLow());
			values.put(TestEntity.CADENCE_HIGH, ergo.getCadenceHigh());
			values.put(TestEntity.ERGO_STEP_TIME, ergo.getStepTime());
		}
		
		// treadmill		
		TestsTreadmill treadmill = entity.getTestsTreadmill();
		if(treadmill!=null) {
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
		if(swim!=null) {
			Date date2 = null;
			Timestamp timestamp2 = swim.getDate2();
			if (timestamp2!=null) {
				date2 =  new Date(timestamp2.getTime());
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
		if(protocol!=null) {
			values.put(TestEntity.PROTOCOL_DESCRIPTION, protocol.getDescription());
			values.put(TestEntity.MODEL, protocol.getModel());
			values.put(TestEntity.MODEL_LACTATE, protocol.getModelLactate());
			values.put(TestEntity.MODEL_SPIRO, protocol.getModelSpiro());
			try {
				buildProtocolGrid();
			} catch (Exception ex) {
				Statusbar.outputAlert(ex.toString(), "Testprotokoll schreiben");
			}
		}
		
		// add bgpaint of fields
		bgpaint.clear();
		// mandatory fields
		for(String field : MANDATORY_FIELDS){
			bgpaint.put(field,Constants.BGP_MANDATORY);
		}		
	}
	
	private void setAthleteDescription(Tests test) {
		Persons athlete = test.getAthlete();
		String athleteDescription = Constants.EMPTY;
		if (athlete!=null) {
			athleteDescription = athlete.toString();
		}
		values.put(TestEntity.PERSON, athleteDescription);
	}
	
	private void setDoctorDescription(Tests test) {
		Doctors doctor = test.getDoctor();
		String doctorDescription = Constants.EMPTY;
		if (doctor!=null) {
			doctorDescription = doctor.toString();
		}
		values.put(TestEntity.DOCTOR, doctorDescription);
	}
	
	public boolean isErgo() {
		if (entity.getType()==null || !entity.getType().equals("ergo")) return false;		
		return true;
	}
	
	public boolean isTreadmill() {
		if (entity.getType()==null || !entity.getType().equals("treadmill")) return false;
		return true;
	}
	
	public boolean isSwim() {
		if (entity.getType()==null || !entity.getType().equals("swim")) return false;
		return true;
	}
	
	public boolean isTypeEnabled() {
		// type just enabled as long as no protocol is created
		if(!isProtocol() && super.m_enabled) return true;
		return false;
	}
	
	public boolean isProtocol() {
		if(entity.getTestsProtocol()!=null) return true;
		return false;
	}
	
	public void onProtocolCreate(ActionEvent event) {
		// create protocol
		TestsProtocol protocol = new TestsProtocol(entity.getId());
		getDaoLayer().getTestsProtocolDAO().save(protocol);		
		entity.setTestsProtocol(protocol);
    }	
	
	// ------------------------------------------------------------------------
	// logic for treadmill protocol
	// ------------------------------------------------------------------------	
	protected FIXGRIDListBinding<GridTreadmillItem> m_gridTreadmill = new FIXGRIDListBinding<GridTreadmillItem>(true);
    public FIXGRIDListBinding<GridTreadmillItem> getGridTreadmill() { return m_gridTreadmill; }
 
    protected FIXGRIDListBinding<GridErgoItem> m_gridErgo = new FIXGRIDListBinding<GridErgoItem>(true);
    public FIXGRIDListBinding<GridErgoItem> getGridErgo() { return m_gridErgo; }
    
    public class AGridItem extends FIXGRIDItem implements java.io.Serializable {
    	Integer step;
    	String step_time;
    	String time_total;
    	Double lactate;
    	Integer hr;
    	Integer o2_absorption;
    	Integer co2_emission;
    	Double rq;
    	
    	public AGridItem(Integer step) {
    		this.step = step;
    	}
    	
		public Integer getStep() {return step;}
		public void setStep(Integer step) { this.step = step; }
		
		public Double getSpeed() {
			TestsTreadmill treadmill = entity.getTestsTreadmill();
			if(!treadmill.getSpeedVariable()) return treadmill.getSpeedInit();
			return treadmill.getSpeedInit() + treadmill.getSpeedStep() * (step-1);
		}
		
		public Integer getIncline() {
			TestsTreadmill treadmill = entity.getTestsTreadmill();
			if(!treadmill.getInclineVariable()) return treadmill.getInclineInit();
			return treadmill.getInclineInit() + treadmill.getInclineStep() * (step-1);
		}		
		
		public String getStep_time() {
			TestsTreadmill treadmill = entity.getTestsTreadmill();
			return treadmill.getStepTime();
		}
		
		public String getTime_total() {
			if(step==1) {
				return getStep_time();
			}
			// get previous step
			AGridItem preItem = m_gridTreadmill.getItems().get(step-2);
			return Helper.calculateDuration(preItem.getTime_total(), getStep_time());			
		}
		
		public Double getLactate() {return lactate;}
		public void setLactate(Double lactate) {this.lactate = lactate;}
		
		public Integer getHr() {return hr;}
		public void setHr(Integer hr) {this.hr = hr;}
		
		public Integer getO2_absorption() {return o2_absorption;}		
		public void setO2_absorption(Integer o2_absorption) {this.o2_absorption = o2_absorption;}
		
		public Integer getCo2_emission() {return co2_emission;}
		public void setCo2_emission(Integer co2_emission) {this.co2_emission = co2_emission;}
		
		public Double getRq() {return rq;}
		public void setRq(Double rq) {this.rq = rq;}
    }
    
    public class GridTreadmillItem extends AGridItem {
    	Double speed;
    	Integer incline;
    	
    	public GridTreadmillItem(Integer step) {
    		super(step);
    	}	
		
		public Double getSpeed() {
			TestsTreadmill treadmill = entity.getTestsTreadmill();
			if(!treadmill.getSpeedVariable()) return treadmill.getSpeedInit();
			return treadmill.getSpeedInit() + treadmill.getSpeedStep() * (step-1);
		}
		
		public Integer getIncline() {
			TestsTreadmill treadmill = entity.getTestsTreadmill();
			if(!treadmill.getInclineVariable()) return treadmill.getInclineInit();
			return treadmill.getInclineInit() + treadmill.getInclineStep() * (step-1);
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
			return ergo.getPowerInit() + ergo.getPowerStep() * (step-1);
		}
		
		public String getCadence() {
			TestsErgo ergo = entity.getTestsErgo();			
			return "[" + ergo.getCadenceLow() + " - " + ergo.getCadenceHigh() + "]";
		}			
    }
    
    public void onAddItem(ActionEvent ae) {
    	// treadmill
    	if(isTreadmill()) {
    		int step = m_gridTreadmill.getRows().size()+1;
        	GridTreadmillItem item = new GridTreadmillItem(step);
        	m_gridTreadmill.getItems().add(item);
        	return;
    	}
    	// ergo
    	if(isErgo()) {
    		int step = m_gridErgo.getRows().size()+1;
        	GridErgoItem item = new GridErgoItem(step);
        	m_gridErgo.getItems().add(item);
        	return;
    	}
    	
    }
    
    public void onRemoveItem(ActionEvent ae) {
    	FIXGRIDListBinding<? extends AGridItem> grid = null;
    	// treadmill
    	if(isTreadmill()) {
    		grid = m_gridTreadmill;
    	}
    	// ergo
    	if(isErgo()) {
    		grid = m_gridErgo;
    	}
    	if(grid==null) return;
    	
    	AGridItem selected = grid.getSelectedItem();
    	if(selected==null) return;
    	grid.getItems().remove(selected);
    	// recalculate step numbers and total time
    	int step = 1;
    	for (AGridItem item : grid.getItems()) {
    		item.setStep(step++);
    	}    	
    }   
    
    private void fillProtocol() {
    	FIXGRIDListBinding<? extends AGridItem> grid = null;
    	// treadmill
    	if(isTreadmill()) {
    		grid = m_gridTreadmill;
    	}
    	// ergo
    	if(isErgo()) {
    		grid = m_gridErgo;
    	}
    	if(grid==null) return;
    	
    	int count_steps = grid.getItems().size();    
    	
    	List<Double> lactates = new ArrayList<Double>();
    	List<Integer> hrs = new ArrayList<Integer>();
    	List<Integer> o2_absorptions = new ArrayList<Integer>();
    	List<Integer> co2_emissions = new ArrayList<Integer>();
    	List<Double> rqs = new ArrayList<Double>();
    	// build lists
    	for (AGridItem item : grid.getItems()) {
    		lactates.add(item.lactate);
    		hrs.add(item.hr);
    		o2_absorptions.add(item.o2_absorption);
    		co2_emissions.add(item.co2_emission);
    		rqs.add(item.rq);
    	}
    	// transform lists to JSON format
    	String strLactates = ((JSONArray)JSONSerializer.toJSON(lactates)).toString();
    	String strHrs = ((JSONArray)JSONSerializer.toJSON(hrs)).toString();
    	String strO2_absorptions = ((JSONArray)JSONSerializer.toJSON(o2_absorptions)).toString();
    	String strCo2_emissions = ((JSONArray)JSONSerializer.toJSON(co2_emissions)).toString();
    	String strRqs = ((JSONArray)JSONSerializer.toJSON(rqs)).toString();
    	TestsProtocol protocol = entity.getTestsProtocol();
    	// fill protocol entity
    	protocol.setCountSteps(count_steps);
    	protocol.setLactate(strLactates);
    	protocol.setHr(strHrs);
    	protocol.setO2Absorption(strO2_absorptions);
    	protocol.setCo2Emission(strCo2_emissions);    	
    	protocol.setRq(strRqs);
    }
    
    @SuppressWarnings("unchecked")
	private void buildProtocolGrid() throws Exception {
    	TestsProtocol protocol = entity.getTestsProtocol();
    	// transform JSON to lists    	
   		List<Double> lactates = null;   		
   		String lactate = protocol.getLactate();
   		if(lactate!=null) lactates = (List<Double>) JSONSerializer.toJava(JSONSerializer.toJSON(lactate));     	   		
    	List<Integer> hrs = null;
    	String hr = protocol.getHr();
    	if(hr!=null) hrs = (List<Integer>) JSONSerializer.toJava(JSONSerializer.toJSON(hr)); 	    	
    	List<Integer> o2_absorptions = null;
    	String o2_absorption = protocol.getO2Absorption();
    	if(o2_absorption!=null) o2_absorptions = (List<Integer>) JSONSerializer.toJava(JSONSerializer.toJSON(o2_absorption));
    	List<Integer> co2_emissions = null;
    	String co2_emission = protocol.getCo2Emission();
    	if(co2_emission!=null) co2_emissions = (List<Integer>) JSONSerializer.toJava(JSONSerializer.toJSON(co2_emission));
    	List<Double> rqs = null;   		
   		String rq = protocol.getRq();
   		if(rq!=null) rqs = (List<Double>) JSONSerializer.toJava(JSONSerializer.toJSON(rq));    	
    	// treadmill
    	if(isTreadmill()) {
    		m_gridTreadmill.getItems().clear();
        	for(int i = 1; i<=protocol.getCountSteps();i++) {
        		GridTreadmillItem item = new GridTreadmillItem(i);   
        		if(lactates!=null) item.setLactate(lactates.get(i-1));   
        		if(hrs!=null) item.setHr(hrs.get(i-1));
        		if(o2_absorptions!=null) item.setO2_absorption(o2_absorptions.get(i-1));
        		if(co2_emissions!=null) item.setCo2_emission(co2_emissions.get(i-1));
        		if(rqs!=null) item.setRq(rqs.get(i-1));
        		m_gridTreadmill.getItems().add(item);
        	}
        	return;
    	}
    	// ergo
    	if(isErgo()) {
    		m_gridErgo.getItems().clear();
        	for(int i = 1; i<=protocol.getCountSteps();i++) {
        		GridErgoItem item = new GridErgoItem(i);   
        		if(lactates!=null) item.setLactate(lactates.get(i-1));   
        		if(hrs!=null) item.setHr(hrs.get(i-1));
        		if(o2_absorptions!=null) item.setO2_absorption(o2_absorptions.get(i-1));
        		if(co2_emissions!=null) item.setCo2_emission(co2_emissions.get(i-1));
        		if(rqs!=null) item.setRq(rqs.get(i-1));
        		m_gridErgo.getItems().add(item);
        	}
        	return;
    	}    	
    }
}