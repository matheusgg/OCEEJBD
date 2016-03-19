package br.com.ejb.stateless;

import br.com.ejb.persistence.mapping.Employee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class EmployeeBeanImpl implements GenericRemoteBean<Employee, Integer> {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Employee save(final Employee employee) {
		if (employee.getId() == null) {
			this.em.persist(employee);
		} else {
			return this.em.merge(employee);
		}
		return employee;
	}

	@Override
	public void delete(final Integer id) {
		this.em.remove(this.findById(id));
	}

	@Override
	public Employee findById(final Integer id) {
		return this.em.find(Employee.class, id);
	}

	@Override
	public List<Employee> findAll() {
		return this.em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
	}
}
