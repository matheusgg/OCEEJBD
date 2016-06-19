package br.com.ejb.interceptors.beans;

import javax.ejb.Remote;

@Remote
public interface RandomRemoteBean {

	int random(final boolean secured);

}
