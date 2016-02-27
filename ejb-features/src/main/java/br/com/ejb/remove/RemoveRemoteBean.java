package br.com.ejb.remove;

import javax.ejb.Remote;

@Remote
public interface RemoveRemoteBean {

	String testRemove();

	String testRemoveCallingByEJBProxy();

	String testRemoveThrowingSystemException();

	String testRemoveThrowingApplicationException() throws IllegalAccessException;
}
