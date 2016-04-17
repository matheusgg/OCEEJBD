package br.com.ejb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Na estrategia de heranca TABLE_PER_CLASS sera gerada uma tabela para cada classe da hierarquia. Deste modo, nao é
 * necessário especificar um discriminator. Caso a entidade base seja uma classe abstrata, o persistence provider nao
 * gerara uma tabela para representa-la.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQuery(name = "Animal.findAll", query = "from Animal")
public abstract class Animal implements Serializable {

	private static final long serialVersionUID = -3728910219568272931L;

	/**
	 * Na estrategia de heranca TABLE_PER_CLASS nao é possivel utilizar a geracao automatica de ID. Desta forma, é preciso especificar
	 * a estrategia de geracao TABLE, ou seja, o persistence provider criara uma tabela para gerar os IDs de forma sequencial.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	private String name;
	private String specie;
}
