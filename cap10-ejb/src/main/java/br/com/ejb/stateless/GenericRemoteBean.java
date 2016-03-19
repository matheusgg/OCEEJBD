package br.com.ejb.stateless;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface GenericRemoteBean<T, I> {

	T save(T entity);

	void delete(I id);

	T findById(I id);

	List<T> findAll();

}
