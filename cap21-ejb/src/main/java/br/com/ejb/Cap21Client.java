package br.com.ejb;

import br.com.ejb.ws.ReservationConsumerRemoteBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

public class Cap21Client {

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
		 * @WebServiceClient / @WebEndpoint / @WebServiceRef
		 * =================================================================================================
		 */
		final ReservationConsumerRemoteBean bean = (ReservationConsumerRemoteBean) ctx.lookup("cap21-ejb/ReservationConsumerBeanImpl!br.com.ejb.ws.ReservationConsumerRemoteBean");
		bean.consume();
	}

}
