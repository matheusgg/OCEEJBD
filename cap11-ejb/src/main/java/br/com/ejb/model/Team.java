package br.com.ejb.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Data
@ToString(exclude = "members")
@Entity
@NamedQuery(name = "Team.findAll", query = "from Team")
public class Team implements Serializable {

	private static final long serialVersionUID = 4447756764717915108L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	/**
	 * É possivel utilizar mapas em relacionamentos One-To-Many e Many-To-many. Se o atributo name nao for especificado, a chave primaria da entidade
	 * alvo sera utilizada como chave do mapa, onde o valor será a própria entidade. Neste caso, a chave do mapa será o nome do Employee.
	 * Caso a anotacao @MapKey nao seja especificada, a chave utilizada durante a insercao dos objetos no mapa sera assumida.
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@MapKey(name = "name")
	private Map<String, Employee> members;
}
