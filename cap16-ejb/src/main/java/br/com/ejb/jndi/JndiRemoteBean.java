package br.com.ejb.jndi;

import javax.ejb.Remote;
import javax.naming.NamingException;
import java.io.Serializable;

@Remote
public interface JndiRemoteBean extends Serializable {

	void execute() throws NamingException;

}
