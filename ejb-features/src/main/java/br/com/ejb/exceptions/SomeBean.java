package br.com.ejb.exceptions;

import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import java.util.UUID;

@Log
@Stateful
@LocalBean
public class SomeBean {

	private String id;

	@PostConstruct
	void init() {
		this.id = UUID.randomUUID().toString();
		log.info("Creating instance with ID " + this.id);
	}

	public void someMethod() {
		throw new RuntimeException("System Exception occurred in someMethod()");
	}

	public void someMethod2() throws IllegalAccessException {
		throw new IllegalAccessException("Application Exception occurred in someMethod2()");
	}

	@PreDestroy
	void destroy() {
		log.info("Destroying instance with ID " + this.id);
	}

}
