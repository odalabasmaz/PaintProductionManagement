package tr.com.ppm.desktop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.dao.JpaDao;
import tr.com.ppm.desktop.model.common.Quantity;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * @author ykarabalkan.
 */
@Service
public class QuantityService {
	private static final Logger LOGGER = LoggerFactory.getLogger(QuantityService.class);

	private JpaDao jpaDao;

	@Autowired
	public QuantityService(JpaDao jpaDao) {
		this.jpaDao = jpaDao;
	}

	public void addQuantity(Quantity quantity) {
		jpaDao.persistEntity(quantity);
	}

	@SuppressWarnings("unchecked")
	public List<Quantity> listQuantity(CriteriaQuery criteriaQuery) {
		return jpaDao.findByCriteriaQuery(criteriaQuery);
	}

	public List<Quantity> listQuantity() {
		return jpaDao.getEntityList(Quantity.class);
	}

	public void removeQuantity(Quantity quantity) {
		jpaDao.removeEntity(quantity);
	}

	public void updateQuantity(Quantity quantity) {
		jpaDao.mergeEntity(quantity);
	}

}
