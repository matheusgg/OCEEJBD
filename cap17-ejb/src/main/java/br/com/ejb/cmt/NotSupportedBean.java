package br.com.ejb.cmt;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class NotSupportedBean {

	@PersistenceContext
	private EntityManager em;

	/**
	 * O tipo de transacao NOT_SUPPORTED faz com que a transacao corrente (se houver alguma) seja suspendida durante a execucao deste metodo.
	 * Desta forma, a transacao do cliente chamador nao é propagada para este EJB, isto é, durante a execucao deste EJB nenhuma
	 * transacao é iniciada ou encerrada.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Object save(final Object value) {
		this.em.persist(value);
		return value;
	}
}
