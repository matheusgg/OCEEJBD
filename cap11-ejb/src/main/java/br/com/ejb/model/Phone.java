package br.com.ejb.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString(exclude = "employee")
@Entity
public class Phone implements Serializable {

	private static final long serialVersionUID = 7465392347868981977L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String number;

	@Enumerated(EnumType.STRING)
	private PhoneType type;

	@ManyToOne
	private Employee employee;

	public enum PhoneType {

		PHONE, CELLPHONE

	}

}
