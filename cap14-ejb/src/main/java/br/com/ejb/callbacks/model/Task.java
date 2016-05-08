package br.com.ejb.callbacks.model;

import br.com.ejb.entitylisteners.TaskEntityListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A anotacao @ExcludeDefaultListeners exclui todos os entity listeners padroes definidos no arquivo de mapeamento de
 * entidade orm.xml.
 * <p>
 * A anotacao @ExcludeSuperclassListeners faz com que os listeners declarados na entidade pai (superclasse) nao seja aplicados
 * na subclasse, ou seja, na entidade filha. Esta anotacao é aplicada quando existe um relacionamento de heranca entre as entidades.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(TaskEntityListener.class)
@ExcludeDefaultListeners
@ExcludeSuperclassListeners
public class Task implements Serializable {

	private static final long serialVersionUID = 4257807524962969385L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
}
