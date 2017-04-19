package tr.com.ppm.desktop.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Map;

/**
 * @author uadak
 */
public interface JpaDao {

	<T> void persistEntity(T var1);

	<T> void removeEntity(T var1);

	<T> T mergeEntity(T var1);

	<T> void refreshEntity(T var1);

	CriteriaBuilder getCriteriaBuilder();

	<T> List<T> findByCriteriaQuery(CriteriaQuery<T> var1);

	<T> List<T> getEntityList(Class<T> var1);

	List executeQuery(String queryString, Map<String, Object> params);
}
