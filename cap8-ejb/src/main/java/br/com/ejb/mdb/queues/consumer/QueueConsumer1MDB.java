package br.com.ejb.mdb.queues.consumer;

import lombok.extern.java.Log;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Mensagens enviadas para queues sao consumidas por apenas um MDB, mesmo que existam varios MDBs escutando a mesma queue apenas um recebera a mensagem.
 * O container EJB é responsavel por receber a mensagem e direciona-la para um MDB especifico do pool de beans.
 */
@Log
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto_acknowledge"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/simple2")})
public class QueueConsumer1MDB implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			log.info(((TextMessage) message).getText());
		} catch (Exception e) {
			log.throwing(this.getClass().getName(), "onMessage", e);
		}
	}
}
