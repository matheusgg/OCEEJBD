package br.com.ejb.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@ToString(exclude = {"manager", "peons", "teams"})
@Entity
@NamedQuery(name = "Employee.findAll", query = "from Employee")
public class Employee implements Serializable {

	private static final long serialVersionUID = -4743763574142896784L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	/**
	 * O relacionamento unidirecional One-To-One especifica a navegacao a partir do Employee para o Address, porém não é possível chegar em um Employee
	 * através de um Address. A anotacao @JoinColumn define a criacao da foreign key. Caso o relacionamento esteja sendo feito no ID da entidade alvo,
	 * o atributo name deve ser especificado com o nome da coluna que será criada na entidade dona do relacionamento (Employee) e que será a foreign key para
	 * a entidade alvo (Address). Se o relacionamento estiver sendo feito em um campo que nao seja o ID, o atributo referencedColumnName deve ser utilizado.
	 * Caso o relacionamento seja feito em chaves compostas, a anotacao @JoinColumns deve ser utilizada.
	 * <p>
	 * Caso o relacionamento seja feito através do ID da entidade alvo, a anotacao @JoinColumn se torna opcional, pois o persistence provider assumirá o ID
	 * da entidade alvo como padrao. Desta forma, o nome da coluna de relacionamento que sera criada na entidade dona sera composto pelo nome do atributo
	 * anotado com @OneToOne seguido por _ (underscore) mais o nome do atributo que é chave primaria na entidade alvo. Ex.: address_id
	 * <p>
	 * A anotacao @PrimaryKeyJoinColumn é utilizada quando o relacionamento One-To-One é feito utilizando o mesmo ID para as duas entidades. O atributo
	 * name especifica o nome da chave da entidade dona (ID). O atributo referencedColumnName especifica o nome da chave na entidade alvo (ID).
	 */
	@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "address_id") // Nome da coluna que será criada na tabela Employee
	//@JoinColumn(referencedColumnName = "state") // Nome do atributo em Address
	@PrimaryKeyJoinColumn
	private Address address;

	/**
	 * O relacionamento One-To-One Bidirecional faz com que exista uma referencia das duas entidades de cada lado relacionamento, ou seja, Employee
	 * possui uma referencia para Computer e Computer possui uma referencia para Employee. A tabela criada na base possui o mesmo esquema da tabela criada
	 * pelo relacionamento Unidirecional.
	 * <p>
	 * No relacionamento One-To-One Bidirecional existe o conceito de dono do relacionamento, que é sempre a entidade que nao especifica o atributo mappedBy na
	 * anotacao @OneToOne. Neste caso, Employee é o dono do relacionamento. Os comportamentos especificados por @PrimaryKeyJoinColumn e @JoinColumn também sao
	 * aplicaveis aqui.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	private Computer computer;

	/**
	 * Existem diversas variacoes do relacionamento One-To-Many Unidirecional. Neste caso, o Hibernate criara uma tabela intermediaria contendo o ID do Employee
	 * e o ID do Phone para realizar o relacionamento.
	 * <p>
	 * O atributo orphanRemoval faz com que o registro do telefone seja removido da base caso ele seja removido da lista de telefones do Employee.
	 * <p>
	 * Caso o atributo mappedBy nao seja especificado, o Hibernate criara a tabela intermediaria para realizar o relacionamento. Com o atributo mappedBy definido,
	 * a entidade alvo devera possuir uma referencia para esta entidade, ou seja, a entidade alvo sera a dona do relacionamento. Neste caso, uma coluna com
	 * o ID desta entidade sera criada na tabela da entidade alvo para indicar o relacionamento ao inves de uma nova tabela ser criada.
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, /*orphanRemoval = true, */mappedBy = "employee")
	private List<Phone> phones;

	@ManyToOne
	private Employee manager;

	/**
	 * Quando nao especificado o atributo value da anotacao @OrderBy, o persistence provider ordena a colecao de forma ascendente pelo ID da entidade
	 * alvo.
	 */
	//@OrderBy("name ASC, id DESC")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "manager")
	private List<Employee> peons;

	/**
	 * Assim como no relacionamento One-To-One, a entidade que nao especifica o atributo mappedBy é a dona do relacionamento. Neste caso, a entidade Team
	 * é a dona do relacionamento.
	 * <p>
	 * Por padrao, quando nao especificada a anotacao @JoinTable, o Hibernate cria uma tabela intermediaria para relacionar as entidades do relacionamento
	 * Many-To-Many. O nome desta tabela é composta pelo nome da entidade dona do relacionamento concatenado com _ (underscore) mais o nome da segunta entidade.
	 * Ex.: Team_Employee.
	 * <p>
	 * A anotacao @JoinTable serve para especificar as configuracoes de criacao da tabela de relacionamento, como por exemplo, os nomes das colunas. Esta anotacao
	 * deve ser especificada na entidade dona do relacionamento.
	 */
	@ManyToMany(mappedBy = "members")
	//@JoinTable(name = "Teams_Employees", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "team_id"))
	private List<Team> teams;

}
