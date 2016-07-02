package br.com.ejb.timer.transaction;

import javax.ejb.Remote;

@Remote
public interface TxTimerRemoteBean {

	void createTimer();

}
