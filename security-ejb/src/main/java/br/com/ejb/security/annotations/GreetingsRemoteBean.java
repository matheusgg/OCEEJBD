package br.com.ejb.security.annotations;

import javax.ejb.Remote;

@Remote
public interface GreetingsRemoteBean {

	String greetings1();

	String greetings2();

	String greetings3();

}
