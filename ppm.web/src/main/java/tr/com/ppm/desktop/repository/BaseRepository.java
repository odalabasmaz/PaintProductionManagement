package tr.com.ppm.desktop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author Orhun Dalabasmaz
 */
@Repository
public interface BaseRepository<T, I extends Serializable> extends JpaRepository<T, I> {
	List<T> findByNameContainingIgnoreCase(String name);
}
