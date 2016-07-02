package br.com.ejb.timer.scheduleexpression;

import javax.ejb.Remote;

@Remote
public interface ScheduleExpressionRemoteBean {

	void schedule();

}
