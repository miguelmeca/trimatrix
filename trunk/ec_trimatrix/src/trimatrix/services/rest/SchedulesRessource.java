package trimatrix.services.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import trimatrix.db.Schedules;
import trimatrix.services.ServiceLayer;

import com.sun.jersey.api.json.JSONWithPadding;
import com.sun.jersey.spi.inject.Inject;
import com.sun.jersey.spi.resource.Singleton;

@Produces("application/json")
@Consumes("application/json")
@Path("schedules")
@Scope("request")
@Component
@Singleton
public class SchedulesRessource {

	@Inject private ServiceLayer serviceLayer;

	@GET
	@Path("{user}")
	@Produces("application/x-javascript")
	public JSONWithPadding getSchedules(@QueryParam("callback") @DefaultValue("fn") String callback, @PathParam("user") String user) {
		List<Schedules> schedules = serviceLayer.getSqlExecutorService().getSchedules(user, null, null, false);
		JSONWithPadding jsonp = new JSONWithPadding(new GenericEntity<List<Schedules>>(schedules) {}, callback);
		return jsonp;
		//return "{productName: 'Sencha Touch',version: '1.0 Alpha',sampleData: [{desc: 'This sample content is loaded from the server.'}]";
	}
}