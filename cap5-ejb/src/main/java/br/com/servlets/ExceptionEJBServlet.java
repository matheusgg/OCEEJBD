package br.com.servlets;

import br.com.ejb.exception.DivideByZeroException;
import br.com.ejb.exception.InvalidOperationApplicationException;
import br.com.ejb.exception.ExceptionBeanRemote;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/ExceptionEJBServlet", loadOnStartup = 1)
public class ExceptionEJBServlet extends HttpServlet {

	private static final long serialVersionUID = -3042452689159899473L;

	@EJB
	private ExceptionBeanRemote exceptionBeanRemote;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num1 = Integer.parseInt(req.getParameter("num1"));
		int num2 = Integer.parseInt(req.getParameter("num2"));

		try {
			resp.getWriter().println(this.exceptionBeanRemote.divide(num1, num2));
		} catch (EJBException | DivideByZeroException | InvalidOperationApplicationException e) {
			throw new ServletException(e);
		}
	}
}
