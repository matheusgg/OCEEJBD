package br.com.ejb.sessioncontext;

import java.io.Serializable;

public interface SessionContext2BeanRemote extends Serializable {

	String getName(Object obj);

}
