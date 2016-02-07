package br.com.ejb.lifecycle;

import javax.ejb.Remote;

/**
 * Created on 07/02/16.
 */
@Remote
public interface LifecycleBeanRemote {

	void firstMethod(String id);

	String doSomeWork();

	String doAnotherWork();

	void removeBean1(boolean throwException);

	void removeBean2();

	void throwSystemException() throws RuntimeException;

	void throwApplicationException() throws Exception;
}
