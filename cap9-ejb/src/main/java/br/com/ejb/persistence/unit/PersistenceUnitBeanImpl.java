package br.com.ejb.persistence.unit;

import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateful;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Log
@Stateful
public class PersistenceUnitBeanImpl implements PersistenceUnitRemoteBean {

	/**
	 * Com a anotacao @PersistenceUnit é possivel injetar uma instancia gerenciada de EntityManagerFactory que será responsavel
	 * por criar EntityManagers. O atributo unitName é opcional quando existe apenas uma unidade de persistencia.
	 */
	@PersistenceUnit
	private EntityManagerFactory emf;

	@PostConstruct
	void init() {
		log.info(this.emf.toString());
	}

	@PostActivate
	void postActivate() {
		log.info("Activating...");
	}

	@Override
	public String constructMessage(final String name) {
		return "Hi " + name;
	}

	@PrePassivate
	void prePassivate() {
		this.emf = null;
		log.info("Passivating...");
	}
}
