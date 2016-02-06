package br.com.ejb.async;

import java.util.concurrent.Future;

public interface AsyncBeanRemote {

	String getGreetings();

	Future<String> doSomeSlowTask();

	Future<String> doAnotherSlowTask();

}
