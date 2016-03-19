package br.com.ejb.stateless;

import br.com.ejb.persistence.mapping.Partner;
import br.com.ejb.persistence.mapping.Partner.PartnerEmbeddedPK;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PartnerBeanImpl implements GenericRemoteBean<Partner, PartnerEmbeddedPK> {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Partner save(final Partner company) {
		this.em.persist(company);
		return company;
	}

	@Override
	public void delete(final PartnerEmbeddedPK pk) {
		this.em.remove(this.findById(pk));
	}

	@Override
	public Partner findById(final PartnerEmbeddedPK pk) {
		return this.em.find(Partner.class, pk);
	}

	@Override
	public List<Partner> findAll() {
		return this.em.createNamedQuery("Partner.findAll", Partner.class).getResultList();
	}
}
