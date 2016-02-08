package br.com.ejb.lifecycle;

import javax.ejb.Remote;

/**
 * Created on 08/02/16.
 */
@Remote
public interface SingletonLifecycleBeanRemote {

	String createMsg();

}
