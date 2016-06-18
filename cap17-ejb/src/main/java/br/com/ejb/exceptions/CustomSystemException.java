package br.com.ejb.exceptions;

/**
 * System Exceptions sao excecoes que estendem java.lang.RuntimeException ou java.rmi.RemoteException.
 */
public class CustomSystemException extends RuntimeException { // ou java.rmi.RemoteException

	private static final long serialVersionUID = 8172915746293310685L;

	public CustomSystemException(final String message) {
		super(message);
	}
}
