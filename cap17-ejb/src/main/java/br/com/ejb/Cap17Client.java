package br.com.ejb;

import br.com.ejb.model.Client;
import br.com.ejb.model.Product;
import br.com.ejb.sessionsynchronization.SessionRemoteBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Properties;

public class Cap17Client {

	public static void main(final String[] args) throws Exception {
		final Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		env.put(Context.PROVIDER_URL, "remote://localhost:4447");
		env.put(Context.SECURITY_PRINCIPAL, "admin");
		env.put(Context.SECURITY_CREDENTIALS, "admin123");
		env.put("jboss.naming.client.ejb.context", true);

		final Context ctx = new InitialContext(env);

		final Product product = new Product(null, "Coca Cola", new BigDecimal("5.79"), null);
		final Client client = new Client(null, "Matheus", new ArrayList<Product>());
		product.setClient(client);
		client.getProducts().add(product);

		/*
		 * =================================================================================================
		 * @TransactionAttribute - Container Managed Transaction
		 * =================================================================================================
		 */
		//		final TransactionAttributesRemoteBean bean = (TransactionAttributesRemoteBean) ctx.lookup("cap17-ejb/TransactionAttributesBeanImpl!br.com.ejb.cmt.TransactionAttributesRemoteBean");
		//		System.out.println(bean.executeNotSupportedWithinTransaction(client));

		//		System.out.println(bean.executeSupportsWithinTransaction(client));
		//		System.out.println(bean.executeSupportsWithoutTransaction(client));

		//		System.out.println(bean.executeRequiredWithinTransaction(client));
		//		System.out.println(bean.executeRequiredWithoutTransaction(client));

		//		System.out.println(bean.executeRequiresNewWithinTransaction(client));
		//		System.out.println(bean.executeRequiresNewWithoutTransaction(client));

		//		System.out.println(bean.executeMandatoryWithinTransaction(client));
		//		System.out.println(bean.executeMandatoryWithoutTransaction(client));

		//		System.out.println(bean.executeNeverWithinTransaction(client));
		//		System.out.println(bean.executeNeverWithoutTransaction(client));

		/*
		 * =================================================================================================
		 * @TransactionAttribute - Bean Managed Transaction
		 * =================================================================================================
		 */
		//		final ExplicitTransactionRemoteBean bean = (ExplicitTransactionRemoteBean) ctx.lookup("cap17-ejb/ExplicitTransactionBeanImpl!br.com.ejb.bmt.ExplicitTransactionRemoteBean");
		//		System.out.println(bean.executeWithUserTransaction(client));
		//		System.out.println(bean.executeWithUserTransaction(client));
		//		System.out.println(bean.executeWithTransactionPropagation(client));

		/*
		 * =================================================================================================
		 * JMS - Container Managed Transaction
		 * =================================================================================================
		 */
		/*final Map<String, String> properties = new HashMap<>();

		final MessageProducerRemoteBean bean = (MessageProducerRemoteBean) ctx.lookup("cap17-ejb/ContainerMessageProducerBeanImpl!br.com.ejb.jms.MessageProducerRemoteBean");
		bean.sendMessage("Teste com sucesso!", properties);

		try {
			bean.sendMessage("", properties);
		} catch (final Exception e) {
			e.printStackTrace();
		}

		properties.put("raiseError", "true");
		bean.sendMessage("Teste com erro no cliente", properties);*/

		/*
		 * =================================================================================================
		 * JMS - Bean Managed Transaction
		 * =================================================================================================
		 */
		/*final Map<String, String> properties = new HashMap<>();

		final MessageProducerRemoteBean bean = (MessageProducerRemoteBean) ctx.lookup("cap17-ejb/BeanMessageProducerImpl!br.com.ejb.jms.MessageProducerRemoteBean");
		bean.sendMessage("Teste com sucesso!", properties);

		try {
			bean.sendMessage("", properties);
		} catch (final Exception e) {
			e.printStackTrace();
		}

		properties.put("raiseError", "true");
		bean.sendMessage("Teste com erro no cliente", properties);*/

		/*
		 * =================================================================================================
		 * CMT - Rollback
		 * =================================================================================================
		 */
		/*final RollbackRemoteBean bean = (RollbackRemoteBean) ctx.lookup("cap17-ejb/CmtRollbackBeanImpl!br.com.ejb.rollback.RollbackRemoteBean");
		System.out.println(bean.executeSystemException(client));*/

		/*
		 * =================================================================================================
		 * BMT - Rollback
		 * =================================================================================================
		 */
		/*final RollbackRemoteBean bean = (RollbackRemoteBean) ctx.lookup("cap17-ejb/BmtRollbackBeanImpl!br.com.ejb.rollback.RollbackRemoteBean");
		System.out.println(bean.executeSystemException(client));

		client.setName(null);
		System.out.println(bean.executeSystemException(client));*/

		/*
		 * =================================================================================================
		 * Exceptions - CMT
		 * =================================================================================================
		 */
		/*final SimpleRemoteBean bean = (SimpleRemoteBean) ctx.lookup("cap17-ejb/SimpleBeanImpl1!br.com.ejb.exceptions.SimpleRemoteBean");
		// bean.executeSystemException(client);
		bean.executeApplicationException(client);*/

		/*
		 * =================================================================================================
		 * Exceptions - BMT
		 * =================================================================================================
		 */
		/*final SimpleRemoteBean bean = (SimpleRemoteBean) ctx.lookup("cap17-ejb/SimpleBeanImpl3!br.com.ejb.exceptions.SimpleRemoteBean");
		// bean.executeSystemException(client);
		bean.executeApplicationException(client);*/

		/*
		 * =================================================================================================
		 * Session Synchronization - Stateful Session Bean
		 * =================================================================================================
		 */
		client.setId(2);
		final SessionRemoteBean bean = (SessionRemoteBean) ctx.lookup("cap17-ejb/SessionBeanImpl!br.com.ejb.sessionsynchronization.SessionRemoteBean");
		System.out.println(bean.save(client));
	}

}
