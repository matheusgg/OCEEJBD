package br.com.ejb.mdb;

import javax.annotation.Resource;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import static javax.jms.Session.AUTO_ACKNOWLEDGE;

@WebServlet(urlPatterns = "/RemainingMessagesExtractorServlet", loadOnStartup = 1)
public class RemainingMessagesExtractorServlet extends HttpServlet {

	private static final long serialVersionUID = 8480236452112754458L;

	@Resource(lookup = "java:/ConnectionFactory")
	private QueueConnectionFactory connectionFactory;

	@Resource(lookup = "java:/queue/simple")
	private Queue queue;

	private Session session;

	@Override
	public void init() throws ServletException {
		try {
			final Connection connection = this.connectionFactory.createQueueConnection();
			this.session = connection.createSession(false, AUTO_ACKNOWLEDGE);
		} catch (final JMSException e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			final PrintWriter writer = response.getWriter();
			writer.print("<html>");
			writer.print("<body>");
			writer.print("<ul>");

			final QueueBrowser browser = this.session.createBrowser(this.queue, "visibility <> 'Public' AND visibility <> 'Private'");
			final Enumeration enumeration = browser.getEnumeration();
			while (enumeration.hasMoreElements()) {
				writer.print("<li>");
				writer.print(((TextMessage) enumeration.nextElement()).getText());
				writer.print("</li>");
			}

			writer.print("</ul>");
			writer.print("</body>");
			writer.print("</html>");
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
