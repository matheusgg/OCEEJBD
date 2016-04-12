package br.com.ejb;

import br.com.ejb.beans.GenericRemoteBean;
import br.com.ejb.model.Employee;
import br.com.ejb.model.Team;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.*;

public class Cap11Client {

	@SuppressWarnings("unchecked")
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
		 * One-To-One Unidirecional
		 * ======================================================================================================================================
		 */
		/*final Address address = new Address();
		address.setStreet("Street");
		address.setState("State");
		address.setCity("City");

		final Employee employee = new Employee();
		employee.setName("Employee");

		final GenericRemoteBean<Employee, Integer> bean = (GenericRemoteBean<Employee, Integer>) ctx.lookup("cap11-ejb/EmployeeBeanImpl!br.com.ejb.beans.GenericRemoteBean");
		bean.save(employee);
		System.out.println(bean.findAll());*/

		/*
		 * ======================================================================================================================================
		 * One-To-One Bidirecional
		 * ======================================================================================================================================
		 */
		/*final Computer computer = new Computer();
		computer.setDescription("Dell");

		final Employee employee = new Employee();
		employee.setName("Employee");

		computer.setEmployee(employee);
		employee.setComputer(computer);

		final GenericRemoteBean<Employee, Integer> bean = (GenericRemoteBean<Employee, Integer>) ctx.lookup("cap11-ejb/EmployeeBeanImpl!br.com.ejb.beans.GenericRemoteBean");
		bean.save(employee);
		System.out.println(bean.findAll());*/

		/*
		 * ======================================================================================================================================
		 * One-To-Many Unidirecional
		 * ======================================================================================================================================
		 */
		/*Employee employee = new Employee();
		employee.setName("Employee");

		final Phone phone = new Phone();
		phone.setNumber("1111-1111");
		phone.setType(Phone.PhoneType.PHONE);
		phone.setEmployee(employee);

		final Phone cellphone = new Phone();
		cellphone.setNumber("99999-9999");
		cellphone.setType(Phone.PhoneType.CELLPHONE);
		cellphone.setEmployee(employee);

		employee.setPhones(new ArrayList<>(Arrays.asList(phone, cellphone)));

		final GenericRemoteBean<Employee, Integer> bean = (GenericRemoteBean<Employee, Integer>) ctx.lookup("cap11-ejb/EmployeeBeanImpl!br.com.ejb.beans.GenericRemoteBean");
		employee = bean.save(employee);
		System.out.println(bean.findAll());

		employee.getPhones().remove(0);
		employee = bean.save(employee);
		System.out.println(bean.findAll());*/

		/*
		 * ======================================================================================================================================
		 * One-To-Many Bidirecional / Many-To-One Bidirecional
		 * ======================================================================================================================================
		 */
		/*Employee manager = new Employee();
		manager.setName("Manager");

		final Employee peon1 = new Employee();
		peon1.setName("Peon 1");
		peon1.setManager(manager);

		final Employee peon2 = new Employee();
		peon2.setName("Peon 2");
		peon2.setManager(manager);

		final Employee peon3 = new Employee();
		peon3.setName("Peon 3");
		peon3.setManager(manager);

		manager.setPeons(new ArrayList<>(Arrays.asList(peon1, peon2, peon3)));

		final GenericRemoteBean<Employee, Integer> bean = (GenericRemoteBean<Employee, Integer>) ctx.lookup("cap11-ejb/EmployeeBeanImpl!br.com.ejb.beans.GenericRemoteBean");
		manager = bean.save(manager);
		System.out.println(manager);
		System.out.println(manager.getPeons());
		System.out.println("============================================================================================");

		final List<Employee> employees = bean.findAll();
		for (final Employee employee : employees) {
			System.out.println(employee);
			System.out.println(employee.getManager());
			System.out.println("============================================================================================");
		}*/

		/*
		 * ======================================================================================================================================
		 * Many-To-Many Bidirecional / @MapKey
		 * ======================================================================================================================================
		 */
		final GenericRemoteBean<Employee, Integer> employeeBean = (GenericRemoteBean<Employee, Integer>) ctx.lookup("cap11-ejb/EmployeeBeanImpl!br.com.ejb.beans.GenericRemoteBean");
		final GenericRemoteBean<Team, Integer> teamBean = (GenericRemoteBean<Team, Integer>) ctx.lookup("cap11-ejb/TeamBeanImpl!br.com.ejb.beans.GenericRemoteBean");

		Employee peon1 = new Employee();
		peon1.setName("Peon 1");
		peon1 = employeeBean.save(peon1);

		Employee peon2 = new Employee();
		peon2.setName("Peon 2");
		peon2 = employeeBean.save(peon2);

		Team workersTeam = new Team();
		workersTeam.setName("Workers Team");
		workersTeam = teamBean.save(workersTeam);

		Team defaultTeam = new Team();
		defaultTeam.setName("Default Team");
		defaultTeam = teamBean.save(defaultTeam);

		peon1.setTeams(new ArrayList<>(Arrays.asList(workersTeam, defaultTeam)));
		employeeBean.save(peon1);

		peon2.setTeams(Collections.singletonList(defaultTeam));
		employeeBean.save(peon2);

		Map<String, Employee> peons = new HashMap<>();
		peons.put(peon1.getName(), peon1);

		workersTeam.setMembers(peons);
		teamBean.save(workersTeam);

		peons = new HashMap<>();
		peons.put(peon1.getName(), peon1);
		peons.put(peon2.getName(), peon2);

		defaultTeam.setMembers(peons);
		teamBean.save(defaultTeam);

		final List<Employee> employees = employeeBean.findAll();
		System.out.println(employees);

		final List<Team> teams = teamBean.findAll();
		System.out.println(teams);
	}

}
