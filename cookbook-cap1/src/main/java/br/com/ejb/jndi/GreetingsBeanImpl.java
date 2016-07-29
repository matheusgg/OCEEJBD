package br.com.ejb.jndi;

import javax.ejb.Stateless;

@Stateless
public class GreetingsBeanImpl {

	public String getFormalGreeting(final String username) {
		return "Dear " + username;
	}

	public String getInformalGreeting(final String username) {
		return "Hi " + username;
	}

}
