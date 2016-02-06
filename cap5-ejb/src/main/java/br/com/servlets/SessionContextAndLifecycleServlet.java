package br.com.servlets;

import br.com.ejb.lifecycle.LifecycleBeanLocal;
import br.com.ejb.model.SumModel;
import br.com.ejb.sessioncontext.SessionContext1BeanRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/SessionContextAndLifecycleServlet", loadOnStartup = 1)
public class SessionContextAndLifecycleServlet extends HttpServlet {

	private static final long serialVersionUID = -2884643158264091015L;

	@EJB
	private SessionContext1BeanRemote sessionContext1BeanRemote;

	@EJB
	private LifecycleBeanLocal someBean3;

	/**
	 * É possivel injetar um SessionContext apenas dentro de um EJB.
	 */
	// @Resource
	// private SessionContext sessionContext;
	@Override
	protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
		httpServletResponse.getWriter().println(this.sessionContext1BeanRemote.getMessage());

		/*
		 * Diferentemente da chamada remota, quando um EJB é invocado atraves de sua interface local os valores dos parametros utilizados para
		 * chamar os metodos de negocio sao passados como referencia, ao inves de copiar todos os valores. Deste modo, qualquer alteracao no
		 * objeto sumModel refletira dentro do método sum do EJB LifecycleBeanImpl. Isso ocorre porque as chamadas atraves de view Local ocorrem dentro
		 * da mesma VM, nao sendo necessário assim, a serializacao e a desserializacao dos parametros, desta forma, é possível passar apenas a referencia
		 * dos mesmos.
		 */
		SumModel sumModel = new SumModel(20, 50);
		httpServletResponse.getWriter().println(this.someBean3.sum(sumModel));
		sumModel.setNum1(50);
		httpServletResponse.getWriter().println(this.someBean3.sum(null));
	}
}
