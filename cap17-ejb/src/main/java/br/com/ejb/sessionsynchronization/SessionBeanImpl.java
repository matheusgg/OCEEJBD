package br.com.ejb.sessionsynchronization;

import br.com.ejb.exceptions.CustomApplicationException;
import br.com.ejb.model.Client;
import lombok.extern.java.Log;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.rmi.RemoteException;

/**
 * A interface SessionSynchronization faz com que um Stateful session bean com transacoes gerenciadas pelo container
 * seja notificado sobre eventos transacionais.
 * <p>
 * <ul>
 * <li>
 * Toda vez que um metodo transacional de um SFSB é invocado por um cliente, o container invoca o método afterBegin() antes de iniciar
 * a chamada ao método original;
 * </li>
 * <li>
 * Depois da execucao do metodo original, o container chama o metodo beforeCompletion() antes de commitar a transacao, neste ponto
 * é possivel realizar o rollback atraves do metodo SessionContext.setRollbackOnly().
 * </li>
 * <li>
 * Depois que o método beforeCompletion() é executado, o container invoca o metodo afterCompletion(boolean) passando um booleano que
 * indica se a transacao foi commitada ou nao.
 * </li>
 * <li>
 * Caso a transacao tenha sido marcada para rollback durante a execucao do metodo original, o container nao invocara o metodo
 * beforeCompletion(), ou seja, o metodo afterCompletion(boolean) é chamad recebendo o valor false, indicando que a transacao
 * nao foi commitada.
 * </li>
 * <li>
 * Caso uma SystemException seja lancada do metodo transacional, o container nao invocara nenhum dos metodos beforeCompletition() ou
 * afterCompletition().
 * </li>
 * </ul>
 * <p>
 */
@Log
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class SessionBeanImpl implements SessionRemoteBean, SessionSynchronization {

	@PersistenceContext
	private EntityManager em;

	@Resource
	private SessionContext sessionContext;

	@Override
	public Client save(final Client client) throws CustomApplicationException {
		if (client.getId() != null) {
			// throw new IllegalArgumentException("ID must be null!");
			this.sessionContext.setRollbackOnly();
			// throw new CustomApplicationException("ApplicationException");
		} else {
			this.em.persist(client);
		}
		return client;
	}

	@Override
	public void afterBegin() throws EJBException, RemoteException {
		log.info("afterBegin");
	}

	@Override
	public void beforeCompletion() throws EJBException, RemoteException {
		log.info("beforeCompletion");
		//		this.sessionContext.setRollbackOnly();
	}

	@Override
	public void afterCompletion(final boolean commited) throws EJBException, RemoteException {
		log.info("afterCompletion: commited? " + commited);
	}
}
