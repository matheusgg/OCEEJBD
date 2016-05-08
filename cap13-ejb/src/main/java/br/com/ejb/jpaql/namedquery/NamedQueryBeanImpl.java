package br.com.ejb.jpaql.namedquery;

import br.com.ejb.jpaql.JPAQLRemoteBean;
import br.com.ejb.model.Employee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class NamedQueryBeanImpl implements JPAQLRemoteBean {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void executeOperations() {
		/**
		 * Named Query
		 */
		//		final TypedQuery<Task> query = this.em.createNamedQuery(Task.SELECT_ALL_NAMED_QUERY, Task.class);
		//		System.out.println(query.getResultList());

		/**
		 * Named Native Query
		 */
		final TypedQuery<Employee> query = this.em.createNamedQuery(Employee.SELECT_ALL_NAMED_NATIVE_QUERY, Employee.class);
		System.out.println(query.getResultList());
	}
}
