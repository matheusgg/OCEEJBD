package br.com.ejb.timer.transaction;

import lombok.extern.java.Log;

import javax.annotation.Resource;
import javax.ejb.*;
import java.util.UUID;

@Log
@Stateless
public class TxTimerBeanImpl implements TxTimerRemoteBean {

	@Resource(lookup = "java:comp/TimerService")
	private TimerService timerService;

	/**
	 * Não é necessário que exista um transacao ativa para se criar um timer. Porém, caso a criacao do timer aconteca
	 * dentro de uma transacao e a mesma for marcada para rollback, o container também desfazera a criacao do timer.
	 */
	@TransactionAttribute(TransactionAttributeType.NEVER)
	@Override
	public void createTimer() {
		this.timerService.createCalendarTimer(new ScheduleExpression()
				.hour("*")
				.minute("*")
				.second("0,30"), new TimerConfig(UUID.randomUUID().toString(), false));
		//		throw new RuntimeException("Exception to rollback current transaction!");
	}

	@Timeout
	void timeout(final Timer timer) {
		log.info("================== TIMEOUT ==================");
		log.info(timer.getInfo().toString());
		log.info(timer.getSchedule().toString());
		log.info(String.valueOf(timer.getTimeRemaining()));
		log.info(timer.getNextTimeout().toString());
	}
}
