package br.com.ejb.cmt;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SupportsBean {

	@PersistenceContext
	private EntityManager em;

	/**
	 * O tipo de transacao SUPPORTS faz com que o EJB invocado utilize (se junte) a transacao corrente (caso exista alguma) e propague essa transacao
	 * para as demais invocacoes, porém, caso nao exista nenhuma transacao, o EJB chamado sera executado de forma nao transacional, ou seja, a partir
	 * dele, todas as invocacoes para outros EJB nao farao parte do contexto transacional.
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Object save(final Object value) {
		this.em.persist(value);
		return value;
	}

}
