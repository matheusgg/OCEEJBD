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

@WebServlet(urlPatterns = "/MessagesProducerServlet", loadOnStartup = 1)
public class MessagesProducerServlet extends HttpServlet {

	private static final long serialVersionUID = 5486236169555773630L;

	@Resource(lookup = "java:/ConnectionFactory")
	private QueueConnectionFactory connectionFactory;

	@Resource(lookup = "java:/queue/simple")
	private Queue queue;

	private Session session;
	private MessageProducer messageProducer;

	@Override
	public void init() throws ServletException {
		try {
			final Connection connection = this.connectionFactory.createQueueConnection();
			this.session = connection.createSession(false, AUTO_ACKNOWLEDGE);
			this.messageProducer = this.session.createProducer(this.queue);
		} catch (final JMSException e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter("message");
		if (message == null) {
			message = "No Message!";
		}

		String visibility = request.getParameter("visibility");
		if (visibility == null) {
			visibility = "Public";
		}

		try {
			final TextMessage textMessage = this.session.createTextMessage(message);
			textMessage.setStringProperty("visibility", visibility);
			this.messageProducer.send(textMessage);

			response.setContentType("text/plain");
			response.getWriter().println("Message Sent!");
		} catch (final Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
	public void destroy() {
		try {
			this.session.close();
		} catch (final JMSException e) {
			e.printStackTrace();
		}
	}
}
