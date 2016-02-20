package br.com.ejb.mdb.topics.producer;

import javax.ejb.Remote;

@Remote
public interface TopicProducerRemoteBean {

	void sendMessage(String message) throws Exception;
}
