package br.com.ejb.timer.timeout;

import javax.ejb.Remote;

@Remote
public interface TimeoutRemoteBean {

	void createTimer();

}
