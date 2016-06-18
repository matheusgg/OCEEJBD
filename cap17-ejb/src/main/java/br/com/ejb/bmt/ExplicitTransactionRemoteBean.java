package br.com.ejb.bmt;

import javax.ejb.Remote;
import java.io.Serializable;

@Remote
public interface ExplicitTransactionRemoteBean extends Serializable {

	Object executeWithTransactionManager(final Object value) throws Exception;

	Object executeWithUserTransaction(final Object value) throws Exception;

	Object executeWithTransactionPropagation(final Object value) throws Exception;

}
