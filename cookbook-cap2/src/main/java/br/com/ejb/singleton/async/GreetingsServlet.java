package br.com.ejb.singleton.async;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/GreetingsServlet", loadOnStartup = 1)
public class GreetingsServlet extends HttpServlet {

	private static final long serialVersionUID = 2767990170603158183L;

	@EJB
	private MessageLocalBean messageBean;

	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		try {
			this.messageBean.logExecution();
			String username = request.getParameter("username");
			if (username == null) {
				username = "anonymous";
			}
			response.getWriter().println(this.messageBean.getMessage(username).get());
		} catch (final Exception e) {
			throw new ServletException(e);
		}
	}
}
