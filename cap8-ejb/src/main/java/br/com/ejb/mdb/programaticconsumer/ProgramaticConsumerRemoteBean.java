package br.com.ejb.mdb.programaticconsumer;

import javax.ejb.Remote;
import javax.jms.JMSException;

@Remote
public interface ProgramaticConsumerRemoteBean {

	String consume() throws JMSException;

}
