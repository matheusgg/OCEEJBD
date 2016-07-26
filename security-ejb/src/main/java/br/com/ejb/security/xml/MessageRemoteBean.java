package br.com.ejb.security.xml;

import javax.ejb.Remote;

@Remote
public interface MessageRemoteBean {

	String message1();

	String message1(final String userName);

	String message2();

	String message2(final String userName);

	String message3();

	String message4();

	String message5();
}
