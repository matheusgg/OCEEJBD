package br.com.ejb.exceptions.cmt;

import br.com.ejb.exceptions.SimpleRemoteBean;
import lombok.extern.java.Log;

import javax.annotation.Resource;
import javax.ejb.*;

@Log
@Stateless
public class SimpleBeanImpl1 implements SimpleRemoteBean {

	@Resource
	private SessionContext sessionContext;

	@EJB
	private SimpleBeanImpl2 beanImpl2;

	/**
	 * Quando o gerenciamento de transacoes é feito pelo container e a transacao é propagada durante a invocacao de outros
	 * beans, caso esses beans se juntem a transacao propagada e lancem uma SystemException, o container fara o rollback
	 * da transacao e encapsulara a SystemException lancada dentro de uma javax.ejb.EJBTransactionRolledbackException.
	 * <p>
	 * Caso os beans chamados nao se juntem a transacao propagada, porém, lancem uma SystemException, o container
	 * nao fara o rollback da transacao propagada e encapsulara a SystemException lancada dentro de uma
	 * javax.ejb.EJBException.
	 * <p>
	 * Uma EJBTransactionRolledbackException só é lancada quando um EJB se junta a uma transacao propagada e o mesmo lanca uma
	 * SystemException.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public Object executeSystemException(final Object value) {
		try {
			return this.beanImpl2.doSomeWork(value);
		} catch (final EJBException e) {
			log.info(String.valueOf(this.sessionContext.getRollbackOnly()));
			throw e;
		}

		/**
		 * Esta linha provocara o rollback da transacao corrente e fara com que o container
		 * lance uma EJBException para o cliente.
		 */
		// throw new CustomSystemException("SystemException");
	}

	/**
	 * ApplicationExceptions nao provocam o rollback da transacao, a nao ser que estejam marcadas com @ApplicationException(rollback=true).
	 * <p>
	 * O container nao encapsula excecoes de aplicacao fazendo com que o cliente receba a excecao original.
	 */
	@Override
	public Object executeApplicationException(final Object value) throws Exception {
		try {
			return this.beanImpl2.doAnotherWork(value);
		} catch (final Exception e) {
			log.info(String.valueOf(this.sessionContext.getRollbackOnly()));
			throw e;
		}
	}
}
