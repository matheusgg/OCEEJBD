package br.com.ejb.exceptions;

import javax.ejb.Remote;
import java.util.concurrent.Future;

@Remote
public interface ExceptionRemoteBean {

	void testRuntimeException();

	void testHandlingRuntimeException();

	void testIllegalAccessException() throws Exception;

	void testHandlingIllegalAccessException() throws Exception;

	void testSystemException();

	void testApplicationException() throws Exception;

	Future<String> testSystemExceptionAsync();

	Future<String> testApplicationExceptionAsync() throws IllegalAccessException;

}
