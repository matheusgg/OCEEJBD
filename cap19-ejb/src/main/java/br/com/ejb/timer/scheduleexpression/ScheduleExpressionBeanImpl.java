package br.com.ejb.timer.scheduleexpression;

import lombok.extern.java.Log;

import javax.annotation.Resource;
import javax.ejb.*;
import java.util.UUID;

/**
 * Stateful Session Beans nao podem definir timers, uma vez que a natureza dos SFSB nao permite que o container utilize
 * qualquer instancia para executar o método timeout, apenas SLSBs e MDBs podem definir timers e utilizar a classe
 * TimerService.
 * <p>
 * Caso um SFSB tente injetar uma instancia de TimerService ou tente invocar o metodo SessionContext.getTimerService(),
 * uma java.lang.IllegalStateException será lancada.
 * <p>
 * A interface javax.ejb.TimedObject serve para definir um metodo de timeout que sera invocado pelo container quando
 * um timer expirar.
 */
@Log
//@Stateful
@Stateless
public class ScheduleExpressionBeanImpl implements ScheduleExpressionRemoteBean, TimedObject {

	@Resource
	private SessionContext sessionContext;

	/**
	 * Assim como SessionContext e UserTransaction, é possivel obter uma intancia do TimerService atraves de injecao.
	 * Para isso, basta especificar o nome JNDI java:comp/TimerService
	 */
	@Resource(lookup = "java:comp/TimerService")
	private TimerService timerService;

	@Override
	public void schedule() {
		/**
		 * Todos os métodos da classe ScheduleExpression sao sobrecarregados para aceitar uma String, desta forma
		 * é possível definir expressoes com o coringa * (que significa 'a cada minuto, segundo, hora, etc').
		 *
		 * As expressoes de agendamento sao semelhantes as expressoes cron, podendo ser informados valores
		 * específicos, listas ou ranges.
		 *
		 * Os valores textuais (Mon, Sun, Jan, Feb) sao todos case insensitive.
		 */
		final ScheduleExpression expression = new ScheduleExpression()
				.hour("*") // [0-23] Ex.: 1,2,3 ou 1-3
				.minute("*") // [0-59] Ex.: 10,11,12 ou 10-20
				.second("0,10,20,30,40,50") // [0-59] Ex.: 10,11,12 ou 10-20
				.dayOfWeek("Mon-Sun") // [0-7] ou [Sun-Sat] Ex.: 2,3,4 ou 2-4 ou Mon,Tue,Wed ou Mon-Wed (o valor 0 e 7 correspondem ao Domingo - Sun)
				.dayOfMonth("*") // [-7-31] Ex.: 10,11,12 ou 10-20 ou Last ou -x (onde x é o número restantes de dias do mes)
				.month("1-12") // [1-12] Ex.: 10,11,12 ou 10-12 ou Jan,Feb,Mar ou Jan-Dec
				.year(2016); // Ex.: 2016,2017,2018 ou 2016-2018

		/**
		 * A classe TimerConfig possui informacoes sobre o timer que esta sendo configurado.
		 * É possivel associar, por exemplo, um ID para o timer e definir se o mesmo deve
		 * ser persistente ou nao.
		 */
		final TimerConfig config = new TimerConfig(UUID.randomUUID().toString(), false);

		// final Timer timer = this.sessionContext.getTimerService().createCalendarTimer(expression); //ou
		// final Timer timer = this.timerService.createCalendarTimer(expression); //ou
		final Timer timer = this.timerService.createCalendarTimer(expression, config);

		/**
		 * TimeHandlers sao objetos que podem ser utilizados para persistir informacoes de timers.
		 * Deste modo, eles estao disponiveis apenas se o timer for persistente. Se o timer
		 * nao for persistente e o método getHandle() for invocado, uma java.lang.IllegalStateException
		 * será lancada.
		 */
		if (timer.isPersistent()) {
			log.info(timer.getHandle().toString());
		}
		log.info(timer.getInfo().toString());
		log.info(timer.getNextTimeout().toString());
		log.info(timer.getSchedule().toString());
		log.info(String.valueOf(timer.getTimeRemaining()));
	}

	/**
	 * O timer e o EJB que o define estao relacionados, por isso quando o timer expira o container EJB
	 * tenta invocar o metodo de timeout do EJB que o definiu.
	 * <p>
	 * Existem 3 maneiras de definir um metodo de timeout: Com @Schedule, com @Timeout ou atraves da interface
	 * javax.ejb.TimedObject que possui o metodo ejbTimeout(Timer) que deve ser implementado.
	 */
	@Override
	public void ejbTimeout(final Timer timer) {
		log.info("==================== TIMEOUT ====================");
		log.info(timer.getInfo().toString());
		log.info(timer.getNextTimeout().toString());
		log.info(timer.getSchedule().toString());
		log.info(String.valueOf(timer.getTimeRemaining()));

		/**
		 * O metodo TimerService.getTimers() retorna todos os timers associados a este EJB.
		 */
		log.info(this.timerService.getTimers().toString());
	}

	/**
	 * É possível injetar o objeto TimerService atraves do metodo setter.
	 */
	/*@Resource(lookup = "java:comp/TimerService")
	public void setTimerService(final TimerService timerService) {
		this.timerService = timerService;
	}*/
}
