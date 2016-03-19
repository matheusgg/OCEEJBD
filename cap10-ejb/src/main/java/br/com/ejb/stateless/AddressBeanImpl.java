package br.com.ejb.stateless;

import br.com.ejb.persistence.mapping.Address;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AddressBeanImpl implements GenericRemoteBean<Address, Integer> {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Address save(final Address address) {
		if (address.getId() == null) {
			this.em.persist(address);
		} else {
			return this.em.merge(address);
		}
		return address;
	}

	@Override
	public void delete(final Integer id) {
		this.em.remove(this.findById(id));
	}

	@Override
	public Address findById(final Integer id) {
		return this.em.find(Address.class, id);
	}

	@Override
	public List<Address> findAll() {
		return this.em.createNamedQuery("Address.findAll", Address.class).getResultList();
	}
}
