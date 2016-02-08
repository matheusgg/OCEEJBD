package br.com.ejb.concurrency.stateful;

import br.com.ejb.concurrency.ConcurrencyBeanRemote;

import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.Stateful;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Em EJBs Stateless e Stateful, o container gerencia o acesso concorrente aos metodos de negocio. Deste modo, uma instancia de um EJB só pode
 * atender uma requisicao por vez. Qualquer outra requisicao ao mesmo objeto resultara em uma excecao de concorrencia.
 * <hr>
 * É possivel aplicar a anotacao '@AccessTimeout' para EJBs Stateful. Uma vez que, para EJBs Stateful, a estrategia de acesso concorrente é
 * sempre gerenciada pelo container (CMC).
 */
@Stateful
@AccessTimeout(value = 15, unit = TimeUnit.SECONDS)
public class StatefulConcurrencyBeanImpl implements ConcurrencyBeanRemote {

	private String id;

	@PostConstruct
	void init() {
		this.id = UUID.randomUUID().toString();
	}

	@Override
	public String method1() throws InterruptedException {
		TimeUnit.SECONDS.sleep(10);
		return this.id + " - Method 1 is done!";
	}

	@Override
	public String method2() throws InterruptedException {
		TimeUnit.SECONDS.sleep(10);
		return this.id + " - Method 2 is done!";
	}
}
