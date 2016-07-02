package br.com.ejb.timer.timeout;

import lombok.extern.java.Log;

import javax.annotation.Resource;
import javax.ejb.*;
import java.util.UUID;

@Log
@Stateless
public class TimoutBeanImpl implements TimeoutRemoteBean {

	@Resource
	private TimerService timerService;

	@Override
	public void createTimer() {
		this.timerService.createCalendarTimer(new ScheduleExpression()
				// .dayOfWeek(6) // defaults to *
				// .dayOfMonth(2) // defaults to *
				// .year(2016) // defaults to *
				// .month("Jul") // defaults to *
				.hour("*")
				.minute("*")
				.second("0, 30"), new TimerConfig(UUID.randomUUID().toString(), false));
	}

	/**
	 * A anotacao @Timeout faz com que um metodo dentro de um EJB seja definido como método de timeout que
	 * sera executado quando os timers criados por este EJB expirarem.
	 * <p>
	 * A anotacao @Timeout substitui a necessidade de se implementar a interface javax.ejb.TimedObject para definir
	 * um metodo de timeout.
	 * <p>
	 * O método anotado com @Timeout sera utilizado como metodo de timout e pode receber, ou nao, um parametro do tipo javax.ejb.Timer,
	 * declarar checked exceptions, possuir qualquer visibilidade, ser void ou retornar qualquer tipo de objeto.
	 */
	@Timeout
	void timeout(final Timer timer) {
		log.info("================== TIMEOUT ==================");
		log.info(timer.getInfo().toString());
		log.info(timer.getSchedule().toString());
		log.info(String.valueOf(timer.getTimeRemaining()));
		log.info(timer.getNextTimeout().toString());
	}
}
