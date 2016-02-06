package br.com.ejb.exception;

import javax.ejb.ApplicationException;

/**
 * Toda excecao que estende Exception ou uma checked exception é considerada como uma ApplicationException pelo container EJB.
 * ApplicationExceptions devem ser utilizadas para expressar problemas de regras de negocio da aplicacao. Quando uma ApplicationException
 * é lançada, o container nao a empacota dentro de uma EJBException, ao inves disso passa a excecao original (ou seja, a ApplicationException)
 * para o cliente. Todos os campos de uma ApplicationException sao serializados antes de serem enviados para o cliente, desta forma, uma
 * ApplicationException nao pode possuir campos nao serializaveis, caso possua, uma excecao de Serializacao sera lancada.
 * <hr>
 * A anotacao @ApplicationException serve para indicar que uma excecao é uma ApplicationException explicitamente, desta forma, é possivel
 * tornar execoes nao checadas em ApplicationExceptions.
 */
@ApplicationException
public class InvalidOperationApplicationException extends Exception {

	private static final long serialVersionUID = -6145667695075877359L;

	public InvalidOperationApplicationException() {
	}

	public InvalidOperationApplicationException(String message) {
		super(message);
	}

	public InvalidOperationApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidOperationApplicationException(Throwable cause) {
		super(cause);
	}

	public InvalidOperationApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}