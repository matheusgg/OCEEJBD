package br.com.ejb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Em um relacionamento de heranca SINGLE_TABLE, é criada apenas uma tabela por hieraraquia. Desta forma, é preciso especificar
 * mais informacoes para o persistence provider poder descobrir qual é o tipo de entidade que cada linha desta tabela armazena.
 * Por este motivo, existem as anotacoes @DiscriminatorColumn e @DiscriminatorValue.
 * <p>
 * A anotacao @DiscriminatorColumn especifica a coluna que sera utilizada como um discriminador de entidades, ou seja, o tipo de
 * entidade armazenada naquela linha. O valor default do atributo name é DTYPE, já o valor default do atributo discriminatorType
 * é STRING (podendo ser também CHAR ou INTEGER).
 * <p>
 * A anotacao @DiscriminatorValue define o tipo da entidade propriamente dita. Neste caso, cada linha que possuir o valor PERSON
 * na coluna DISCRIMINATOR será do tipo br.com.ejb.model.Person. Caso esta anotacao nao seja especificada, o persistence provider
 * assumira um valor default de acordo com o discriminatorType especificado em @DiscriminatorColumn.
 * <p>
 * Uma desvantagem da estrategia SINGLE_TABLE é que como todos os campos da hierarquia sao mapeados em uma unica tabela, nao é
 * possivel incluir constraints como NOT NULL, pois senao, uma entidade que nao possui um determinado campo nao podera ser salva.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorColumn(name = "DISCRIMINATOR", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("PERSON")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQuery(name = "Person.findAll", query = "from Person")
public class Person implements Serializable {

	private static final long serialVersionUID = -3011206036875464823L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String firstName;
	private String lastName;

}
