package br.com.ejb.jpaql.nativequery;

import br.com.ejb.jpaql.JPAQLRemoteBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class NativeBeanImpl implements JPAQLRemoteBean {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public void executeOperations() {
		/**
		 * Simple Native Query
		 */
		//		final Query query = this.em.createNativeQuery("select e.id, e.name from Employee e");
		//		final List<Object[]> result = query.getResultList();
		//		for (final Object[] fields : result) {
		//			System.out.println(fields[0]);
		//			System.out.println(fields[1]);
		//		}

		/**
		 * Mapping Native Query
		 */
		//		final Query query = this.em.createNativeQuery("select e.id, e.name, e.hireDate, t.* from Employee e " +
		//				"inner join Employee_Task et on e.id = et.Employee_id " +
		//				"inner join Task t on et.tasks_id = t.id", Employee.class);
		//		System.out.println(query.getResultList());

		/**
		 * Complex native Query Mapping
		 */
		final Query query = this.em.createNativeQuery("select e.*, t.id as t_id, t.name as t_name from Employee e " +
				"inner join Employee_Task et on e.id = et.Employee_id " +
				"inner join Task t on et.tasks_id = t.id", "Employee_Tasks_Mapping");
		System.out.println(query.getResultList());

	}

}
