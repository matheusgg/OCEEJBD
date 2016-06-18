package br.com.ejb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "client")
public class Product implements Serializable {

	private static final long serialVersionUID = 3109892490093484699L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String description;
	private BigDecimal price;

	@ManyToOne
	private Client client;
}
