package br.com.ejb.persistence.mapping;

import lombok.AllArgsConstructor;
import lombok.Data;

import br.com.ejb.persistence.mapping.Company.CompanyPK;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(CompanyPK.class)
@NamedQuery(name = "Company.findAll", query = "from Company")
public class Company implements Serializable {

	private static final long serialVersionUID = 5528904495756500121L;

	@Id
	private String realName;

	@Id
	private Integer code;

	private String fictionalName;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CompanyPK implements Serializable {
		private static final long serialVersionUID = 6176587382536434667L;
		private String realName;
		private Integer code;
	}

}
