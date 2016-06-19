package br.com.ejb.interceptors.beans;

import javax.ejb.Remote;

@Remote
public interface MessageRemoteBean {

	String getMessage();

}
