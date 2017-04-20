package tr.com.ppm.desktop.repository;

import org.springframework.stereotype.Repository;
import tr.com.ppm.desktop.model.material.Product;

/**
 * @author ykarabalkan.
 */
@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {

}
