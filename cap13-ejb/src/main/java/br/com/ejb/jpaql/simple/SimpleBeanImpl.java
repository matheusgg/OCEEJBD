package br.com.ejb.jpaql.simple;

import br.com.ejb.jpaql.JPAQLRemoteBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SimpleBeanImpl implements JPAQLRemoteBean {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void executeOperations() {
		/**
		 * Simple Query
		 */
		//		Query query = this.em.createQuery("from Employee where id = 1");
		//		System.out.println(query.getSingleResult());
		//
		//		query = this.em.createQuery("from Employee");
		//		System.out.println(query.getResultList());

		/**
		 * Named and Positional Parameters
		 */
		//		Query query = this.em.createQuery("from Employee where name = :name");
		//		query.setParameter("name", "Employee 5");
		//		System.out.println(query.getSingleResult());
		//
		//		query = this.em.createQuery("from Employee where id = ?1");
		//		query.setParameter(1, 10);
		//		System.out.println(query.getSingleResult());

		/**
		 * Date Parameters
		 */
		//		Query query = this.em.createQuery("from Employee where hireDate = :date");
		//		query.setParameter("date", new Date(), TemporalType.DATE);
		//		System.out.println(query.getResultList());

		/**
		 * Pagination
		 */
		//		final int max = 2;
		//		int first = 0;
		//		List<Employee> result = new ArrayList<>();
		//
		//		do {
		//			final TypedQuery<Employee> query = this.em.createQuery("from Employee", Employee.class);
		//			result = query.setFirstResult(first).setMaxResults(max).getResultList();
		//			System.out.println(result);
		//			first += max;
		//			this.em.clear();
		//		} while (first < 10);

		/**
		 * Hints - São funcionalidades disponibilizadas pelo Persistence Provider que podem ser utilizadas atraves da definicao do método
		 * setHint() especificando a chave da feature e o valor desejado de configuracao.
		 */
		//		final Query query = this.em.createQuery("from Employee");
		//		query.setHint("org.hibernate.timeout", 1000);
		//		System.out.println(query.getResultList());

		/**
		 * Select multiple fields
		 */
		//		final TypedQuery<Object[]> query = this.em.createQuery("select e.id, e.name from Employee e", Object[].class);
		//		final List<Object[]> result = query.getResultList();
		//		for (final Object[] fields : result) {
		//			System.out.println(fields[0] + " - " + fields[1]);
		//		}

	}
}
