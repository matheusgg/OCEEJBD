package br.com.ejb;

import br.com.ejb.restrictions.RestrictionsRemoteBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

public class EJBFeaturesClient {

	public static void main(String[] args) throws Exception {
		final Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		env.put(Context.PROVIDER_URL, "remote://localhost:4447");
		env.put(Context.SECURITY_PRINCIPAL, "admin");
		env.put(Context.SECURITY_CREDENTIALS, "admin123");
		env.put("jboss.naming.client.ejb.context", true);

		final Context ctx = new InitialContext(env);

		/*
		 * ======================================================================================================================================
		 * Restrictions
		 * ======================================================================================================================================
		 */
		final RestrictionsRemoteBean bean = (RestrictionsRemoteBean) ctx.lookup("ejb-features/RestrictionsBeanImpl!br.com.ejb.restrictions.RestrictionsRemoteBean");
		bean.testRestrictions();

		/*
		 * ======================================================================================================================================
		 * Exceptions
		 * ======================================================================================================================================
		 */
//		final ExceptionRemoteBean bean = (ExceptionRemoteBean) ctx.lookup("ejb-features/ExceptionBeanImpl!br.com.ejb.exceptions.ExceptionRemoteBean");
		//		bean.testRuntimeException();
		//		bean.testHandlingRuntimeException();
		//		bean.testIllegalAccessException();
		//		bean.testHandlingIllegalAccessException();
		//		bean.testSystemException();
		//		bean.testApplicationException();
		//		System.out.println(bean.testSystemExceptionAsync().get());
//		System.out.println(bean.testApplicationExceptionAsync().get());

		/*
		 * ======================================================================================================================================
		 * @Remove
		 * ======================================================================================================================================
		 */
		//		final RemoveRemoteBean bean = (RemoveRemoteBean) ctx.lookup("ejb-features/RemoveBeanImpl!br.com.ejb.remove.RemoveRemoteBean");
		//		System.out.println(bean.testRemove());
		//		System.out.println(bean.testRemoveCallingByEJBProxy());
		//		System.out.println(bean.testRemoveThrowingSystemException());
		//		System.out.println(bean.testRemoveThrowingApplicationException());
	}

}
