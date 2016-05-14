package br.com.ejb.security.model;

import br.com.ejb.security.model.enums.EmployeeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "tasks")
public class Employee implements Serializable {

	private static final long serialVersionUID = -5336427192994376866L;
	private String id;
	private String name;
	private EmployeeType type;
	private List<Task> tasks;

}
