package br.com.ejb.timers;

import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.interceptor.AroundTimeout;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;
import java.util.ArrayList;
import java.util.List;

@Log
@Stateless
@Interceptors(TimerInterceptor.class)
public class MemoryReportBeanImpl {

	@Resource(lookup = "java:comp/TimerService")
	private TimerService timerService;

	private Runtime runtime;

	@PostConstruct
	void init() {
		this.runtime = Runtime.getRuntime();
	}

	/**
	 * O atributo dayOfMonth também pode receber os valores de -7 até -1, que representam o dia antes do último dia do mês,
	 * ou seja, 7 dias antes do último dia do mês, 6 dias antes do último dia do mês, etc.
	 */
	//	@Schedule(second = "0,30", hour = "*", minute = "*", persistent = false, info = "7754633")
	void showMemoryUsage(final Timer timer) {
		log.info("Memory Usage: " + this.runtime.totalMemory());
	}

	//	@Schedules({
	//			@Schedule(second = "0,20,40", hour = "*", minute = "*", persistent = false, info = "97854854"),
	//			@Schedule(second = "10,30,50", hour = "*", minute = "*", persistent = false, info = "82647263")
	//	})
	void showFreeMemory(final Timer timer) {
		log.info(timer.getInfo().toString() + " - Free Memory: " + this.runtime.freeMemory());
	}

	/**
	 * Além dos valores do tipo lista (Sun,Mon,Tue...) e range (1-31) também existem os valores de incremento (20/5) que só podem
	 * ser utilizados nos campos second, minute e hour. No caso do exemplo second=20/5 este método de timeout será chamado de 5 em 5
	 * segundos a partir do segundo 20. No exemplo hour=14/24 este método será chamado a cada 24 horas a partir das 14 horas.
	 */
	//	@Schedule(second = "20/5", minute = "*", hour = "14/24", persistent = false, info = "121323")
	void showMaxMemory(final Timer timer) {
		log.info("Max Memory Available: " + this.runtime.maxMemory());
	}

	public List<Timer> getTimers() {
		return new ArrayList<>(this.timerService.getTimers());
	}

	public void cancelTimer(final String timerId) {
		for (final Timer timer : this.getTimers()) {
			if (timerId.equals(timer.getInfo())) {
				timer.cancel();
				break;
			}
		}
	}

	/**
	 * É possível declarar métodos interceptadores de timeout dentro do próprio EJB.
	 */
	@AroundTimeout
	Object interceptTimeout(final InvocationContext context) throws Exception {
		log.info("==========> " + ((Timer) context.getTimer()).getInfo());
		return context.proceed();
	}

}
