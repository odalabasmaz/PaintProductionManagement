package tr.com.ppm.desktop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.dao.JpaDao;

/**
 * @author ykarabalkan.
 */
@Service
public class SimpleService<T> {

	@Autowired
	private JpaDao jpaDao;

	public void persist(T entity) {
		jpaDao.persistEntity(entity);
	}

}
