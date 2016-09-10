package br.com.ejb.interceptors;

import lombok.extern.java.Log;

import javax.interceptor.InvocationContext;

@Log
public class SimpleInterceptor {

	/**
	 * Métodos de callback dentro de interceptors não devem possuir retorno.
	 */
	//	@PostConstruct
	void preInit(final InvocationContext context) throws Exception {
		log.info("Pre init");
		context.proceed();

	}

	//	@AroundInvoke
	Object invoke(final InvocationContext context) throws Exception {
		log.info("Pre Invoke");
		return context.proceed();
	}

	//	@AroundTimeout
	Object invokeTimeout(final InvocationContext context) throws Exception {
		log.info("Pre invoke timeout");
		return context.proceed();
	}

}
