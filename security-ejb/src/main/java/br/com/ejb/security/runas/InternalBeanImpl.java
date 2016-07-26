package br.com.ejb.security.runas;

import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
@SecurityDomain("other")
public class InternalBeanImpl {

	@Resource
	private SessionContext context;

	@RolesAllowed("admin")
	public String getClientName() {
		return this.context.getCallerPrincipal().getName();
	}

}
