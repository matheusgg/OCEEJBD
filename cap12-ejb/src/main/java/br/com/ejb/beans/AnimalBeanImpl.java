package br.com.ejb.beans;

import br.com.ejb.model.Animal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AnimalBeanImpl implements GenericRemoteBean<Animal, Integer> {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Animal save(final Animal animal) {
		if (animal.getId() == null) {
			this.em.persist(animal);
		} else {
			return this.em.merge(animal);
		}
		return animal;
	}

	@Override
	public void delete(final Integer id) {
		this.em.remove(this.findById(id));
	}

	@Override
	public Animal findById(final Integer id) {
		return this.em.find(Animal.class, id);
	}

	@Override
	public List<Animal> findAll() {
		return this.em.createNamedQuery("Animal.findAll", Animal.class).getResultList();
	}
}
