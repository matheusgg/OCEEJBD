package br.com.ejb.security.programatic;

import javax.ejb.Remote;

@Remote
public interface TaskRemoteBean {

	String task1();

	String task2();

}
