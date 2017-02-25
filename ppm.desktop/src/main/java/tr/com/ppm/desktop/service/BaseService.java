package tr.com.ppm.desktop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tr.com.ppm.desktop.dao.JpaDao;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Map;

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

	public List<T> executeQuery(String queryString, Map<String, Object> params) {
		return jpaDao.executeQuery(queryString, params);
	}

	public List<T> list() {
		return jpaDao.getEntityList(getEntityClass());
	}

	public void save(T entity) {
		jpaDao.persistEntity(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(T entity) {
		T mergeEntity = jpaDao.mergeEntity(entity);
		jpaDao.removeEntity(mergeEntity);
	}

	public void update(T entity) {
		jpaDao.mergeEntity(entity);
	}

	protected abstract <T> Class<T> getEntityClass();

}
