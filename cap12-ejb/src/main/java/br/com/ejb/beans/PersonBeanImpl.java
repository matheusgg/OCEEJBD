package br.com.ejb.beans;

import br.com.ejb.model.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PersonBeanImpl implements GenericRemoteBean<Person, Integer> {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Person save(final Person person) {
		if (person.getId() == null) {
			this.em.persist(person);
		} else {
			return this.em.merge(person);
		}
		return person;
	}

	@Override
	public void delete(final Integer id) {
		this.em.remove(this.findById(id));
	}

	@Override
	public Person findById(final Integer id) {
		return this.em.find(Person.class, id);
	}

	@Override
	public List<Person> findAll() {
		return this.em.createNamedQuery("Person.findAll", Person.class).getResultList();
	}
}
