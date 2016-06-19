package br.com.ejb.interceptors;

import lombok.extern.java.Log;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * Interceptors sao executados dentro do mesmo contexto do EJB invocado. Desta forma, é possivel injetar objetos dentro
 * dos interceptors da mesma forma como acontece nos EJBs.
 */
@Log
public class LogUserInterceptor {

	/**
	 * Este objeto sera injetado neste atributo e inserido no ENC do EJB alvo da invocacao com o nome
	 * java:comp/env/br.com.ejb.interceptors.LogUserInterceptor/sessionContext.
	 */
	@Resource
	private SessionContext sessionContext;

	@AroundInvoke
	Object logUser(final InvocationContext context) throws Exception {
		log.info("=================== USER: " + this.sessionContext.getCallerPrincipal().getName() + " ===================");
		return context.proceed();
	}

}
