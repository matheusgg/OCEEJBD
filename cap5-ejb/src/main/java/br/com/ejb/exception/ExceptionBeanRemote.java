package br.com.ejb.exception;

public interface ExceptionBeanRemote {

	int divide(int num1, int num2) throws DivideByZeroException, InvalidOperationApplicationException;

}
