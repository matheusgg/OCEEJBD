package br.com.ejb.mdb.queues.producer;

import javax.ejb.Remote;

@Remote
public interface QueueProducerRemoteBean {

	void sendMessage(String message) throws Exception;

}
