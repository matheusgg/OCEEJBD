package br.com.ejb.beans;

import br.com.ejb.model.House;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class HouseBeanImpl implements GenericRemoteBean<House, Integer> {

	@PersistenceContext
	private EntityManager em;

	@Override
	public House save(final House house) {
		if (house.getId() == null) {
			this.em.persist(house);
		} else {
			return this.em.merge(house);
		}
		return house;
	}

	@Override
	public void delete(final Integer id) {
		this.em.remove(this.findById(id));
	}

	@Override
	public House findById(final Integer id) {
		return this.em.find(House.class, id);
	}

	@Override
	public List<House> findAll() {
		return this.em.createNamedQuery("House.findAll", House.class).getResultList();
	}
}
