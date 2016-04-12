package br.com.ejb.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString(exclude = "employee")
@Entity
public class Computer implements Serializable {

	private static final long serialVersionUID = 324650107643782683L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String description;

	@OneToOne(mappedBy = "computer")
	private Employee employee;

}
