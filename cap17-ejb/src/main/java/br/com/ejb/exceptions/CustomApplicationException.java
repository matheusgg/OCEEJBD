package br.com.ejb.exceptions;

/**
 * Application Exceptions sao excecoes que estendem java.lang.Exception ou sao anotadas
 * com @ApplicationException
 */
//@ApplicationException(rollback = true)
public class CustomApplicationException extends Exception {

	private static final long serialVersionUID = 817661330542850480L;

	public CustomApplicationException(final String message) {
		super(message);
	}
}
