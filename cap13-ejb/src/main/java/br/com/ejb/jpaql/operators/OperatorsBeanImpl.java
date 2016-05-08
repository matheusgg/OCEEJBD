package br.com.ejb.jpaql.operators;

import br.com.ejb.jpaql.JPAQLRemoteBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class OperatorsBeanImpl implements JPAQLRemoteBean {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void executeOperations() {
		/**
		 * Select NEW
		 */
		//		final Query query = this.em.createQuery("select new br.com.ejb.model.Employee(e.id, e.name, current_date) from Employee e");
		//		System.out.println(query.getResultList());

		/**
		 * IN
		 */
		//		final Query query = this.em.createQuery("select t.name from Employee e, in(e.tasks) t where e.id = :id");
		//		query.setParameter("id", 1);
		//		System.out.println(query.getResultList());

		/**
		 * INNER JOIN
		 */
		//		final Query query = this.em.createQuery("select t.name from Employee e inner join e.tasks t where e.id = :id");
		//		query.setParameter("id", 1);
		//		System.out.println(query.getResultList());

		/**
		 * LEFT JOIN
		 */
		//		final TypedQuery<Object[]> query = this.em.createQuery("select e.name, t.name from Employee e left join e.tasks t", Object[].class);
		//		final List<Object[]> result = query.getResultList();
		//		for (final Object[] fields : result) {
		//			System.out.println(fields[0] + " - " + fields[1]);
		//		}

		/**
		 * FETCH JOIN
		 */
		//		final TypedQuery<Employee> query = this.em.createQuery("select e from Employee e inner join fetch e.tasks", Employee.class);
		//		final List<Employee> result = query.getResultList();
		//		for (final Employee employee : result) {
		//			System.out.println(employee.getName());
		//			System.out.println(employee.getTasks().size());
		//		}

		/**
		 * DISTINCT
		 */
		//		final TypedQuery<Employee> query = this.em.createQuery("select distinct e from Employee e inner join fetch e.tasks", Employee.class);
		//		final List<Employee> result = query.getResultList();
		//		for (final Employee employee : result) {
		//			System.out.println(employee.getName());
		//			System.out.println(employee.getTasks().size());
		//		}

		/**
		 * BETWEEN
		 */
		//		final Query query = this.em.createQuery("select e.name from Employee e where e.id between 3 and 8");
		//		System.out.println(query.getResultList());

		/**
		 * IN Operator
		 */
		//		Query query = this.em.createQuery("select e.name from Employee e where e.id in(3,8)");
		//		System.out.println(query.getResultList());
		//
		//		query = this.em.createQuery("select e.name from Employee e where e.id in(?1, ?2, ?3)");
		//		query.setParameter(1, 1);
		//		query.setParameter(2, 2);
		//		query.setParameter(3, 4);
		//		System.out.println(query.getResultList());
		//
		//		query = this.em.createQuery("select e.name from Employee e where e.id in(:ids)");
		//		query.setParameter("ids", Arrays.asList(7, 8, 9));
		//		System.out.println(query.getResultList());

		/**
		 * IS NULL
		 */
		//		final Query query = this.em.createQuery("select e.name from Employee e left join e.tasks t where t.name is null");
		//		System.out.println(query.getResultList());

		/**
		 * IS EMPTY
		 */
		//		final Query query = this.em.createQuery("select e.name from Employee e where e.tasks is empty");
		//		System.out.println(query.getResultList());

		/**
		 * MEMBER OF
		 */
		//		Query query = this.em.createQuery("select t from Task t where t.id = :id");
		//		query.setParameter("id", 1);
		//		final Task task = (Task) query.getSingleResult();
		//
		//		query = this.em.createQuery("select e.name from Employee e where :task member of e.tasks");
		//		query.setParameter("task", task);
		//		System.out.println(query.getResultList());

		/**
		 * LIKE - Com o operador LIKE é possivel utilizar dois caracteres especiais dentro do pattern especificado: % (porcentagem) e _ (underscore).
		 *
		 * A porcentagem (%) realiza a busca por qualquer sequencia de caracteres.
		 * Ex.: Employee% retornara Employee 1, Employee 2, etc.
		 * Ex.: %10 retornara Employee 10.
		 *
		 * Já o underscore (_) realiza a busca por apenas um caractere.
		 * Ex.: Employ_e 3 retornara Employee 3
		 * Ex.: Employe_3 retornara Employee 3
		 */
		//		Query query = this.em.createQuery("select e.name from Employee e where e.name like :name");
		//		query.setParameter("name", "Employee%");
		//		System.out.println(query.getResultList());
		//
		//		query = this.em.createQuery("select e.name from Employee e where e.name like :name");
		//		query.setParameter("name", "%10");
		//		System.out.println(query.getResultList());
		//
		//		query = this.em.createQuery("select e.name from Employee e where e.name like :name");
		//		query.setParameter("name", "Employee_3");
		//		System.out.println(query.getResultList());
		//
		//		query = this.em.createQuery("select e.name from Employee e where e.name like :name");
		//		query.setParameter("name", "Employee_");
		//		System.out.println(query.getResultList());

		/**
		 * ORDER BY
		 */
		Query query = this.em.createQuery("select e.name from Employee e order by e.name asc, e.id desc");
		System.out.println(query.getResultList());

		query = this.em.createQuery("select e.name from Employee e, in(e.tasks) t order by t.name");
		System.out.println(query.getResultList());
	}
}
