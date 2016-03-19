package br.com.ejb.persistence.mapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = "Address.findAll", query = "from Address")
@TableGenerator(name = "MY_GENERATOR", table = "GENERATOR_TABLE", pkColumnName = "PK", pkColumnValue = "PRIMARY_KEY_VALUE", valueColumnName = "ID", allocationSize = 1)
public class Address implements Serializable {

	private static final long serialVersionUID = -7875747621108680736L;

	@Id
	@GeneratedValue(generator = "MY_GENERATOR", strategy = GenerationType.TABLE)
	private Integer id;
	private String street;

	/**
	 * Assim como chaves primarias compostas, é possivel especificar objetos embedados dentro de entidades. Estes objetos nao precisam
	 * ser necessariamente entidades e todos os atributos dos mesmos sao mapeados como colunas da entidade portadora deste objeto embedado.
	 * Basta especificar a anotacao @Embedded para adicionar um objeto embedado. Caso a anotacao nao seja especificada, o provedor de persistencia
	 * considerara este campo como um @Lob type. Assim como acontece com @EmbeddedId, é possivel sobrescrever as colunas especificadas pelo
	 * objeto embedado.
	 */
	@Embedded
	@AttributeOverride(name = "state", column = @Column(name = "addressState"))
	private AddressInfo addressInfo;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Embeddable
	public static class AddressInfo implements Serializable {
		private static final long serialVersionUID = 6344837317744160825L;

		@Column(name = "addressCity")
		private String city;

		@Column(name = "someState")
		private String state;
	}

}
