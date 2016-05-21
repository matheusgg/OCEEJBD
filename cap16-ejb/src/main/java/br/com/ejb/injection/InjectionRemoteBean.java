package br.com.ejb.injection;

import javax.ejb.Remote;
import javax.naming.NamingException;
import java.io.Serializable;

@Remote
public interface InjectionRemoteBean extends Serializable {

	void execute() throws NamingException;

}
