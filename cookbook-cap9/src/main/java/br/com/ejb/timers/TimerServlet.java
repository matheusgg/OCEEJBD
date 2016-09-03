package br.com.ejb.timers;

import javax.ejb.EJB;
import javax.ejb.Timer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/Timers", loadOnStartup = 1)
public class TimerServlet extends HttpServlet {

	private static final long serialVersionUID = 734525925018903085L;

	@EJB
	private MemoryReportBeanImpl memoryReportBean;

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final PrintWriter writer = response.getWriter();
		writer.print("<html>");
		writer.print("<body>");
		writer.print("<h1>Timers</h1>");
		writer.print("<ul>");

		for (final Timer timer : this.memoryReportBean.getTimers()) {
			writer.print("<li>");
			writer.print(timer.getInfo());
			writer.print("</li>");
		}

		writer.print("</ul>");
		writer.print("</body>");
		writer.print("</html>");
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final String timerId = request.getParameter("timerId");
		if (timerId != null) {
			this.memoryReportBean.cancelTimer(timerId);
		}
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
