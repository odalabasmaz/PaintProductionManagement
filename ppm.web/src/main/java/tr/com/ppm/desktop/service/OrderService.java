package tr.com.ppm.desktop.service;

import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.model.order.Order;

/**
 * @author ykarabalkan
 */
@Service
public class OrderService extends BaseService<Order> {

	@Override
	protected Class<Order> getEntityClass() {
		return Order.class;
	}

}
