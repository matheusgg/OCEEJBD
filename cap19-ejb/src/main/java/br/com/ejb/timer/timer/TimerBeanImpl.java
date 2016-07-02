package br.com.ejb.timer.timer;

import lombok.extern.java.Log;

import javax.annotation.Resource;
import javax.ejb.*;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Log
@Stateless
public class TimerBeanImpl implements TimerRemoteBean {

	@Resource
	private TimerService timerService;

	@Override
	public void createTimers() throws Exception {
		final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		final TimerConfig config = new TimerConfig(UUID.randomUUID().toString(), false);

		/**
		 * Calendar Timer
		 */
		// Cria um calendar timer baseado em uma ScheduleExpressiom
		/*this.timerService.createCalendarTimer(new ScheduleExpression()
				.hour("*")
				.minute("*")
				.second("0,30"), config);*/

		/**
		 * Interval Timer
		 * Este método é sobrecarregado para receber tanto um Date como um long como primeiro parametro.
		 * Caso receba um Date, o timer sera executado a partir da data representada por este Date. Caso o método que
		 * recebe um long seja chamado, o timer será executado depois do tempo representado pelo long informado em millis.
		 */
		// Cria um interval timer que sera executado a partir da data informada
		// this.timerService.createIntervalTimer(format.parse("02/07/2016 15:03:00"), TimeUnit.SECONDS.toMillis(5), config);

		// Cria um interval timer que sera executado 10 segundos apos a sua criacao
		// this.timerService.createIntervalTimer(TimeUnit.SECONDS.toMillis(10), TimeUnit.SECONDS.toMillis(5), config);

		/**
		 * Timer
		 */
		// Cria um single action timer que sera exectado um vez exatamente 10 segundos apos a sua criacao.
		// this.timerService.createTimer(TimeUnit.SECONDS.toMillis(10), UUID.randomUUID().toString());

		// Cria um interval timer que sera iniciado 10 segundos apos a sua criacao e sera executado de 5 em 5 segundos
		// this.timerService.createTimer(TimeUnit.SECONDS.toMillis(10), TimeUnit.SECONDS.toMillis(5), UUID.randomUUID().toString());

		// Cria um single action timer que sera exectado um vez exatamente quando a data informada por alcancada.
		// this.timerService.createTimer(format.parse("02/07/2016 15:25:00"), UUID.randomUUID().toString());

		// Cria um interval timer que sera iniciado na data informada e sera executado de 5 em 5 segundos
		// this.timerService.createTimer(format.parse("02/07/2016 15:27:00"), TimeUnit.SECONDS.toMillis(5), UUID.randomUUID().toString());

		/**
		 * Single Action Timer
		 */
		// Cria um timer que sera executado apenas uma vez na data informada
		// this.timerService.createSingleActionTimer(format.parse("02/07/2016 15:30:00"), config);

		// Cria um timer que sera executado apenas um vez 10 segundos apos a sua criacao
		this.timerService.createSingleActionTimer(TimeUnit.SECONDS.toMillis(10), config);
	}

	@Timeout
	void timeout(final Timer timer) {
		log.info("================== TIMEOUT ==================");
		log.info(timer.getInfo().toString());

		/**
		 * O método Timer.getSchedule() só pode ser chamado se o timer for baseado no calendario, ou seja,
		 * se o timer foi criado utilizando uma ScheduleExpression atraves do metodo
		 * TimerService.createCalendarTimer(ScheduleExpression).
		 */
		if (timer.isCalendarTimer()) {
			log.info(timer.getSchedule().toString());
		}

		/**
		 * Caso o timer seja um Single Action Timer, a invocacao dos metodos Timer.getTimeRemaning() e Timer.getNextTimeout()
		 * resultarao em uma excecao.
		 */
		try {
			log.info(String.valueOf(timer.getTimeRemaining()));
		} catch (final Exception e) {
			log.warning(e.getMessage());
		}

		try {
			log.info(timer.getNextTimeout().toString());
		} catch (final Exception e) {
			log.warning(e.getMessage());
		}
	}
}
