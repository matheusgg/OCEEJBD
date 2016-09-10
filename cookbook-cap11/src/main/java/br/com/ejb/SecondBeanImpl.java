package br.com.ejb;

import lombok.extern.java.Log;

import javax.ejb.EJBException;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import java.rmi.RemoteException;

@Log
@Stateful
public class SecondBeanImpl implements SessionSynchronization {

	public String getMessage() {
		return "getMessage";
	}

	public String getMessage2() {
		return "getMessage2";
	}

	@Override
	public void afterBegin() throws EJBException, RemoteException {
		log.info("afterBegin");
	}

	@Override
	public void beforeCompletion() throws EJBException, RemoteException {
		log.info("beforeCompletion");
	}

	@Override
	public void afterCompletion(final boolean b) throws EJBException, RemoteException {
		log.info("afterCompletion");
	}
}
