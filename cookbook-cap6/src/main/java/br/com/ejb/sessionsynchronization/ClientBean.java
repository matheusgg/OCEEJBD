package br.com.ejb.sessionsynchronization;

import br.com.ejb.model.Client;
import lombok.extern.java.Log;

import javax.ejb.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.rmi.RemoteException;
import java.util.List;

import static javax.ejb.TransactionManagementType.CONTAINER;

/**
 * Quando existe mais de um EJB participando da mesma transacao e ambos implementam a interface
 * javax.ejb.SessionSynchronization, a ordem de invocacao dos metodos beforeCompletition() e
 * afterCompletition() se torna um pouco complexa.
 * <p>
 * Os EJBs que estao na raiz da stack de invocacao têm o metodo beforeCompletition() invocados primeiro;
 * <p>
 * Já o metodo afterCompletition() é invocado nos EJBs que estao no topo da stack de invocacao, ou seja,
 * na ordem cotraria de invocacao do metodo beforeCompletition().
 */
@Log
@Stateful
@TransactionManagement(CONTAINER)
public class ClientBean implements SessionSynchronization {

	@PersistenceContext
	private EntityManager em;

	@EJB
	private ProductBean productBean;

	/**
	 * Ordem de invocacao: 2
	 */
	public Client save(final Client client) {
		this.em.persist(client);
		this.productBean.associate(client);
		return client;
	}

	public List<Client> findAll() {
		final CriteriaBuilder cb = this.em.getCriteriaBuilder();
		final CriteriaQuery<Client> query = cb.createQuery(Client.class);
		query.select(query.from(Client.class));
		return this.em.createQuery(query).getResultList();
	}

	/**
	 * Ordem de invocacao: 1
	 */
	@Override
	public void afterBegin() throws EJBException, RemoteException {
		log.info("=========== afterBegin ===========");
	}

	/**
	 * Ordem de invocacao: 5
	 */
	@Override
	public void beforeCompletion() throws EJBException, RemoteException {
		log.info("=========== beforeCompletion ===========");
	}

	/**
	 * Ordem de invocacao: 8
	 */
	@Override
	public void afterCompletion(final boolean b) throws EJBException, RemoteException {
		log.info("=========== afterCompletion ===========");
	}

	@PrePassivate
	void prePassivate() {
		this.em = null;
		this.productBean = null;
	}

	@PostActivate
	void postActivate() {
		try {
			this.em = InitialContext.doLookup("java:comp/env/br.com.ejb.sessionsynchronization.ClientBean/em");
			this.productBean = InitialContext.doLookup("java:global/cookbook-cap6/ProductBean!br.com.ejb.sessionsynchronization.ProductBean");
		} catch (final NamingException e) {
			throw new EJBException(e);
		}
	}
}
