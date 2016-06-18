package br.com.ejb.sessionsynchronization;

import br.com.ejb.exceptions.CustomApplicationException;
import br.com.ejb.model.Client;

import javax.ejb.Remote;

@Remote
public interface SessionRemoteBean {

	Client save(final Client client) throws CustomApplicationException;

}
