package tr.com.ppm.desktop.dao;

/**
 * @author uadak
 */
public interface JpaDao<T> {
	void persistEntity(T entity);
}
