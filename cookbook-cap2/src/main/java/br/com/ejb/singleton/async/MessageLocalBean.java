package br.com.ejb.singleton.async;

import javax.ejb.Local;
import java.util.concurrent.Future;

@Local
public interface MessageLocalBean {

	void logExecution();

	Future<String> getMessage(final String username);

}
