package trimatrix.logic;

import java.util.List;

import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.springframework.dao.DataIntegrityViolationException;

import trimatrix.db.CompetitionsScouts;
import trimatrix.db.CompetitionsScoutsId;
import trimatrix.db.DAOLayer;
import trimatrix.db.IComplexDAO;
import trimatrix.db.ListVariants;
import trimatrix.db.ListVariantsId;
import trimatrix.entities.IEntityData;
import trimatrix.entities.IEntityObject;
import trimatrix.services.ServiceLayer;
import trimatrix.structures.SGridMetaData;
import trimatrix.structures.SListVariant;
import trimatrix.utils.Constants;

public class EntityListLogic {	
	private ServiceLayer serviceLayer;
	private DAOLayer daoLayer;
		
	/**
	 * Get metadata of entity
	 * @param entity entity
	 * @return metadata of entity
	 */
	public List<SGridMetaData> getGridMetaData(Constants.Entity entity) {
		return serviceLayer.getResolverService().getGridMetaData(entity);
	}
	
	public List<IEntityData> getData(Constants.Entity entity) {
		return serviceLayer.getResolverService().getData(entity);
	}	
	
	public List<IEntityData> getData(Constants.Entity entity, String personId) {
		if(personId==null || personId.length() == 0) {
			return serviceLayer.getResolverService().getData(entity);
		} 
		return serviceLayer.getResolverService().getData(entity, personId);
	}
	
	public List<IEntityData> getData(Constants.Entity entity, List<String> ids) {
		return serviceLayer.getResolverService().getData(entity, ids);
	}
	
	public boolean delete(Constants.Entity entity, String id) {
		return serviceLayer.getResolverService().delete(entity, id);
	}
	
	public boolean delete(Constants.Entity entity, String id, String personId) {
		return serviceLayer.getResolverService().delete(entity, id, personId);
	}
	
	public IEntityObject create(Constants.Entity entity) {
		return serviceLayer.getResolverService().create(entity);
	}
	
	public IEntityObject get(Constants.Entity entity, String id) {
		return serviceLayer.getResolverService().get(entity, id);
	}
	
	public IEntityObject save(Constants.Entity entity, IEntityObject entityObject) {
		return serviceLayer.getResolverService().save(entity, entityObject);
	}
	
	public void reload(Constants.Entity entity, IEntityObject entityObject) {
		serviceLayer.getResolverService().reload(entity, entityObject);
	}
	
	public IEntityObject copy(Constants.Entity entity, IEntityObject entityObject) {
		return serviceLayer.getResolverService().copy(entity, entityObject);
	}
	
	public boolean isCopyable(Constants.Entity entity, IEntityObject entityObject) {
		return serviceLayer.getResolverService().isCopyable(entity, entityObject);
	}
	
	public void saveGridState(Constants.Entity entity, SListVariant data) {
		String user_id = serviceLayer.getDictionaryService().getMyUser().getId();
		ListVariantsId lv_id = new ListVariantsId(Constants.P_ENTITYLIST, entity.name(), user_id);
		ListVariants lv = new ListVariants(lv_id, data.columnsSequence, data.columnsWidth);
		daoLayer.getListVariantsDAO().merge(lv);
	}
	
	public SListVariant loadGridState(Constants.Entity entity) {
		String user_id = serviceLayer.getDictionaryService().getMyUser().getId();
		ListVariantsId lv_id = new ListVariantsId(Constants.P_ENTITYLIST, entity.name(), user_id);
		IComplexDAO<ListVariants, ListVariantsId> dao = daoLayer.getListVariantsDAO();
		ListVariants lv = dao.findById(lv_id);		
		if (lv==null) return null;
		return new SListVariant(lv.getColumnsSequence(), lv.getColumnsWidth());
	}
	
	public boolean isPersonEqualPersonLoggedOn(String personId) {
		return personId.equals(serviceLayer.getDictionaryService().getMyPerson().getId());
	}
	
	public boolean isUserEqualUserLoggedOn(String userId) {
		return userId.equals(serviceLayer.getDictionaryService().getMyUser().getId());
	}
	
	// ----------------------------------------------------------------------
	// Competition entity
	// ----------------------------------------------------------------------
	public CompetitionsScouts getCompetitionScouts(String competitionId) {
		CompetitionsScoutsId csId = new CompetitionsScoutsId();
		csId.setCompetitionId(competitionId);
		csId.setScoutId(serviceLayer.getDictionaryService().getMyPerson().getId());
		return daoLayer.getCompetitionsScoutsDAO().findById(csId);
	}
	
	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}

	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}	
}
