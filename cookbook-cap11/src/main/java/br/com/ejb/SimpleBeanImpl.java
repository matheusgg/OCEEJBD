package br.com.ejb;

import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.Timer;

@Log
@Stateless
public class SimpleBeanImpl {

	@PostConstruct
	void init() {
		log.info("init");
	}

	public String execute() {
		return "Executed!";
	}

	public String execute(final String param) {
		return param + " Executed!";
	}

	//	@Schedule(second = "0/10", minute = "*", hour = "*", persistent = false, info = "Schedule 1")
	void timeout(final Timer timer) {
		log.info(timer.getInfo().toString());
	}

	//	@Schedule(second = "0/10", minute = "*", hour = "*", persistent = false, info = "Schedule 2")
	void timeout2(final Timer timer) {
		log.info(timer.getInfo().toString());
	}

}
