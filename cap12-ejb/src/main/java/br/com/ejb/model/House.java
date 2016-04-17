package br.com.ejb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Na estrategia de heranca JOINED, as tabelas criadas na base estarao normalizadas. Nao é necessário um discriminador, uma vez
 * que cada entidade possuira sua propria tabela.
 * <p>
 * Todas as tabelas que representam as entidades filhas possuirao um relacionamento com a tabela da entidade pai. Por padrao, este
 * relacionamento será feito atraves da chave primaria da entidade pai com a chave da entidade filha.
 * <p>
 * A anotacao @MappedSuperclass permite que uma classe que nao é uma entidade seja utilizada como superclasse de entidades mapeadas.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//@MappedSuperclass
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "House.findAll", query = "from House")
public class House implements Serializable {

	private static final long serialVersionUID = 2551823392110724777L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String address;
}
