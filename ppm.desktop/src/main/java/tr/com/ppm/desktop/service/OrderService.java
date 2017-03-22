package tr.com.ppm.desktop.service;

import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.dao.QueryHelper;
import tr.com.ppm.desktop.model.material.PaintSubType;
import tr.com.ppm.desktop.model.material.PaintType;
import tr.com.ppm.desktop.model.material.Product;
import tr.com.ppm.desktop.model.order.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ykarabalkan
 */
@Service
public class OrderService extends BaseService<Order> {

	public List<Order> list() {
		Map<String, Object> params = new HashMap<>();
		String queryString = QueryHelper.getInstance("Order", params)
				.build();

		return executeQuery(queryString, params);
	}

	@Override
	protected Class<Order> getEntityClass() {
		return Order.class;
	}
}
