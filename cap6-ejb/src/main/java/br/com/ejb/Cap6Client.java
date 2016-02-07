package br.com.ejb;

import br.com.ejb.lifecycle.LifecycleBeanRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created on 07/02/16.
 */
public class Cap6Client {

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
		 * Lifecycle
		 * ======================================================================================================================================
		 */
		LifecycleBeanRemote lifecycleBeanRemote = (LifecycleBeanRemote) context.lookup("cap6-ejb/LifecycleBeanImpl!br.com.ejb.lifecycle.LifecycleBeanRemote");
		System.out.println(lifecycleBeanRemote.doSomeWork());
		System.out.println(lifecycleBeanRemote.doAnotherWork());

		/*try {
			lifecycleBeanRemote.throwSystemException();
			lifecycleBeanRemote.throwApplicationException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}*/

		/*try {
			// Depois que um EJB Stateful é removido da fase Method-Ready, qualquer chamada de metodo seguinte resultara em uma NoSuchEJBException.
			lifecycleBeanRemote.removeBean1(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}*/

		// lifecycleBeanRemote.removeBean2();
		System.out.println(lifecycleBeanRemote.doSomeWork());

		/*try {
			lifecycleBeanRemote.removeBean2(true);
		} catch (Exception e) {
			// Nothing
		}*/

		// Test Passivation
		TimeUnit.SECONDS.sleep(20);

		System.out.println(lifecycleBeanRemote.doAnotherWork());
	}

}
