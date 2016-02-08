package br.com.ejb.concurrency;

import javax.ejb.Remote;

/**
 * Created on 08/02/16.
 */
@Remote
public interface ConcurrencyBeanRemote {

	String method1() throws Exception;

	String method2() throws Exception;

}
