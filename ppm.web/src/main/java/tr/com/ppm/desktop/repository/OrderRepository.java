package tr.com.ppm.desktop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.ppm.desktop.model.order.Order;

/**
 * @author ykarabalkan.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
