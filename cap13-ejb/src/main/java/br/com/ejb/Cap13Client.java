package br.com.ejb;

import br.com.ejb.jpaql.JPAQLRemoteBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

public class Cap13Client {

	@SuppressWarnings("unchecked")
	public static void main(final String[] args) throws Exception {
		final Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		env.put(Context.PROVIDER_URL, "remote://localhost:4447");
		env.put(Context.SECURITY_PRINCIPAL, "admin");
		env.put(Context.SECURITY_CREDENTIALS, "admin123");
		env.put("jboss.naming.client.ejb.context", true);

		final Context ctx = new InitialContext(env);

		/*
		 * ======================================================================================================================================
		 * Simple JPAQL Operations
		 * ======================================================================================================================================
		 */
		//		final JPAQLRemoteBean bean = (JPAQLRemoteBean) ctx.lookup("cap13-ejb/SimpleBeanImpl!br.com.ejb.jpaql.JPAQLRemoteBean");
		//		bean.executeOperations();

		/*
		 * ======================================================================================================================================
		 * JPAQL Operators
		 * ======================================================================================================================================
		 */
		//		final JPAQLRemoteBean bean = (JPAQLRemoteBean) ctx.lookup("cap13-ejb/OperatorsBeanImpl!br.com.ejb.jpaql.JPAQLRemoteBean");
		//		bean.executeOperations();

		/*
		 * ======================================================================================================================================
		 * JPAQL Functions
		 * ======================================================================================================================================
		 */
		//		final JPAQLRemoteBean bean = (JPAQLRemoteBean) ctx.lookup("cap13-ejb/FunctionsBeanImpl!br.com.ejb.jpaql.JPAQLRemoteBean");
		//		bean.executeOperations();

		/*
		 * ======================================================================================================================================
		 * JPAQL Native Query
		 * ======================================================================================================================================
		 */
		//		final JPAQLRemoteBean bean = (JPAQLRemoteBean) ctx.lookup("cap13-ejb/NativeBeanImpl!br.com.ejb.jpaql.JPAQLRemoteBean");
		//		bean.executeOperations();

		/*
		 * ======================================================================================================================================
		 * JPAQL Named and Native Query
		 * ======================================================================================================================================
		 */
		final JPAQLRemoteBean bean = (JPAQLRemoteBean) ctx.lookup("cap13-ejb/NamedQueryBeanImpl!br.com.ejb.jpaql.JPAQLRemoteBean");
		bean.executeOperations();
	}

}
