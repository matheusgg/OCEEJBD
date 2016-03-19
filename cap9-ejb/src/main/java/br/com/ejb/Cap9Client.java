package br.com.ejb;

import br.com.ejb.model.Employee;
import br.com.ejb.model.enums.EmployeeType;
import br.com.ejb.persistence.context.EmployeeRemoteBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.List;
import java.util.Properties;

public class Cap9Client {

	public static void main(final String[] args) throws Exception {
		final Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		env.put(Context.PROVIDER_URL, "remote://localhost:4447");
		env.put(Context.SECURITY_PRINCIPAL, "admin");
		env.put(Context.SECURITY_CREDENTIALS, "admin123");
		env.put("jboss.naming.client.ejb.context", true);

		final Context ctx = new InitialContext(env);

		/*
		 * ======================================================================================================================================
		 * @PersistenceContext TRANSACTION e EXTENDED
		 * ======================================================================================================================================
		 */
		final EmployeeRemoteBean bean = (EmployeeRemoteBean) ctx.lookup("cap9-ejb/EmployeeBeanImpl!br.com.ejb.persistence.context.EmployeeRemoteBean");

		Employee simple = new Employee(null, "Employee 1", EmployeeType.SIMPLE);
		Employee manager = new Employee(null, "Employee 2", EmployeeType.MANAGER);
		Employee director = new Employee(null, "Employee 3", EmployeeType.DIRECTOR);

		// Save
		simple = bean.save(simple);
		System.out.println(simple);

		manager = bean.save(manager);
		System.out.println(manager);

		director = bean.save(director);
		System.out.println(director);

		// Update
		manager.setName("Employee Manager");
		manager = bean.save(manager);
		System.out.println(manager);

		// Find
		final Employee someDirector = bean.find(3);
		System.out.println(someDirector);

		// FindAll
		final List<Employee> employees = bean.findAll();
		System.out.println(employees);

		// Delete
		bean.delete(1);

		/*
		 * ======================================================================================================================================
		 * Inject an EntityManagerFactory with @PersistenceUnit
		 * ======================================================================================================================================
		 */
		/*final PersistenceUnitRemoteBean bean = (PersistenceUnitRemoteBean) ctx.lookup("cap9-ejb/PersistenceUnitBeanImpl!br.com.ejb.persistence.unit.PersistenceUnitRemoteBean");
		System.out.println(bean.constructMessage("Some User"));
		TimeUnit.SECONDS.sleep(10);
		System.out.println(bean.constructMessage("Some User"));*/
	}
}
