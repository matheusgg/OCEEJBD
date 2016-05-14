package br.com.ejb.security;

import br.com.ejb.security.model.Employee;
import br.com.ejb.security.model.Task;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface TaskRemoteBean {

	Task assign(final Task task, final Employee employee);

	List<Task> findAll();

	Task findById(final String id);

	Task findOwnTask(final Employee employee);

	Task create(final String id);

}
