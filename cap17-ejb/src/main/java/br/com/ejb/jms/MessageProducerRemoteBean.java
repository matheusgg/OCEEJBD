package br.com.ejb.jms;

import javax.ejb.Remote;
import java.util.Map;

@Remote
public interface MessageProducerRemoteBean {

	void sendMessage(final String message, final Map<String, String> properties) throws Exception;

}
