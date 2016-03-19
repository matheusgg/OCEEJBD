package br.com.ejb.stateless;

import br.com.ejb.persistence.mapping.Company;
import br.com.ejb.persistence.mapping.Company.CompanyPK;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CompanyBeanImpl implements GenericRemoteBean<Company, CompanyPK> {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Company save(final Company company) {
		this.em.persist(company);
		return company;
	}

	@Override
	public void delete(final CompanyPK pk) {
		this.em.remove(this.findById(pk));
	}

	@Override
	public Company findById(final CompanyPK pk) {
		return this.em.find(Company.class, pk);
	}

	@Override
	public List<Company> findAll() {
		return this.em.createNamedQuery("Company.findAll", Company.class).getResultList();
	}
}
