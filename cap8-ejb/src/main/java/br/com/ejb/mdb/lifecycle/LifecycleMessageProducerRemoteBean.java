package br.com.ejb.mdb.lifecycle;

import javax.ejb.Remote;

@Remote
public interface LifecycleMessageProducerRemoteBean {

	void sendMessage(final String message) throws Exception;
}
