package br.com.ejb.sessionsynchronization;

import br.com.ejb.model.Client;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/ClientServlet", loadOnStartup = 1)
public class ClientServlet extends HttpServlet {

	private static final long serialVersionUID = 7832814052943059218L;

	@EJB
	private ClientBean clientBean;

	@Override
	protected void doPost(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
		String name = httpServletRequest.getParameter("name");
		if (name == null) {
			name = "user";
		}
		final Client client = this.clientBean.save(new Client(null, name));
		httpServletResponse.getWriter().println(client);
	}

	@Override
	protected void doGet(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
		httpServletResponse.getWriter().println(this.clientBean.findAll());
	}
}
