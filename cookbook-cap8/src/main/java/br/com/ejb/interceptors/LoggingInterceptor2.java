package br.com.ejb.interceptors;

import lombok.extern.java.Log;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Arrays;

@Log
public class LoggingInterceptor2 implements Serializable {

	private static final long serialVersionUID = -5155533809066458390L;

	@AroundInvoke
	Object log(final InvocationContext context) throws Exception {
		final String method = context.getMethod().getName();
		final String target = context.getTarget().toString();
		final Object[] params = context.getParameters();

		log.info(MessageFormat.format("Invoking method {0} of {1} with params {2}", method, target, Arrays.toString(params)));
		final Object result = context.proceed();
		log.info("Mathod " + method + " invoked successfully!");

		return result;
	}

}
