package br.com.ejb.interceptors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

public class LifecycleInterceptor implements Serializable {

	private static final long serialVersionUID = 1423812213460512262L;

	@PostConstruct
	void postConstruct(final InvocationContext context) throws Exception {
		System.out.println("LifecycleInterceptor.postConstruct");
		context.proceed();
	}

	@PreDestroy
	void preDestroy(final InvocationContext context) throws Exception {
		System.out.println("LifecycleInterceptor.preDestroy");
		context.proceed();
	}

	@PrePassivate
	void prePassivate(final InvocationContext context) throws Exception {
	System.out.println("LifecycleInterceptor.prePassivate");
		context.proceed();
	}

	@PostActivate
	void postActivate(final InvocationContext context) throws Exception {
		System.out.println("LifecycleInterceptor.postActivate");
		context.proceed();
	}

	@AroundInvoke
	Object invoke(final InvocationContext context) throws Exception {
		System.out.println("LifecycleInterceptor.invoke");
		return context.proceed();
	}

}
