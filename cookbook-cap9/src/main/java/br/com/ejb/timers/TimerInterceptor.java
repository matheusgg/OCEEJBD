package br.com.ejb.timers;

import lombok.extern.java.Log;

import javax.ejb.Timer;
import javax.interceptor.AroundInvoke;
import javax.interceptor.AroundTimeout;
import javax.interceptor.InvocationContext;

@Log
public class TimerInterceptor {

	/**
	 * Para interceptar métodos de timeout de Timers é necessário criar um método anotado
	 * com @AroundTimeout. Quando um interceptor de timeout é invocado, o método getTimer() do parametro
	 * InvocationContext retornará o Timer associado ao método de timeout interceptado.
	 */
	@AroundTimeout
	Object invokeTimeout(final InvocationContext context) throws Exception {
		final Timer timer = (Timer) context.getTimer();
		log.info("============================================================");
		log.info("Invoking timeout method " + context.getMethod().getName() + " with timer " + timer.getInfo());
		return context.proceed();
	}

	@AroundInvoke
	Object invoke(final InvocationContext context) throws Exception {
		log.info("============================================================");
		log.info("Invoking method " + context.getMethod().getName() + " of the bean " + context.getTarget().getClass().getSimpleName());
		return context.proceed();
	}

}
