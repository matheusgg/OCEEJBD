package br.com.ejb.security.runas;

import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.RolesAllowed;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
@RolesAllowed("user")
@RunAs("admin")
@SecurityDomain("other")
public class ClientBeanImpl implements ClientRemoteBean {

	@EJB
	private InternalBeanImpl internalBean;

	@Override
	public String clientName() {
		return this.internalBean.getClientName();
	}
}
