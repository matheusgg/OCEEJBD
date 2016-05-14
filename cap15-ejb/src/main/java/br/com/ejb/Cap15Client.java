package br.com.ejb;

import br.com.ejb.security.EmployeeRemoteBean;
import br.com.ejb.security.model.Employee;
import br.com.ejb.security.model.enums.EmployeeType;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;
import java.util.UUID;

public class Cap15Client {

	/**
	 * Management
	 * root/root123
	 * <p>
	 * Application
	 * admin/admin123
	 * someUser/user123
	 *
	 * @param args Args
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception {
		final Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		env.put(Context.PROVIDER_URL, "remote://localhost:4447");
		/*env.put(Context.SECURITY_PRINCIPAL, "admin");
		env.put(Context.SECURITY_CREDENTIALS, "admin123");*/
		env.put(Context.SECURITY_PRINCIPAL, "someUser");
		env.put(Context.SECURITY_CREDENTIALS, "user123");
		env.put("jboss.naming.client.ejb.context", true);

		final Context ctx = new InitialContext(env);

		/*
		 * =================================================================================================
		 * @DeclareRoles e @RolesAllowed
		 * =================================================================================================
		 */
		/*final Employee employee = new Employee(UUID.randomUUID().toString(), "Employee 1", MANAGER, null);
		final Task task = new Task(UUID.randomUUID().toString());

		final TaskRemoteBean bean = (TaskRemoteBean) ctx.lookup("cap15-ejb/TaskBeanImpl!br.com.ejb.security.TaskRemoteBean");

		// Acesso restrito para apenas usuarios admins
		try {
			final Task assignedTask = bean.assign(task, employee);
			System.out.println(assignedTask);
		} catch (final Exception e) {
			e.printStackTrace();
		}

		try {
			final List<Task> tasks = bean.findAll();
			System.out.println(tasks);
		} catch (final Exception e) {
			e.printStackTrace();
		}

		try {
			final Task secondTask = bean.create(UUID.randomUUID().toString());
			System.out.println(secondTask);
		} catch (final Exception e) {
			e.printStackTrace();
		}

		// Acesso liberado para qualquer usuario
		final Task ownTask = bean.findOwnTask(employee);
		System.out.println(ownTask);

		// Acesso liberado para apenas users e admins
		final Task someTask = bean.findById(task.getId());
		System.out.println(someTask);*/

		/*
		 * =================================================================================================
		 * @RunAs
		 * =================================================================================================
		 */
		final EmployeeRemoteBean bean = (EmployeeRemoteBean) ctx.lookup("cap15-ejb/EmployeeBeanImpl!br.com.ejb.security.EmployeeRemoteBean");
		final Employee employee = bean.create("Some Employee", EmployeeType.MANAGER, UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
		System.out.println(employee);
		System.out.println(employee.getTasks());
	}

}
