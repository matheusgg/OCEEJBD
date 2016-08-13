package br.com.ejb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client implements Serializable {

	private static final long serialVersionUID = -9201982645590885046L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private String name;

}
