package br.com.ejb.callbacks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Log
public class Employee implements Serializable {

	private static final long serialVersionUID = -8654188442146018297L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	@Temporal(TemporalType.DATE)
	private Date hireDate;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Task> tasks;

	/**
	 * Caso existam entitylListeners associados a esta entidade, os métodos de callback declarados diretamente na entidade
	 * sempre serao os ultimos a serem invocados.
	 */
	@PrePersist
	void prePersist() {
		log.info("Persisting Employee entity...");
	}

	@PostPersist
	void postPersist() {
		log.info("Employee entity persisted!");
	}

}
