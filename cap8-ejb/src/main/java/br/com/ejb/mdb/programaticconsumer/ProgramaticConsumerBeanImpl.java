package br.com.ejb.mdb.programaticconsumer;

import lombok.extern.java.Log;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;

@Log
@Stateless
public class ProgramaticConsumerBeanImpl implements ProgramaticConsumerRemoteBean {

	@Resource(name = "java:/ConnectionFactory")
	private ConnectionFactory factory;

	@Resource(name = "java:/queue/simple4")
	private Queue queue;

	@Override
	public String consume() throws JMSException {
		final Connection connection = this.factory.createConnection();

		/*
		 * De acordo com a especificacao de EJBs, os parametros informados na criacao de uma session sao ignorados, uma vez
		 * que é o container EJB que gerencia as transacoes.
		 * Na especificacao JMS, quado o valor do segundo parametro é igual a 0, ou seja,
		 * Session.SESSION_TRANSACTED, e a sessao é transacionada, o acknowledgeMode é ignorado.
		 */
		final Session session = connection.createSession(false, Session.SESSION_TRANSACTED);
		connection.start();

		final MessageConsumer consumer = session.createConsumer(this.queue);
		final TextMessage message = (TextMessage) consumer.receive();
		connection.close();

		return message != null ? message.getText() : null;
	}
}
