package tr.com.ppm.desktop.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * @author uadak
 */
@Repository
@Transactional
public class SimpleJpaDao<T> implements JpaDao<T> {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void persistEntity(T entity) {
		entityManager.persist(entity);
	}

}
