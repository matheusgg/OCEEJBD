package br.com.ejb.mdb;

import lombok.extern.java.Log;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Log
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/simple"),
		@ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "visibility = 'Private'"),
})
public class PrivateMessagesBean implements MessageListener {

	@Override
	public void onMessage(final Message message) {
		try {
			log.info("========================== Private Message Received! ==========================");
			log.info(((TextMessage) message).getText());
			log.info("===============================================================================");
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
