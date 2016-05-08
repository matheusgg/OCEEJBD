package br.com.ejb.jpaql;

import javax.ejb.Remote;

@Remote
public interface JPAQLRemoteBean {

	void executeOperations();
}
