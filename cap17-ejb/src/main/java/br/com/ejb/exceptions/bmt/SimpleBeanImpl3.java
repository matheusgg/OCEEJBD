package br.com.ejb.exceptions.bmt;

import br.com.ejb.exceptions.SimpleRemoteBean;
import lombok.extern.java.Log;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;

@Log
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class SimpleBeanImpl3 implements SimpleRemoteBean {

	@Resource
	private UserTransaction userTransaction;

	@EJB
	private SimpleBeanImpl4 beanImpl4;

	/**
	 * Mesmo utilizando BMT, caso a transacao seja propagada, um CMT bean se junte a esta transacao e lance uma SystemException, o
	 * container fara o rollback da transacao e encapsulara a SystemException dentro de uma javax.ejb.EJBTransactionRolledbackException.
	 * <p>
	 * Caso um BMT inicie uma nova transacao e nao complete a mesma, uma EJBException sera lancada informando que a transacao nao foi completada.
	 */
	@Override
	public Object executeSystemException(final Object value) throws Exception {
		try {
			this.userTransaction.begin();
			this.beanImpl4.doSomeWork(value);
			this.userTransaction.commit();
		} catch (final Exception e) {
			log.info("Status =================> " + String.valueOf(this.userTransaction.getStatus()));
			this.userTransaction.rollback();
			throw e;
		}
		return value;
	}

	@Override
	public Object executeApplicationException(final Object value) throws Exception {
		try {
			this.userTransaction.begin();
			this.beanImpl4.doAnotherWork(value);
			this.userTransaction.commit();
		} catch (final Exception e) {
			log.info("Status =================> " + String.valueOf(this.userTransaction.getStatus()));
			this.userTransaction.rollback();
			throw e;
		}
		return value;
	}
}
