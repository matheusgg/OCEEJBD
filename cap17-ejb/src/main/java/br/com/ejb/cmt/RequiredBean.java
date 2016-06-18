package br.com.ejb.cmt;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RequiredBean {

	@PersistenceContext
	private EntityManager em;

	/**
	 * O tipo de transacao REQUIRED é utilizado por padrao caso a anotacao @TransactionAttribute nao seja especificada. Este tipo de transacao
	 * faz com que o EJB chamado se junte a transacao corrente (caso exista alguma) ou inicie uma nova transacao (caso o EJB chamador nao esteja
	 * sendo executado dentro de uma transacao).
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Object save(final Object value) {
		this.em.persist(value);
		return value;
	}
}
