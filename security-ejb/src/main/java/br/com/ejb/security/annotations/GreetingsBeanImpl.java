package br.com.ejb.security.annotations;

import lombok.extern.java.Log;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

/**
 * A anotacao @RolesAllowed pode receber os nomes de varias roles. Para o metodo de negocio ser invocado, o cliente
 * precisa possuir pelo menos uma das roles informadas.
 */
@Log
@Stateless
@SecurityDomain("other")
@RolesAllowed("user")
public class GreetingsBeanImpl implements GreetingsRemoteBean {

	/**
	 * A anotacao @RolesAllowed a nivel de metodo sobrescreve a anotacao definida na classe.
	 */
	@RolesAllowed("admin")
	@Override
	public String greetings1() {
		return "greetings1";
	}

	/**
	 * As anotacoes @RolesAllowed, @PermitAll e @DenyAll definidas a nivel de metodo sempre sobrescrevem
	 * as anotacoes especificadas na classe.
	 */
	@PermitAll
	@Override
	public String greetings2() {
		return "greetings2";
	}

	@DenyAll
	@Override
	public String greetings3() {
		return "greetings3";
	}

}
