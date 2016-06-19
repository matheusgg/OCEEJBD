package br.com.ejb.interceptors;

import lombok.extern.java.Log;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.logging.Level;

@Log
public class StringReplacerInterceptor {

	@AroundInvoke
	Object replace(final InvocationContext context) {
		Object value = null;
		try {
			log.info("========================= CHECKING FOR REPLACE =========================");

			final Object[] newParams = new Object[context.getParameters().length];
			final Object[] oldParams = context.getParameters();

			for (int i = 0; i < oldParams.length; i++) {
				final Object param = oldParams[i];
				if ((param instanceof String) && param.toString().isEmpty()) {
					newParams[i] = "O.O";
				} else {
					newParams[i] = param;
				}
			}

			context.setParameters(newParams);
			value = context.proceed();

		} catch (final Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
		return value;
	}

}
