package br.com.ejb.mdb;

import javax.annotation.Resource;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.jms.Session.AUTO_ACKNOWLEDGE;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

@WebServlet(urlPatterns = "/Request", loadOnStartup = 1)
public class RequestLoggerServlet extends HttpServlet {

	private static final long serialVersionUID = 3299552647794560751L;

	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/queue/request")
	private Queue queue;

	private Session session;

	private MessageProducer producer;

	@Override
	public void init() throws ServletException {
		try {
			final Connection connection = this.connectionFactory.createConnection();
			this.session = connection.createSession(false, AUTO_ACKNOWLEDGE);
			this.producer = this.session.createProducer(this.queue);
		} catch (final Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doGet(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
		try {
			final TextMessage host = this.session.createTextMessage(httpServletRequest.getRemoteAddr());
			this.producer.send(host);
		} catch (final JMSException e) {
			httpServletResponse.sendError(SC_INTERNAL_SERVER_ERROR);
		}
	}
}
