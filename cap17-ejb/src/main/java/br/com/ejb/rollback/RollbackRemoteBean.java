package br.com.ejb.rollback;

import javax.ejb.Remote;

@Remote
public interface RollbackRemoteBean {

	Object execute(final Object value) throws Exception;

}
