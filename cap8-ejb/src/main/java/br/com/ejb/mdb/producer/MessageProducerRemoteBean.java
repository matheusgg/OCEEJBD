package br.com.ejb.mdb.producer;

import javax.ejb.Remote;

@Remote
public interface MessageProducerRemoteBean {

	void sendMessage(String selector, String message) throws Exception;

}
