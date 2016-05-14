package br.com.ejb.security;

import br.com.ejb.security.model.Employee;
import br.com.ejb.security.model.enums.EmployeeType;

import javax.ejb.Remote;

@Remote
public interface EmployeeRemoteBean {

	Employee create(final String name, final EmployeeType type, final String... tasks);

}
