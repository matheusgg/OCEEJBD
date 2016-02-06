package br.com.servlets;

import br.com.ejb.xml.EJBDeploymentDescriptorBeanRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/EJBDeploymentDescriptorServlet", loadOnStartup = 1)
public class EJBDeploymentDescriptorServlet extends HttpServlet {

	private static final long serialVersionUID = -3042452689159899473L;

	@EJB
	private EJBDeploymentDescriptorBeanRemote EJBDeploymentDescriptorBeanRemote;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println(this.EJBDeploymentDescriptorBeanRemote.createMsg());
	}
}
