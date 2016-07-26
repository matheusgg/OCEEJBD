package br.com.ejb.interceptors;

import lombok.extern.java.Log;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

@Log
public class LoggingInterceptor {

	@Resource
	private SessionContext context;

	/**
	 * Quando um cliente nao possui a role necessaria para executar um determinado metodo de negocio de um EJB, o container lancara uma excecao
	 * antes de invocar os interceptors associados a este EJB.
	 */
	@AroundInvoke
	Object intercept(final InvocationContext context) throws Exception {
		log.info("==================================================");
		log.info(context.getMethod().getName() + " invoked by " + this.context.getCallerPrincipal().getName());
		log.info("==================================================");
		return context.proceed();
	}

}