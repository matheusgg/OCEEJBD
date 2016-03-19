package br.com.ejb.model;

import br.com.ejb.model.enums.EmployeeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "Employee.findAll", query = "from Employee")
public class Employee implements Serializable {

	private static final long serialVersionUID = -4112121957731598927L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@Enumerated(EnumType.ORDINAL)
	private EmployeeType type;

}
