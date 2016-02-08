package br.com.ejb;

import br.com.ejb.concurrency.ConcurrencyBeanRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created on 08/02/16.
 */
public class Cap7Client {

	public static void main(String[] args) throws Exception {
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		env.put(Context.PROVIDER_URL, "remote://localhost:4447");
		env.put(Context.SECURITY_PRINCIPAL, "admin");
		env.put(Context.SECURITY_CREDENTIALS, "admin123");
		env.put("jboss.naming.client.ejb.context", true);

		Context context = new InitialContext(env);

		/*
		 * ======================================================================================================================================
		 * Stateful Concurrency Bean
		 * ======================================================================================================================================
		 */
		/*ConcurrencyBeanRemote concurrencyBeanRemote = (ConcurrencyBeanRemote) context.lookup("cap7-ejb/StatefulConcurrencyBeanImpl!br.com.ejb.concurrency.ConcurrencyBeanRemote");

		// A mesma instancia de um EJB so pode atender uma requisicao por vez.
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		executorService.submit(new Caller(concurrencyBeanRemote, true));
		executorService.submit(new Caller(concurrencyBeanRemote, false));
		executorService.shutdown();
		executorService.awaitTermination(60, TimeUnit.SECONDS);
		System.out.println(concurrencyBeanRemote.method1());*/

		/*
		 * ======================================================================================================================================
		 * Singleton Lifecycle
		 * ======================================================================================================================================
		 */
		/*SingletonLifecycleBeanRemote singletonLifecycleBeanRemote = (SingletonLifecycleBeanRemote) context.
				lookup("cap7-ejb/SingletonLifecycleBeanImpl!br.com.ejb.lifecycle.SingletonLifecycleBeanRemote");
		System.out.println(singletonLifecycleBeanRemote.createMsg());*/

		/*
		 * ======================================================================================================================================
		 * Singleton Concurrency Managed Container (CMC)
		 * ======================================================================================================================================
		 */
		ConcurrencyBeanRemote concurrencyBeanRemote = (ConcurrencyBeanRemote) context.lookup("cap7-ejb/SingletonCMCBeanImpl!br.com.ejb.concurrency.ConcurrencyBeanRemote");
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		executorService.submit(new Caller(concurrencyBeanRemote, true));
		executorService.submit(new Caller(concurrencyBeanRemote, false));
		executorService.shutdown();

		/*
		 * ======================================================================================================================================
		 * Singleton Bean Managed Container (BMC)
		 * ======================================================================================================================================
		 */
		/*ConcurrencyBeanRemote concurrencyBeanRemote = (ConcurrencyBeanRemote) context.lookup("cap7-ejb/SingletonBMCBeanImpl!br.com.ejb.concurrency.ConcurrencyBeanRemote");
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		executorService.submit(new Caller(concurrencyBeanRemote, true));
		executorService.submit(new Caller(concurrencyBeanRemote, false));
		executorService.shutdown();*/
	}

}

class Caller implements Runnable {

	private ConcurrencyBeanRemote bean;
	private boolean invokeMethod1;

	public Caller(ConcurrencyBeanRemote bean, boolean invokeMethod1) {
		this.bean = bean;
		this.invokeMethod1 = invokeMethod1;
	}

	@Override
	public void run() {
		try {
			if (this.invokeMethod1) {
				System.out.println(Thread.currentThread().getName() + " - Calling method 1");
				System.out.println(this.bean.method1());
			} else {
				System.out.println(Thread.currentThread().getName() + " - Calling method 2");
				System.out.println(this.bean.method2());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
