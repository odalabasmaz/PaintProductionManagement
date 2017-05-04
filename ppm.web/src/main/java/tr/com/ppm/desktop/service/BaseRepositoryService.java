package tr.com.ppm.desktop.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author ykarabalkan
 */
@Service
public abstract class BaseRepositoryService<T, I extends Serializable> {

	private final JpaRepository<T, I> jpaRepository;

	BaseRepositoryService(JpaRepository jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	public T findById(I id) {
		return jpaRepository.findOne(id);
	}

	public void save(T entity) {
		jpaRepository.save(entity);
	}

	public void update(T entity) {
		save(entity);
	}

	public void delete(T entity) {
		jpaRepository.delete(entity);
	}

	public void deleteById(I id) {
		jpaRepository.delete(id);
	}

	public void deleteAll() {
		jpaRepository.deleteAll();
	}

	public List<T> findAll() {
		return jpaRepository.findAll();
	}

}
