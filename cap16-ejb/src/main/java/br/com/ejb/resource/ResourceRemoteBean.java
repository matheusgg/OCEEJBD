package br.com.ejb.resource;

import javax.ejb.Remote;
import javax.naming.NamingException;
import java.io.Serializable;

@Remote
public interface ResourceRemoteBean extends Serializable {

	void execute() throws NamingException;
}
