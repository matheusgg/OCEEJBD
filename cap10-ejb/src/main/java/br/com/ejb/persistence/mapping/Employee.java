package br.com.ejb.persistence.mapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
//@Access(AccessType.FIELD)
@NamedQuery(name = "Employee.findAll", query = "from Employee")
public class Employee implements Serializable {

	private static final long serialVersionUID = -1419421158331041456L;

	/**
	 * O local onde está definida a anotacao @Id especifica o AccessType caso o mesmo nao seja informado,
	 * isto é, se a anotacao @Id estiver definida em um field, o AccessType será definido como FIELD.
	 * Se a anotacao @Id estiver definida em um getter ou setter, o AccessType será definido como PROPERTY.
	 */
	@Id
	@Column(name = "id_pk")

	/**
	 * A anotacao @TableGenerator define uma tabela que será utilizada para armazenar os IDs gerados pelo
	 * provedor de persistencia. Toda vez que uma entidade for salva na base esta tabela sera consultada
	 * e atualizada para recuperar o valor do ID que sera utilizado. A tabela gerada possuira apenas duas
	 * colunas: PK e ID. Além disso, apenas um registro sera armazenado nesta tabela, ou seja, ele sempre
	 * sera consultado e atualizado. Este registro possuirá apenas dois campos: o valor PRIMARY_KEY_VALUE
	 * na coluna PK e o valor dos IDs que será consultado e atualizado na coluna ID.
	 */
	@TableGenerator(name = "MY_GENERATOR", table = "GENERATOR_TABLE", pkColumnName = "PK",
			pkColumnValue = "PRIMARY_KEY_VALUE", valueColumnName = "ID", allocationSize = 1)
	@GeneratedValue(generator = "MY_GENERATOR", strategy = GenerationType.TABLE)
	//@SequenceGenerator(name = "MY_SEQUENCE_GENERATOR", sequenceName = "SOME_SEQUENCE")
	//@GeneratedValue(generator = "MY_SEQUENCE_GENERATOR", strategy = GenerationType.TABLE)
	private Integer id;

	@Column(nullable = false, length = 350)
	private String name;

	@Temporal(TemporalType.DATE)
	private Date birth;

}
