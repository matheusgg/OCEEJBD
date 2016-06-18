package br.com.ejb.cmt;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MandatoryBean {

	@PersistenceContext
	private EntityManager em;

	/**
	 * O tipo de transacao MANDATORY obriga a existencia de uma transacao corrente durante a chamada deste método. Ou seja, se um cliente que faz parte de uma transacao
	 * chamar este EJB, ele se juntara a transacao do cliente, ou seja, o cliente propagara a transacao. Porém, caso nao exista nenhuma transacao ativa, uma excecao
	 * javax.ejb.EJBTransactionRequiredException será lancada, pois é obrigatorio que o EJB marcado com MANDATORY seja chamado dentro de uma transacao, pois ele
	 * sempre tentara se juntar a mesma.
	 */
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public Object save(final Object value) {
		this.em.persist(value);
		return value;
	}

}
