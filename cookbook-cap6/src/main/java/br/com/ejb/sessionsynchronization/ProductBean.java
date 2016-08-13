package br.com.ejb.sessionsynchronization;

import br.com.ejb.model.Client;
import lombok.extern.java.Log;

import javax.ejb.EJBException;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import java.rmi.RemoteException;

import static javax.ejb.TransactionManagementType.CONTAINER;

@Log
@Stateful
@TransactionManagement(CONTAINER)
//@TransactionAttribute(NOT_SUPPORTED)
public class ProductBean implements SessionSynchronization {

	/**
	 * Ordem de invocacao: 4
	 */
	public void associate(final Client client) {
		log.info(client.toString());
	}

	/**
	 * Ordem de invocacao: 3
	 */
	@Override
	public void afterBegin() throws EJBException, RemoteException {
		log.info("=========== afterBegin ===========");
	}

	/**
	 * Ordem de invocacao: 6
	 */
	@Override
	public void beforeCompletion() throws EJBException, RemoteException {
		log.info("=========== beforeCompletion ===========");
	}

	/**
	 * Ordem de invocacao: 7
	 */
	@Override
	public void afterCompletion(final boolean b) throws EJBException, RemoteException {
		log.info("=========== afterCompletion ===========");
	}
}
