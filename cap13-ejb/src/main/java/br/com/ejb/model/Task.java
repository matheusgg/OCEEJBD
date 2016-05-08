package br.com.ejb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import static br.com.ejb.model.Task.SELECT_ALL_NAMED_QUERY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = SELECT_ALL_NAMED_QUERY, query = "from Task")
public class Task implements Serializable {

	private static final long serialVersionUID = 4257807524962969385L;
	public static final String SELECT_ALL_NAMED_QUERY = "Task.selectAll";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
}
