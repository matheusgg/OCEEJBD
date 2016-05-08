package br.com.ejb.entitylisteners;

import br.com.ejb.callbacks.model.Task;
import lombok.extern.java.Log;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

@Log
public class TaskEntityListener {

	@PrePersist
	void prePersist(final Task task) {
		log.info("Persisting Task entity " + task.getName());
	}

	@PostPersist
	void postPersist(final Task task) {
		log.info(task.getId() + " persisted!");
	}

}
