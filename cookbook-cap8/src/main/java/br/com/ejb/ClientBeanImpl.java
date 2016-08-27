package br.com.ejb;

import br.com.ejb.interceptors.LifecycleInterceptor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import java.util.Random;

@Stateful
@Interceptors(LifecycleInterceptor.class)
public class ClientBeanImpl {

	@PostConstruct
	void init() {
		System.out.println("ClientBeanImpl.init");
	}

	@PrePassivate
	void prePassivate() {
		System.out.println("ClientBeanImpl.prePassivate");
	}

	@PostActivate
	void postActivate() {
		System.out.println("ClientBeanImpl.postActivate");
	}

	public String getClientName(final String clientName) {
		return clientName;
	}

	public String getClientIdentity(final String clientName) {
		return clientName + " - " + this.getIdentity();
	}

	private int getIdentity() {
		return new Random().nextInt(9999);
	}

	@PreDestroy
	void destroy() {
		System.out.println("ClientBeanImpl.destroy");
	}
}
