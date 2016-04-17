package br.com.ejb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Dog extends Animal {

	private static final long serialVersionUID = 2413922698902505384L;

	private String race;
	private Double weight;
}
