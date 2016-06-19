package br.com.ejb;

import br.com.ejb.interceptors.beans.RandomRemoteBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

public class Cap18Client {

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
		 * @Interceptors - @AroundInvoke
		 * =================================================================================================
		 */
		/*final ClientRemoteBean bean = (ClientRemoteBean) ctx.lookup("cap18-ejb/ClientBeanImpl!br.com.ejb.interceptors.beans.ClientRemoteBean");
		System.out.println(bean.greetings("Matheus"));
		System.out.println(bean.greetings(null));
		System.out.println(bean.greetings(""));*/

		/*
		 * =================================================================================================
		 * XML
		 * =================================================================================================
		 */
		/*final TokenRemoteBean bean = (TokenRemoteBean) ctx.lookup("cap18-ejb/TokenBeanImpl!br.com.ejb.interceptors.beans.TokenRemoteBean");
		System.out.println(bean.generateToken());
		System.out.println(bean.generateToken(5));*/

		/*
		 * =================================================================================================
		 * Injection
		 * =================================================================================================
		 */
		/*final MessageRemoteBean bean = (MessageRemoteBean) ctx.lookup("cap18-ejb/MessageBeanImpl!br.com.ejb.interceptors.beans.MessageRemoteBean");
		System.out.println(bean.getMessage());*/

		/*
		 * =================================================================================================
		 * Lifecycle Interceptors
		 * =================================================================================================
		 */
		/*final MessageRemoteBean bean2 = (MessageRemoteBean) ctx.lookup("cap18-ejb/MessageBeanImpl!br.com.ejb.interceptors.beans.MessageRemoteBean");
		System.out.println(bean2.getMessage());*/

		/*
		 * =================================================================================================
		 * @AroundInvoke in bean class
		 * =================================================================================================
		 */
		final RandomRemoteBean bean = (RandomRemoteBean) ctx.lookup("cap18-ejb/RandomBeanImpl!br.com.ejb.interceptors.beans.RandomRemoteBean");
		System.out.println(bean.random(false));
		System.out.println(bean.random(true));
	}

}
