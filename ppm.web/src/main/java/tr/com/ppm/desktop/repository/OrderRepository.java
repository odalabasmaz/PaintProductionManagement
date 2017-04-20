package tr.com.ppm.desktop.repository;

import org.springframework.stereotype.Repository;
import tr.com.ppm.desktop.model.order.Order;

/**
 * @author ykarabalkan.
 */
@Repository
public interface OrderRepository extends BaseRepository<Order, Long> {

}
