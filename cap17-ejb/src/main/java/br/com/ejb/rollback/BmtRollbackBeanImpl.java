package br.com.ejb.rollback;

import br.com.ejb.model.Client;
import lombok.extern.java.Log;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

@Log
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class BmtRollbackBeanImpl implements RollbackRemoteBean {

	@PersistenceContext
	private EntityManager em;

	//	@Resource(lookup = "java:comp/EJBContext") // ou
	@Resource
	private SessionContext sessionContext;

	//	@Resource(lookup = "java:comp/UserTransaction") // ou
	@Resource
	private UserTransaction userTransaction;

	@Override
	public Object execute(final Object value) throws Exception {
		final UserTransaction tx = this.sessionContext.getUserTransaction();
		try {
			tx.begin();
			this.em.persist(value);
			if (((Client) value).getName() == null) {
				throw new IllegalArgumentException("Name is null!");
			}
			tx.commit();
		} catch (final Exception e) {
			log.warning(e.getMessage());
			/**
			 * O método UserTransation.rollback() é exclusivo e serve para executar o processo de rollback das operacoes realizadas, ou seja,
			 * somente commit() ou rolback() podem ser chamados no objeto UserTransaction, caso esses dois métodos sejam chamados, uma excecao
			 * será lancada.
			 */
			tx.rollback();

			/**
			 * Já o método UserTransaction.setRollbackOnly() pode ser chamado junto com o método commit(). Este método
			 * serve para marcar uma transacao para rollback, porém a transacao precisa ser completada, ou seja, o método
			 * commit() deve ser invocado depois da chamada de setRollbackOnly() (comportamento varia entre servidores
			 * de aplicacao).
			 */
			// tx.setRollbackOnly();
		}
		return value;
	}
}
