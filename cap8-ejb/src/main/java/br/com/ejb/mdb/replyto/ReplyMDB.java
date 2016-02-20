package br.com.ejb.mdb.replyto;

import lombok.extern.java.Log;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;

@Log
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto_acknowledge"),
		@ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "Seletor='3'"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/simple")})
public class ReplyMDB implements MessageListener {

	@Resource(name = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Override
	public void onMessage(final Message message) {
		try {
			final TextMessage textMessage = (TextMessage) message;
			final String msg = textMessage.getText();

			if ("ERRO".equals(msg)) {
				throw new IllegalArgumentException("A mensagem informada contém erros.");
			}

			log.info(textMessage.getText());
			this.repplyWithSuccess(message);
		} catch (Exception e) {
			this.repplyWithError(message, e.getMessage());
			log.throwing(this.getClass().getName(), "onMessage", e);
		}
	}

	private void repplyWithSuccess(final Message message) {
		this.sendRepply("Mensagem Enviada com Sucesso!", "Success", message);
	}

	private void repplyWithError(final Message message, final String errorMessage) {
		this.sendRepply("Mensagem Enviada com erro: " + errorMessage, "Error", message);
	}

	private void sendRepply(final String message, final String selector, final Message jmsMessage) {
		try {
			final Connection connection = this.connectionFactory.createConnection();
			final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// Recuperando o destino de resposta
			final MessageProducer producer = session.createProducer(jmsMessage.getJMSReplyTo());
			final TextMessage textMessage = session.createTextMessage(message);
			textMessage.setStringProperty("Seletor", selector);
			producer.send(textMessage);
			connection.close();
		} catch (Exception e) {
			log.throwing(this.getClass().getName(), "onMessage", e);
		}
	}
}
