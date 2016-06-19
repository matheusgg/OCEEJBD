package br.com.ejb.interceptors.beans;

import br.com.ejb.interceptors.LifecycleInterceptor;
import br.com.ejb.interceptors.LogUserInterceptor;
import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 * Interceptors de ciclo de vida devem ser declarados a nivel de classe.
 */
@Log
@Stateless
@Interceptors({LogUserInterceptor.class, LifecycleInterceptor.class})
public class MessageBeanImpl implements MessageRemoteBean {

	@Resource
	private SessionContext sessionContext;

	/**
	 * As anotacoes @ExcludeClassInterceptors e @ExcludeDefaultInterceptors sao ignoradas em metodos de ciclo de vida, ou seja,
	 * interceptors de eventos de ciclo de vida serao invocados mesmo se essas anotacoes forem especificadas.
	 */
	//	@ExcludeClassInterceptors
	@PostConstruct
	void init() {
		log.info("Initializing bean...");
	}

	@Override
	public String getMessage() {
		log.info(this.sessionContext.lookup("br.com.ejb.interceptors.LogUserInterceptor/sessionContext").toString());
		return "Welcome to Message Bean";
	}
}
