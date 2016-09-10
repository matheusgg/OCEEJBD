package br.com.ejb;

import lombok.extern.java.Log;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Log
@WebServlet(urlPatterns = "/SimpleServlet", loadOnStartup = 1)
public class SimpleServlet extends HttpServlet {

	private static final long serialVersionUID = -3333516295266184571L;

	@EJB
	private SimpleBeanImpl simpleBean;

	@EJB
	private SecondBeanImpl secondBean;

	@Override
	protected void doGet(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
		final PrintWriter writer = httpServletResponse.getWriter();

		final String execute = this.simpleBean.execute();
		log.info(execute);
		writer.println(execute);

		final String execute2 = this.simpleBean.execute("Method");
		log.info(execute2);
		writer.println(execute2);

		final String message = this.secondBean.getMessage();
		log.info(message);
		writer.println(message);

		final String message2 = this.secondBean.getMessage2();
		log.info(message2);
		writer.println(message2);
	}
}
