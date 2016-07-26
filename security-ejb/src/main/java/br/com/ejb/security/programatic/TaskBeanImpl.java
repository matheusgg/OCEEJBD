package br.com.ejb.security.programatic;

import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.Resource;
import javax.ejb.EJBAccessException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
@SecurityDomain("other")
public class TaskBeanImpl implements TaskRemoteBean {

	@Resource
	private SessionContext context;

	@Override
	public String task1() {
		if (this.context.isCallerInRole("admin")) {
			return "task1";
		}
		throw new EJBAccessException("User not allowed!");
	}

	@Override
	public String task2() {
		if (this.context.isCallerInRole("user")) {
			return "task2";
		}
		throw new EJBAccessException("User not allowed!");
	}
}
