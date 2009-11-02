package trimatrix.logic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import trimatrix.db.DAOLayer;
import trimatrix.services.ServiceLayer;

public class TestLogic {
	public static final Log logger = LogFactory.getLog(TestLogic.class);
	private DAOLayer daoLayer;
	private ServiceLayer serviceLayer;
	
	public void deleteAllSwimProtocolls(String id) {
		int size = serviceLayer.getSqlExecutorService().deleteAllSwimProtocols(id);
		logger.debug(size + " : Swim protocols deleted!");
	}
	
	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}
	
	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}
}
