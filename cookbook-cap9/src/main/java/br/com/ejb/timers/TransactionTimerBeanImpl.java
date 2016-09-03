package br.com.ejb.timers;

import lombok.extern.java.Log;

import javax.annotation.Resource;
import javax.ejb.*;
import java.util.Collection;

@Log
@Stateless
public class TransactionTimerBeanImpl {

	@Resource
	private TimerService timerService;

	/**
	 * Quando um Timer é criado dentro de um contexto transacional, caso uma SystemException seja lancada o container EJB
	 * fara o rollback da transacao, com isso, o Timer nao será criado.
	 */
	public Timer createTimer(final String second, final String minute, final String hour, final String id) {
		final ScheduleExpression expression = new ScheduleExpression()
				.second(second)
				.minute(minute)
				.hour(hour);
		final Timer timer = this.timerService.createCalendarTimer(expression, new TimerConfig(id, false));
		if ("error".equals(id)) {
			throw new EJBException("Invalid ID!");
		}
		return timer;
	}

	@Timeout
	void timeout(final Timer timer) {
		log.info("Info: " + timer.getInfo());
		log.info("Next Timeout: " + timer.getNextTimeout());
		log.info("Time Remaining: " + timer.getTimeRemaining());
		log.info("Calendar Timer: " + timer.isCalendarTimer());
		log.info("Schedule: " + timer.getSchedule());
		log.info("Persistent: " + timer.isPersistent());
	}

	public Collection<Timer> getTimers() {
		return this.timerService.getTimers();
	}

	/**
	 * Quando um Timer é cancelado dentro de um contexto transacional, caso uma SystemException seja lancada o container EJB
	 * fara o rollback da transacao, com isso, o Timer nao será removido.
	 */
	public void cancel(final String id) {
		for (final Timer timer : this.getTimers()) {
			if (id.equals(timer.getInfo())) {
				timer.cancel();
				break;
			}
		}
		/*if ("782648".equals(id)) {
			throw new EJBException("Invalid ID!");
		}*/
	}

}
