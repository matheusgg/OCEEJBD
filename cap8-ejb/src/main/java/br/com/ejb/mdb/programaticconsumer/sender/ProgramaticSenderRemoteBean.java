package br.com.ejb.mdb.programaticconsumer.sender;

import javax.ejb.Remote;
import javax.jms.JMSException;

@Remote
public interface ProgramaticSenderRemoteBean {

	void sendMessage(String message) throws JMSException;

}
