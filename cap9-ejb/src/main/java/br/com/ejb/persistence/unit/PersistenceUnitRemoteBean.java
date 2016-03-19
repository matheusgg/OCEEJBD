package br.com.ejb.persistence.unit;

import javax.ejb.Remote;

@Remote
public interface PersistenceUnitRemoteBean {

	String constructMessage(String name);

}
