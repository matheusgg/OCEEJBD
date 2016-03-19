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
@NamedQuery(name = "Partner.findAll", query = "from Partner")
public class Partner implements Serializable {

	private static final long serialVersionUID = -800541187314855882L;

	/**
	 * A anotacao @AttributeOverride serve para sobrescrever uma definicao de coluna em uma Embedded ID class.
	 * O atributo name especifica o nome do atributo da classe e o atributo column especifica a sobrecrita da
	 * coluna definida na Embedded ID class.
	 */
	@EmbeddedId
	@AttributeOverride(name = "name", column = @Column(name = "name"))
	private PartnerEmbeddedPK pk;

	@Temporal(TemporalType.DATE)
	private Date associationDate;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Embeddable
	public static class PartnerEmbeddedPK implements Serializable {

		private static final long serialVersionUID = -4925035084264083822L;

		@Column(name = "partnerName")
		private String name;
		private Integer code;
	}

}
