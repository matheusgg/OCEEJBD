package br.com.ejb.exceptions.cmt;

import br.com.ejb.exceptions.CustomApplicationException;
import br.com.ejb.exceptions.CustomSystemException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@LocalBean
public class SimpleBeanImpl2 {

	/**
	 * Caso este bean se junte a transacao corrente e lance uma SystemException, o container fara o rollback da transacao
	 * e encapsulara a SystemException dentro de uma javax.ejb.EJBTransactionRolledbackException.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Object doSomeWork(final Object value) {
		throw new CustomSystemException("SystemException");
	}

	/**
	 * Mesmo que este bean se junte a transacao corrente, lancar uma ApplicationException nao provocara o rollback da transacao.
	 */
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public Object doAnotherWork(final Object value) throws CustomApplicationException {
		throw new CustomApplicationException("ApplicationException");
	}
}
