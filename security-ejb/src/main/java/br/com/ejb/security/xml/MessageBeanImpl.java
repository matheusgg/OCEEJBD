package br.com.ejb.security.xml;

import br.com.ejb.security.runas.InternalBeanImpl;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.Resource;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

/**
 * As anotacoes @RolesAllowed, @PermitAll e @DenyAll serao ignoradas, pois foi definida uma configuracao de permissao de metodo no arquivo ejb-jar.xml,
 * e o mesmo sobrescreve estas anotacoes.
 * <p>
 * A anotacao @RunAs sera ignorada caso haja alguma configuracao de security-identity no arquivo ejb-jar.xml.
 */
@Stateless
@SecurityDomain("other")
@RunAs("user")
public class MessageBeanImpl implements MessageRemoteBean {

	@Resource
	private SessionContext context;

	@EJB
	private InternalBeanImpl internalBean;

	//	@RolesAllowed("admin")
	@Override
	public String message1() {
		return "message1";
	}

	//	@PermitAll
	//	@DenyAll
	@Override
	public String message1(final String userName) {
		return "message1 " + userName;
	}

	@Override
	public String message2() {
		return "message2";
	}

	@Override
	public String message2(final String userName) {
		return "message2 " + userName;
	}

	@Override
	public String message3() {
		if (this.context.isCallerInRole("owner")) {
			return "message3";
		}
		throw new EJBAccessException("User not allowed!");
	}

	@Override
	public String message4() {
		return "message4";
	}

	@Override
	public String message5() {
		return this.internalBean.getClientName();
	}
}
