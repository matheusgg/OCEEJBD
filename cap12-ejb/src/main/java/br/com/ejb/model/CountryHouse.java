package br.com.ejb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Quando uma classe base anotada com @MappedSuperclass é utilizada, é possivel redefinir os campos desta atraves
 * da anotacao @AttributeOverride.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
//@AttributeOverride(name = "address", column = @Column(name = "street"))
public class CountryHouse extends House {

	private static final long serialVersionUID = 5984639024155295143L;

	private String countryName;
}
