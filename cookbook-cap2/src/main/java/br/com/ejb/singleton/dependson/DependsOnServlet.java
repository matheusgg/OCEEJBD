package br.com.ejb.singleton.dependson;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/DependsOnServlet", loadOnStartup = 1)
public class DependsOnServlet extends HttpServlet {

	private static final long serialVersionUID = 6751916479021011748L;

	@EJB
	private SingletonBean2 singletonBean2;

	@Override
	protected void doGet(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
		httpServletResponse.getWriter().println(this.singletonBean2.getMessage());
	}
}
