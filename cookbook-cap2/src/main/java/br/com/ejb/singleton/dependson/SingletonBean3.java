package br.com.ejb.singleton.dependson;

import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;

@Log
@Singleton
@DependsOn("SingletonBean1")
public class SingletonBean3 {

	@PostConstruct
	void init() {
		log.info("Initializing SingletonBean3...");
	}

	public String getMessage() {
		return "Message from SingletonBean3";
	}

}
