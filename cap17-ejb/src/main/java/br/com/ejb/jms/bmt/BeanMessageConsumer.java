package br.com.ejb.jms.bmt;

import lombok.extern.java.Log;

import javax.annotation.Resource;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.transaction.UserTransaction;

@Log
/*@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto_acknowledge"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/tx"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})*/
@TransactionManagement(TransactionManagementType.BEAN)
public class BeanMessageConsumer implements MessageListener {

	@Resource(lookup = "java:comp/UserTransaction")
	private UserTransaction userTransaction;

	@Override
	public void onMessage(final Message message) {
		try {
			this.userTransaction.begin();
			final TextMessage textMessage = (TextMessage) message;
			final String raiseError = textMessage.getStringProperty("raiseError");
			if (raiseError != null) {
				throw new IllegalArgumentException("Property raiseError setted!");
			}
			log.info("========================================> " + textMessage.getText());
			this.userTransaction.commit();
		} catch (final Exception e) {
			try {
				this.userTransaction.rollback();
				log.severe(e.getMessage());
			} catch (final Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
