package br.com.ejb.singleton.dependson;

import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

@Log
@Singleton
public class SingletonBean1 {

	@PostConstruct
	void init() {
		log.info("Initializing SingletonBean1...");
	}

	public String getMessage() {
		return "Message from SingletonBean1";
	}

}
