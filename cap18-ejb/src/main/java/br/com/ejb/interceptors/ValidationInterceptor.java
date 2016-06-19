package br.com.ejb.interceptors;

import lombok.extern.java.Log;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

@Log
public class ValidationInterceptor {

	@AroundInvoke
	Object validate(final InvocationContext context) throws IllegalArgumentException {
		Object value = null;
		try {
			log.info("========================= VALIDATING PARAMETERS =========================");

			final List<Object> params = Arrays.asList(context.getParameters());
			for (final Object param : params) {
				if (param == null) {
					throw new IllegalArgumentException("Parameters must not be null!");
				}
			}

			value = context.proceed();

		} catch (final Exception e) {
			log.severe("========================= VALIDATION FAILED! =========================");
			log.log(Level.SEVERE, e.getMessage(), e);
		}
		return value;
	}

}
