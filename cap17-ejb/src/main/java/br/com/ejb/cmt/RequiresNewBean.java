package br.com.ejb.cmt;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RequiresNewBean {

	@PersistenceContext
	private EntityManager em;

	/**
	 * O tipo de transacao REQUIRES_NEW faz com que uma nova transacao seja iniciada toda vez que o metodo deste EJB for invocado. Caso o EJB chamador
	 * já esteja sendo executado dentro de uma transacao, ela será suspendida durante a invocacao do método do EJB marcado com REQUIRES_NEW. Quando a execucao
	 * do EJB que foi chamado for encerrada, a transacao do EJB chamador é resumida. Desta forma, o EJB chamador nao propaga a transacao para o EJB marcado com
	 * REQUIRES_NEW, uma vez que uma nova transacao sempre será iniciada.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Object save(final Object value) {
		this.em.persist(value);
		return value;
	}

}
