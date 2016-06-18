package br.com.ejb.exceptions;

import javax.ejb.Remote;

@Remote
public interface SimpleRemoteBean {

	Object executeSystemException(final Object value) throws Exception;

	Object executeApplicationException(final Object value) throws Exception;

}
