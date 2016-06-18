package br.com.ejb.jms.cmt;

import lombok.extern.java.Log;

import javax.ejb.*;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Log
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto_acknowledge"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/tx"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class ContainerMessageConsumerBean implements MessageListener {

	//	@Resource(lookup = "java:comp/EJBContext") //ou
	//	@Resource
	//	private MessageDrivenContext sessionContext;

	/**
	 * Por padrao, o container EJB nao executa MDBs dentro de transacoes, por este motivo, nao é possivel utilizar
	 * o tipo de transacao MANDATORY.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public void onMessage(final Message message) {
		try {
			final TextMessage textMessage = (TextMessage) message;
			final String raiseError = textMessage.getStringProperty("raiseError");
			if (raiseError != null) {
				throw new IllegalArgumentException("Property raiseError setted!");
			}
			log.info("========================================> " + textMessage.getText());
		} catch (final JMSException e) {
			e.printStackTrace();
		}
	}
}
