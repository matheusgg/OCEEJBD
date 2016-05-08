package br.com.ejb.jpaql.functions;

import br.com.ejb.jpaql.JPAQLRemoteBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class FunctionsBeanImpl implements JPAQLRemoteBean {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void executeOperations() {
		/**
		 * LOWER
		 */
		//		final Query query = this.em.createQuery("select lower(e.name) from Employee e");
		//		System.out.println(query.getResultList());

		/**
		 * UPPER
		 */
		//		final Query query = this.em.createQuery("select upper(e.name) from Employee e");
		//		System.out.println(query.getResultList());

		/**
		 * TRIM
		 */
		//		Query query = this.em.createQuery("select e.name from Employee e where e.name = trim(:name)");
		//		query.setParameter("name", "     Employee 2  ");
		//		System.out.println(query.getResultList());
		//
		//		query = this.em.createQuery("select e.name from Employee e where e.name = trim(leading from :name)");
		//		query.setParameter("name", "     Employee 10");
		//		System.out.println(query.getResultList());
		//
		//		query = this.em.createQuery("select e.name from Employee e where e.name = trim(trailing from :name)");
		//		query.setParameter("name", "Employee 3   ");
		//		System.out.println(query.getResultList());
		//
		//		query = this.em.createQuery("select e.name from Employee e where e.name = trim(both from :name)");
		//		query.setParameter("name", "       Employee 5   ");
		//		System.out.println(query.getResultList());
		//
		//		query = this.em.createQuery("select e.name from Employee e where e.name = trim(both '*' from :name)");
		//		query.setParameter("name", "********Employee 5**********");
		//		System.out.println(query.getResultList());

		/**
		 * CONCAT
		 */
		//		final Query query = this.em.createQuery("select concat(concat(e.id, ' - '),  e.name) from Employee e");
		//		System.out.println(query.getResultList());

		/**
		 * LENGTH
		 */
		//		final Query query = this.em.createQuery("select length(e.name) from Employee e");
		//		System.out.println(query.getResultList());

		/**
		 * LOCATE
		 */
		//		final Query query = this.em.createQuery("select locate('1', e.name) from Employee e");
		//		System.out.println(query.getResultList());

		/**
		 * SUBSTRING
		 */
		//		final Query query = this.em.createQuery("select substring(e.name, 1, 3) from Employee e");
		//		System.out.println(query.getResultList());

		/**
		 * ABS
		 */
		//		final Query query = this.em.createQuery("select abs(-240) from Employee");
		//		System.out.println(query.getResultList());

		/**
		 * SQRT
		 */
		//		final Query query = this.em.createQuery("select sqrt(49) from Employee");
		//		System.out.println(query.getResultList());

		/**
		 * MOD
		 */
		//		final Query query = this.em.createQuery("select mod(10, 3) from Employee");
		//		System.out.println(query.getResultList());

		/**
		 * DATE FUNCTIONS
		 */
		//		final TypedQuery<Object[]> query = this.em.createQuery("select current_date, current_time, current_timestamp from Employee", Object[].class);
		//		final List<Object[]> result = query.getResultList();
		//		for (final Object[] fields : result) {
		//			System.out.println(fields[0]);
		//			System.out.println(fields[1]);
		//			System.out.println(fields[2]);
		//		}

		/**
		 * COUNT
		 */
		//		final Query query = this.em.createQuery("select count(e) from Employee e");
		//		System.out.println(query.getResultList());

		/**
		 * MAX e MIN
		 */
		//		Query query = this.em.createQuery("select max(e.name) from Employee e");
		//		System.out.println(query.getResultList());
		//
		//		query = this.em.createQuery("select min(e.name) from Employee e");
		//		System.out.println(query.getResultList());

		/**
		 * SUM e AVG
		 */
		Query query = this.em.createQuery("select sum(e.id) from Employee e");
		System.out.println(query.getResultList());

		query = this.em.createQuery("select avg(e.id) from Employee e");
		System.out.println(query.getResultList());
	}
}
