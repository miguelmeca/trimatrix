package trimatrix.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.PropertyFactoryRegistry;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Version;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import trimatrix.db.Schedules;
import trimatrix.logic.LogicLayer;
import trimatrix.services.ServiceLayer;
import trimatrix.utils.Helper;

public class ICalServlet extends HttpServlet {
	/**
	 * Constructor of the object.
	 */
	public ICalServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	@Override
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 *
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		// get parameters
		String personId = request.getParameter("id");
		if(Helper.isEmpty(personId)) {
			response.setContentType("text/html");
			response.getWriter().println("Parameter ID is missing!");
			return;
		}

		// access to spring context
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		LogicLayer logicLayer = LogicLayer.getFromApplicationContext(context);
		ServiceLayer serviceLayer = ServiceLayer.getFromApplicationContext(context);
		// build calendar
		response.setContentType("text/calendar");
		final Calendar calendar = new Calendar();
		calendar.getProperties().add(new ProdId("-//Trimatrix//Schedules 1.0//EN"));
		calendar.getProperties().add(Version.VERSION_2_0);
		calendar.getProperties().add(CalScale.GREGORIAN);
		// extensions

		// select schedules from db
		List<Schedules> schedules = logicLayer.getScheduleLogic().getSchedulesByQuery(personId, null, null);
		// build events
		for(Schedules schedule : schedules) {
			//String type = serviceLayer.getTranslationService().getDescription(schedule.getType(), TranslationService.TYPE.SCHEDULETYPES);
			String type = schedule.getType();
			DateTime start = new DateTime(schedule.getStart().getTime());
			DateTime end = new DateTime(start.getTime() + schedule.getDuration() * 60000);
			VEvent event = new VEvent(start, type);
			event.getProperties().add(new DtEnd(end));
			event.getProperties().add(new Description(logicLayer.getScheduleLogic().getSummary(schedule)));
			event.getProperties().add(new Uid(schedule.getId()+ "@trimatrix.com"));
			calendar.getComponents().add(event);
		}
		// output data
		final CalendarOutputter cout = new CalendarOutputter();
		try {
			cout.output(calendar, response.getOutputStream());
		} catch (ValidationException ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 *
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	@Override
	public void init() throws ServletException {
		// Put your code here
	}
}
