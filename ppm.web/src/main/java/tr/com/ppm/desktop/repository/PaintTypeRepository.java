package tr.com.ppm.desktop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.ppm.desktop.model.material.PaintType;

import java.util.List;

/**
 * @author ykarabalkan.
 */
@Repository
public interface PaintTypeRepository extends JpaRepository<PaintType, Long> {
	List<PaintType> findByNameContainingIgnoreCase(String name);
}
