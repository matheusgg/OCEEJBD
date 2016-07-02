package br.com.ejb;

import br.com.ejb.timer.timer.TimerRemoteBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

public class Cap19Client {

	public static void main(final String[] args) throws Exception {
		final Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		env.put(Context.PROVIDER_URL, "remote://localhost:4447");
		env.put(Context.SECURITY_PRINCIPAL, "admin");
		env.put(Context.SECURITY_CREDENTIALS, "admin123");
		env.put("jboss.naming.client.ejb.context", true);

		final Context ctx = new InitialContext(env);

		/*
		 * =================================================================================================
		 * ScheduleExpression
		 * =================================================================================================
		 */
		/*final ScheduleExpressionRemoteBean bean = (ScheduleExpressionRemoteBean) ctx.lookup("cap19-ejb/ScheduleExpressionBeanImpl!br.com.ejb.timer.scheduleexpression.ScheduleExpressionRemoteBean");
		bean.schedule();*/

		/*
		 * =================================================================================================
		 * @Schedule
		 * =================================================================================================
		 */
		/*final ScheduleRemoteBean bean = (ScheduleRemoteBean) ctx.lookup("cap-19-ejb/ScheduleBeanImpl!br.com.ejb.timer.schedule.ScheduleRemoteBean");
		bean.businessMethod();*/

		/*
		 * =================================================================================================
		 * @Timeout
		 * =================================================================================================
		 */
		/*final TimeoutRemoteBean bean = (TimeoutRemoteBean) ctx.lookup("cap19-ejb/TimoutBeanImpl!br.com.ejb.timer.timeout.TimeoutRemoteBean");
		bean.createTimer();*/

		/*
		 * =================================================================================================
		 * Timers / Transactions
		 * =================================================================================================
		 */
		/*final TxTimerRemoteBean bean = (TxTimerRemoteBean) ctx.lookup("cap19-ejb/TxTimerBeanImpl!br.com.ejb.timer.transaction.TxTimerRemoteBean");
		bean.createTimer();*/

		/*
		 * =================================================================================================
		 * Timers
		 * =================================================================================================
		 */
		final TimerRemoteBean bean = (TimerRemoteBean) ctx.lookup("cap19-ejb/TimerBeanImpl!br.com.ejb.timer.timer.TimerRemoteBean");
		bean.createTimers();
	}

}
