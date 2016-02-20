package br.com.ejb.mdb.lifecycle;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;

@Stateless
//@LocalBean
public class LifecycleMessageProducerBeanImpl implements LifecycleMessageProducerRemoteBean {

	@Resource(name = "java:/ConnectionFactory")
	private ConnectionFactory factory;

	@Resource(name = "java:/queue/simple3")
	private Queue queue;

	@Override
	public void sendMessage(final String message) throws Exception {
		final Connection connection = this.factory.createConnection();
		final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		final MessageProducer producer = session.createProducer(this.queue);
		final TextMessage textMessage = session.createTextMessage(message);
		producer.send(textMessage);
		connection.close();
	}

}
