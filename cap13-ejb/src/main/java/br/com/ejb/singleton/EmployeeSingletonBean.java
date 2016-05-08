package br.com.ejb.singleton;

import br.com.ejb.model.Employee;
import br.com.ejb.model.Task;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Singleton
@Startup
public class EmployeeSingletonBean {

	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	void init() {
		for (int i = 0; i < 10; i++) {
			final Employee employee = new Employee(null, "Employee " + (i + 1), new Date(), new ArrayList<Task>());

			for (int j = 0; j < 3 && i < 5; j++) {
				employee.getTasks().add(new Task(null, UUID.randomUUID().toString()));
			}

			this.em.persist(employee);
		}
	}

}
