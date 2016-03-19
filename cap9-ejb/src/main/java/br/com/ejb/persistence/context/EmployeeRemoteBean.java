package br.com.ejb.persistence.context;

import br.com.ejb.model.Employee;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface EmployeeRemoteBean {

	Employee save(Employee employee);

	Employee find(Integer id);

	List<Employee> findAll();

	void delete(Integer id);

}
