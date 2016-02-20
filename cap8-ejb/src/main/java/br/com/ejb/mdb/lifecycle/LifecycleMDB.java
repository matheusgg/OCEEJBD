package br.com.ejb.mdb.lifecycle;

import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.UUID;

/**
 * O ciclo de vida de um Message Driven Bean é semelhante ao ciclo de um Stateless Bean e possui apenas 2 fases:
 * Does Not Exist e Method Ready Pool.
 * <hr>
 * Quando ainda nao ha nenhuma instancia do MDB na memoria, diz-se que ele esta na fase Does Not Exist
 * <hr>
 * Quando o container EJB recebe uma mensagem JMS e nenhuma instancia de MDB existe na memoria, o bean é instanciado
 * atraves da chamada a Class.newInstance() (o MDB deve possuir um construtor padrao), todos os pontos de injecao sao
 * inicializados e o metodo marcado com a anotacao '@PostConstruct' é invocado. Após essas 3 etapas o bean é colocado
 * na fase Method Ready Pool para atender a solicitacao de processamento da mensagem recebida.
 * <hr>
 * Enquanto uma instancia de MDB esta processando uma mensagem, ela nao pode atender mais nenhuma outra requisicao,
 * por este motivo os servidores podem criar pools de MDBs.
 */
@Log
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/simple3"),
})
public class LifecycleMDB implements MessageListener {

	private String id;

	/**
	 * O metodo anotado com '@PostConstruct', assim como o metodo anotado com '@PreDestroy', pode ter qualquer nivel
	 * de acesso, deve ser void, nao deve receber parametros e nao deve lancar checked exceptions (ou seja, application
	 * exceptions).
	 */
	@PostConstruct
	void init() {
		this.id = UUID.randomUUID().toString();
		log.info("Creating MDB instance with ID " + this.id);
	}

	@Override
	public void onMessage(final Message message) {
		try {
			log.info(this.id + " ===> " + ((TextMessage) message).getText());
		} catch (Exception e) {
			log.throwing(this.getClass().getName(), "onMessage", e);
		}
	}

	@PreDestroy
	void destroy() {
		log.info("Destroying MDB instance with ID " + this.id);
	}
}
