package br.com.ejb.interceptors.beans;

import br.com.ejb.interceptors.LifecycleInterceptor;

import javax.ejb.Stateless;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.ExcludeDefaultInterceptors;
import javax.interceptor.Interceptors;
import java.util.UUID;

/**
 * A anotacao @ExcludeDefaultInterceptors serve para desabilitar interceptors definidos atraves do XML utilizando o coringa (*).
 * <p>
 * Esta anotacao pode ser aplicada tanto a nivel de classe quanto a nivel de metodo.
 * <p>
 * Interceptors de ciclo de vida sao sempre executados.
 */
@Stateless
@ExcludeDefaultInterceptors
@Interceptors(LifecycleInterceptor.class)
public class TokenBeanImpl implements TokenRemoteBean {

	@Override
	public String generateToken() {
		return UUID.randomUUID().toString();
	}

	/**
	 * A anotacao @ExcludeClassInterceptos serve para desabilitar interceptors definidos na classe durante a invocacao
	 * deste metodo.
	 * <p>
	 * Esta anotacao pode ser aplicada apenas a nivel de metodo.
	 */
	@ExcludeClassInterceptors
	@Override
	public String generateToken(final int size) {
		return this.generateToken().substring(0, size);
	}
}
