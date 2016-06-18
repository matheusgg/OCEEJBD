package br.com.ejb.exceptions.bmt;

import br.com.ejb.exceptions.CustomApplicationException;
import br.com.ejb.exceptions.CustomSystemException;

import javax.ejb.*;

@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
public class SimpleBeanImpl4 {

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Object doSomeWork(final Object value) {
		throw new CustomSystemException("SystemException");
	}

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public Object doAnotherWork(final Object value) throws CustomApplicationException {
		throw new CustomApplicationException("ApplicationException");
	}

}
