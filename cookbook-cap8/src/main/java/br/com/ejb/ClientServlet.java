package br.com.ejb;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/Client", loadOnStartup = 1)
public class ClientServlet extends HttpServlet {

	private static final long serialVersionUID = 2880511806837032535L;

	@EJB
	private ClientBeanImpl clientBean;

	@Override
	protected void doGet(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
		final String name = httpServletRequest.getParameter("name");
		final PrintWriter writer = httpServletResponse.getWriter();
		writer.print("<html>");
		writer.print("<body>");
		writer.println("<h3> Name: " + this.clientBean.getClientName(name) + "</h3>");
		writer.println("<h3> Full Description: " + this.clientBean.getClientIdentity(name) + "</h3>");
		writer.print("</body>");
		writer.print("</html>");
	}
}
