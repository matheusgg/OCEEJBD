package br.com.ejb;

import br.com.ejb.resource.ResourceRemoteBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

public class Cap16Client {

	public static void main(final String[] args) throws Exception {
		final Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		env.put(Context.PROVIDER_URL, "remote://localhost:4447");
		env.put(Context.SECURITY_PRINCIPAL, "admin");
		env.put(Context.SECURITY_CREDENTIALS, "admin123");
		env.put("jboss.naming.client.ejb.context", true);

		final Context ctx = new InitialContext(env);

		/*
		 * =================================================================================================
		 * Global JNDI e ENC
		 * =================================================================================================
		 */
		/*final JndiRemoteBean bean = (JndiRemoteBean) ctx.lookup("cap16-ejb/JndiBeanImpl!br.com.ejb.jndi.JndiRemoteBean");
		bean.execute();*/

		/*
		 * =================================================================================================
		 * Injection
		 * =================================================================================================
		 */
		/*final InjectionRemoteBean bean = (InjectionRemoteBean) ctx.lookup("cap16-ejb/InjectionBeanImpl!br.com.ejb.injection.InjectionRemoteBean");
		bean.execute();*/

		/*
		 * =================================================================================================
		 * @Resource
		 * =================================================================================================
		 */
		final ResourceRemoteBean bean = (ResourceRemoteBean) ctx.lookup("cap16-ejb/ResourceBeanImpl!br.com.ejb.resource.ResourceRemoteBean");
		bean.execute();
	}

}
