package br.com.ejb.mdb.producer;

import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;

@Log
@Stateless
public class MessageProducerBeanImpl implements MessageProducerRemoteBean {

	@Resource(name = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(name = "java:/queue/simple")
	private Queue queue;

	@Resource(name = "java:/topic/simple")
	private Topic notificationTopic;

	@PostConstruct
	void init() {
		log.info(this.connectionFactory.toString());
		log.info(this.queue.toString());
	}

	@Override
	public void sendMessage(final String selector, final String message) throws Exception {
		final Connection connection = this.connectionFactory.createConnection();
		final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		final MessageProducer producer = session.createProducer(this.queue);
		final Message textMessage = session.createTextMessage(message);
		textMessage.setStringProperty("Seletor", selector);

		/*
		 * As mensagens JMS sao compostas pelo header e pelo body, desta forma, é possivel especificar propriedades
		 * que serao enviadas junto com a mensagem atraves da sessao de header. Uma dessas propriedades é a JMSReplyTo,
		 * que nada mais é do que um destino JMS que pode ser utilizado para enviar mensagens de resposta.
		 */
		textMessage.setJMSReplyTo(this.notificationTopic);
		producer.send(textMessage);
		connection.close();
	}
}
