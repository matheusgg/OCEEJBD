package br.com.ejb.mdb.messagedrivencontext;

import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Log
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto_acknowledge"),
		@ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "Seletor='2'"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/simple")})
public class MessageDrivenContextMDB implements MessageListener {

	/**
	 * O objeto MessageDrivenContext é semelhante ao SessionContext, ou seja, com ele é possivel recuperar informacoes do contexto
	 * da invocacao atual e utilizar os servicos do container EJB. Com MessageDrivenContext é possivel apenas utilizar os recursos transacionais
	 * (caso o gerenciamento da transacao seja feito pelo bean - BMT), realizar o lookup de recursos dentro do JNDI ENC, recuperar propriedades
	 * de contexto ou utilizar os servicos de agendamento. Uma vez que MDBs nao possuem view, a invocacao a qualquer metodo *Home* lancara uma excecao.
	 */
	@Resource
	private MessageDrivenContext messageDrivenContext;

	/**
	 * Durante a nvocacao do metodo anotado com '@PostConstruct', qualquer chamada aos metodos getCallerPrincipal e isCallerInRole resultarao em uma
	 * excecao, uma vez que ainda nao ha nenhum contexto de seguranca neste momento.
	 */
	@PostConstruct
	void init() {
		log.info(this.messageDrivenContext.toString());
		log.info(this.messageDrivenContext.getContextData().toString());

		/*log.info(this.messageDrivenContext.getCallerPrincipal().toString());
		log.info(String.valueOf(this.messageDrivenContext.isCallerInRole("admin")));*/

		/*log.info(this.messageDrivenContext.getUserTransaction().toString());
		this.messageDrivenContext.setRollbackOnly();*/

		log.info(this.messageDrivenContext.getTimerService().toString());
		log.info(this.messageDrivenContext.lookup("java:module/MessageProducerBeanImpl!br.com.ejb.mdb.producer.MessageProducerRemoteBean").toString());
	}

	@Override
	public void onMessage(Message message) {
		try {
			log.info(((TextMessage) message).getText());
		} catch (Exception e) {
			log.throwing(this.getClass().getName(), "onMessage", e);
		}
	}
}
