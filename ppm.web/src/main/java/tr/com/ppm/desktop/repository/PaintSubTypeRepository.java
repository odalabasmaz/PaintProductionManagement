package tr.com.ppm.desktop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.ppm.desktop.model.material.PaintSubType;

import java.util.List;

/**
 * @author uadak.
 */
@Repository
public interface PaintSubTypeRepository extends JpaRepository<PaintSubType, Long> {
	List<PaintSubType> findByNameContainingIgnoreCase(String name);
}
