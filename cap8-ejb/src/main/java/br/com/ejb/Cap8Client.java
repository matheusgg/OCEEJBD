package br.com.ejb;

import br.com.ejb.mdb.programaticconsumer.ProgramaticConsumerRemoteBean;
import br.com.ejb.mdb.programaticconsumer.sender.ProgramaticSenderRemoteBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

public class Cap8Client {

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
		 * Activation Config Properties / Message Selectors / JMSReplyTo
		 * ======================================================================================================================================
		 */
		/*final MessageProducerRemoteBean bean = (MessageProducerRemoteBean) ctx.lookup("cap8-ejb/MessageProducerBeanImpl!br.com.ejb.mdb.producer.MessageProducerRemoteBean");
		bean.sendMessage("1", "Mensagem de Teste");
		bean.sendMessage("2", "Mensagem de Teste 2");
		bean.sendMessage("Error", "Mensagem de Teste 3");
		bean.sendMessage("Success", "Mensagem de Teste 4");*/

		/*
		 * ======================================================================================================================================
		 * Queues
		 * ======================================================================================================================================
		 */
		/*final QueueProducerRemoteBean bean = (QueueProducerRemoteBean) ctx.lookup("cap8-ejb/QueueProducerBeanImpl!br.com.ejb.mdb.queues.producer.QueueProducerRemoteBean");
		bean.sendMessage("Mensagem de Teste");*/

		/*
		 * ======================================================================================================================================
		 * Topics
		 * ======================================================================================================================================
		 */
		/*final TopicProducerRemoteBean bean = (TopicProducerRemoteBean) ctx.lookup("cap8-ejb/TopicProducerBeanImpl!br.com.ejb.mdb.topics.producer.TopicProducerRemoteBean");
		bean.sendMessage("Mensagem de Teste");*/

		/*
		 * ======================================================================================================================================
		 * Lifecycle
		 * ======================================================================================================================================
		 */
		/*final LifecycleMessageProducerRemoteBean bean = (LifecycleMessageProducerRemoteBean) ctx
				.lookup("cap8-ejb/LifecycleMessageProducerBeanImpl!br.com.ejb.mdb.lifecycle.LifecycleMessageProducerRemoteBean");
		bean.sendMessage("Mensagem de Teste");*/

		/*
		 * ======================================================================================================================================
		 * Programatic Consumer
		 * ======================================================================================================================================
		 */
		final ProgramaticSenderRemoteBean senderBean = (ProgramaticSenderRemoteBean) ctx
				.lookup("cap8-ejb/ProgramaticSenderBeanImpl!br.com.ejb.mdb.programaticconsumer.sender.ProgramaticSenderRemoteBean");
		senderBean.sendMessage("Mensagem de Teste");

		final ProgramaticConsumerRemoteBean consumerBean = (ProgramaticConsumerRemoteBean) ctx
				.lookup("cap8-ejb/ProgramaticConsumerBeanImpl!br.com.ejb.mdb.programaticconsumer.ProgramaticConsumerRemoteBean");
		final String message = consumerBean.consume();
		System.out.println(message);

	}

}
