package br.com.ejb.security.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmployeeType {

	PEON(Roles.USER),
	MANAGER(Roles.ADMIN);

	private String type;

	public interface Roles {
		String USER = "user";
		String ADMIN = "admin";
	}

}
