package br.com.ejb.jndi;

import lombok.extern.java.Log;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Log
@WebServlet(urlPatterns = "/Greetings", loadOnStartup = 1)
public class GreetingsServlet extends HttpServlet {

	private static final long serialVersionUID = -8126284758424734012L;

	private GreetingsBeanImpl greetingsBean;

	@Override
	public void init() throws ServletException {
		try {
			log.info(InitialContext.doLookup("java:global/cookbook-cap1/GreetingsBeanImpl").toString());
			log.info(InitialContext.doLookup("java:app/cookbook-cap1/GreetingsBeanImpl").toString());
			log.info(InitialContext.doLookup("java:module/GreetingsBeanImpl").toString());

			log.info(InitialContext.doLookup("java:global/cookbook-cap1/GreetingsBeanImpl!br.com.ejb.jndi.GreetingsBeanImpl").toString());
			log.info(InitialContext.doLookup("java:app/cookbook-cap1/GreetingsBeanImpl!br.com.ejb.jndi.GreetingsBeanImpl").toString());
			log.info(InitialContext.doLookup("java:module/GreetingsBeanImpl!br.com.ejb.jndi.GreetingsBeanImpl").toString());

			this.greetingsBean = InitialContext.doLookup("java:global/cookbook-cap1/GreetingsBeanImpl");
		} catch (final Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doGet(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
		String username = httpServletRequest.getParameter("username");
		if (username == null) {
			username = "anonymous";
		}

		final PrintWriter writer = httpServletResponse.getWriter();
		writer.println("<html>");
		writer.println("<body>");
		writer.println("<h1>");
		writer.println("anonymous".equals(username) ? this.greetingsBean.getInformalGreeting(username) : this.greetingsBean.getFormalGreeting(username));
		writer.println("</h1>");
		writer.println("</body>");
		writer.println("</html>");
	}
}
