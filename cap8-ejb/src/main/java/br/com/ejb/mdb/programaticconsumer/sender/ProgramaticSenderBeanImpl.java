package br.com.ejb.mdb.programaticconsumer.sender;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateful;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Stateful
public class ProgramaticSenderBeanImpl implements ProgramaticSenderRemoteBean {

	@Resource(name = "java:/ConnectionFactory")
	private ConnectionFactory factory;

	@Resource(name = "java:/queue/simple4")
	private Queue queue;

	private Connection connection;

	private Session session;

	@PostConstruct
	@PostActivate
	void init() throws JMSException, NamingException {
		if (this.factory == null) {
			this.factory = InitialContext.doLookup("java:/ConnectionFactory");
		}

		if (this.queue == null) {
			this.queue = InitialContext.doLookup("java:/queue/simple4");
		}

		this.connection = this.factory.createConnection();
		this.session = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	}

	@Override
	public void sendMessage(final String message) throws JMSException {
		final MessageProducer producer = this.session.createProducer(this.queue);
		final TextMessage textMessage = this.session.createTextMessage(message);
		producer.send(textMessage);
	}

	@PreDestroy
	@PrePassivate
	void destroy() throws JMSException {
		this.connection.close();
		this.connection = null;
		this.session = null;
		this.factory = null;
		this.queue = null;
	}
}
