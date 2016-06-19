package br.com.ejb.interceptors;

import lombok.extern.java.Log;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.Arrays;

@Log
public class AuditInterceptor {

	/**
	 * A anotacao @AroundInvoke serve para definir um metodo dentro de um interceptor que sera invocado antes da invocacao
	 * metodo original.
	 * <p>
	 * O método anotado com @AroundInvoke pode ter qualquer visibilidade e declarar, ou nao, excecoes. Porém, este método deve
	 * receber exatamente um parametro do tipo InvocationContext, que possui informacoes sobre a invocacao atual.
	 * <p>
	 * Um interceptor deve possuir apenas um metodo anotado com @AroundInvoke, caso exista mais de um metodo com esta anotacao,
	 * a ordem de invocacao é indefinida.
	 */
	@AroundInvoke
	Object audit(final InvocationContext context) throws Exception {
		Object value = null;
		try {
			log.info("========================= LOGGING =========================");
			log.info("Target Bean: " + context.getTarget());
			log.info("Invoked Method: " + context.getMethod());
			log.info("Parameters: " + Arrays.asList(context.getParameters()));
			log.info("Context Data: " + context.getContextData());
			log.info("Timer: " + context.getTimer());

			value = context.proceed();

		} catch (final Exception e) {
			log.severe("========================= AN EXCEPTION HAS OCCURRED! =========================");
			log.throwing(context.getTarget().toString(), context.getMethod().toString(), e);
		}
		return value;
	}

}
