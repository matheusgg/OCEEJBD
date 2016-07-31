package br.com.ejb.singleton.async;

import lombok.extern.java.Log;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Log
@Stateless
public class MessageBeanImpl implements MessageLocalBean {

	private static final String[] GREETINGS = {"Hi", "Hello", "Dear", "Welcome"};

	@Asynchronous
	@Override
	public void logExecution() {
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		log.info("=========================== Execution Started! ===========================");
	}

	@Asynchronous
	@Override
	public Future<String> getMessage(final String username) {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		return new AsyncResult<>(GREETINGS[(int) (Math.random() * 4)] + " " + username + "!");
	}
}
