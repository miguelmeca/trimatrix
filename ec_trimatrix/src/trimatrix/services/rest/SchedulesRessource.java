package trimatrix.services.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sun.jersey.spi.inject.Inject;
import com.sun.jersey.spi.resource.Singleton;

import trimatrix.db.DAOLayer;
import trimatrix.db.Schedules;

@Produces("application/json")
@Consumes("application/json")
@Path("schedules")
@Scope("request")
@Component
@Singleton
public class SchedulesRessource {

	@Inject private DAOLayer daoLayer;

	@GET
	@Path("{user}")
	@Produces("application/json")
	public List<Schedules> getSchedules(@PathParam("user") String user) {
		return daoLayer.getSchedulesDAO().findAll();
	}

	public void setDaoLayer(DAOLayer daoLayer) {
        this.daoLayer = daoLayer;
    }
}