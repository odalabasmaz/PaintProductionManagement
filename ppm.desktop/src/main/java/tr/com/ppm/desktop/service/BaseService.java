package tr.com.ppm.desktop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.dao.JpaDao;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * @author Orhun Dalabasmaz
 */
@Service
public abstract class BaseService<T> {

	@Autowired
	protected JpaDao jpaDao;

	@SuppressWarnings("unchecked")
	public List<T> list(CriteriaQuery criteriaQuery) {
		return jpaDao.findByCriteriaQuery(criteriaQuery);
	}

	public List<T> list() {
		return jpaDao.getEntityList(getEntityClass());
	}

	public void save(T entity) {
		jpaDao.persistEntity(entity);
	}

	public void remove(T entity) {
		jpaDao.removeEntity(entity);
	}

	public void update(T entity) {
		jpaDao.mergeEntity(entity);
	}

	protected abstract <T> Class<T> getEntityClass();

}
