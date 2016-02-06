package br.com.ejb.sessioncontext;

import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Caso um EJB esteja marcado com a anotcao @LocalBean, significa que ele nao expoe nenhuma view, nem remota, nem local.
 */
@Stateless
@Remote
//@Local
//@LocalBean
public class SessionContext2BeanImpl implements SessionContext2BeanRemote {

	private static final long serialVersionUID = -3117603574360150008L;

	@Override
	public String getName(Object obj) {
		return "Some Name " + obj;
	}
}
