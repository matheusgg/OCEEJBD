package br.com.ejb.persistence.context;

import br.com.ejb.model.Employee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Stateless
public class EmployeeBeanImpl implements EmployeeRemoteBean {

	/**
	 * Um PersistenceContext é um conjunto de entidades gerenciadas pelo EntityManager. Todas as entidades gerenciadas sao sincronizadas com
	 * a base para salvar as modificacoes realizadas nos objetos.
	 * <p>
	 * Para injetar um EntityManager gerenciado pelo container basta anota-lo com @PersistenceContext. Esta anotacao possui o atributo unitName,
	 * que permite especificar a unidade de persistencia declarada no arquivo persistence.xml (opcional caso exista apenas uma unidade de persistencia).
	 * O atributo type permite especificar o tipo do EntityManager, podendo ser TRANSACTION ou EXTENDED. Ainda existem os atributos name (utilizado para
	 * nomear o EntityManager) e properties (utilizado para especificar propriedades especificas do persistence provider).
	 * <p>
	 * Quando o EntityManager é do tipo TRANSACTION significa que ele estará ativo durante o tempo de vida da transacao, isto é, quando uma transacao é iniciada
	 * o container cria um EntityManager e gerencia o mesmo até que a transacao seja encerrada, quando a transacao é finalizada, o container encerra o EntityManager
	 * e todas as entidades relacionadas a este se tornam dettached, ou seja, nao gerenciadas.
	 * Quando o EntityManager é do tipo EXTENDED, ele continua ativo mesmo depois que a transacao seja finalizada, ou seja, o container gerencia o EntityManager
	 * e o mantém aberto durante várias transacoes, desta forma, todas as entidades associadas a este EntityManager continuam gerenciadas mesmo depois do fim da
	 * transacao.
	 */
	@PersistenceContext(unitName = "cap9-ejb-PU", type = PersistenceContextType.TRANSACTION)
	private EntityManager em;

	@Override
	public Employee save(final Employee employee) {
		if (employee.getId() != null) {
			return this.em.merge(employee);
		}
		this.em.persist(employee);
		return employee;
	}

	@Override
	public Employee find(final Integer id) {
		return this.em.find(Employee.class, id);
	}

	@Override
	public List<Employee> findAll() {
		return this.em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
	}

	@Override
	public void delete(final Integer id) {
		final Employee employee = this.find(id);
		if (employee != null) {
			this.em.remove(employee);
		}
	}
}
