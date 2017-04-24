package tr.com.ppm.desktop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.model.order.Order;
import tr.com.ppm.desktop.repository.OrderRepository;

/**
 * @author ykarabalkan
 */
@Service
public class OrderService extends BaseNewService<Order, Long> {

	private OrderRepository repository;

	@Autowired
	public OrderService(OrderRepository repository) {
		super(repository);
	}

}
