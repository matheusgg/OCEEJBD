package br.com.servlets;

import br.com.ejb.noview.NoViewBeanImpl;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 08/02/16.
 */
@WebServlet(value = "/Cap4Servlet", loadOnStartup = 1)
public class Cap4Servlet extends HttpServlet {

	private static final long serialVersionUID = 364768801259897697L;

	@EJB
	private NoViewBeanImpl noViewBean;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println(this.noViewBean.createMsg());
	}

}
