package br.com.ejb.mdb.topics.consumer;

import lombok.extern.java.Log;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Mensagens enviadas para topics sao consumidas por todos os subscribers daquele determinado topico.
 * <hr>
 * Quando a propriedade subscriptionDurability é especificada com o valor Durable, significa que este MDB nao perdera
 * as mensagens enviadas para o topico quando houver algum periodo de indisponibilidade.
 * <hr>
 * Quando subscriptionDurability possui o valor Durable é preciso especificar o clientId deste MDB, ou seja, é preciso
 * definir o ID do subscriber.
 */
@Log
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto_acknowledge"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/topic/simple2"),
		@ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
		@ActivationConfigProperty(propertyName = "clientId", propertyValue = "Client2")})
public class TopicCosumer2MDB implements MessageListener {

	@Override
	public void onMessage(final Message message) {
		try {
			log.info(((TextMessage) message).getText());
		} catch (Exception e) {
			log.throwing(this.getClass().getName(), "onMessage", e);
		}
	}
}
