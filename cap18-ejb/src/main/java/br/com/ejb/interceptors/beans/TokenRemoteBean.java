package br.com.ejb.interceptors.beans;

import javax.ejb.Remote;

@Remote
public interface TokenRemoteBean {

	String generateToken();

	String generateToken(final int size);

}
