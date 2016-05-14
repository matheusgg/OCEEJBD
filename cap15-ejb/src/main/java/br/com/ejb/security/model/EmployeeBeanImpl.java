package br.com.ejb.security.model;

import br.com.ejb.security.EmployeeRemoteBean;
import br.com.ejb.security.TaskRemoteBean;
import br.com.ejb.security.model.enums.EmployeeType;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.Resource;
import javax.annotation.security.RunAs;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static br.com.ejb.security.model.enums.EmployeeType.Roles.ADMIN;

/**
 * A anotacao @RunAs faz com que um Session Bean seja executando utilizando uma determinada role, desta forma, caso este bean
 * invoque outro Session Bean, essa invocacao acontecera atraves da role especificada em @RunAs, ao inves da role do cliente que chamou o
 * primeiro bean. Ex.:
 * <ul>
 * <li>Bean A está executando com a anotacao @RunAs(ADMIN);</li>
 * <li>Bean A é invocado pelo cliente X que possui a role USER;</li>
 * <li>Bean A chama o método create() do Bean B que exige que o chamador possua a role ADMIN;</li>
 * <li>O método create() do Bean B será executado com sucesso, pois a chamada acontecera com a role ADMIN declarada em @RunAs do Bean A.</li>
 * </ul>
 */
@RunAs(ADMIN)
@Stateless
@SecurityDomain("other")
public class EmployeeBeanImpl implements EmployeeRemoteBean {

	@Resource
	private SessionContext context;

	/**
	 * java:global/cap15-ejb/TaskBeanImpl!br.com.ejb.security.TaskRemoteBean
	 * java:app/cap15-ejb/TaskBeanImpl!br.com.ejb.security.TaskRemoteBean
	 * java:module/TaskBeanImpl!br.com.ejb.security.TaskRemoteBean
	 * java:jboss/exported/cap15-ejb/TaskBeanImpl!br.com.ejb.security.TaskRemoteBean
	 * java:global/cap15-ejb/TaskBeanImpl
	 * java:app/cap15-ejb/TaskBeanImpl
	 * java:module/TaskBeanImpl
	 */
	@Resource(name = "java:module/TaskBeanImpl")
	private TaskRemoteBean taskRemoteBean;

	@Override
	public Employee create(final String name, final EmployeeType type, final String... tasks) {
		final List<Task> employeeTasks = new ArrayList<>();
		final Employee employee = new Employee(UUID.randomUUID().toString(), name, type, employeeTasks);

		for (final String id : tasks) {
			Task task = this.taskRemoteBean.create(id);
			task = this.taskRemoteBean.assign(task, employee);
			employeeTasks.add(task);
		}

		return employee;
	}
}
