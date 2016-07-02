package br.com.ejb.timer.schedule;

import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timer;

@Log
@Stateless
public class ScheduleBeanImpl implements ScheduleRemoteBean {

	@PostConstruct
	void init() {
		log.info("Initializing bean...");
	}

	@Override
	public void businessMethod() {
		log.info("Business method invoked!");
	}

	/**
	 * Com a anotacao @Schedule é possivel definir a criacao automatica de timers dentro de um EJB.
	 * <p>
	 * Quando é feito o deploy de um EJB que possui algum metodo anotado com @Schedule, o container realiza a criacao automatica
	 * do timer e define o metodo anotado como o metodo de timeout.
	 * <p>
	 * O método anotado com @Schedule sera utilizado como metodo de timout e pode receber, ou nao, um parametro do tipo javax.ejb.Timer,
	 * declarar checked exceptions, possuir qualquer visibilidade, ser void ou retornar qualquer tipo de objeto.
	 */
	@Schedule(second = "0,30", hour = "*", minute = "*", dayOfMonth = "1-31", month = "Jul", dayOfWeek = "Sat,Sun", year = "2016", info = "ID:237864", persistent = false)
	void schedule(final Timer timer) throws Exception {
		log.info("================== TIMEOUT ==================");
		log.info(timer.getInfo().toString());
		log.info(timer.getSchedule().toString());
		log.info(String.valueOf(timer.getTimeRemaining()));
		log.info(timer.getNextTimeout().toString());
	}
}
