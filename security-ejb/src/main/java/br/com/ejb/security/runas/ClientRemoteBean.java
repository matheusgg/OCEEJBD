package br.com.ejb.security.runas;

import javax.ejb.Remote;

@Remote
public interface ClientRemoteBean {

	String clientName();
}
