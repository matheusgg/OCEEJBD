package br.com.ejb.exception;

import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * A anotacao @Stateless possui o atributo name que é utilizado para especificar o nome do EJB. Caso este atributo seja omitido,
 * o nome simples da classe será utilizada como padrao.
 */
@Stateless(name = "ExceptionBean")
@Remote
public class ExceptionBeanImpl implements ExceptionBeanRemote {

	@Override
	public int divide(int num1, int num2) throws InvalidOperationApplicationException, DivideByZeroException {
		if (num1 == 0) {
			throw new InvalidOperationApplicationException("Num1 cannot be zero!");
		}

		if (num2 == 0) {
			throw new DivideByZeroException("Cannot divide by zero!");
		}

		return num1 / num2;
	}
}
