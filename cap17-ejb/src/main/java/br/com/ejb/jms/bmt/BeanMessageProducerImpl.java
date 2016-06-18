package br.com.ejb.jms.bmt;

import br.com.ejb.jms.MessageProducerRemoteBean;
import lombok.extern.java.Log;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.*;
import javax.transaction.UserTransaction;
import java.util.Map;

@Log
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class BeanMessageProducerImpl implements MessageProducerRemoteBean {

	@Resource(lookup = "java:comp/UserTransaction")
	private UserTransaction userTransaction;

	/**
	 * Para utilizar a api JTA com JMS, é preciso configurar uma ConnectionFactory que suporte transacoes.
	 */
	@Resource(lookup = "java:/JmsXA")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/queue/tx")
	private Queue queue;

	@Override
	public void sendMessage(final String message, final Map<String, String> properties) throws Exception {
		try {
			this.userTransaction.begin();
			final Connection con = this.connectionFactory.createConnection();
			// Cria uma sessao transactional
			final Session session = con.createSession(true, Session.AUTO_ACKNOWLEDGE);
			final MessageProducer producer = session.createProducer(this.queue);
			final TextMessage textMessage = session.createTextMessage(message);
			for (final Map.Entry<String, String> entry : properties.entrySet()) {
				textMessage.setStringProperty(entry.getKey(), entry.getValue());
			}
			producer.send(textMessage);
			if (message == null || message.isEmpty()) {
				throw new IllegalArgumentException("Message cannot bet empty!");
			}
			con.close();
			this.userTransaction.commit();
		} catch (final Exception e) {
			this.userTransaction.rollback();
			log.severe(e.getMessage());
		}
	}
}
