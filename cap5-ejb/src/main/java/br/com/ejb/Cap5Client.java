package br.com.ejb;

import br.com.ejb.async.AsyncBeanRemote;
import br.com.ejb.inconsistency.InconsistencyBeanRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import java.util.concurrent.Future;

/**
 * Created by Matheus on 31/01/16.
 */
public class Cap5Client {

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
		 * Inconsistency
		 * ======================================================================================================================================
		 */
		/*ExecutorService executorService = Executors.newFixedThreadPool(10);
		executorService.submit(new Caller("Caller 1", "Matheus", context));
		executorService.submit(new Caller("Caller 2", "Matheus Gomes", context));
		executorService.shutdown();*/

		/*
		 * ======================================================================================================================================
		 * Pass by value vs Pass by reference
		 * ======================================================================================================================================
		 */
		/*
		 * Quando um EJB é invocado atraves de uma view remota, os parametros utilizados na chamada de qualquer método de negocio
		 * sao passados por valor, e nao como referencia, uma vez que esses parametros precisam ser serializados antes de serem enviados
		 * para o EJB, os mesmos sao copiados ao inves de serem passados como referencia.
		 */
		// SumModel sumModel = new SumModel(20, 50);
		// LifecycleBeanRemote lifecycleBeanRemote = (LifecycleBeanRemote) context.
		//		lookup("cap5-ejb/LifecycleBeanImpl!br.com.ejb.lifecycle.LifecycleBeanRemote");
		// System.out.println(lifecycleBeanRemote.sum(sumModel));
		/*
		 * Essa alteracao no objeto SumModel nao refletira dentro do EJB LifecycleBeanImpl, ja que os valores deste objeto foram copiados.
		 * Desta forma, o objeto passado para o metodo sum nao é o mesmo objeto que foi criado aqui.
		 */
		// sumModel.setNum1(50);
		// System.out.println(lifecycleBeanRemote.sum(null));

		/*
		 * ======================================================================================================================================
		 * Asynchronous
		 * ======================================================================================================================================
		 */
		AsyncBeanRemote asyncBeanRemote = (AsyncBeanRemote) context.lookup("cap5-ejb/AsyncBeanImpl!br.com.ejb.async.AsyncBeanRemote");
		Future<String> task1 = asyncBeanRemote.doSomeSlowTask();
		System.out.println(asyncBeanRemote.getGreetings());
		Future<String> task2 = asyncBeanRemote.doAnotherSlowTask();
		System.out.println(asyncBeanRemote.getGreetings());
		System.out.println(task1.get());
		System.out.println(task2.get());
	}

}

class Caller implements Runnable {

	private String callerName;
	private String name;
	private Context context;

	public Caller(String callerName, String name, Context context) {
		this.callerName = callerName;
		this.name = name;
		this.context = context;
	}

	@Override
	public void run() {
		try {
			InconsistencyBeanRemote inconsistencyBeanRemote = (InconsistencyBeanRemote) this.context.
					lookup("cap5-ejb/InconsistencyBeanImpl!br.com.ejb.inconsistency.InconsistencyBeanRemote");
			System.out.println(this.callerName + " - " + inconsistencyBeanRemote.sayHello(this.name));
			System.out.println(this.callerName + " - " + inconsistencyBeanRemote.sayHello(this.name));
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}
}
