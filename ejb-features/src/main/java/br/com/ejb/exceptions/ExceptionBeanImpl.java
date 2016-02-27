package br.com.ejb.exceptions;

import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Log
@Stateful
public class ExceptionBeanImpl implements ExceptionRemoteBean {

	private String id;

	@EJB
	private SomeBean someBean;

	@PostConstruct
	void init() {
		this.id = UUID.randomUUID().toString();
		log.info("Creating instance with ID " + this.id);
	}

	/**
	 * Quando um EJB lança uma System Excepton (qualquer exceção que estenda RuntimeException e que não esteja anotada com @ApplicationException) a instancia
	 * é destruida pelo container. Neste caso, o método SomeBean.someMethod() lança uma RuntimeExcecption e já que o método ExceptionBeanImpl.testRuntimeException()
	 * não tratou essa excecao, os dois beans serão destruidos pelo container.
	 */
	@Override
	public void testRuntimeException() {
		this.someBean.someMethod();
	}

	/**
	 * Neste caso, o método SomeBean.someMethod() lança uma RuntimeException, então a instancia desse EJB sera destruida pelo container. Porém,
	 * ExceptionBeanImpl não será destruído, pois tratou a exceção lançada.
	 */
	@Override
	public void testHandlingRuntimeException() {
		try {
			this.someBean.someMethod();
		} catch (RuntimeException e) {
			log.warning("Exception occurred in testHandlingRuntimeException(): " + e.getMessage());
		}
	}

	/**
	 * Quando um EJB lança uma Application Exception (qualquer exceção que estenda Exception ou que esteja anotada com @ApplicationException) a instancia não é
	 * destruida pelo container. Neste caso, o método SomeBean.someMethod2() lança uma IllegalAccessException e mesmo que o método
	 * ExceptionBeanImpl.testIllegalAccessException() não trate essa excecao, nenhum dos dois beans serão destruidos pelo container.
	 */
	@Override
	public void testIllegalAccessException() throws Exception {
		this.someBean.someMethod2();
	}

	/**
	 * Neste caso, o método SomeBean.someMethod2() lança uma IllegalAccessException, mesmo assim nenhuma instancia será destruida pelo container.
	 */
	@Override
	public void testHandlingIllegalAccessException() throws Exception {
		try {
			this.someBean.someMethod2();
		} catch (Exception e) {
			log.warning("Exception occurred in testHandlingIllegalAccessException(): " + e.getMessage());
		}
	}

	/**
	 * Quando um EJB lança uma System Exception, essa exceção é encapsulada dentro de uma EJBException para ser enviada para o cliente. Desta forma,
	 * quando uma System Exception é lançada, o cliente sempre receberá uma EJBException.
	 */
	@Override
	public void testSystemException() {
		throw new IllegalArgumentException("Test throwing a System Exception");
	}

	/**
	 * Quando um EJB lança uma Application Exception, essa exceção é enviada diretamente para o cliente sem ser encapsulada dentro de uma EJBException.
	 * Desta forma, quando uma Application Exception é lançada, o cliente sempre receberá a exceção original.
	 */
	@Override
	public void testApplicationException() throws Exception {
		throw new IllegalAccessException("Test throwing an Application Exception");
	}

	/**
	 * Quando uma System Exception é lançada de um método assincrono, o container EJB nao destroi a instancia.
	 * No caso de métodos assincronos, o cliente sempre receberá uma ExecutionException. Quando uma system exception
	 * é lancada de um método assincrono, ela é encapsulada dentro de uma EJBException e esta, por sua vez, é encapsulada
	 * dentro de uma ExecutionException.
	 */
	@Asynchronous
	@Override
	public Future<String> testSystemExceptionAsync() {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			log.throwing(ExceptionBeanImpl.class.toString(), "testSystemExceptionAsync()", e);
		}
		throw new IllegalArgumentException("Test throwing a System Exception");
	}

	/**
	 * Quando uma Application Exception é lançada de um método assincrono, o container EJB nao destroi a instancia.
	 * No caso de métodos assincronos, o cliente sempre receberá uma ExecutionException. Quando uma Application Exception
	 * é lancada de um método assincrono, ela é encapsulada dentro de uma ExecutionException.
	 */
	@Asynchronous
	@Override
	public Future<String> testApplicationExceptionAsync() throws IllegalAccessException {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			log.throwing(ExceptionBeanImpl.class.toString(), "testApplicationExceptionAsync()", e);
		}
		throw new IllegalAccessException("Test throwing an Application Exception");
	}

	@PreDestroy
	void destroy() {
		log.info("Destroying instance with ID " + this.id);
	}
}
