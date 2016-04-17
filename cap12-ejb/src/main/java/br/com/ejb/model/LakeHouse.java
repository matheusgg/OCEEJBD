package br.com.ejb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Na estrategia de heranca JOINED, caso a chave primaria da entidade filha tenha um nome diferente da chave da entidade pai,
 * é necessário especificar a anotacao @PrimaryKeyJoinColumn na entidade filha, onde o atributo name define o nome da chave
 * da entidade filha (entidade atual) e o atributo referencedColumnName define a chave da entidade pai (por padrao será utilizado
 * o ID da entidade pai).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@PrimaryKeyJoinColumn(name = "lakeHouseId")
public class LakeHouse extends House {

	private static final long serialVersionUID = -8657797015418015440L;

	private String lakeName;
}
