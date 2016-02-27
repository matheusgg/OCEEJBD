package br.com.ejb.restrictions;

import javax.ejb.Remote;
import javax.naming.NamingException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Remote
public interface RestrictionsRemoteBean {

	void testRestrictions() throws InterruptedException, IOException, NamingException;

}
