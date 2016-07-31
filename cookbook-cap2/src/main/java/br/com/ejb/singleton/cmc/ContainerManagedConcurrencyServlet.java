package br.com.ejb.singleton.cmc;

import br.com.ejb.singleton.cmc.SimulationSingleton.Status;
import lombok.extern.java.Log;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log
@WebServlet(urlPatterns = "/ContainerManagedConcurrencyServlet", loadOnStartup = 1)
public class ContainerManagedConcurrencyServlet extends HttpServlet {

	private static final long serialVersionUID = -3682215783879128981L;

	@EJB
	private SimulationSingleton simulationSingleton;

	@Override
	protected void doGet(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
		log.info("===================== doGet() =====================");
		final String action = httpServletRequest.getParameter("action");

		if (action != null) {
			final Status status = Status.valueOf(action);
			if (Status.STARTED.equals(status)) {
				this.simulationSingleton.start();

			} else if (Status.FINISHED.equals(status)) {
				this.simulationSingleton.finish();
			}
		}

		httpServletResponse.getWriter().print("Status: " + this.simulationSingleton.status());
	}
}