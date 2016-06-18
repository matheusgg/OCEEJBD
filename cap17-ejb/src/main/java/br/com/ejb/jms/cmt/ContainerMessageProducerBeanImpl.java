package br.com.ejb.jms.cmt;

import br.com.ejb.jms.MessageProducerRemoteBean;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.*;
import java.util.Map;

@Stateless
public class ContainerMessageProducerBeanImpl implements MessageProducerRemoteBean {

	/**
	 * Para utilizar a api JTA com JMS, é preciso configurar uma ConnectionFactory que suporte transacoes.
	 */
	@Resource(lookup = "java:/JmsXA")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/queue/tx")
	private Queue queue;

	/**
	 * Utilizando JTA com JMS e o gerenciamento de transacoes do CONTAINER, a mensagem só será enviada para a fila caso o método seja concluido com sucesso.
	 * Se alguma excecao ocorrer, o container fara o rollback da transacao e a mensagem nao sera enviada para a fila.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public void sendMessage(final String message, final Map<String, String> properties) throws Exception {
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
	}
}
