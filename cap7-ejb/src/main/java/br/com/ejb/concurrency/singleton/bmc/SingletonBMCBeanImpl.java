package br.com.ejb.concurrency.singleton.bmc;

import br.com.ejb.concurrency.ConcurrencyBeanRemote;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Com a estrategia de gerenciamento de acesso concorrente BMC, é possivel ter mais flexibilidade e utilizar todos os recursos de programacao concorrente
 * da linguagem Java. Porém, esta abordagem requer mais trabalho e mais atencao por parte do desenvolvedor, uma vez que ele que tera que definir a forma
 * de acesso concorrente aos metodos do EJB.
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class SingletonBMCBeanImpl implements ConcurrencyBeanRemote {

	private AtomicInteger integer;

	@PostConstruct
	void inti() {
		this.integer = new AtomicInteger(0);
	}

	@Override
	public String method1() throws Exception {
		synchronized (this) {
			TimeUnit.SECONDS.sleep(10);
			return "Method1 - " + this.integer.incrementAndGet();
		}
	}

	@Override
	public String method2() throws Exception {
		return "Method2 - " + this.integer.incrementAndGet();
	}
}
