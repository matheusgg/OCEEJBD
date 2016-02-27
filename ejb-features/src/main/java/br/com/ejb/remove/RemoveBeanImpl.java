package br.com.ejb.remove;

import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Remove;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import java.util.UUID;

@Log
@Stateful
public class RemoveBeanImpl implements RemoveRemoteBean {

	@Resource
	private SessionContext sessionContext;

	private String id;

	@PostConstruct
	void init() {
		this.id = UUID.randomUUID().toString();
		log.info("Creating instance with ID " + this.id);
	}

	/**
	 * A anotação @Remove deve ser aplicada em métodos de negócio de EJBs Stateful para indicar que após a execução dos mesmos
	 * a instancia do bean deve ser removida. Essa anotação não tem efeito em outros tipos de EJBs, como por exemplo,
	 * Stateless, Singleton e MDB.
	 */
	@Remove
	@Override
	public String testRemove() {
		return "testRemove()";
	}

	/**
	 * Quando um método comum chama um método anotado com @Remove através do proxy EJB, o container remove o bean assim que a execução
	 * do método que chamou o método de remoção for concluído.
	 */
	@Override
	public String testRemoveCallingByEJBProxy() {
		return this.sessionContext.getBusinessObject(RemoveRemoteBean.class).testRemove() + " calling by EJB Proxy.";
	}

	/**
	 * Neste caso, esta instancia será destruida mesmo especificando retainIfException = true, pois uma System Exception está sendo lançada e quando
	 * esse tipo de exceção é lançada, o container EJB remove a instancia do pool de beans.
	 */
	@Remove(retainIfException = true)
	@Override
	public String testRemoveThrowingSystemException() {
		throw new IllegalArgumentException("Throwing a System Exception...");
	}

	/**
	 * Mesmo lançando uma exceção essa instancia nao sera destruida, pois a exceção é uma Application Exception e a anotação @Remove
	 * foi especificada com o atributo retainIfException = true. Este bean seria destruído somente se uma System Exception fosse
	 * lançada.
	 */
	@Remove(retainIfException = true)
	@Override
	public String testRemoveThrowingApplicationException() throws IllegalAccessException {
		throw new IllegalAccessException("Throwing an Application Exception...");
	}

	@PreDestroy
	void destroy() {
		log.info("Destroying instance with ID " + this.id);
	}
}
