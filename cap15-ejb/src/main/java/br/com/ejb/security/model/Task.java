package br.com.ejb.security.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Task implements Serializable {

	private static final long serialVersionUID = -346169472887534441L;
	private String id;
	private Employee owner;

	public Task(final String id) {
		this.id = id;
	}

}
