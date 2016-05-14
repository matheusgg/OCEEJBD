package br.com.ejb.security;

import br.com.ejb.security.model.Employee;
import br.com.ejb.security.model.Task;
import lombok.extern.java.Log;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBAccessException;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.ejb.security.model.enums.EmployeeType.Roles.ADMIN;
import static br.com.ejb.security.model.enums.EmployeeType.Roles.USER;

/**
 * O controle de acesso, isto e, a autorizacao dentro do container EJB acontece a nivel de metodo. É possivel definir autorizacao
 * tanto de forma declarativa (atraves de anotacoes ou xml) ou programatica (atraves do SessionContext).
 * <p>
 * É possivel aplicar regras de autorizacao apenas nos Session Beans, para tal, é necessário primeiramente declarar as roles
 * que serao utilizadas para o controle de acesso atraves da anotacao @DeclareRoles.
 * <p>
 * A anotacao @RolesAllowed pode ser aplicada tanto em nivel de classe quanto em nivel de metodo e serve para definir as roles necessarias
 * que um usuario deve possuir para acessar um determinado metodo.
 * <p>
 * A anotacao @PermitAll pode ser definida tanto na classe quanto no metodo e especifica que qualquer usuario (até mesmo anonimo) pode acessar
 * um determinado metodo. Caso nenhuma anotacao de seguranca seja aplicada, o container EJB assumira o comportamento da anotacao @PermitAll
 * por padrao.
 */
@Log
@Singleton
@Startup
@DeclareRoles({ADMIN, USER})
@RolesAllowed({})
//@PermitAll
@SecurityDomain("other")
public class TaskBeanImpl implements TaskRemoteBean {

	private static final Map<String, Task> TASKS_ASSIGNED;
	private static final List<Task> TASKS;

	static {
		TASKS_ASSIGNED = new HashMap<>();
		TASKS = new ArrayList<>();
	}

	@Resource
	private SessionContext sessionContext;

	/**
	 * Quando um cliente tenta acessar um metodo protegido de um EJB sem a role necessaria, uma EJBAccessException (subclasse de EJBException) é lancada.
	 *
	 * @param task
	 * @param employee
	 * @return
	 */
	@RolesAllowed(ADMIN)
	@Override
	public Task assign(final Task task, final Employee employee) {
		log.info(this.sessionContext.getCallerPrincipal().getName());
		task.setOwner(employee);
		TASKS_ASSIGNED.put(employee.getId(), task);
		return task;
	}

	@RolesAllowed(ADMIN)
	@Override
	public List<Task> findAll() {
		log.info(this.sessionContext.getCallerPrincipal().getName());
		final List<Task> tasks = new ArrayList<>(TASKS_ASSIGNED.values());
		tasks.addAll(TASKS);
		return tasks;
	}

	@RolesAllowed({USER, ADMIN})
	@Override
	public Task findById(final String id) {
		log.info(this.sessionContext.getCallerPrincipal().getName());
		for (final Map.Entry<String, Task> entry : TASKS_ASSIGNED.entrySet()) {
			final Task task = entry.getValue();
			if (task.getId().equals(id)) {
				return task;
			}
		}
		return null;
	}

	@PermitAll
	@Override
	public Task findOwnTask(final Employee employee) {
		log.info(this.sessionContext.getCallerPrincipal().getName());
		return TASKS_ASSIGNED.get(employee.getId());
	}

	/**
	 * É possível recuperar o cliente que esta tentando acessar o metodo, assim como verificar se o mesmo possui uma determinada role
	 * programaticamente.
	 *
	 * @param id
	 * @return
	 */
	@PermitAll
	@Override
	public Task create(final String id) {
		final Principal principal = this.sessionContext.getCallerPrincipal();
		log.info("User " + principal.getName() + " is trying to create a new task. Checking permissions...");

		if (!this.sessionContext.isCallerInRole(ADMIN)) {
			throw new EJBAccessException("User " + principal.getName() + " does not have access to create new tasks!");
		}

		final Task task = new Task(id);
		TASKS.add(task);
		return task;
	}

}
