package br.com.ejb;

import br.com.ejb.security.xml.MessageRemoteBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

public class SecurityEJB {

	/**
	 * Management
	 * root/root123
	 * <p>
	 * Application
	 * admin/admin123
	 * someUser/user123
	 *
	 * @param args Args
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception {
		final Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		env.put(Context.PROVIDER_URL, "remote://localhost:4447");
		env.put(Context.SECURITY_PRINCIPAL, "admin");
		env.put(Context.SECURITY_CREDENTIALS, "admin123");
		//		env.put(Context.SECURITY_PRINCIPAL, "someUser");
		//		env.put(Context.SECURITY_CREDENTIALS, "user123");
		env.put("jboss.naming.client.ejb.context", true);

		final Context ctx = new InitialContext(env);

		/*
		 * =================================================================================================
		 * @RolesAllowed / @@PermitAll / @DenyAll
		 * =================================================================================================
		 */
		/*final GreetingsRemoteBean bean = (GreetingsRemoteBean) ctx.lookup("security-ejb/GreetingsBeanImpl!br.com.ejb.security.annotations.GreetingsRemoteBean");
		try {
			System.out.println("=========================> " + bean.greetings1());
		} catch (final Exception e) {
			System.out.println("=========================> " + e.getMessage());
		}

		try {
			System.out.println("=========================> " + bean.greetings2());
		} catch (final Exception e) {
			System.out.println("=========================> " + e.getMessage());
		}

		try {
			System.out.println("=========================> " + bean.greetings3());
		} catch (final Exception e) {
			System.out.println("=========================> " + e.getMessage());
		}*/

		/*
		 * =================================================================================================
		 * Programatic Security
		 * =================================================================================================
		 */
		/*final TaskRemoteBean bean = (TaskRemoteBean) ctx.lookup("security-ejb/TaskBeanImpl!br.com.ejb.security.programatic.TaskRemoteBean");
		try {
			System.out.println("=========================> " + bean.task1());
		} catch (final Exception e) {
			System.out.println("=========================> " + e.getMessage());
		}

		try {
			System.out.println("=========================> " + bean.task2());
		} catch (final Exception e) {
			System.out.println("=========================> " + e.getMessage());
		}*/

		/*
		 * =================================================================================================
		 * @RunAs
		 * =================================================================================================
		 */
		/*final ClientRemoteBean bean = (ClientRemoteBean) ctx.lookup("security-ejb/ClientBeanImpl!br.com.ejb.security.runas.ClientRemoteBean");
		try {
			System.out.println("=========================> " + bean.clientName());
		} catch (final Exception e) {
			System.out.println("=========================> " + e.getMessage());
		}*/

		/*
		 * =================================================================================================
		 * Method Permission via XML
		 * =================================================================================================
		 */
		final MessageRemoteBean bean = (MessageRemoteBean) ctx.lookup("security-ejb/MessageBeanImpl!br.com.ejb.security.xml.MessageRemoteBean");
		try {
			System.out.println("=========================> " + bean.message1());
		} catch (final Exception e) {
			System.out.println("=========================> " + e.getMessage());
		}

		try {
			System.out.println("=========================> " + bean.message1("Matheus"));
		} catch (final Exception e) {
			System.out.println("=========================> " + e.getMessage());
		}

		try {
			System.out.println("=========================> " + bean.message2());
		} catch (final Exception e) {
			System.out.println("=========================> " + e.getMessage());
		}

		try {
			System.out.println("=========================> " + bean.message2("Matheus"));
		} catch (final Exception e) {
			System.out.println("=========================> " + e.getMessage());
		}

		try {
			System.out.println("=========================> " + bean.message3());
		} catch (final Exception e) {
			System.out.println("=========================> " + e.getMessage());
		}

		try {
			System.out.println("=========================> " + bean.message4());
		} catch (final Exception e) {
			System.out.println("=========================> " + e.getMessage());
		}

		try {
			System.out.println("=========================> " + bean.message5());
		} catch (final Exception e) {
			System.out.println("=========================> " + e.getMessage());
		}
	}

}
