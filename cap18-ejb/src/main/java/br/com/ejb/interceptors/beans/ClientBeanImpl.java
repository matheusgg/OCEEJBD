package br.com.ejb.interceptors.beans;

import br.com.ejb.interceptors.AuditInterceptor;
import br.com.ejb.interceptors.StringReplacerInterceptor;
import br.com.ejb.interceptors.ValidationInterceptor;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 * A anotacao @Interceptors serve para especificar as classes que interceptarao as chamadas dos metodos
 * deste EJB.
 * <p>
 * A ordem especificada na anotacao sera a ordem de invocacao dos interceptors.
 * <p>
 * A anotacao @Interceptors pode ser especificada tanto a nivel de classe quanto a nivel de metodo.
 */
@Stateless
@Remote
@Interceptors({AuditInterceptor.class, ValidationInterceptor.class})
public class ClientBeanImpl implements ClientRemoteBean {

	/**
	 * Quando a anotacao @Interceptors é especificada tanto a nivel de classe quanto a nivel de metodo, todos os interceptors
	 * sao chamados de acordo com a ordem de declaracao, ou seja, primeiro os especificados na classe e depois os especificados
	 * no metodo.
	 */
	@Interceptors(StringReplacerInterceptor.class)
	@Override
	public String greetings(final String clientName) {
		return "Welcome " + clientName;
	}
}
