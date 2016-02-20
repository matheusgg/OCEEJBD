package br.com.ejb.mdb.topics.producer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;

@Stateless
public class TopicProducerBeanImpl implements TopicProducerRemoteBean {

	@Resource(name = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(name = "java:/topic/simple2")
	private Topic topic;

	private Connection connection;

	private Session session;

	private MessageProducer producer;

	@PostConstruct
	void init() throws Exception {
		this.connection = this.connectionFactory.createConnection();
		this.session = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		this.producer = this.session.createProducer(this.topic);
	}

	@Override
	public void sendMessage(final String message) throws Exception {
		final TextMessage textMessage = this.session.createTextMessage(message);
		this.producer.send(textMessage);
	}

	@PreDestroy
	void destroy() throws Exception {
		this.connection.close();
	}
}
