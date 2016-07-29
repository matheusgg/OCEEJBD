package br.com.ejb.mdb;

import lombok.extern.java.Log;

import javax.ejb.EJBException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Log
//@MessageDriven(activationConfig = {
//		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/request"),
//		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
//		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
//})
public class RequestLoggerBean implements MessageListener {

	@Override
	public void onMessage(final Message message) {
		try {
			final String host = ((TextMessage) message).getText();
			log.info("New request received from " + host);
		} catch (final Exception e) {
			throw new EJBException(e);
		}
	}
}
