package br.com.ejb.cmt;

import javax.ejb.Remote;
import java.io.Serializable;

@Remote
public interface TransactionAttributesRemoteBean extends Serializable {

	Object executeNotSupportedWithinTransaction(final Object value);

	Object executeSupportsWithinTransaction(final Object value);

	Object executeSupportsWithoutTransaction(final Object value);

	Object executeRequiredWithinTransaction(final Object value);

	Object executeRequiredWithoutTransaction(final Object value);

	Object executeRequiresNewWithinTransaction(final Object value);

	Object executeRequiresNewWithoutTransaction(final Object value);

	Object executeMandatoryWithinTransaction(final Object value);

	Object executeMandatoryWithoutTransaction(final Object value);

	Object executeNeverWithinTransaction(final Object value);

	Object executeNeverWithoutTransaction(final Object value);

}
