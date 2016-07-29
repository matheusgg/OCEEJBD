package br.com.ejb.embeddable;

import javax.ejb.Stateless;

@Stateless
public class EchoBeanImpl {

	public String echo(final String message) {
		return message;
	}

}
