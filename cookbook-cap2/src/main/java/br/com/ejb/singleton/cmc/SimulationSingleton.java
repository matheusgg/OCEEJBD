package br.com.ejb.singleton.cmc;

import lombok.extern.java.Log;

import javax.ejb.AccessTimeout;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import java.util.concurrent.TimeUnit;

import static br.com.ejb.singleton.cmc.SimulationSingleton.Status.FINISHED;
import static br.com.ejb.singleton.cmc.SimulationSingleton.Status.STARTED;
import static javax.ejb.ConcurrencyManagementType.CONTAINER;
import static javax.ejb.LockType.READ;
import static javax.ejb.LockType.WRITE;

/**
 * Quando um método é marcado com LockType WRITE (padrao) ou READ, o container EJB bloqueia a instancia inteira.
 * <p>
 * Caso um metodo marcado com WRITE seja invocado e outra thread tente chamar outro metodo do mesmo singleton (seja WRITE ou READ),
 * o container EJB lancara uma javax.ejb.ConcurrentAccessTimeoutException quando o timeout for atingido.
 * <p>
 * Caso um metodo marcado com READ seja invocado e outra thread tente chamar um outro metodo READ, o container EJB enfileirara a
 * requisicao ate que a primeira invocacao seja finalizada. Quando o primeiro metodo invocado for concluido, o segundo metodo
 * solicitado sera chamado.
 * <p>
 * Caso um metodo marcado com READ seja invocado e outra thread tente chamar um metodo marcado com WRITE, uma
 * javax.ejb.ConcurrentAccessTimeoutException seja lancada.
 */
@Log
@Singleton
@ConcurrencyManagement(CONTAINER)
public class SimulationSingleton {

	public enum Status {
		STARTED, FINISHED
	}

	private Status status;

	/**
	 * A anotacao @AccessTimout serve para especificar o tempo de timeout antes de uma javax.ejb.ConcurrentAccessTimeoutException
	 * ser lancada caso uma thread nao consiga obter o lock deste EJB.
	 * <p>
	 * O valor 0 indica que este metodo nao suporta acesso concorrente. Uma javax.ejb.ConcurrentAccessException sera lancada caso
	 * este metodo seja acessado de maneira concorrente.
	 * <p>
	 * O valor -1 indica que o container EJB esperara o tempo que for necessario para obter o lock deste objeto.
	 */
	@Lock(WRITE)
	@AccessTimeout(-1)
	public void start() {
		log.info("Starting...");
		try {
			TimeUnit.SECONDS.sleep(10);
			this.status = STARTED;
		} catch (final Exception e) {
			e.printStackTrace();
		}
		log.info("Started!");
	}

	@Lock(WRITE)
	public void finish() {
		log.info("Finishing...");
		try {
			TimeUnit.SECONDS.sleep(10);
			this.status = FINISHED;
		} catch (final Exception e) {
			e.printStackTrace();
		}
		log.info("Finished!");
	}

	@Lock(READ)
	public Status status() {
		log.info("Getting status...");
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.status;
	}

}
