package tr.com.ppm.desktop.service;

import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.model.customer.Customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Orhun Dalabasmaz
 */
@Service
public class CustomerService extends BaseService<Customer> {
	private static final String QUERY_STRING = "from Customer where lower(name) like :name";

	public List<Customer> listByName(String customer) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", "%" + customer.trim().toLowerCase() + "%");
		return executeQuery(QUERY_STRING, params);
	}


	@Override
	protected Class<Customer> getEntityClass() {
		return Customer.class;
	}
}
