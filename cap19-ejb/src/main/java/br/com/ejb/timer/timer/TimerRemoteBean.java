package br.com.ejb.timer.timer;

import javax.ejb.Remote;

@Remote
public interface TimerRemoteBean {

	void createTimers() throws Exception;

}
