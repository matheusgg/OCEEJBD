package br.com.ejb.rollback;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@TransactionManagement // CONTAINER by default
public class CmtRollbackBeanImpl implements RollbackRemoteBean {

	@PersistenceContext
	private EntityManager em;

	//	@Resource(lookup = "java:comp/EJBContext") // ou
	@Resource
	private SessionContext sessionContext;

	/**
	 * Quando a transacao é controlada pelo Container, é possivel marcar um rollback atraves do metodo SessionContext.setRollbackOnly().
	 * <p>
	 * Apenas beans com transacoes gerenciadas pelo container podem chamar os metodos SessionContext.setRollbackOnly() e SessionContext.getRollbackOnly().
	 * Caso EJBs marcados com @TransactionManagement(BEAN) tentarem invocar esses metodos, uma java.lang.IllegalStateException sera lancada.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public Object execute(final Object value) {
		this.em.persist(value);
		if (!this.sessionContext.getRollbackOnly()) {
			this.sessionContext.setRollbackOnly();
		}

		/**
		 * Esta linha gerara uma excecao, pois beans com transacoes gerenciadas pelo container nao podem chamar o metodo SessionContext.getUserTransaction().
		 */
		// this.sessionContext.getUserTransaction()

		return value;
	}
}
