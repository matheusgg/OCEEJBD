package br.com.ejb.exception;

/**
 * Toda excecao lancada que descenda de RuntimeException será empacotada pelo container EJB dentro de uma EJBException.
 * Diferentemente das ApplicationException, excecoes de tempo de execucao nao sao serializadas antes de serem enviadas
 * para o cliente.
 * <hr>
 * A anotacao @ApplicationException serve para indicar que uma excecao é uma ApplicationException explicitamente, desta forma, é possivel
 * tornar execoes nao checadas em ApplicationExceptions.
 */
// @ApplicationException
public class DivideByZeroException extends RuntimeException {

	private static final long serialVersionUID = 5194227788404498181L;

	public DivideByZeroException() {
	}

	public DivideByZeroException(String message) {
		super(message);
	}

	public DivideByZeroException(String message, Throwable cause) {
		super(message, cause);
	}

	public DivideByZeroException(Throwable cause) {
		super(cause);
	}

	public DivideByZeroException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
