package tr.com.ppm.desktop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.model.order.Order;
import tr.com.ppm.desktop.repositories.OrderRepository;

import java.util.List;

/**
 * @author ykarabalkan
 */
@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public void saveOrder(Order order) {
		orderRepository.save(order);
	}

	public void updateOrder(Order order) {
		saveOrder(order);
	}

	public void deleteOrder(Order order) {
		orderRepository.delete(order);
	}

	public void deleteOrderById(Long id) {
		orderRepository.delete(id);
	}

	public void deleteAllOrders() {
		orderRepository.deleteAll();
	}

	public List<Order> findAllOrders() {
		return orderRepository.findAll();
	}
}
