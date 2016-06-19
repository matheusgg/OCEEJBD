package br.com.ejb.interceptors.beans;

import br.com.ejb.interceptors.AuditInterceptor;
import br.com.ejb.interceptors.LifecycleInterceptor;

import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;
import java.security.SecureRandom;
import java.util.Random;

@Stateless
@Interceptors({LifecycleInterceptor.class, AuditInterceptor.class})
public class RandomBeanImpl implements RandomRemoteBean {

	@Override
	public int random(final boolean secured) {
		return new Random().nextInt();
	}

	/**
	 * É possivel especificar a anotacao @AroundInvoke em metodos do EJB alvo. Neste caso, o metodo anotado com @AroundInvoke se
	 * torna o ultimo interceptor da cadeia e sera invocado antes da chamada ao metodo original.
	 */
	@AroundInvoke
	Object intercept(final InvocationContext context) throws Exception {
		final boolean secured = (Boolean) context.getParameters()[0];
		if (secured) {
			return new SecureRandom().nextInt();
		}
		return context.proceed();
	}
}
