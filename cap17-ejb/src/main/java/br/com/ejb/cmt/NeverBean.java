package br.com.ejb.cmt;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class NeverBean {

	@PersistenceContext
	private EntityManager em;

	/**
	 * O tipo de transacao NEVER obriga que este EJB seja invocado fora de um escopo transacional, ou seja, se um cliente que faz parte de uma transacao
	 * chamar um EJB marcado como NEVER, uma excecao javax.ejb.EJBException será lancada. Somente clientes que nao fazem parte de nenhuma transacao podem
	 * chamar EJBs marcados como NEVER.
	 */
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public Object save(final Object value) {
		this.em.persist(value);
		return value;
	}
}
